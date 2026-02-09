package com.viajescarolina.content.infra.storage;

import jakarta.enterprise.context.ApplicationScoped;

/**
 * Servicio para integración con Google Cloud Storage.
 * Implementación actual: placeholder.
 * En producción, se integraría con Google Cloud Storage SDK.
 */
@ApplicationScoped
public class GcpStorageService {

    /**
     * Sube una imagen a Google Cloud Storage.
     */
    public void uploadImage(String path, byte[] imageData, String contentType) {
        // TODO: Implementar integración con GCP Cloud Storage SDK
        // Usar google-cloud-storage library
        throw new UnsupportedOperationException("GCP Storage no está configurado. Use almacenamiento local para desarrollo.");
    }

    /**
     * Descarga una imagen de Google Cloud Storage.
     */
    public byte[] downloadImage(String path) {
        // TODO: Implementar integración con GCP Cloud Storage SDK
        throw new UnsupportedOperationException("GCP Storage no está configurado. Use almacenamiento local para desarrollo.");
    }

    /**
     * Obtiene la URL pública de una imagen en GCP.
     */
    public String getImageUrl(String imageId) {
        // TODO: Implementar integración con GCP Cloud Storage SDK
        throw new UnsupportedOperationException("GCP Storage no está configurado.");
    }
}

