package com.viajescarolina.content.domain.port;

import com.viajescarolina.content.domain.home.HeroSection;

import java.util.Optional;

/**
 * Puerto para acceso a datos de la sección hero.
 */
public interface PageHeroRepository {

    /**
     * Busca la sección hero por slug de página.
     * @param slug el slug de la página (ej: "home")
     * @return Optional con HeroSection si existe
     */
    Optional<HeroSection> findHeroByPageSlug(String slug);
}

