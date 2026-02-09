package com.viajescarolina.agency.infra.jpa.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.*;
import java.time.LocalDateTime;

/**
 * Entity para la tabla 'agency_gallery_image'.
 */
@Entity
@Table(name = "agency_gallery_image")
public class AgencyGalleryImageEntity extends PanacheEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "agency_id", nullable = false)
    public AgencyInfoEntity agency;

    @Column(name = "image_url", nullable = false, length = 500)
    public String imageUrl;

    @Column(name = "thumbnail_url", length = 500)
    public String thumbnailUrl;

    @Column(name = "alt_text", length = 255)
    public String altText;

    @Column(name = "caption", length = 500)
    public String caption;

    @Column(name = "category", length = 50)
    public String category;

    @Column(name = "sort_order", nullable = false)
    public Integer sortOrder = 0;

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
