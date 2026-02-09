package com.viajescarolina.content.infra.jpa.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.*;
import java.time.LocalDateTime;

/**
 * Entity para la tabla 'page_hero_cta'.
 */
@Entity
@Table(name = "page_hero_cta")
public class PageHeroCtaEntity extends PanacheEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hero_id", nullable = false)
    public PageHeroEntity hero;

    @Column(name = "text", nullable = false, length = 100)
    public String text;

    @Column(name = "link", nullable = false, length = 255)
    public String link;

    @Column(name = "icon", length = 50)
    public String icon;

    @Column(name = "style", nullable = false, length = 30)
    public String style; // PRIMARY, SECONDARY

    @Column(name = "is_external", nullable = false)
    public Boolean isExternal = false;

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

