package com.viajescarolina.content.domain.home;

import java.math.BigDecimal;
import java.util.List;

/**
 * Representa el bloque de promociones destacadas en el home.
 */
public class PromotionsBlock {
    private String title;
    private String subtitle;
    private List<PromotionSummary> items;
    private String viewAllLink;
    private String viewAllText;

    public PromotionsBlock() {
    }

    public PromotionsBlock(String title, String subtitle, List<PromotionSummary> items,
                          String viewAllLink, String viewAllText) {
        this.title = title;
        this.subtitle = subtitle;
        this.items = items;
        this.viewAllLink = viewAllLink;
        this.viewAllText = viewAllText;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public List<PromotionSummary> getItems() {
        return items;
    }

    public void setItems(List<PromotionSummary> items) {
        this.items = items;
    }

    public String getViewAllLink() {
        return viewAllLink;
    }

    public void setViewAllLink(String viewAllLink) {
        this.viewAllLink = viewAllLink;
    }

    public String getViewAllText() {
        return viewAllText;
    }

    public void setViewAllText(String viewAllText) {
        this.viewAllText = viewAllText;
    }

    public static class PromotionSummary {
        private String id;
        private String title;
        private String description;
        private String imageUrl;
        private String badgeText;
        private String badgeType;
        private String validity;
        private BigDecimal originalPrice;
        private BigDecimal currentPrice;
        private String currency;
        private String link;

        public PromotionSummary() {
        }

        public PromotionSummary(String id, String title, String description, String imageUrl,
                               String badgeText, String badgeType, String validity,
                               BigDecimal originalPrice, BigDecimal currentPrice,
                               String currency, String link) {
            this.id = id;
            this.title = title;
            this.description = description;
            this.imageUrl = imageUrl;
            this.badgeText = badgeText;
            this.badgeType = badgeType;
            this.validity = validity;
            this.originalPrice = originalPrice;
            this.currentPrice = currentPrice;
            this.currency = currency;
            this.link = link;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getImageUrl() {
            return imageUrl;
        }

        public void setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
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

        public String getValidity() {
            return validity;
        }

        public void setValidity(String validity) {
            this.validity = validity;
        }

        public BigDecimal getOriginalPrice() {
            return originalPrice;
        }

        public void setOriginalPrice(BigDecimal originalPrice) {
            this.originalPrice = originalPrice;
        }

        public BigDecimal getCurrentPrice() {
            return currentPrice;
        }

        public void setCurrentPrice(BigDecimal currentPrice) {
            this.currentPrice = currentPrice;
        }

        public String getCurrency() {
            return currency;
        }

        public void setCurrency(String currency) {
            this.currency = currency;
        }

        public String getLink() {
            return link;
        }

        public void setLink(String link) {
            this.link = link;
        }
    }
}

