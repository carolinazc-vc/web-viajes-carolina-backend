package com.viajescarolina.promotions.app;

import com.viajescarolina.content.domain.home.PromotionsBlock;

/**
 * Servicio de aplicación para promociones.
 */
public interface PromotionService {

    /**
     * Obtiene las promociones destacadas para mostrar en el home.
     * @param limit número máximo de promociones a retornar
     * @return PromotionsBlock con las promociones destacadas
     */
    PromotionsBlock getFeaturedForHome(int limit);
}

