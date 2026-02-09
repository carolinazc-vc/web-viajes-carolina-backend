package com.viajescarolina.promotions.app;

import com.viajescarolina.content.domain.home.PromotionsBlock;
import com.viajescarolina.content.domain.home.PromotionsBlock.PromotionSummary;
import com.viajescarolina.promotions.domain.FacebookPost;
import com.viajescarolina.promotions.domain.port.FacebookClient;
import com.viajescarolina.promotions.infra.jpa.entity.PromotionEntity;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.inject.Inject;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementación del servicio de promociones.
 * Consulta promociones de la base de datos y de Facebook.
 */
@ApplicationScoped
public class PromotionServiceImpl implements PromotionService {

    @Inject
    EntityManager entityManager;

    @Inject
    FacebookClient facebookClient;

    @ConfigProperty(name = "promotions.include-facebook", defaultValue = "false")
    boolean includeFacebookPosts;

    @Override
    public PromotionsBlock getFeaturedForHome(int limit) {

        // Obtener promociones de la base de datos
        List<PromotionEntity> promotionEntities = loadDatabasePromotions(limit);
        List<PromotionSummary> promotions = new ArrayList<>(convertToSummaries(promotionEntities));

        // Si está habilitado, agregar publicaciones de Facebook
        if (includeFacebookPosts && promotions.size() < limit) {
            int remainingLimit = limit - promotions.size();
            List<FacebookPost> facebookPosts = loadFacebookPosts(remainingLimit);
            promotions.addAll(convertFacebookToSummaries(facebookPosts));
        }

        return new PromotionsBlock(
                "Promociones Destacadas",
                "Descubre nuestras mejores ofertas y paquetes especiales",
                promotions,
                "/promotions",
                "Ver todas las promociones"
        );
    }

    private List<PromotionEntity> loadDatabasePromotions(int limit) {
        return entityManager
                .createQuery(
                        "SELECT p FROM PromotionEntity p " +
                        "WHERE p.isFeatured = true AND p.status = 'PUBLISHED' " +
                        "ORDER BY p.validFrom DESC",
                        PromotionEntity.class
                )
                .setMaxResults(limit)
                .getResultList();
    }

    private List<FacebookPost> loadFacebookPosts(int limit) {
        try {
            return facebookClient.getFeedPosts(limit);
        } catch (Exception e) {
            // Log silenciosamente si falla Facebook, pero continúa con promociones de BD
            System.err.println("Error fetching Facebook posts: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    private List<PromotionSummary> convertToSummaries(List<PromotionEntity> entities) {
        List<PromotionSummary> summaries = new ArrayList<>();
        for (PromotionEntity entity : entities) {
            summaries.add(new PromotionSummary(
                    String.valueOf(entity.id),
                    entity.title,
                    entity.shortDescription,
                    entity.mainImageUrl,
                    entity.badgeText,
                    entity.badgeType,
                    entity.validityLabel,
                    entity.priceFrom,
                    entity.priceFrom,
                    entity.currency,
                    "/promotions/" + entity.slug
            ));
        }
        return summaries;
    }

    private List<PromotionSummary> convertFacebookToSummaries(List<FacebookPost> posts) {
        List<PromotionSummary> summaries = new ArrayList<>();
        for (FacebookPost post : posts) {
            String title = extractTitle(post.getMessage());
            String description = truncateMessage(post.getMessage());

            summaries.add(new PromotionSummary(
                    post.getId(),
                    title,
                    description,
                    post.getFullPicture(),
                    "Facebook",
                    "info",
                    "Vigente",
                    null,
                    null,
                    null,
                    post.getPermalinkUrl()
            ));
        }
        return summaries;
    }

    private String extractTitle(String message) {
        if (message == null || message.isEmpty()) {
            return "Promoción especial";
        }
        int endIndex = Math.min(50, message.indexOf('\n') > 0 ? message.indexOf('\n') : message.length());
        return message.substring(0, endIndex).trim();
    }

    private String truncateMessage(String message) {
        final int MAX_LENGTH = 150;
        if (message == null) {
            return "";
        }
        if (message.length() <= MAX_LENGTH) {
            return message;
        }
        return message.substring(0, MAX_LENGTH) + "...";
    }
}

