package com.viajescarolina.agency.infra.jpa.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.*;
import java.time.LocalDateTime;

/**
 * Entity para la tabla 'agency_mission_vision'.
 */
@Entity
@Table(name = "agency_mission_vision")
public class AgencyMissionVisionEntity extends PanacheEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "agency_id", nullable = false)
    public AgencyInfoEntity agency;

    @Column(name = "mission_title", length = 150)
    public String missionTitle;

    @Column(name = "mission_text", columnDefinition = "TEXT")
    public String missionText;

    @Column(name = "mission_icon", length = 50)
    public String missionIcon;

    @Column(name = "vision_title", length = 150)
    public String visionTitle;

    @Column(name = "vision_text", columnDefinition = "TEXT")
    public String visionText;

    @Column(name = "vision_icon", length = 50)
    public String visionIcon;

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
