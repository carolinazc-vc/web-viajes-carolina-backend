package com.viajescarolina.destinations.infra.jpa.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.*;
import java.time.LocalDateTime;

/**
 * Entity para la tabla 'destination'.
 */
@Entity
@Table(name = "destination")
public class DestinationEntity extends PanacheEntity {

    @Column(name = "slug", nullable = false, unique = true, length = 120)
    public String slug;

    @Column(name = "name", nullable = false, length = 150)
    public String name;

    @Column(name = "country", length = 100)
    public String country;

    @Column(name = "region", length = 100)
    public String region;

    @Column(name = "city", length = 100)
    public String city;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    public DestinationCategoryEntity category;

    // RESUMEN PARA HOME / LISTA
    @Column(name = "summary_title", length = 150)
    public String summaryTitle;

    @Column(name = "summary_excerpt", length = 255)
    public String summaryExcerpt;

    @Column(name = "summary_badge", length = 50)
    public String summaryBadge;

    @Column(name = "summary_image_url", length = 255)
    public String summaryImageUrl;

    // SEO Y METADATOS
    @Column(name = "meta_title", length = 160)
    public String metaTitle;

    @Column(name = "meta_description", length = 320)
    public String metaDescription;

    @Column(name = "featured_image_url", length = 255)
    public String featuredImageUrl;

    @Column(name = "reading_time_minutes")
    public Integer readingTimeMinutes;

    @Column(name = "published_at")
    public LocalDateTime publishedAt;

    // CONTENIDO DETALLE ESTILO BLOG (deprecado - usar secciones)
    @Column(name = "intro_text", columnDefinition = "TEXT")
    public String introText;

    @Column(name = "content", columnDefinition = "TEXT")
    public String content;

    @Column(name = "highlight_tips", columnDefinition = "TEXT")
    public String highlightTips;

    @Column(name = "is_recommended", nullable = false)
    public Boolean isRecommended = false;

    @Column(name = "recommended_order", nullable = false)
    public Integer recommendedOrder;

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

