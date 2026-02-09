package com.viajescarolina.content.domain.home;

import java.util.List;

/**
 * Representa el bloque de badges de confianza.
 */
public class TrustSection {
    private String id;
    private String title;
    private String subtitle;
    private List<TrustBadge> badges;
    private String agencyImageUrl;
    private String agencyImageAlt;
    private String agencyDescription;

    public TrustSection() {
    }

    public TrustSection(String id, String title, String subtitle, List<TrustBadge> badges) {
        this.id = id;
        this.title = title;
        this.subtitle = subtitle;
        this.badges = badges;
    }

    public TrustSection(String id, String title, String subtitle, List<TrustBadge> badges,
                        String agencyImageUrl, String agencyImageAlt, String agencyDescription) {
        this.id = id;
        this.title = title;
        this.subtitle = subtitle;
        this.badges = badges;
        this.agencyImageUrl = agencyImageUrl;
        this.agencyImageAlt = agencyImageAlt;
        this.agencyDescription = agencyDescription;
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

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public List<TrustBadge> getBadges() {
        return badges;
    }

    public void setBadges(List<TrustBadge> badges) {
        this.badges = badges;
    }

    public String getAgencyImageUrl() {
        return agencyImageUrl;
    }

    public void setAgencyImageUrl(String agencyImageUrl) {
        this.agencyImageUrl = agencyImageUrl;
    }

    public String getAgencyImageAlt() {
        return agencyImageAlt;
    }

    public void setAgencyImageAlt(String agencyImageAlt) {
        this.agencyImageAlt = agencyImageAlt;
    }

    public String getAgencyDescription() {
        return agencyDescription;
    }

    public void setAgencyDescription(String agencyDescription) {
        this.agencyDescription = agencyDescription;
    }

    public static class TrustBadge {
        private String id;
        private String icon;
        private String title;
        private String description;
        private Integer sortOrder;

        public TrustBadge() {
        }

        public TrustBadge(String id, String icon, String title, String description, Integer sortOrder) {
            this.id = id;
            this.icon = icon;
            this.title = title;
            this.description = description;
            this.sortOrder = sortOrder;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
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

        public Integer getSortOrder() {
            return sortOrder;
        }

        public void setSortOrder(Integer sortOrder) {
            this.sortOrder = sortOrder;
        }
    }
}

