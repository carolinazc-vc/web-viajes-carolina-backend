package com.viajescarolina.agency.infra.jpa.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.*;
import java.time.LocalDateTime;

/**
 * Entity para la tabla 'agency_social_link'.
 */
@Entity
@Table(name = "agency_social_link")
public class AgencySocialLinkEntity extends PanacheEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "agency_id", nullable = false)
    public AgencyInfoEntity agency;

    @Column(name = "platform", nullable = false, length = 50)
    public String platform; // FACEBOOK, INSTAGRAM, TIKTOK, YOUTUBE

    @Column(name = "url", nullable = false, length = 255)
    public String url;

    @Column(name = "is_active", nullable = false)
    public Boolean isActive = true;

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

