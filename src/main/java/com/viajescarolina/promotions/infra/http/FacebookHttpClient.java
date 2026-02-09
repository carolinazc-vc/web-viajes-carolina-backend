package com.viajescarolina.promotions.infra.http;

import com.viajescarolina.promotions.domain.FacebookPost;
import com.viajescarolina.promotions.domain.port.FacebookClient;
import jakarta.enterprise.context.ApplicationScoped;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.logging.Logger;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementación del cliente HTTP para consumir la API Graph de Facebook.
 * Obtiene las publicaciones de la página de Viajes Carolina.
 *
 * Soporta dos flujos:
 * 1. Si se proporciona pageToken con permisos user_token: obtiene pageId de /me/accounts y luego los posts
 * 2. Si se proporciona pageToken con permisos page_token: obtiene directamente los posts del pageId configurado
 */
@ApplicationScoped
public class FacebookHttpClient implements FacebookClient {

    private static final Logger logger = Logger.getLogger(FacebookHttpClient.class);

    @ConfigProperty(name = "facebook.page.token", defaultValue = "")
    String pageAccessToken;

    @ConfigProperty(name = "facebook.page.id", defaultValue = "108156039053235")
    String pageId;

    @RestClient
    FacebookGraphApiClient facebookGraphApiClient;

    private static final String ACCOUNTS_FIELDS = "id,name,access_token";
    private static final String POSTS_FIELDS = "id,message,created_time,full_picture,permalink_url";
    private static final DateTimeFormatter FACEBOOK_DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssZ");

    @Override
    public List<FacebookPost> getFeedPosts(int limit) {
        try {
            List<FacebookPost> posts = new ArrayList<>();

            // Si no hay token configurado, retornar lista vacía
            if (pageAccessToken == null || pageAccessToken.isBlank()) {
                logger.warn("❌ Facebook token NOT configured. Set FACEBOOK_PAGE_TOKEN environment variable.");
                logger.warn("📍 Token must start with: EAA");
                return posts;
            }

            // Validar formato del token
            if (!pageAccessToken.startsWith("EAA")) {
                logger.error("❌ INVALID Facebook token format. Must start with 'EAA'");
                logger.error("Current token starts with: " + pageAccessToken.substring(0, Math.min(10, pageAccessToken.length())));
                logger.error("Token length: " + pageAccessToken.length());
                return posts;
            }

            logger.info("✅ Fetching Facebook posts for pageId=" + pageId + " with limit=" + limit);

            // Resolver el pageId si es necesario
            String resolvedPageId = resolvePageId();

            if (resolvedPageId == null || resolvedPageId.isBlank()) {
                logger.warn("❌ Could not resolve Facebook pageId, returning empty list");
                return posts;
            }

            logger.info("✅ Using resolved pageId: " + resolvedPageId);
            logger.info("📍 Token starts with: " + pageAccessToken.substring(0, Math.min(10, pageAccessToken.length())));
            logger.info("📍 Token length: " + pageAccessToken.length());

            FacebookPostsResponse response = facebookGraphApiClient.getPagePosts(
                    resolvedPageId,
                    POSTS_FIELDS,
                    pageAccessToken,
                    limit
            );

            logger.info("✅ Successfully received response with " + (response != null && response.data() != null ? response.data().size() : 0) + " posts");

            if (response != null && response.data() != null) {
                for (FacebookPostsResponse.PostData postData : response.data()) {
                    if (postData.message() != null && !postData.message().isBlank()) {
                        try {
                            ZonedDateTime createdTime = ZonedDateTime.parse(postData.created_time(), FACEBOOK_DATE_FORMATTER);
                            posts.add(new FacebookPost(
                                    postData.id(),
                                    postData.message(),
                                    createdTime,
                                    postData.full_picture(),
                                    postData.permalink_url()
                            ));
                        } catch (Exception dateEx) {
                            logger.warn("⚠️  Could not parse date for post " + postData.id() + ": " + postData.created_time());
                        }
                    }
                }
            }

            return posts;
        } catch (Exception e) {
            logger.error("❌ Error fetching Facebook posts: " + e.getMessage());
            logger.error("Exception type: " + e.getClass().getSimpleName());
            if (e.getMessage() != null && e.getMessage().contains("400")) {
                logger.error("💡 Status 400 suggests: Invalid token, expired token, or incorrect pageId");
                logger.error("💡 Verify: FACEBOOK_PAGE_TOKEN environment variable");
                logger.error("💡 Token must: Start with 'EAA' and be a valid user or page token");
            }
            return new ArrayList<>();
        }
    }

    /**
     * Resuelve el pageId automáticamente si es necesario.
     * Si el pageId configurado es válido, lo usa.
     * Si no, intenta obtenerlo del endpoint /me/accounts.
     */
    private String resolvePageId() {
        try {
            // Validar token antes de hacer llamadas
            if (pageAccessToken == null || pageAccessToken.isBlank()) {
                logger.warn("❌ Token not configured for pageId resolution");
                return pageId;
            }

            if (!pageAccessToken.startsWith("EAA")) {
                logger.warn("❌ Invalid token format, cannot resolve pageId");
                return pageId;
            }

            // Usar el pageId configurado si existe
            if (pageId != null && !pageId.isBlank() && !pageId.equals("108156039053235")) {
                logger.info("✅ Using configured pageId: " + pageId);
                return pageId;
            }

            // Si no está configurado o es el default, intentar obtenerlo de /me/accounts
            logger.info("📍 Attempting to resolve pageId from /me/accounts endpoint");
            FacebookAccountsResponse accountsResponse = facebookGraphApiClient.getAccounts(
                    ACCOUNTS_FIELDS,
                    pageAccessToken
            );

            if (accountsResponse != null && accountsResponse.data != null && !accountsResponse.data.isEmpty()) {
                FacebookAccountsResponse.FacebookAccount account = accountsResponse.data.get(0);
                logger.info("✅ Resolved pageId from /me/accounts: " + account.id + " (" + account.name + ")");
                return account.id;
            }

            logger.warn("❌ Could not resolve pageId from /me/accounts response");
            return pageId;

        } catch (Exception e) {
            logger.warn("❌ Error resolving pageId from /me/accounts: " + e.getMessage());
            if (e.getMessage() != null && e.getMessage().contains("400")) {
                logger.warn("💡 Status 400 from /me/accounts - Token might not have user_pages permission");
            }
            logger.warn("💡 Falling back to configured pageId: " + pageId);
            return pageId;
        }
    }

    @Override
    public String getPageId() {
        return pageId;
    }
}
