package com.viajescarolina.promotions.infra.jpa.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Entity para la tabla 'promotion'.
 */
@Entity
@Table(name = "promotion")
public class PromotionEntity extends PanacheEntity {

    @Column(name = "slug", nullable = false, unique = true, length = 255)
    public String slug;

    @Column(name = "title", nullable = false, length = 500)
    public String title;

    @Column(name = "short_description", length = 1000)
    public String shortDescription;

    @Column(name = "long_description", columnDefinition = "TEXT")
    public String longDescription;

    @Column(name = "main_image_url", columnDefinition = "TEXT")
    public String mainImageUrl;

    @Column(name = "price_from", precision = 12, scale = 2)
    public BigDecimal priceFrom;

    @Column(name = "currency", length = 10)
    public String currency;

    @Column(name = "duration_days")
    public Integer durationDays;

    @Column(name = "duration_nights")
    public Integer durationNights;

    @Column(name = "badge_text", length = 50)
    public String badgeText;

    @Column(name = "badge_type", length = 30)
    public String badgeType; // ACCENT, PRIMARY, etc.

    @Column(name = "validity_label", length = 100)
    public String validityLabel; // Hasta 31 Dic, Cupos limitados

    @Column(name = "is_featured", nullable = false)
    public Boolean isFeatured = false;

    @Column(name = "status", nullable = false, length = 30)
    public String status = "DRAFT"; // DRAFT, PUBLISHED, ARCHIVED

    @Column(name = "valid_from")
    public LocalDate validFrom;

    @Column(name = "valid_to")
    public LocalDate validTo;

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

