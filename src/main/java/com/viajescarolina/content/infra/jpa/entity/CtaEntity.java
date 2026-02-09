package com.viajescarolina.content.infra.jpa.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.*;
import java.time.LocalDateTime;

/**
 * Entity para la tabla 'cta' (Call To Action para home).
 * No tiene relación con page, es un bloque independiente del home.
 */
@Entity
@Table(name = "cta")
public class CtaEntity extends PanacheEntity {

    @Column(name = "headline", nullable = false, length = 150)
    public String headline;

    @Column(name = "description", length = 255)
    public String description;

    @Column(name = "button_text", nullable = false, length = 100)
    public String buttonText;

    @Column(name = "link_base", nullable = false, length = 255)
    public String linkBase; // ej: https://wa.me/

    @Column(name = "link_type", nullable = false, length = 30)
    public String linkType; // WHATSAPP

    @Column(name = "prefilled_message", length = 255)
    public String prefilledMessage; // opcional

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

