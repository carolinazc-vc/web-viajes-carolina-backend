package com.viajescarolina.agency.infra.jpa.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.*;
import java.time.LocalDateTime;

/**
 * Entity para la tabla 'agency_team_member'.
 */
@Entity
@Table(name = "agency_team_member")
public class AgencyTeamMemberEntity extends PanacheEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_section_id", nullable = false)
    public AgencyTeamSectionEntity teamSection;

    @Column(name = "name", nullable = false, length = 150)
    public String name;

    @Column(name = "position", length = 100)
    public String position;

    @Column(name = "bio", columnDefinition = "TEXT")
    public String bio;

    @Column(name = "photo_url", length = 500)
    public String photoUrl;

    @Column(name = "photo_alt", length = 255)
    public String photoAlt;

    @Column(name = "email", length = 150)
    public String email;

    @Column(name = "phone", length = 50)
    public String phone;

    @Column(name = "linkedin_url", length = 255)
    public String linkedinUrl;

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
