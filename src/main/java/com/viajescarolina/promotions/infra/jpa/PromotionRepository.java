package com.viajescarolina.promotions.infra.jpa;

import com.viajescarolina.promotions.infra.jpa.entity.PromotionEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * Repositorio para operaciones CRUD de Promotion usando Panache.
 */
@ApplicationScoped
public class PromotionRepository implements PanacheRepository<PromotionEntity> {

    /**
     * Busca una promoción que ya existe por su ID de Facebook.
     * Se almacena como parte del slug con prefijo "fb_".
     */
    public Optional<PromotionEntity> findByFacebookId(String facebookId) {
        return find("slug LIKE ?1", "fb_" + facebookId + "%").firstResultOptional();
    }

    /**
     * Encuentra promociones por estado, ordenadas por fecha de inicio (descending).
     */
    public List<PromotionEntity> findByStatusOrderByValidFromDesc(String status) {
        return list("status = ?1 ORDER BY validFrom DESC NULLS LAST", status);
    }

    /**
     * Encuentra promociones destacadas por estado.
     */
    public List<PromotionEntity> findByIsFeaturedAndStatusOrderByUpdatedAtDesc(Boolean isFeatured, String status) {
        return list("isFeatured = ?1 AND status = ?2 ORDER BY updatedAt DESC", isFeatured, status);
    }

    /**
     * Encuentra promociones activas (cuya fecha de validación incluye hoy).
     */
    public List<PromotionEntity> findActivePromotions(int limit) {
        LocalDate today = LocalDate.now();
        return find(
            "status = ?1 AND (validFrom IS NULL OR validFrom <= ?2) AND (validTo IS NULL OR validTo >= ?2) ORDER BY validFrom DESC",
            "PUBLISHED",
            today
        ).page(0, limit).list();
    }
}

