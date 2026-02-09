package com.viajescarolina.content.api;

import com.viajescarolina.content.infra.storage.ImageStorageService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.jboss.logging.Logger;
import org.jboss.resteasy.reactive.RestForm;
import org.jboss.resteasy.reactive.multipart.FileUpload;

import java.io.IOException;

/**
 * API REST para gestionar testimonios con imágenes.
 */
@Path("/api/testimonials")
@Consumes(MediaType.MULTIPART_FORM_DATA)
@Produces(MediaType.APPLICATION_JSON)
public class TestimonialResource {

    private static final Logger logger = Logger.getLogger(TestimonialResource.class);

    @Inject
    ImageStorageService imageStorageService;

    /**
     * Sube una imagen de testimonial.
     * POST /api/testimonials/upload
     */
    @POST
    @Path("/upload")
    public Response uploadTestimonialImage(
            @RestForm("image") FileUpload image,
            @RestForm("quote") String quote,
            @RestForm("author") String author,
            @RestForm("role") String role,
            @RestForm("years") String years) {
        try {
            // Validar entrada
            if (image == null) {
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity("{\"error\": \"Imagen no proporcionada\"}")
                        .build();
            }

            if (quote == null || quote.isEmpty()) {
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity("{\"error\": \"Quote es requerido\"}")
                        .build();
            }

            // Leer datos de la imagen desde FileUpload
            java.nio.file.Path filePath = image.uploadedFile();
            byte[] imageData = java.nio.file.Files.exists(filePath) && java.nio.file.Files.size(filePath) > 0
                ? java.nio.file.Files.readAllBytes(filePath)
                : new byte[0];

            if (imageData.length == 0) {
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity("{\"error\": \"Archivo de imagen vacío\"}")
                        .build();
            }

            String filename = image.fileName();

            // Guardar imagen (usar false para desarrollo con almacenamiento local)
            String imageId = imageStorageService.saveImage(imageData, filename, false);

            logger.info("Imagen guardada con ID: " + imageId);

            // TODO: Guardar testimonial en BD con imageId

            return Response.ok()
                    .entity(new UploadResponse(imageId, quote, author, role, years))
                    .build();

        } catch (IOException e) {
            logger.error("Error al guardar imagen: " + e.getMessage(), e);
            return Response.serverError()
                    .entity("{\"error\": \"Error al guardar imagen\"}")
                    .build();
        } catch (Exception e) {
            logger.error("Error inesperado: " + e.getMessage(), e);
            return Response.serverError()
                    .entity("{\"error\": \"" + e.getMessage() + "\"}")
                    .build();
        }
    }

    /**
     * Response después de guardar testimonial.
     */
    public static class UploadResponse {
        public String imageId;
        public String quote;
        public String author;
        public String role;
        public String years;
        public String message = "Testimonio guardado exitosamente";

        public UploadResponse(String imageId, String quote, String author, String role, String years) {
            this.imageId = imageId;
            this.quote = quote;
            this.author = author;
            this.role = role;
            this.years = years;
        }
    }
}

