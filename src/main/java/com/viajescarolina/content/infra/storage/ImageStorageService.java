package com.viajescarolina.content.infra.storage;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.jboss.logging.Logger;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.UUID;

/**
 * Servicio para almacenar y recuperar imágenes.
 * Soporta almacenamiento local para desarrollo y GCP para producción.
 */
@ApplicationScoped
public class ImageStorageService {

    private static final Logger logger = Logger.getLogger(ImageStorageService.class);
    private static final String LOCAL_STORAGE_PATH = "storage/testimonials";
    private static final String ALLOWED_EXTENSIONS = "jpg,jpeg,png,webp";

    @Inject
    GcpStorageService gcpStorageService;

    /**
     * Guarda una imagen y devuelve el ID único (UUID).
     * Para desarrollo: se guarda localmente.
     * Para producción: se guarda en GCP Cloud Storage.
     */
    public String saveImage(byte[] imageData, String originalFilename, boolean useGcp) {
        String imageId = UUID.randomUUID().toString();

        try {
            // Validar extensión
            String extension = getFileExtension(originalFilename);
            if (!isAllowedExtension(extension)) {
                throw new IllegalArgumentException("Extensión de archivo no permitida: " + extension);
            }

            if (useGcp) {
                // Guardar en GCP
                String gcpPath = "testimonials/" + imageId + "." + extension;
                gcpStorageService.uploadImage(gcpPath, imageData, getContentType(extension));
                logger.info("Imagen guardada en GCP: " + gcpPath);
            } else {
                // Guardar localmente
                saveLocalImage(imageId, imageData, extension);
                logger.info("Imagen guardada localmente: " + imageId + "." + extension);
            }

            return imageId;
        } catch (Exception e) {
            logger.error("Error al guardar imagen: " + e.getMessage(), e);
            throw new RuntimeException("Error al guardar imagen", e);
        }
    }

    /**
     * Obtiene una imagen como Base64.
     * Para desarrollo: la lee del almacenamiento local.
     * Para producción: la descarga de GCP.
     */
    public String getImageAsBase64(String imageId, boolean useGcp) {
        try {
            byte[] imageData;

            if (useGcp) {
                imageData = gcpStorageService.downloadImage("testimonials/" + imageId);
                logger.info("Imagen descargada de GCP: " + imageId);
            } else {
                imageData = getLocalImage(imageId);
                logger.info("Imagen leída localmente: " + imageId);
            }

            if (imageData == null || imageData.length == 0) {
                return null;
            }

            return Base64.getEncoder().encodeToString(imageData);
        } catch (Exception e) {
            logger.error("Error al obtener imagen: " + e.getMessage(), e);
            return null;
        }
    }

    /**
     * Obtiene la URL de una imagen (para acceso directo).
     */
    public String getImageUrl(String imageId, boolean useGcp) {
        if (useGcp) {
            return gcpStorageService.getImageUrl(imageId);
        } else {
            return "/api/images/" + imageId;
        }
    }

    /**
     * Obtiene una imagen local como bytes.
     */
    public byte[] getLocalImage(String imageId) throws IOException {
        // Buscar archivo con cualquier extensión permitida
        for (String ext : ALLOWED_EXTENSIONS.split(",")) {
            Path imagePath = Paths.get(LOCAL_STORAGE_PATH, imageId + "." + ext.trim());
            if (Files.exists(imagePath)) {
                return Files.readAllBytes(imagePath);
            }
        }
        return null;
    }

    // ===================== Métodos privados =====================

    private void saveLocalImage(String imageId, byte[] imageData, String extension) throws IOException {
        Path storagePath = Paths.get(LOCAL_STORAGE_PATH);

        // Crear directorio si no existe
        if (!Files.exists(storagePath)) {
            Files.createDirectories(storagePath);
        }

        Path imagePath = storagePath.resolve(imageId + "." + extension);
        Files.write(imagePath, imageData);
    }

    private String getFileExtension(String filename) {
        if (filename == null || !filename.contains(".")) {
            return "";
        }
        return filename.substring(filename.lastIndexOf(".") + 1).toLowerCase();
    }

    private boolean isAllowedExtension(String extension) {
        String[] allowed = ALLOWED_EXTENSIONS.split(",");
        for (String ext : allowed) {
            if (ext.trim().equalsIgnoreCase(extension)) {
                return true;
            }
        }
        return false;
    }

    private String getContentType(String extension) {
        return switch (extension.toLowerCase()) {
            case "jpg", "jpeg" -> "image/jpeg";
            case "png" -> "image/png";
            case "webp" -> "image/webp";
            default -> "application/octet-stream";
        };
    }
}

