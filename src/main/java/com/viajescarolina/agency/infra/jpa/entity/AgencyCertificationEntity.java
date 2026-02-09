package com.viajescarolina.agency.infra.jpa.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Entity para la tabla 'agency_certification'.
 */
@Entity
@Table(name = "agency_certification")
public class AgencyCertificationEntity extends PanacheEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "agency_id", nullable = false)
    public AgencyInfoEntity agency;

    @Column(name = "name", nullable = false, length = 150)
    public String name;

    @Column(name = "issuer", length = 150)
    public String issuer;

    @Column(name = "description", length = 500)
    public String description;

    @Column(name = "logo_url", length = 500)
    public String logoUrl;

    @Column(name = "certificate_url", length = 500)
    public String certificateUrl;

    @Column(name = "issue_date")
    public LocalDate issueDate;

    @Column(name = "expiry_date")
    public LocalDate expiryDate;

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
