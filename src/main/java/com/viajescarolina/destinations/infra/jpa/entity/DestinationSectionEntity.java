package com.viajescarolina.destinations.infra.jpa.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.*;
import java.time.LocalDateTime;

/**
 * Entity para secciones de contenido de un destino (blog-style).
 */
@Entity
@Table(name = "destination_section")
public class DestinationSectionEntity extends PanacheEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "destination_id", nullable = false)
    public DestinationEntity destination;

    @Column(name = "title", length = 200)
    public String title;

    @Column(name = "content_html", columnDefinition = "TEXT")
    public String contentHtml;

    @Enumerated(EnumType.STRING)
    @Column(name = "section_type", nullable = false, length = 30)
    public SectionType sectionType;

    @Column(name = "icon", length = 50)
    public String icon;

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
