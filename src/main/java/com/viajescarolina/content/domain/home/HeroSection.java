package com.viajescarolina.content.domain.home;

import java.util.List;

/**
 * Representa el bloque hero del home.
 */
public class HeroSection {
    private String id;
    private String badgeIcon;
    private String badgeText;
    private String titleLine1;
    private String titleLine2;
    private String subtitle;
    private List<HeroStat> stats;
    private List<HeroCta> ctas;
    private List<HeroTrustIndicator> trustIndicators;
    private HeroImage image;
    private HeroFloatingBadge floatingBadge;

    public HeroSection() {
    }

    public HeroSection(String id, String badgeIcon, String badgeText, String titleLine1,
                       String titleLine2, String subtitle, List<HeroStat> stats,
                       List<HeroCta> ctas, List<HeroTrustIndicator> trustIndicators,
                       HeroImage image, HeroFloatingBadge floatingBadge) {
        this.id = id;
        this.badgeIcon = badgeIcon;
        this.badgeText = badgeText;
        this.titleLine1 = titleLine1;
        this.titleLine2 = titleLine2;
        this.subtitle = subtitle;
        this.stats = stats;
        this.ctas = ctas;
        this.trustIndicators = trustIndicators;
        this.image = image;
        this.floatingBadge = floatingBadge;
    }

    // Getters y Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBadgeIcon() {
        return badgeIcon;
    }

    public void setBadgeIcon(String badgeIcon) {
        this.badgeIcon = badgeIcon;
    }

    public String getBadgeText() {
        return badgeText;
    }

    public void setBadgeText(String badgeText) {
        this.badgeText = badgeText;
    }

    public String getTitleLine1() {
        return titleLine1;
    }

    public void setTitleLine1(String titleLine1) {
        this.titleLine1 = titleLine1;
    }

    public String getTitleLine2() {
        return titleLine2;
    }

    public void setTitleLine2(String titleLine2) {
        this.titleLine2 = titleLine2;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public List<HeroStat> getStats() {
        return stats;
    }

    public void setStats(List<HeroStat> stats) {
        this.stats = stats;
    }

    public List<HeroCta> getCtas() {
        return ctas;
    }

    public void setCtas(List<HeroCta> ctas) {
        this.ctas = ctas;
    }

    public List<HeroTrustIndicator> getTrustIndicators() {
        return trustIndicators;
    }

    public void setTrustIndicators(List<HeroTrustIndicator> trustIndicators) {
        this.trustIndicators = trustIndicators;
    }

    public HeroImage getImage() {
        return image;
    }

    public void setImage(HeroImage image) {
        this.image = image;
    }

    public HeroFloatingBadge getFloatingBadge() {
        return floatingBadge;
    }

    public void setFloatingBadge(HeroFloatingBadge floatingBadge) {
        this.floatingBadge = floatingBadge;
    }

    // Clases internas
    public static class HeroStat {
        private String value;
        private String label;

        public HeroStat() {
        }

        public HeroStat(String value, String label) {
            this.value = value;
            this.label = label;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public String getLabel() {
            return label;
        }

        public void setLabel(String label) {
            this.label = label;
        }
    }

    public static class HeroCta {
        private String text;
        private String link;
        private String icon;
        private String style;
        private Boolean external;

        public HeroCta() {
        }

        public HeroCta(String text, String link, String icon, String style, Boolean external) {
            this.text = text;
            this.link = link;
            this.icon = icon;
            this.style = style;
            this.external = external;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public String getLink() {
            return link;
        }

        public void setLink(String link) {
            this.link = link;
        }

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

        public String getStyle() {
            return style;
        }

        public void setStyle(String style) {
            this.style = style;
        }

        public Boolean getExternal() {
            return external;
        }

        public void setExternal(Boolean external) {
            this.external = external;
        }
    }

    public static class HeroTrustIndicator {
        private String icon;
        private String highlight;
        private String text;

        public HeroTrustIndicator() {
        }

        public HeroTrustIndicator(String icon, String highlight, String text) {
            this.icon = icon;
            this.highlight = highlight;
            this.text = text;
        }

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

        public String getHighlight() {
            return highlight;
        }

        public void setHighlight(String highlight) {
            this.highlight = highlight;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }
    }

    public static class HeroImage {
        private String url;
        private String alt;

        public HeroImage() {
        }

        public HeroImage(String url, String alt) {
            this.url = url;
            this.alt = alt;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getAlt() {
            return alt;
        }

        public void setAlt(String alt) {
            this.alt = alt;
        }
    }

    public static class HeroFloatingBadge {
        private String icon;
        private String label;
        private String text;

        public HeroFloatingBadge() {
        }

        public HeroFloatingBadge(String icon, String label, String text) {
            this.icon = icon;
            this.label = label;
            this.text = text;
        }

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

        public String getLabel() {
            return label;
        }

        public void setLabel(String label) {
            this.label = label;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }
    }
}

