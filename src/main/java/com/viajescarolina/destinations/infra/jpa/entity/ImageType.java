package com.viajescarolina.destinations.infra.jpa.entity;

/**
 * Tipos de imagen para destinos.
 */
public enum ImageType {
    HEADER("Imagen de cabecera"),
    GALLERY("Galería"),
    INLINE("Dentro del contenido"),
    THUMBNAIL("Miniatura");

    private final String displayName;

    ImageType(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
