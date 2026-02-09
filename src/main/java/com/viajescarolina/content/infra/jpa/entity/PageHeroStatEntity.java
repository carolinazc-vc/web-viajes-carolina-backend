package com.viajescarolina.content.infra.jpa.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.*;
import java.time.LocalDateTime;

/**
 * Entity para la tabla 'page_hero_stat'.
 */
@Entity
@Table(name = "page_hero_stat")
public class PageHeroStatEntity extends PanacheEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hero_id", nullable = false)
    public PageHeroEntity hero;

    @Column(name = "value", nullable = false, length = 50)
    public String value;

    @Column(name = "label", nullable = false, length = 100)
    public String label;

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

