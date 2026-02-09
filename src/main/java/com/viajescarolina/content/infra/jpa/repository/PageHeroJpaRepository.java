package com.viajescarolina.content.infra.jpa.repository;

import com.viajescarolina.content.domain.home.HeroSection;
import com.viajescarolina.content.domain.port.PageHeroRepository;
import com.viajescarolina.content.infra.jpa.entity.PageEntity;
import com.viajescarolina.content.infra.jpa.entity.PageHeroEntity;
import com.viajescarolina.content.infra.jpa.mapper.HomeContentMapper;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.inject.Inject;

import java.util.Optional;

/**
 * Implementación JPA del repositorio PageHeroRepository.
 */
@ApplicationScoped
public class PageHeroJpaRepository implements PageHeroRepository {

    @Inject
    EntityManager entityManager;

    @Override
    public Optional<HeroSection> findHeroByPageSlug(String slug) {
        try {
            // Buscar la página por slug
            PageEntity page = entityManager.createQuery(
                    "SELECT p FROM PageEntity p WHERE p.slug = :slug AND p.isActive = true",
                    PageEntity.class
            )
                    .setParameter("slug", slug)
                    .getSingleResult();

            // Buscar el hero asociado a esta página
            PageHeroEntity heroEntity = entityManager.createQuery(
                    "SELECT ph FROM PageHeroEntity ph WHERE ph.page.id = :pageId AND ph.isActive = true",
                    PageHeroEntity.class
            )
                    .setParameter("pageId", page.id)
                    .getSingleResult();

            HeroSection heroSection = HomeContentMapper.toHeroSection(heroEntity);
            return Optional.ofNullable(heroSection);
        } catch (Exception e) {
            return Optional.empty();
        }
    }
}

