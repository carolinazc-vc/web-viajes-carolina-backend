package com.viajescarolina.destinations.infra.jpa;

import com.viajescarolina.destinations.infra.jpa.entity.DestinationCategoryEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;
import java.util.Optional;

/**
 * Repositorio para operaciones de DestinationCategory usando Panache.
 */
@ApplicationScoped
public class DestinationCategoryRepository implements PanacheRepository<DestinationCategoryEntity> {

    /**
     * Busca una categoría por su slug.
     */
    public Optional<DestinationCategoryEntity> findBySlug(String slug) {
        return find("slug", slug).firstResultOptional();
    }

    /**
     * Obtiene todas las categorías ordenadas por nombre.
     */
    public List<DestinationCategoryEntity> findAllOrdered() {
        return list("ORDER BY name ASC");
    }
}
