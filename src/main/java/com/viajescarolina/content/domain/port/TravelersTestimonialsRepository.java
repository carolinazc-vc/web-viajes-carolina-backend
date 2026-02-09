package com.viajescarolina.content.domain.port;

import com.viajescarolina.content.domain.home.TravelersTestimonialsSection;

import java.util.Optional;

/**
 * Puerto para acceso a datos de la sección de testimonios de viajeros.
 */
public interface TravelersTestimonialsRepository {

    /**
     * Busca la sección de testimonios de viajeros activa con sus items.
     * @return Optional con TravelersTestimonialsSection si existe una activa
     */
    Optional<TravelersTestimonialsSection> findActiveSection();
}
