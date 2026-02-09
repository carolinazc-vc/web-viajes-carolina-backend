package com.viajescarolina.content.infra.jpa.repository;

import com.viajescarolina.content.domain.home.TravelersTestimonialsSection;
import com.viajescarolina.content.domain.port.TravelersTestimonialsRepository;
import com.viajescarolina.content.infra.jpa.entity.TravelersTestimonialsSectionEntity;
import com.viajescarolina.content.infra.jpa.entity.TravelerTestimonialItemEntity;
import com.viajescarolina.content.infra.jpa.mapper.HomeContentMapper;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;

import java.util.List;
import java.util.Optional;

/**
 * Implementación JPA del repositorio TravelersTestimonialsRepository.
 */
@ApplicationScoped
public class TravelersTestimonialsJpaRepository implements TravelersTestimonialsRepository {

    @Inject
    EntityManager entityManager;

    @Inject
    HomeContentMapper mapper;

    @Override
    public Optional<TravelersTestimonialsSection> findActiveSection() {
        // Buscar la sección activa
        List<TravelersTestimonialsSectionEntity> sections = entityManager
                .createQuery(
                    "SELECT s FROM TravelersTestimonialsSectionEntity s WHERE s.isActive = true ORDER BY s.id ASC",
                    TravelersTestimonialsSectionEntity.class)
                .setMaxResults(1)
                .getResultList();

        if (sections.isEmpty()) {
            return Optional.empty();
        }

        TravelersTestimonialsSectionEntity sectionEntity = sections.get(0);

        // Buscar los items activos ordenados
        List<TravelerTestimonialItemEntity> items = entityManager
                .createQuery(
                    "SELECT i FROM TravelerTestimonialItemEntity i WHERE i.isActive = true ORDER BY i.displayOrder ASC",
                    TravelerTestimonialItemEntity.class)
                .getResultList();

        return Optional.of(mapper.toTravelersTestimonialsSection(sectionEntity, items));
    }
}
