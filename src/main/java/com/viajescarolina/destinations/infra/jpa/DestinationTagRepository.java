package com.viajescarolina.destinations.infra.jpa;

import com.viajescarolina.destinations.infra.jpa.entity.DestinationTagEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;
import java.util.Optional;

/**
 * Repositorio para operaciones de DestinationTag.
 */
@ApplicationScoped
public class DestinationTagRepository implements PanacheRepository<DestinationTagEntity> {

    /**
     * Busca un tag por su slug.
     */
    public Optional<DestinationTagEntity> findBySlug(String slug) {
        return find("slug", slug).firstResultOptional();
    }

    /**
     * Obtiene todos los tags activos ordenados.
     */
    public List<DestinationTagEntity> findAllActive() {
        return list("isActive = true ORDER BY sortOrder ASC, name ASC");
    }

    /**
     * Obtiene todos los tags ordenados (para admin).
     */
    public List<DestinationTagEntity> findAllOrdered() {
        return list("ORDER BY sortOrder ASC, name ASC");
    }
}
