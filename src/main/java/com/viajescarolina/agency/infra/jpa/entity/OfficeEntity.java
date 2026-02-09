package com.viajescarolina.agency.infra.jpa.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Entity para la tabla 'office'.
 */
@Entity
@Table(name = "office")
public class OfficeEntity extends PanacheEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "agency_id", nullable = false)
    public AgencyInfoEntity agency;

    @Column(name = "name", nullable = false, length = 150)
    public String name;

    @Column(name = "address", nullable = false, length = 255)
    public String address;

    @Column(name = "city", length = 100)
    public String city;

    @Column(name = "country", length = 100)
    public String country;

    @Column(name = "latitude", precision = 9, scale = 6)
    public BigDecimal latitude;

    @Column(name = "longitude", precision = 9, scale = 6)
    public BigDecimal longitude;

    @Column(name = "phone", length = 50)
    public String phone;

    @Column(name = "email", length = 150)
    public String email;

    @Column(name = "is_main_office", nullable = false)
    public Boolean isMainOffice = false;

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

