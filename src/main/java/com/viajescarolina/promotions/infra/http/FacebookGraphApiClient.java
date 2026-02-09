package com.viajescarolina.promotions.infra.http;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@Path("")
@RegisterRestClient(configKey = "facebook-graph-api")
@Produces(MediaType.APPLICATION_JSON)
public interface FacebookGraphApiClient {

    /**
     * Obtiene los datos de la cuenta/página del usuario autenticado.
     * Endpoint: GET /v24.0/me/accounts
     */
    @GET
    @Path("/me/accounts")
    FacebookAccountsResponse getAccounts(
            @QueryParam("fields") String fields,
            @QueryParam("access_token") String accessToken
    );

    /**
     * Obtiene las publicaciones de una página específica.
     * Endpoint: GET /v24.0/{pageId}/posts
     */
    @GET
    @Path("/{pageId}/posts")
    FacebookPostsResponse getPagePosts(
            @PathParam("pageId") String pageId,
            @QueryParam("fields") String fields,
            @QueryParam("access_token") String pageAccessToken,
            @QueryParam("limit") int limit
    );
}
