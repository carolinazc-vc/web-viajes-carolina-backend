package com.viajescarolina.agency.infra.jpa.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;
import java.time.LocalDateTime;

/**
 * Entity para la tabla 'agency_info'.
 */
@Entity
@Table(name = "agency_info")
public class AgencyInfoEntity extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @Column(name = "name", nullable = false, length = 150)
    public String name;

    @Column(name = "slogan", length = 255)
    public String slogan;

    @Column(name = "description", columnDefinition = "TEXT")
    public String description;

    @Column(name = "primary_email", length = 150)
    public String primaryEmail;

    @Column(name = "primary_phone", length = 50)
    public String primaryPhone;

    @Column(name = "whatsapp_number", length = 50)
    public String whatsappNumber;

    @Column(name = "website_url", length = 255)
    public String websiteUrl;

    @Column(name = "logo_url", length = 255)
    public String logoUrl;

    @Column(name = "main_address", length = 255)
    public String mainAddress;

    @Column(name = "city", length = 100)
    public String city;

    @Column(name = "country", length = 100)
    public String country;

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
