package com.viajescarolina.destinations.infra.jpa.entity;

/**
 * Tipos de sección para contenido de destino tipo blog.
 */
public enum SectionType {
    INTRO("Introducción"),
    HIGHLIGHTS("Qué ver y hacer"),
    TIPS("Consejos de viaje"),
    GALLERY("Galería de fotos"),
    FAQ("Preguntas frecuentes"),
    WHEN_TO_VISIT("Mejor época para visitar"),
    HOW_TO_GET("Cómo llegar"),
    WHERE_TO_STAY("Dónde alojarse"),
    GASTRONOMY("Gastronomía"),
    CULTURE("Cultura y tradiciones"),
    ITINERARY("Itinerario"),
    INCLUDES("Qué incluye"),
    WHAT_TO_PACK("Qué llevar"),
    SAFETY("Seguridad"),
    CUSTOM("Sección personalizada");

    private final String displayName;

    SectionType(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
