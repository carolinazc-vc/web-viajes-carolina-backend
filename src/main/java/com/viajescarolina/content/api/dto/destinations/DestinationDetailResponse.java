package com.viajescarolina.content.api.dto.destinations;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * DTO de respuesta para el detalle de un destino.
 */
public class DestinationDetailResponse {

    @JsonProperty("id")
    private String id;

    @JsonProperty("slug")
    private String slug;

    @JsonProperty("name")
    private String name;

    @JsonProperty("country")
    private String country;

    @JsonProperty("region")
    private String region;

    @JsonProperty("city")
    private String city;

    @JsonProperty("category")
    private CategoryDto category;

    @JsonProperty("summaryTitle")
    private String summaryTitle;

    @JsonProperty("summaryExcerpt")
    private String summaryExcerpt;

    @JsonProperty("summaryBadge")
    private String summaryBadge;

    // SEO y metadatos
    @JsonProperty("metaTitle")
    private String metaTitle;

    @JsonProperty("metaDescription")
    private String metaDescription;

    @JsonProperty("featuredImageUrl")
    private String featuredImageUrl;

    @JsonProperty("readingTimeMinutes")
    private Integer readingTimeMinutes;

    @JsonProperty("publishedAt")
    private String publishedAt;

    @JsonProperty("introText")
    private String introText;

    @JsonProperty("content")
    private String content;

    @JsonProperty("highlightTips")
    private String highlightTips;

    @JsonProperty("images")
    private List<ImageDto> images;

    // Contenido modular
    @JsonProperty("sections")
    private List<SectionDto> sections;

    @JsonProperty("faqs")
    private List<FaqDto> faqs;

    @JsonProperty("tags")
    private List<TagDto> tags;

    @JsonProperty("isRecommended")
    private boolean isRecommended;

    @JsonProperty("relatedDestinations")
    private List<RelatedDestinationDto> relatedDestinations;

    public DestinationDetailResponse() {
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

    public CategoryDto getCategory() {
        return category;
    }

    public void setCategory(CategoryDto category) {
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

    public String getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(String publishedAt) {
        this.publishedAt = publishedAt;
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

    public List<ImageDto> getImages() {
        return images;
    }

    public void setImages(List<ImageDto> images) {
        this.images = images;
    }

    public List<SectionDto> getSections() {
        return sections;
    }

    public void setSections(List<SectionDto> sections) {
        this.sections = sections;
    }

    public List<FaqDto> getFaqs() {
        return faqs;
    }

    public void setFaqs(List<FaqDto> faqs) {
        this.faqs = faqs;
    }

    public List<TagDto> getTags() {
        return tags;
    }

    public void setTags(List<TagDto> tags) {
        this.tags = tags;
    }

    public boolean isRecommended() {
        return isRecommended;
    }

    public void setRecommended(boolean recommended) {
        isRecommended = recommended;
    }

    public List<RelatedDestinationDto> getRelatedDestinations() {
        return relatedDestinations;
    }

    public void setRelatedDestinations(List<RelatedDestinationDto> relatedDestinations) {
        this.relatedDestinations = relatedDestinations;
    }

    // DTOs internas
    public static class CategoryDto {
        @JsonProperty("id")
        private String id;

        @JsonProperty("name")
        private String name;

        @JsonProperty("slug")
        private String slug;

        public CategoryDto() {
        }

        public CategoryDto(String id, String name, String slug) {
            this.id = id;
            this.name = name;
            this.slug = slug;
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
    }

    public static class ImageDto {
        @JsonProperty("id")
        private String id;

        @JsonProperty("url")
        private String url;

        @JsonProperty("alt")
        private String alt;

        public ImageDto() {
        }

        public ImageDto(String id, String url, String alt) {
            this.id = id;
            this.url = url;
            this.alt = alt;
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
    }

    public static class RelatedDestinationDto {
        @JsonProperty("id")
        private String id;

        @JsonProperty("slug")
        private String slug;

        @JsonProperty("name")
        private String name;

        @JsonProperty("imageUrl")
        private String imageUrl;

        public RelatedDestinationDto() {
        }

        public RelatedDestinationDto(String id, String slug, String name, String imageUrl) {
            this.id = id;
            this.slug = slug;
            this.name = name;
            this.imageUrl = imageUrl;
        }

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

        public String getImageUrl() {
            return imageUrl;
        }

        public void setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
        }
    }

    public static class SectionDto {
        @JsonProperty("id")
        private String id;

        @JsonProperty("title")
        private String title;

        @JsonProperty("contentHtml")
        private String contentHtml;

        @JsonProperty("sectionType")
        private String sectionType;

        @JsonProperty("sectionTypeDisplayName")
        private String sectionTypeDisplayName;

        @JsonProperty("icon")
        private String icon;

        @JsonProperty("sortOrder")
        private int sortOrder;

        public SectionDto() {
        }

        public SectionDto(String id, String title, String contentHtml, String sectionType,
                          String sectionTypeDisplayName, String icon, int sortOrder) {
            this.id = id;
            this.title = title;
            this.contentHtml = contentHtml;
            this.sectionType = sectionType;
            this.sectionTypeDisplayName = sectionTypeDisplayName;
            this.icon = icon;
            this.sortOrder = sortOrder;
        }

        public String getId() { return id; }
        public void setId(String id) { this.id = id; }
        public String getTitle() { return title; }
        public void setTitle(String title) { this.title = title; }
        public String getContentHtml() { return contentHtml; }
        public void setContentHtml(String contentHtml) { this.contentHtml = contentHtml; }
        public String getSectionType() { return sectionType; }
        public void setSectionType(String sectionType) { this.sectionType = sectionType; }
        public String getSectionTypeDisplayName() { return sectionTypeDisplayName; }
        public void setSectionTypeDisplayName(String sectionTypeDisplayName) { this.sectionTypeDisplayName = sectionTypeDisplayName; }
        public String getIcon() { return icon; }
        public void setIcon(String icon) { this.icon = icon; }
        public int getSortOrder() { return sortOrder; }
        public void setSortOrder(int sortOrder) { this.sortOrder = sortOrder; }
    }

    public static class FaqDto {
        @JsonProperty("id")
        private String id;

        @JsonProperty("question")
        private String question;

        @JsonProperty("answer")
        private String answer;

        @JsonProperty("sortOrder")
        private int sortOrder;

        public FaqDto() {
        }

        public FaqDto(String id, String question, String answer, int sortOrder) {
            this.id = id;
            this.question = question;
            this.answer = answer;
            this.sortOrder = sortOrder;
        }

        public String getId() { return id; }
        public void setId(String id) { this.id = id; }
        public String getQuestion() { return question; }
        public void setQuestion(String question) { this.question = question; }
        public String getAnswer() { return answer; }
        public void setAnswer(String answer) { this.answer = answer; }
        public int getSortOrder() { return sortOrder; }
        public void setSortOrder(int sortOrder) { this.sortOrder = sortOrder; }
    }

    public static class TagDto {
        @JsonProperty("id")
        private String id;

        @JsonProperty("name")
        private String name;

        @JsonProperty("slug")
        private String slug;

        @JsonProperty("icon")
        private String icon;

        @JsonProperty("color")
        private String color;

        @JsonProperty("description")
        private String description;

        public TagDto() {
        }

        public TagDto(String id, String name, String slug, String icon, String color, String description) {
            this.id = id;
            this.name = name;
            this.slug = slug;
            this.icon = icon;
            this.color = color;
            this.description = description;
        }

        public String getId() { return id; }
        public void setId(String id) { this.id = id; }
        public String getName() { return name; }
        public void setName(String name) { this.name = name; }
        public String getSlug() { return slug; }
        public void setSlug(String slug) { this.slug = slug; }
        public String getIcon() { return icon; }
        public void setIcon(String icon) { this.icon = icon; }
        public String getColor() { return color; }
        public void setColor(String color) { this.color = color; }
        public String getDescription() { return description; }
        public void setDescription(String description) { this.description = description; }
    }
}
