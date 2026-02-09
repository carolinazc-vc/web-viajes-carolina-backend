package com.viajescarolina.promotions.api.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * DTO completo para Promotion con todos los campos.
 * Replica la información de una publicación de Facebook.
 */
public class PromotionDto {

    @JsonProperty("id")
    public Long id;

    @JsonProperty("slug")
    public String slug;

    @JsonProperty("title")
    public String title;

    @JsonProperty("shortDescription")
    public String shortDescription;

    @JsonProperty("longDescription")
    public String longDescription;

    @JsonProperty("mainImageUrl")
    public String mainImageUrl;

    @JsonProperty("priceFrom")
    public BigDecimal priceFrom;

    @JsonProperty("currency")
    public String currency;

    @JsonProperty("durationDays")
    public Integer durationDays;

    @JsonProperty("durationNights")
    public Integer durationNights;

    @JsonProperty("badgeText")
    public String badgeText;

    @JsonProperty("badgeType")
    public String badgeType;

    @JsonProperty("validityLabel")
    public String validityLabel;

    @JsonProperty("isFeatured")
    public Boolean isFeatured;

    @JsonProperty("status")
    public String status;

    @JsonProperty("validFrom")
    public LocalDate validFrom;

    @JsonProperty("validTo")
    public LocalDate validTo;

    @JsonProperty("createdAt")
    public LocalDateTime createdAt;

    @JsonProperty("updatedAt")
    public LocalDateTime updatedAt;

    // Constructores
    public PromotionDto() {
    }

    public PromotionDto(Long id, String slug, String title, String shortDescription,
                        String longDescription, String mainImageUrl, BigDecimal priceFrom,
                        String currency, Integer durationDays, Integer durationNights,
                        String badgeText, String badgeType, String validityLabel,
                        Boolean isFeatured, String status, LocalDate validFrom,
                        LocalDate validTo, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.slug = slug;
        this.title = title;
        this.shortDescription = shortDescription;
        this.longDescription = longDescription;
        this.mainImageUrl = mainImageUrl;
        this.priceFrom = priceFrom;
        this.currency = currency;
        this.durationDays = durationDays;
        this.durationNights = durationNights;
        this.badgeText = badgeText;
        this.badgeType = badgeType;
        this.validityLabel = validityLabel;
        this.isFeatured = isFeatured;
        this.status = status;
        this.validFrom = validFrom;
        this.validTo = validTo;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getLongDescription() {
        return longDescription;
    }

    public void setLongDescription(String longDescription) {
        this.longDescription = longDescription;
    }

    public String getMainImageUrl() {
        return mainImageUrl;
    }

    public void setMainImageUrl(String mainImageUrl) {
        this.mainImageUrl = mainImageUrl;
    }

    public BigDecimal getPriceFrom() {
        return priceFrom;
    }

    public void setPriceFrom(BigDecimal priceFrom) {
        this.priceFrom = priceFrom;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Integer getDurationDays() {
        return durationDays;
    }

    public void setDurationDays(Integer durationDays) {
        this.durationDays = durationDays;
    }

    public Integer getDurationNights() {
        return durationNights;
    }

    public void setDurationNights(Integer durationNights) {
        this.durationNights = durationNights;
    }

    public String getBadgeText() {
        return badgeText;
    }

    public void setBadgeText(String badgeText) {
        this.badgeText = badgeText;
    }

    public String getBadgeType() {
        return badgeType;
    }

    public void setBadgeType(String badgeType) {
        this.badgeType = badgeType;
    }

    public String getValidityLabel() {
        return validityLabel;
    }

    public void setValidityLabel(String validityLabel) {
        this.validityLabel = validityLabel;
    }

    public Boolean getIsFeatured() {
        return isFeatured;
    }

    public void setIsFeatured(Boolean isFeatured) {
        this.isFeatured = isFeatured;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDate getValidFrom() {
        return validFrom;
    }

    public void setValidFrom(LocalDate validFrom) {
        this.validFrom = validFrom;
    }

    public LocalDate getValidTo() {
        return validTo;
    }

    public void setValidTo(LocalDate validTo) {
        this.validTo = validTo;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
        return "PromotionDto{" +
                "id=" + id +
                ", slug='" + slug + '\'' +
                ", title='" + title + '\'' +
                ", priceFrom=" + priceFrom +
                ", currency='" + currency + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}

