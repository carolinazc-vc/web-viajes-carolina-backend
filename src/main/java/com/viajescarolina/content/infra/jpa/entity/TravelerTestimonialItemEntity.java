package com.viajescarolina.content.infra.jpa.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.*;
import java.time.LocalDateTime;

/**
 * Entity para la tabla 'traveler_testimonial_item'.
 * Almacena los items individuales de testimonios de viajeros.
 */
@Entity
@Table(name = "traveler_testimonial_item")
@SequenceGenerator(name = "travelerTestimonialItemSeq", sequenceName = "traveler_testimonial_item_seq", allocationSize = 1)
public class TravelerTestimonialItemEntity extends PanacheEntity {

    @Column(name = "image_url", nullable = false, length = 500)
    public String imageUrl;

    @Column(name = "image_alt", length = 255)
    public String imageAlt;

    @Column(name = "customer_name", nullable = false, length = 150)
    public String customerName;

    @Column(name = "short_quote", nullable = false, length = 255)
    public String shortQuote;

    @Column(name = "destination", nullable = false, length = 150)
    public String destination;

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
