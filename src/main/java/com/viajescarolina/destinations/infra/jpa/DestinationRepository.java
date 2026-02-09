package com.viajescarolina.destinations.infra.jpa;

import com.viajescarolina.destinations.infra.jpa.entity.DestinationEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;
import java.util.Optional;

/**
 * Repositorio para operaciones de Destination usando Panache.
 */
@ApplicationScoped
public class DestinationRepository implements PanacheRepository<DestinationEntity> {

    /**
     * Busca un destino por su slug.
     */
    public Optional<DestinationEntity> findBySlug(String slug) {
        return find("slug = ?1 AND isActive = true", slug).firstResultOptional();
    }

    /**
     * Obtiene todos los destinos activos ordenados por nombre.
     */
    public List<DestinationEntity> findAllActive() {
        return list("isActive = true ORDER BY name ASC");
    }

    /**
     * Obtiene destinos activos con paginación.
     */
    public List<DestinationEntity> findAllActivePaginated(int page, int size) {
        return find("isActive = true ORDER BY name ASC")
                .page(page, size)
                .list();
    }

    /**
     * Obtiene destinos activos por slug de categoría.
     */
    public List<DestinationEntity> findByCategorySlug(String categorySlug) {
        return list("category.slug = ?1 AND isActive = true ORDER BY name ASC", categorySlug);
    }

    /**
     * Obtiene destinos activos por slug de categoría con paginación.
     */
    public List<DestinationEntity> findByCategorySlugPaginated(String categorySlug, int page, int size) {
        return find("category.slug = ?1 AND isActive = true ORDER BY name ASC", categorySlug)
                .page(page, size)
                .list();
    }

    /**
     * Obtiene destinos recomendados activos.
     */
    public List<DestinationEntity> findRecommended(int limit) {
        return find("isRecommended = true AND isActive = true ORDER BY recommendedOrder ASC")
                .page(0, limit)
                .list();
    }

    /**
     * Búsqueda por texto en nombre, país o región.
     */
    public List<DestinationEntity> search(String query) {
        String searchPattern = "%" + query.toLowerCase() + "%";
        return list("isActive = true AND (LOWER(name) LIKE ?1 OR LOWER(country) LIKE ?1 OR LOWER(region) LIKE ?1) ORDER BY name ASC",
                    searchPattern);
    }

    /**
     * Obtiene todos los destinos para admin (incluyendo inactivos).
     */
    public List<DestinationEntity> findAllForAdmin() {
        return list("ORDER BY name ASC");
    }

    /**
     * Cuenta destinos activos.
     */
    public long countActive() {
        return count("isActive = true");
    }

    /**
     * Cuenta destinos por categoría.
     */
    public long countByCategorySlug(String categorySlug) {
        return count("category.slug = ?1 AND isActive = true", categorySlug);
    }
}
