package com.viajescarolina.content.api.dto.destinations;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * DTO de respuesta para la página de destinos.
 */
public class DestinationsPageResponse {

    @JsonProperty("hero")
    private HeroDto hero;

    @JsonProperty("categories")
    private List<CategoryDto> categories;

    @JsonProperty("destinations")
    private List<DestinationItemDto> destinations;

    @JsonProperty("pagination")
    private PaginationDto pagination;

    public DestinationsPageResponse() {
    }

    // Getters y Setters
    public HeroDto getHero() {
        return hero;
    }

    public void setHero(HeroDto hero) {
        this.hero = hero;
    }

    public List<CategoryDto> getCategories() {
        return categories;
    }

    public void setCategories(List<CategoryDto> categories) {
        this.categories = categories;
    }

    public List<DestinationItemDto> getDestinations() {
        return destinations;
    }

    public void setDestinations(List<DestinationItemDto> destinations) {
        this.destinations = destinations;
    }

    public PaginationDto getPagination() {
        return pagination;
    }

    public void setPagination(PaginationDto pagination) {
        this.pagination = pagination;
    }

    // DTOs internas
    public static class HeroDto {
        @JsonProperty("badge")
        private BadgeDto badge;

        @JsonProperty("title")
        private TitleDto title;

        @JsonProperty("subtitle")
        private String subtitle;

        @JsonProperty("image")
        private ImageDto image;

        public HeroDto() {
        }

        public BadgeDto getBadge() {
            return badge;
        }

        public void setBadge(BadgeDto badge) {
            this.badge = badge;
        }

        public TitleDto getTitle() {
            return title;
        }

        public void setTitle(TitleDto title) {
            this.title = title;
        }

        public String getSubtitle() {
            return subtitle;
        }

        public void setSubtitle(String subtitle) {
            this.subtitle = subtitle;
        }

        public ImageDto getImage() {
            return image;
        }

        public void setImage(ImageDto image) {
            this.image = image;
        }
    }

    public static class BadgeDto {
        @JsonProperty("icon")
        private String icon;

        @JsonProperty("text")
        private String text;

        public BadgeDto() {
        }

        public BadgeDto(String icon, String text) {
            this.icon = icon;
            this.text = text;
        }

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }
    }

    public static class TitleDto {
        @JsonProperty("line1")
        private String line1;

        @JsonProperty("line2")
        private String line2;

        public TitleDto() {
        }

        public TitleDto(String line1, String line2) {
            this.line1 = line1;
            this.line2 = line2;
        }

        public String getLine1() {
            return line1;
        }

        public void setLine1(String line1) {
            this.line1 = line1;
        }

        public String getLine2() {
            return line2;
        }

        public void setLine2(String line2) {
            this.line2 = line2;
        }
    }

    public static class ImageDto {
        @JsonProperty("url")
        private String url;

        @JsonProperty("alt")
        private String alt;

        public ImageDto() {
        }

        public ImageDto(String url, String alt) {
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

    public static class CategoryDto {
        @JsonProperty("id")
        private String id;

        @JsonProperty("name")
        private String name;

        @JsonProperty("slug")
        private String slug;

        @JsonProperty("count")
        private long count;

        public CategoryDto() {
        }

        public CategoryDto(String id, String name, String slug, long count) {
            this.id = id;
            this.name = name;
            this.slug = slug;
            this.count = count;
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

        public long getCount() {
            return count;
        }

        public void setCount(long count) {
            this.count = count;
        }
    }

    public static class DestinationItemDto {
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

        @JsonProperty("summaryExcerpt")
        private String summaryExcerpt;

        @JsonProperty("summaryBadge")
        private String summaryBadge;

        @JsonProperty("imageUrl")
        private String imageUrl;

        @JsonProperty("category")
        private CategoryDto category;

        public DestinationItemDto() {
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

        public String getImageUrl() {
            return imageUrl;
        }

        public void setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
        }

        public CategoryDto getCategory() {
            return category;
        }

        public void setCategory(CategoryDto category) {
            this.category = category;
        }
    }

    public static class PaginationDto {
        @JsonProperty("page")
        private int page;

        @JsonProperty("size")
        private int size;

        @JsonProperty("total")
        private long total;

        @JsonProperty("totalPages")
        private int totalPages;

        public PaginationDto() {
        }

        public PaginationDto(int page, int size, long total) {
            this.page = page;
            this.size = size;
            this.total = total;
            this.totalPages = (int) Math.ceil((double) total / size);
        }

        public int getPage() {
            return page;
        }

        public void setPage(int page) {
            this.page = page;
        }

        public int getSize() {
            return size;
        }

        public void setSize(int size) {
            this.size = size;
        }

        public long getTotal() {
            return total;
        }

        public void setTotal(long total) {
            this.total = total;
        }

        public int getTotalPages() {
            return totalPages;
        }

        public void setTotalPages(int totalPages) {
            this.totalPages = totalPages;
        }
    }
}
