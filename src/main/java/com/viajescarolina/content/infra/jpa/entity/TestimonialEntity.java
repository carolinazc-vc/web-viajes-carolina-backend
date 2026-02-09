package com.viajescarolina.content.infra.jpa.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.*;
import java.time.LocalDateTime;

/**
 * Entity para la tabla 'testimonial'.
 * Almacena testimonios de clientes para mostrar en la sección de Trust Badges.
 */
@Entity
@Table(name = "testimonial")
@SequenceGenerator(name = "testimonialSeq", sequenceName = "testimonial_id_seq", allocationSize = 1)
public class TestimonialEntity extends PanacheEntity {

    @Column(name = "quote", nullable = false, columnDefinition = "TEXT")
    public String quote;

    @Column(name = "author", nullable = false, length = 255)
    public String author;

    @Column(name = "role", nullable = false, length = 255)
    public String role;

    @Column(name = "years", nullable = false, length = 100)
    public String years;

    @Column(name = "image_id", length = 36)
    public String imageId;

    @Column(name = "image_url", length = 500)
    public String imageUrl;

    @Column(name = "is_active", nullable = false)
    public Boolean isActive = true;

    @Column(name = "display_order", nullable = false)
    public Integer displayOrder = 0;

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

