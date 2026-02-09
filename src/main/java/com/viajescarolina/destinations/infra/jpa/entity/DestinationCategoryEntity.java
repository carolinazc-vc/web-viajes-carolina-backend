package com.viajescarolina.destinations.infra.jpa.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.*;
import java.time.LocalDateTime;

/**
 * Entity para la tabla 'destination_category'.
 */
@Entity
@Table(name = "destination_category")
public class DestinationCategoryEntity extends PanacheEntity {

    @Column(name = "name", nullable = false, length = 100)
    public String name;

    @Column(name = "slug", nullable = false, unique = true, length = 100)
    public String slug;

    @Column(name = "description", length = 255)
    public String description;

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

