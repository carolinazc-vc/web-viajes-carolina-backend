package com.viajescarolina.destinations.infra.jpa.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.*;
import java.time.LocalDateTime;

/**
 * Entity para preguntas frecuentes de un destino.
 */
@Entity
@Table(name = "destination_faq")
public class DestinationFaqEntity extends PanacheEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "destination_id", nullable = false)
    public DestinationEntity destination;

    @Column(name = "question", nullable = false, length = 500)
    public String question;

    @Column(name = "answer", columnDefinition = "TEXT", nullable = false)
    public String answer;

    @Column(name = "sort_order", nullable = false)
    public Integer sortOrder;

    @Column(name = "is_visible", nullable = false)
    public Boolean isVisible = true;

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
