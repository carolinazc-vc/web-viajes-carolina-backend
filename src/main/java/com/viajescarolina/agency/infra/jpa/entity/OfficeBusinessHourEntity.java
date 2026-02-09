package com.viajescarolina.agency.infra.jpa.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.*;
import java.time.LocalDateTime;

/**
 * Entity para la tabla 'office_business_hour'.
 */
@Entity
@Table(name = "office_business_hour")
public class OfficeBusinessHourEntity extends PanacheEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "office_id", nullable = false)
    public OfficeEntity office;

    @Column(name = "day_of_week", nullable = false, length = 20)
    public String dayOfWeek; // LUNES, MARTES, etc.

    @Column(name = "open_time")
    public java.time.LocalTime openTime;

    @Column(name = "close_time")
    public java.time.LocalTime closeTime;

    @Column(name = "is_closed", nullable = false)
    public Boolean isClosed = false;

    @Column(name = "notes", length = 255)
    public String notes;

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

