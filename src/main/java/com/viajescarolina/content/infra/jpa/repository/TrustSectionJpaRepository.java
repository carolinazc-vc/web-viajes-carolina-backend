package com.viajescarolina.content.infra.jpa.repository;

import com.viajescarolina.content.domain.home.TrustSection;
import com.viajescarolina.content.domain.port.TrustSectionRepository;
import com.viajescarolina.agency.infra.jpa.entity.TrustSectionEntity;
import com.viajescarolina.content.infra.jpa.mapper.HomeContentMapper;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.inject.Inject;

import java.util.Optional;

/**
 * Implementación JPA del repositorio TrustSectionRepository.
 */
@ApplicationScoped
public class TrustSectionJpaRepository implements TrustSectionRepository {

    @Inject
    EntityManager entityManager;

    @Inject
    HomeContentMapper homeContentMapper;

    @Override
    public Optional<TrustSection> findActiveSection() {
        try {
            TrustSectionEntity entity = entityManager.createQuery(
                    "SELECT ts FROM TrustSectionEntity ts WHERE ts.isActive = true ORDER BY ts.id ASC",
                    TrustSectionEntity.class
            )
                    .setMaxResults(1)
                    .getSingleResult();

            TrustSection trustSection = homeContentMapper.toTrustSection(entity);

            return Optional.ofNullable(trustSection);
        } catch (Exception e) {
            return Optional.empty();
        }
    }
}

