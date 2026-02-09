package com.viajescarolina.promotions.domain.port;

import com.viajescarolina.promotions.domain.FacebookPost;

import java.util.List;

/**
 * Puerto que define el contrato para obtener publicaciones de Facebook.
 */
public interface FacebookClient {

    /**
     * Obtiene las publicaciones recientes de la página de Facebook.
     * @param limit número máximo de publicaciones a retornar
     * @return lista de publicaciones de Facebook
     */
    List<FacebookPost> getFeedPosts(int limit);

    /**
     * Obtiene la información de la cuenta de Facebook (page ID).
     * @return ID de la página de Facebook
     */
    String getPageId();
}

