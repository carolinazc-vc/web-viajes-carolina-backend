package com.viajescarolina.promotions.api.dto;

import com.viajescarolina.promotions.infra.jpa.entity.PromotionEntity;

/**
 * Mapper para convertir entre PromotionEntity y PromotionDto.
 */
public class PromotionMapper {

    /**
     * Convierte PromotionEntity a PromotionDto.
     */
    public static PromotionDto toDtoFull(PromotionEntity entity) {
        if (entity == null) {
            return null;
        }

        return new PromotionDto(
            entity.id,
            entity.slug,
            entity.title,
            entity.shortDescription,
            entity.longDescription,
            entity.mainImageUrl,
            entity.priceFrom,
            entity.currency,
            entity.durationDays,
            entity.durationNights,
            entity.badgeText,
            entity.badgeType,
            entity.validityLabel,
            entity.isFeatured,
            entity.status,
            entity.validFrom,
            entity.validTo,
            entity.createdAt,
            entity.updatedAt
        );
    }

    /**
     * Convierte PromotionDto a PromotionEntity.
     */
    public static PromotionEntity toEntity(PromotionDto dto) {
        if (dto == null) {
            return null;
        }

        PromotionEntity entity = new PromotionEntity();
        entity.id = dto.getId();
        entity.slug = dto.getSlug();
        entity.title = dto.getTitle();
        entity.shortDescription = dto.getShortDescription();
        entity.longDescription = dto.getLongDescription();
        entity.mainImageUrl = dto.getMainImageUrl();
        entity.priceFrom = dto.getPriceFrom();
        entity.currency = dto.getCurrency();
        entity.durationDays = dto.getDurationDays();
        entity.durationNights = dto.getDurationNights();
        entity.badgeText = dto.getBadgeText();
        entity.badgeType = dto.getBadgeType();
        entity.validityLabel = dto.getValidityLabel();
        entity.isFeatured = dto.getIsFeatured();
        entity.status = dto.getStatus();
        entity.validFrom = dto.getValidFrom();
        entity.validTo = dto.getValidTo();
        entity.createdAt = dto.getCreatedAt();
        entity.updatedAt = dto.getUpdatedAt();

        return entity;
    }
}

