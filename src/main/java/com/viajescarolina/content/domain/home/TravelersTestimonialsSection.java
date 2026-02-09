package com.viajescarolina.content.domain.home;

import java.util.ArrayList;
import java.util.List;

/**
 * Representa la sección de testimonios de viajeros con fotos.
 * Muestra experiencias visuales de clientes en diferentes destinos.
 */
public class TravelersTestimonialsSection {
    private String id;
    private String title;
    private String subtitle;
    private List<TravelerTestimonialItem> items;

    public TravelersTestimonialsSection() {
        this.items = new ArrayList<>();
    }

    public TravelersTestimonialsSection(String id, String title, String subtitle, List<TravelerTestimonialItem> items) {
        this.id = id;
        this.title = title;
        this.subtitle = subtitle;
        this.items = items != null ? items : new ArrayList<>();
    }

    // Getters y Setters
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

    public List<TravelerTestimonialItem> getItems() {
        return items;
    }

    public void setItems(List<TravelerTestimonialItem> items) {
        this.items = items;
    }

    /**
     * Representa un item individual de testimonio de viajero.
     */
    public static class TravelerTestimonialItem {
        private String id;
        private String imageUrl;
        private String imageAlt;
        private String customerName;
        private String shortQuote;
        private String destination;

        public TravelerTestimonialItem() {
        }

        public TravelerTestimonialItem(String id, String imageUrl, String imageAlt,
                                       String customerName, String shortQuote, String destination) {
            this.id = id;
            this.imageUrl = imageUrl;
            this.imageAlt = imageAlt;
            this.customerName = customerName;
            this.shortQuote = shortQuote;
            this.destination = destination;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getImageUrl() {
            return imageUrl;
        }

        public void setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
        }

        public String getImageAlt() {
            return imageAlt;
        }

        public void setImageAlt(String imageAlt) {
            this.imageAlt = imageAlt;
        }

        public String getCustomerName() {
            return customerName;
        }

        public void setCustomerName(String customerName) {
            this.customerName = customerName;
        }

        public String getShortQuote() {
            return shortQuote;
        }

        public void setShortQuote(String shortQuote) {
            this.shortQuote = shortQuote;
        }

        public String getDestination() {
            return destination;
        }

        public void setDestination(String destination) {
            this.destination = destination;
        }
    }
}
