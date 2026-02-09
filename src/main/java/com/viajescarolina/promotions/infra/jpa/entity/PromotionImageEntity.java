package com.viajescarolina.promotions.infra.jpa.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.*;
import java.time.LocalDateTime;

/**
 * Entity para la tabla 'promotion_image'.
 */
@Entity
@Table(name = "promotion_image")
public class PromotionImageEntity extends PanacheEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "promotion_id", nullable = false)
    public PromotionEntity promotion;

    @Column(name = "image_url", nullable = false, length = 255)
    public String imageUrl;

    @Column(name = "alt_text", length = 255)
    public String altText;

    @Column(name = "sort_order", nullable = false)
    public Integer sortOrder;

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

