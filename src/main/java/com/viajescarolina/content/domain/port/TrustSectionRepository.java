package com.viajescarolina.content.domain.port;

import com.viajescarolina.content.domain.home.TrustSection;

import java.util.Optional;

/**
 * Puerto para acceso a datos de la sección de confianza.
 */
public interface TrustSectionRepository {

    /**
     * Busca la sección de confianza activa.
     * @return Optional con TrustSection si existe una activa
     */
    Optional<TrustSection> findActiveSection();
}

