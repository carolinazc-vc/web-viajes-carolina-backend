package com.viajescarolina.promotions.api;

import com.viajescarolina.promotions.api.dto.PromotionDto;
import com.viajescarolina.promotions.api.dto.PromotionMapper;
import com.viajescarolina.promotions.app.PromotionService;
import com.viajescarolina.promotions.app.FacebookSyncService;
import com.viajescarolina.promotions.infra.jpa.PromotionRepository;
import com.viajescarolina.promotions.infra.jpa.entity.PromotionEntity;
import com.viajescarolina.content.domain.home.PromotionsBlock;
import com.viajescarolina.content.domain.home.PromotionsBlock.PromotionSummary;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Resource REST que expone los endpoints públicos de promociones.
 */
@Path("/api/promotions")
@Produces(MediaType.APPLICATION_JSON)
public class PromotionResource {

    @Inject
    PromotionService promotionService;

    @Inject
    FacebookSyncService facebookSyncService;

    @Inject
    PromotionRepository promotionRepository;

    /**
     * Obtiene todas las promociones con todos los campos (para web).
     * Retorna información completa de cada promoción.
     *
     * @param limit número máximo de promociones (por defecto 20)
     * @param offset offset para paginación (por defecto 0)
     * @param status filtro por estado (por defecto PUBLISHED)
     * @return Lista de promociones con todos los campos
     */
    @GET
    @Path("/all")
    public Response getAllPromotionsFullDetails(
            @QueryParam("limit") Integer limit,
            @QueryParam("offset") Integer offset,
            @QueryParam("status") String status) {

        if (limit == null || limit <= 0) {
            limit = 20;
        }
        if (offset == null || offset < 0) {
            offset = 0;
        }
        if (status == null || status.isEmpty()) {
            status = "PUBLISHED";
        }

        List<PromotionEntity> promotions = promotionRepository.findByStatusOrderByValidFromDesc(status);

        List<PromotionDto> promotionDtos = promotions.stream()
                .skip(offset)
                .limit(limit)
                .map(PromotionMapper::toDtoFull)
                .collect(Collectors.toList());

        return Response.ok(new AllPromotionsResponse(
                promotionDtos,
                offset,
                limit,
                promotions.size()
        )).build();
    }

    /**
     * Obtiene una promoción específica con todos los detalles.
     *
     * @param promotionId ID de la promoción
     * @return Promoción con todos los campos o 404
     */
    @GET
    @Path("/detail/{promotionId}")
    public Response getPromotionFullDetail(@jakarta.ws.rs.PathParam("promotionId") Long promotionId) {
        PromotionEntity promotion = promotionRepository.findById(promotionId);

        if (promotion == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(new ErrorResponse("Promoción no encontrada", 404))
                    .build();
        }

        PromotionDto dto = PromotionMapper.toDtoFull(promotion);
        return Response.ok(dto).build();
    }

    /**
     * Obtiene las promociones activas (válidas hoy) con todos los detalles.
     *
     * @param limit número máximo de promociones (por defecto 10)
     * @return Lista de promociones activas
     */
    @GET
    @Path("/active")
    public Response getActivePromotions(@QueryParam("limit") Integer limit) {
        if (limit == null || limit <= 0) {
            limit = 10;
        }

        List<PromotionEntity> activePromotions = promotionRepository.findActivePromotions(limit);

        List<PromotionDto> promotionDtos = activePromotions.stream()
                .map(PromotionMapper::toDtoFull)
                .collect(Collectors.toList());

        return Response.ok(promotionDtos).build();
    }

    /**
     * Obtiene las promociones destacadas con todos los detalles.
     *
     * @param limit número máximo de promociones (por defecto 10)
     * @return Lista de promociones destacadas
     */
    @GET
    @Path("/featured-full")
    public Response getFeaturedPromotionsFull(@QueryParam("limit") Integer limit) {
        if (limit == null || limit <= 0) {
            limit = 10;
        }

        List<PromotionEntity> featured = promotionRepository.findByIsFeaturedAndStatusOrderByUpdatedAtDesc(true, "PUBLISHED");

        List<PromotionDto> promotionDtos = featured.stream()
                .limit(limit)
                .map(PromotionMapper::toDtoFull)
                .collect(Collectors.toList());

        return Response.ok(promotionDtos).build();
    }

    // ...existing code...

    /**
     * Sincroniza nuevas publicaciones de Facebook con la base de datos.
     * Solo guarda aquellas que no existen aún.
     *
     * @param limit número máximo de posts a sincronizar (por defecto 10)
     * @return SyncResult con información del proceso
     */
    @POST
    @Path("/sync-facebook")
    public Response syncFacebookPosts(@QueryParam("limit") Integer limit) {
        if (limit == null || limit <= 0) {
            limit = 10;
        }

        FacebookSyncService.SyncResult result = facebookSyncService.syncNewFacebookPostsToDatabase(limit);
        return Response.ok(result).build();
    }

    /**
     * Obtiene una promoción específica por ID.
     *
     * @param promotionId ID de la promoción
     * @return Promoción encontrada o 404
     */
    @GET
    @Path("/{promotionId}")
    @SuppressWarnings("unused")
    public Response getPromotionById(@jakarta.ws.rs.PathParam("promotionId") String promotionId) {
        // Esta funcionalidad podría extenderse en el futuro
        // Por ahora, retornamos un error 501 (Not Implemented)
        return Response.status(Response.Status.NOT_IMPLEMENTED)
                .entity(new ErrorResponse("Not implemented yet", 501))
                .build();
    }

    /**
     * DTO para la respuesta de todas las promociones con detalles completos.
     */
    public static class AllPromotionsResponse {
        public List<PromotionDto> promotions;
        public int offset;
        public int limit;
        public int total;

        public AllPromotionsResponse(List<PromotionDto> promotions, int offset, int limit, int total) {
            this.promotions = promotions;
            this.offset = offset;
            this.limit = limit;
            this.total = total;
        }
    }

    /**
     * DTO para la respuesta de todas las promociones.
     */
    public static class PromotionsResponse {
        public List<PromotionSummary> promotions;
        public int offset;
        public int limit;
        public int total;
        public String title;
        public String subtitle;

        public PromotionsResponse(List<PromotionSummary> promotions, int offset, int limit,
                                 int total, String title, String subtitle) {
            this.promotions = promotions;
            this.offset = offset;
            this.limit = limit;
            this.total = total;
            this.title = title;
            this.subtitle = subtitle;
        }
    }

    /**
     * DTO para respuestas de error.
     */
    public static class ErrorResponse {
        public String message;
        public int code;

        public ErrorResponse(String message, int code) {
            this.message = message;
            this.code = code;
        }
    }
}

