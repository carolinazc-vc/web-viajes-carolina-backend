package com.viajescarolina.destinations.domain;

/**
 * Modelo de dominio para categoría de destino.
 */
public class DestinationCategory {
    private String id;
    private String name;
    private String slug;
    private String description;
    private long count; // Cantidad de destinos en esta categoría

    public DestinationCategory() {
    }

    public DestinationCategory(String id, String name, String slug, String description) {
        this.id = id;
        this.name = name;
        this.slug = slug;
        this.description = description;
    }

    public DestinationCategory(String id, String name, String slug, String description, long count) {
        this.id = id;
        this.name = name;
        this.slug = slug;
        this.description = description;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }
}
