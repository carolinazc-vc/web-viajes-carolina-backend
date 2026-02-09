package com.viajescarolina.content.infra.jpa;

import com.viajescarolina.content.infra.jpa.entity.TestimonialEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import java.util.List;

/**
 * Repositorio para acceder a testimonios en la base de datos.
 */
@ApplicationScoped
public class TestimonialRepository implements PanacheRepository<TestimonialEntity> {

    /**
     * Obtiene todos los testimonios activos ordenados por display_order.
     */
    public List<TestimonialEntity> findAllActive() {
        return list("isActive", true)
                .stream()
                .sorted((a, b) -> a.displayOrder.compareTo(b.displayOrder))
                .toList();
    }

    /**
     * Obtiene un testimonio aleatorio activo.
     * Útil para mostrar un testimonial diferente en cada carga.
     */
    public TestimonialEntity findRandomActive() {
        List<TestimonialEntity> activeTestimonials = findAllActive();
        if (activeTestimonials.isEmpty()) {
            return null;
        }
        int randomIndex = (int) (Math.random() * activeTestimonials.size());
        return activeTestimonials.get(randomIndex);
    }
}

