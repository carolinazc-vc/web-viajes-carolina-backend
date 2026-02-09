package com.viajescarolina.destinations.infra.jpa;

import com.viajescarolina.destinations.infra.jpa.entity.DestinationImageEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

/**
 * Repositorio para operaciones de DestinationImage usando Panache.
 */
@ApplicationScoped
public class DestinationImageRepository implements PanacheRepository<DestinationImageEntity> {

    /**
     * Obtiene todas las imágenes de un destino ordenadas.
     */
    public List<DestinationImageEntity> findByDestinationId(Long destinationId) {
        return list("destination.id = ?1 ORDER BY sortOrder ASC", destinationId);
    }

    /**
     * Obtiene la primera imagen de un destino.
     */
    public DestinationImageEntity findFirstByDestinationId(Long destinationId) {
        return find("destination.id = ?1 ORDER BY sortOrder ASC", destinationId)
                .firstResult();
    }

    /**
     * Elimina todas las imágenes de un destino.
     */
    public long deleteByDestinationId(Long destinationId) {
        return delete("destination.id", destinationId);
    }
}
