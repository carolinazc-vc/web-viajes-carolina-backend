package com.viajescarolina.destinations.domain;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Modelo de dominio para un Destino completo (estilo blog).
 */
public class Destination {
    private String id;
    private String slug;
    private String name;
    private String country;
    private String region;
    private String city;
    private DestinationCategory category;

    // Resumen para listados
    private String summaryTitle;
    private String summaryExcerpt;
    private String summaryBadge;
    private String summaryImageUrl;

    // SEO y metadatos
    private String metaTitle;
    private String metaDescription;
    private String featuredImageUrl;
    private Integer readingTimeMinutes;
    private LocalDateTime publishedAt;

    // Contenido detalle (deprecado - usar secciones)
    private String introText;
    private String content;
    private String highlightTips;

    // Contenido modular (nuevo)
    private List<DestinationSection> sections;
    private List<DestinationFaq> faqs;
    private List<DestinationTag> tags;

    // Imágenes
    private List<DestinationImage> images;

    // Flags
    private boolean isRecommended;
    private int recommendedOrder;
    private boolean isActive;

    public Destination() {
    }

    // Getters y Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public DestinationCategory getCategory() {
        return category;
    }

    public void setCategory(DestinationCategory category) {
        this.category = category;
    }

    public String getSummaryTitle() {
        return summaryTitle;
    }

    public void setSummaryTitle(String summaryTitle) {
        this.summaryTitle = summaryTitle;
    }

    public String getSummaryExcerpt() {
        return summaryExcerpt;
    }

    public void setSummaryExcerpt(String summaryExcerpt) {
        this.summaryExcerpt = summaryExcerpt;
    }

    public String getSummaryBadge() {
        return summaryBadge;
    }

    public void setSummaryBadge(String summaryBadge) {
        this.summaryBadge = summaryBadge;
    }

    public String getSummaryImageUrl() {
        return summaryImageUrl;
    }

    public void setSummaryImageUrl(String summaryImageUrl) {
        this.summaryImageUrl = summaryImageUrl;
    }

    public String getIntroText() {
        return introText;
    }

    public void setIntroText(String introText) {
        this.introText = introText;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getHighlightTips() {
        return highlightTips;
    }

    public void setHighlightTips(String highlightTips) {
        this.highlightTips = highlightTips;
    }

    public String getMetaTitle() {
        return metaTitle;
    }

    public void setMetaTitle(String metaTitle) {
        this.metaTitle = metaTitle;
    }

    public String getMetaDescription() {
        return metaDescription;
    }

    public void setMetaDescription(String metaDescription) {
        this.metaDescription = metaDescription;
    }

    public String getFeaturedImageUrl() {
        return featuredImageUrl;
    }

    public void setFeaturedImageUrl(String featuredImageUrl) {
        this.featuredImageUrl = featuredImageUrl;
    }

    public Integer getReadingTimeMinutes() {
        return readingTimeMinutes;
    }

    public void setReadingTimeMinutes(Integer readingTimeMinutes) {
        this.readingTimeMinutes = readingTimeMinutes;
    }

    public LocalDateTime getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(LocalDateTime publishedAt) {
        this.publishedAt = publishedAt;
    }

    public List<DestinationSection> getSections() {
        return sections;
    }

    public void setSections(List<DestinationSection> sections) {
        this.sections = sections;
    }

    public List<DestinationFaq> getFaqs() {
        return faqs;
    }

    public void setFaqs(List<DestinationFaq> faqs) {
        this.faqs = faqs;
    }

    public List<DestinationTag> getTags() {
        return tags;
    }

    public void setTags(List<DestinationTag> tags) {
        this.tags = tags;
    }

    public List<DestinationImage> getImages() {
        return images;
    }

    public void setImages(List<DestinationImage> images) {
        this.images = images;
    }

    public boolean isRecommended() {
        return isRecommended;
    }

    public void setRecommended(boolean recommended) {
        isRecommended = recommended;
    }

    public int getRecommendedOrder() {
        return recommendedOrder;
    }

    public void setRecommendedOrder(int recommendedOrder) {
        this.recommendedOrder = recommendedOrder;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    /**
     * Imagen de destino.
     */
    public static class DestinationImage {
        private String id;
        private String url;
        private String alt;
        private String caption;
        private String imageType;
        private boolean isFeatured;
        private int sortOrder;

        public DestinationImage() {
        }

        public DestinationImage(String id, String url, String alt, int sortOrder) {
            this.id = id;
            this.url = url;
            this.alt = alt;
            this.sortOrder = sortOrder;
        }

        public DestinationImage(String id, String url, String alt, String caption,
                                String imageType, boolean isFeatured, int sortOrder) {
            this.id = id;
            this.url = url;
            this.alt = alt;
            this.caption = caption;
            this.imageType = imageType;
            this.isFeatured = isFeatured;
            this.sortOrder = sortOrder;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
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

        public String getCaption() {
            return caption;
        }

        public void setCaption(String caption) {
            this.caption = caption;
        }

        public String getImageType() {
            return imageType;
        }

        public void setImageType(String imageType) {
            this.imageType = imageType;
        }

        public boolean isFeatured() {
            return isFeatured;
        }

        public void setFeatured(boolean featured) {
            isFeatured = featured;
        }

        public int getSortOrder() {
            return sortOrder;
        }

        public void setSortOrder(int sortOrder) {
            this.sortOrder = sortOrder;
        }
    }

    /**
     * Sección de contenido del destino (estilo blog).
     */
    public static class DestinationSection {
        private String id;
        private String title;
        private String contentHtml;
        private String sectionType;
        private String sectionTypeDisplayName;
        private String icon;
        private int sortOrder;
        private boolean isVisible;

        public DestinationSection() {
        }

        public DestinationSection(String id, String title, String contentHtml,
                                   String sectionType, String sectionTypeDisplayName,
                                   String icon, int sortOrder, boolean isVisible) {
            this.id = id;
            this.title = title;
            this.contentHtml = contentHtml;
            this.sectionType = sectionType;
            this.sectionTypeDisplayName = sectionTypeDisplayName;
            this.icon = icon;
            this.sortOrder = sortOrder;
            this.isVisible = isVisible;
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

        public String getContentHtml() {
            return contentHtml;
        }

        public void setContentHtml(String contentHtml) {
            this.contentHtml = contentHtml;
        }

        public String getSectionType() {
            return sectionType;
        }

        public void setSectionType(String sectionType) {
            this.sectionType = sectionType;
        }

        public String getSectionTypeDisplayName() {
            return sectionTypeDisplayName;
        }

        public void setSectionTypeDisplayName(String sectionTypeDisplayName) {
            this.sectionTypeDisplayName = sectionTypeDisplayName;
        }

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

        public int getSortOrder() {
            return sortOrder;
        }

        public void setSortOrder(int sortOrder) {
            this.sortOrder = sortOrder;
        }

        public boolean isVisible() {
            return isVisible;
        }

        public void setVisible(boolean visible) {
            isVisible = visible;
        }
    }

    /**
     * Pregunta frecuente del destino.
     */
    public static class DestinationFaq {
        private String id;
        private String question;
        private String answer;
        private int sortOrder;
        private boolean isVisible;

        public DestinationFaq() {
        }

        public DestinationFaq(String id, String question, String answer, int sortOrder, boolean isVisible) {
            this.id = id;
            this.question = question;
            this.answer = answer;
            this.sortOrder = sortOrder;
            this.isVisible = isVisible;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getQuestion() {
            return question;
        }

        public void setQuestion(String question) {
            this.question = question;
        }

        public String getAnswer() {
            return answer;
        }

        public void setAnswer(String answer) {
            this.answer = answer;
        }

        public int getSortOrder() {
            return sortOrder;
        }

        public void setSortOrder(int sortOrder) {
            this.sortOrder = sortOrder;
        }

        public boolean isVisible() {
            return isVisible;
        }

        public void setVisible(boolean visible) {
            isVisible = visible;
        }
    }

    /**
     * Tag/etiqueta del destino para filtrado.
     */
    public static class DestinationTag {
        private String id;
        private String name;
        private String slug;
        private String icon;
        private String color;
        private String description;

        public DestinationTag() {
        }

        public DestinationTag(String id, String name, String slug, String icon, String color, String description) {
            this.id = id;
            this.name = name;
            this.slug = slug;
            this.icon = icon;
            this.color = color;
            this.description = description;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getSlug() {
            return slug;
        }

        public void setSlug(String slug) {
            this.slug = slug;
        }

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

        public String getColor() {
            return color;
        }

        public void setColor(String color) {
            this.color = color;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }
    }
}
