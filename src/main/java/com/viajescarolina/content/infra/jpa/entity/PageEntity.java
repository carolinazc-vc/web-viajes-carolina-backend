package com.viajescarolina.content.infra.jpa.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.*;
import java.time.LocalDateTime;

/**
 * Entity para la tabla 'page'.
 */
@Entity
@Table(name = "page")
public class PageEntity extends PanacheEntity {

    @Column(name = "slug", nullable = false, unique = true, length = 100)
    public String slug;

    @Column(name = "title", nullable = false, length = 150)
    public String title;

    @Column(name = "meta_title", length = 150)
    public String metaTitle;

    @Column(name = "meta_description", length = 255)
    public String metaDescription;

    @Column(name = "is_active", nullable = false)
    public Boolean isActive = true;

    @Column(name = "page_type", nullable = false, length = 50)
    public String pageType; // HOME, ABOUT, CONTACT, DESTINOS, GENERIC

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

