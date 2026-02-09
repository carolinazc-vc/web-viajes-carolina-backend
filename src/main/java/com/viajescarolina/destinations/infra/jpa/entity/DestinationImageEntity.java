package com.viajescarolina.destinations.infra.jpa.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.*;
import java.time.LocalDateTime;

/**
 * Entity para la tabla 'destination_image'.
 */
@Entity
@Table(name = "destination_image")
public class DestinationImageEntity extends PanacheEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "destination_id", nullable = false)
    public DestinationEntity destination;

    @Column(name = "image_url", nullable = false, length = 255)
    public String imageUrl;

    @Column(name = "alt_text", length = 255)
    public String altText;

    @Column(name = "caption", length = 500)
    public String caption;

    @Enumerated(EnumType.STRING)
    @Column(name = "image_type", length = 20)
    public ImageType imageType;

    @Column(name = "is_featured", nullable = false)
    public Boolean isFeatured = false;

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

