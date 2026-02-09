package com.viajescarolina.agency.infra.jpa.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Entity para la tabla 'trust_section'.
 * Representa la sección de confianza/credenciales de la agencia.
 */
@Entity
@Table(name = "trust_section")
public class TrustSectionEntity extends PanacheEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "agency_id")
    public AgencyInfoEntity agency;

    @Column(name = "title", nullable = false, length = 150)
    public String title;

    @Column(name = "subtitle", length = 255)
    public String subtitle;

    @Column(name = "is_active", nullable = false)
    public Boolean isActive = true;

    @Column(name = "agency_image_url", length = 500)
    public String agencyImageUrl;

    @Column(name = "agency_image_alt", length = 255)
    public String agencyImageAlt;

    @Column(name = "agency_description", columnDefinition = "TEXT")
    public String agencyDescription;

    @Column(name = "created_at", nullable = false, updatable = false)
    public LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    public LocalDateTime updatedAt;

    @OneToMany(mappedBy = "section", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @OrderBy("sortOrder ASC")
    public List<TrustBadgeEntity> badges;

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
