package com.viajescarolina.content.domain.home;

import java.util.List;

/**
 * Representa el bloque de destinos top recomendados en el home.
 */
public class TopDestinationsBlock {
    private String title;
    private String subtitle;
    private List<DestinationSummary> items;

    public TopDestinationsBlock() {
    }

    public TopDestinationsBlock(String title, String subtitle, List<DestinationSummary> items) {
        this.title = title;
        this.subtitle = subtitle;
        this.items = items;
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

    public List<DestinationSummary> getItems() {
        return items;
    }

    public void setItems(List<DestinationSummary> items) {
        this.items = items;
    }

    public static class DestinationSummary {
        private String id;
        private String name;
        private String description;
        private String imageUrl;
        private String link;

        public DestinationSummary() {
        }

        public DestinationSummary(String id, String name, String description, String imageUrl, String link) {
            this.id = id;
            this.name = name;
            this.description = description;
            this.imageUrl = imageUrl;
            this.link = link;
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

        public String getLink() {
            return link;
        }

        public void setLink(String link) {
            this.link = link;
        }
    }
}

