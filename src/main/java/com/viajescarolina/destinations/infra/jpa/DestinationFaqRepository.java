package com.viajescarolina.destinations.infra.jpa;

import com.viajescarolina.destinations.infra.jpa.entity.DestinationFaqEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

/**
 * Repositorio para operaciones de DestinationFaq.
 */
@ApplicationScoped
public class DestinationFaqRepository implements PanacheRepository<DestinationFaqEntity> {

    /**
     * Obtiene FAQs de un destino ordenados.
     */
    public List<DestinationFaqEntity> findByDestinationId(Long destinationId) {
        return list("destination.id = ?1 ORDER BY sortOrder ASC", destinationId);
    }

    /**
     * Obtiene FAQs visibles de un destino ordenados.
     */
    public List<DestinationFaqEntity> findVisibleByDestinationId(Long destinationId) {
        return list("destination.id = ?1 AND isVisible = true ORDER BY sortOrder ASC", destinationId);
    }

    /**
     * Elimina todos los FAQs de un destino.
     */
    public long deleteByDestinationId(Long destinationId) {
        return delete("destination.id = ?1", destinationId);
    }
}
