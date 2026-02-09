package com.viajescarolina.content.infra.jpa.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.*;
import java.time.LocalDateTime;

/**
 * Entity para la tabla 'travelers_testimonials_section'.
 * Almacena la configuración de la sección de testimonios de viajeros.
 */
@Entity
@Table(name = "travelers_testimonials_section")
@SequenceGenerator(name = "travelersTestimonialsSectionSeq", sequenceName = "travelers_testimonials_section_seq", allocationSize = 1)
public class TravelersTestimonialsSectionEntity extends PanacheEntity {

    @Column(name = "title", nullable = false, length = 255)
    public String title;

    @Column(name = "subtitle", length = 500)
    public String subtitle;

    @Column(name = "is_active", nullable = false)
    public Boolean isActive = true;

    @Column(name = "created_at", nullable = false, updatable = false)
    public LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    public LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}
