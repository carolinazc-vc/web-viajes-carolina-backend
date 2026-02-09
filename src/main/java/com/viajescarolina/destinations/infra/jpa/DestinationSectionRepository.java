package com.viajescarolina.destinations.infra.jpa;

import com.viajescarolina.destinations.infra.jpa.entity.DestinationSectionEntity;
import com.viajescarolina.destinations.infra.jpa.entity.SectionType;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

/**
 * Repositorio para operaciones de DestinationSection.
 */
@ApplicationScoped
public class DestinationSectionRepository implements PanacheRepository<DestinationSectionEntity> {

    /**
     * Obtiene secciones de un destino ordenadas.
     */
    public List<DestinationSectionEntity> findByDestinationId(Long destinationId) {
        return list("destination.id = ?1 ORDER BY sortOrder ASC", destinationId);
    }

    /**
     * Obtiene secciones visibles de un destino ordenadas.
     */
    public List<DestinationSectionEntity> findVisibleByDestinationId(Long destinationId) {
        return list("destination.id = ?1 AND isVisible = true ORDER BY sortOrder ASC", destinationId);
    }

    /**
     * Obtiene sección por tipo de un destino.
     */
    public List<DestinationSectionEntity> findByDestinationIdAndType(Long destinationId, SectionType type) {
        return list("destination.id = ?1 AND sectionType = ?2 ORDER BY sortOrder ASC", destinationId, type);
    }

    /**
     * Elimina todas las secciones de un destino.
     */
    public long deleteByDestinationId(Long destinationId) {
        return delete("destination.id = ?1", destinationId);
    }
}
