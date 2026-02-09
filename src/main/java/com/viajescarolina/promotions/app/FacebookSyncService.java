package com.viajescarolina.promotions.app;

import com.viajescarolina.promotions.domain.FacebookPost;
import com.viajescarolina.promotions.domain.port.FacebookClient;
import com.viajescarolina.promotions.infra.jpa.PromotionRepository;
import com.viajescarolina.promotions.infra.jpa.entity.PromotionEntity;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.jboss.logging.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * Servicio de sincronización de publicaciones de Facebook a la base de datos.
 * Convierte posts de Facebook en promociones almacenadas en BD.
 */
@ApplicationScoped
public class FacebookSyncService {

    private static final Logger logger = Logger.getLogger(FacebookSyncService.class);

    @Inject
    FacebookClient facebookClient;

    @Inject
    PromotionRepository promotionRepository;

    /**
     * Sincroniza nuevas publicaciones de Facebook con la BD.
     * Solo guarda aquellas que no existen aún en la BD.
     *
     * @param limit número máximo de posts a sincronizar
     * @return SyncResult con información del proceso
     */
    @Transactional
    public SyncResult syncNewFacebookPostsToDatabase(int limit) {
        SyncResult result = new SyncResult();

        try {
            // Obtener publicaciones de Facebook
            List<FacebookPost> facebookPosts = facebookClient.getFeedPosts(limit);
            result.totalFacebookPosts = facebookPosts.size();

            // Procesar cada post
            for (FacebookPost post : facebookPosts) {
                // Verificar si ya existe
                if (promotionRepository.findByFacebookId(post.getId()).isEmpty()) {
                    // Crear nueva promoción
                    PromotionEntity promotion = convertFacebookPostToPromotion(post);
                    promotionRepository.persist(promotion);
                    result.successfulSaves++;
                    result.savedIds.add(post.getId());
                } else {
                    result.alreadyExists++;
                }
            }

            result.status = "SUCCESS";
            result.message = String.format(
                "Sincronización completada: %d nuevas, %d ya existentes",
                result.successfulSaves,
                result.alreadyExists
            );

        } catch (Exception e) {
            result.status = "ERROR";
            result.message = "Error durante sincronización: " + e.getMessage();
            logger.error("Error en syncNewFacebookPostsToDatabase: " + e.getMessage(), e);
        }

        return result;
    }

    /**
     * Convierte un post de Facebook en una entidad PromotionEntity.
     */
    private PromotionEntity convertFacebookPostToPromotion(FacebookPost post) {
        PromotionEntity promotion = new PromotionEntity();

        // Generar slug único a partir del ID de Facebook (máx 255 caracteres)
        String baseSlug = "fb_" + post.getId().replaceAll("[_-]", "");
        promotion.slug = baseSlug.length() > 255 ? baseSlug.substring(0, 255) : baseSlug;

        // Usar el mensaje como título (primeras 500 caracteres)
        String title = extractTitle(post.getMessage());
        promotion.title = truncate(title, 500);

        // Usar el mensaje completo como descripción (hasta 1000 caracteres)
        promotion.shortDescription = truncate(post.getMessage(), 1000);
        promotion.longDescription = post.getMessage();

        // Imagen
        promotion.mainImageUrl = post.getFullPicture();

        // Badge y metadata
        promotion.badgeText = "Facebook";
        promotion.badgeType = "info";
        promotion.validityLabel = "Vigente";

        // Precios no disponibles desde Facebook
        promotion.priceFrom = null;
        promotion.currency = null;

        // Configuración para mostrar
        promotion.isFeatured = true;           // Mostrar en destacados
        promotion.status = "PUBLISHED";        // Publicado inmediatamente

        // Fechas
        java.time.ZonedDateTime createdTime = post.getCreatedTime();
        promotion.validFrom = createdTime.toLocalDate();
        promotion.validTo = createdTime.toLocalDate().plusDays(30); // Válido por 30 días

        return promotion;
    }

    /**
     * Extrae un título del mensaje de Facebook.
     */
    private String extractTitle(String message) {
        if (message == null || message.isEmpty()) {
            return "Promoción de Facebook";
        }

        // Tomar primeros 50 caracteres o hasta el primer salto de línea
        int endIndex = Math.min(50, message.indexOf('\n') > 0 ? message.indexOf('\n') : message.length());
        return message.substring(0, endIndex).trim();
    }

    /**
     * Trunca un texto a una longitud máxima.
     * @param text texto a truncar
     * @param maxLength longitud máxima
     * @return texto truncado con "..." al final si fue truncado
     */
    private String truncate(String text, int maxLength) {
        if (text == null) {
            return "";
        }
        if (text.length() <= maxLength) {
            return text;
        }
        return text.substring(0, maxLength - 3) + "...";
    }

    /**
     * DTO para el resultado de la sincronización.
     */
    public static class SyncResult {
        public String status;                    // SUCCESS, ERROR, PENDING
        public String message;                   // Mensaje descriptivo
        public int totalFacebookPosts;           // Total de posts obtenidos
        public int successfulSaves;              // Posts guardados exitosamente
        public int alreadyExists;                // Posts que ya existían
        public List<String> savedIds = new ArrayList<>();  // IDs de posts guardados

        @Override
        public String toString() {
            return "SyncResult{" +
                    "status='" + status + '\'' +
                    ", message='" + message + '\'' +
                    ", totalFacebookPosts=" + totalFacebookPosts +
                    ", successfulSaves=" + successfulSaves +
                    ", alreadyExists=" + alreadyExists +
                    ", savedIds=" + savedIds +
                    '}';
        }
    }
}

