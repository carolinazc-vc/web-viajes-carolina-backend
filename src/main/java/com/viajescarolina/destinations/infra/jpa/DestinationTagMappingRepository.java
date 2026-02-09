package com.viajescarolina.destinations.infra.jpa;

import com.viajescarolina.destinations.infra.jpa.entity.DestinationTagMappingEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

/**
 * Repositorio para operaciones de DestinationTagMapping.
 */
@ApplicationScoped
public class DestinationTagMappingRepository implements PanacheRepository<DestinationTagMappingEntity> {

    /**
     * Obtiene mappings de un destino.
     */
    public List<DestinationTagMappingEntity> findByDestinationId(Long destinationId) {
        return list("destination.id = ?1", destinationId);
    }

    /**
     * Obtiene mappings de un tag.
     */
    public List<DestinationTagMappingEntity> findByTagId(Long tagId) {
        return list("tag.id = ?1", tagId);
    }

    /**
     * Elimina todos los mappings de un destino.
     */
    public long deleteByDestinationId(Long destinationId) {
        return delete("destination.id = ?1", destinationId);
    }

    /**
     * Verifica si existe un mapping.
     */
    public boolean existsMapping(Long destinationId, Long tagId) {
        return count("destination.id = ?1 AND tag.id = ?2", destinationId, tagId) > 0;
    }
}
