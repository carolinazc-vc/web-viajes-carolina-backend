package com.viajescarolina.content.infra.jpa.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Entity para la tabla 'page_hero'.
 */
@Entity
@Table(name = "page_hero")
public class PageHeroEntity extends PanacheEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "page_id", nullable = false)
    public PageEntity page;

    @Column(name = "badge_icon", length = 10)
    public String badgeIcon;

    @Column(name = "badge_text", length = 100)
    public String badgeText;

    @Column(name = "title_line1", nullable = false, length = 100)
    public String titleLine1;

    @Column(name = "title_line2", length = 100)
    public String titleLine2;

    @Column(name = "subtitle", length = 255)
    public String subtitle;

    @Column(name = "image_url", length = 255)
    public String imageUrl;

    @Column(name = "image_alt", length = 255)
    public String imageAlt;

    @Column(name = "floating_icon", length = 10)
    public String floatingIcon;

    @Column(name = "floating_label", length = 100)
    public String floatingLabel;

    @Column(name = "floating_text", length = 150)
    public String floatingText;

    @Column(name = "is_active", nullable = false)
    public Boolean isActive = true;

    @Column(name = "created_at", nullable = false, updatable = false)
    public LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    public LocalDateTime updatedAt;

    @OneToMany(mappedBy = "hero", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @OrderBy("sort_order ASC")
    public List<PageHeroStatEntity> stats;

    @OneToMany(mappedBy = "hero", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @OrderBy("sort_order ASC")
    public List<PageHeroCtaEntity> ctas;

    @OneToMany(mappedBy = "hero", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @OrderBy("sort_order ASC")
    public List<PageHeroTrustIndicatorEntity> trustIndicators;

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

