package com.viajescarolina.agency.infra.jpa.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.*;
import java.time.LocalDateTime;

/**
 * Entity para la tabla 'trust_badge'.
 * Representa los indicadores de confianza de la agencia (años, clientes, viajes, etc.)
 */
@Entity
@Table(name = "trust_badge")
public class TrustBadgeEntity extends PanacheEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "section_id", nullable = false)
    public TrustSectionEntity section;

    @Column(name = "icon", length = 10)
    public String icon;

    @Column(name = "title", nullable = false, length = 100)
    public String title;

    @Column(name = "description", length = 255)
    public String description;

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
