package com.viajescarolina.content.infra.jpa.repository;

import com.viajescarolina.content.domain.home.CtaBlock;
import com.viajescarolina.content.domain.port.CtaRepository;
import com.viajescarolina.content.infra.jpa.entity.CtaEntity;
import com.viajescarolina.content.infra.jpa.mapper.HomeContentMapper;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.inject.Inject;

import java.util.Optional;

/**
 * Implementación JPA del repositorio CtaRepository.
 */
@ApplicationScoped
public class CtaJpaRepository implements CtaRepository {

    @Inject
    EntityManager entityManager;

    @Override
    public Optional<CtaBlock> findActiveCta() {
        try {
            CtaEntity entity = entityManager.createQuery(
                    "SELECT c FROM CtaEntity c WHERE c.isActive = true ORDER BY c.id ASC",
                    CtaEntity.class
            )
                    .setMaxResults(1)
                    .getSingleResult();

            CtaBlock ctaBlock = HomeContentMapper.toCtaBlock(entity);
            return Optional.ofNullable(ctaBlock);
        } catch (Exception e) {
            return Optional.empty();
        }
    }
}

