package com.viajescarolina.destinations.infra.jpa.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.*;
import java.time.LocalDateTime;

/**
 * Entity para tags/etiquetas de destinos (para filtrado).
 */
@Entity
@Table(name = "destination_tag")
public class DestinationTagEntity extends PanacheEntity {

    @Column(name = "name", nullable = false, length = 100)
    public String name;

    @Column(name = "slug", nullable = false, unique = true, length = 100)
    public String slug;

    @Column(name = "icon", length = 50)
    public String icon;

    @Column(name = "color", length = 20)
    public String color;

    @Column(name = "description", length = 255)
    public String description;

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
