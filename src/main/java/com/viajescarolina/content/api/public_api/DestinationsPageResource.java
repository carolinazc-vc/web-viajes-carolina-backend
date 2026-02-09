package com.viajescarolina.content.api.public_api;

import com.viajescarolina.content.api.dto.destinations.DestinationDetailResponse;
import com.viajescarolina.content.api.dto.destinations.DestinationsPageDtoMapper;
import com.viajescarolina.content.api.dto.destinations.DestinationsPageResponse;
import com.viajescarolina.content.app.DestinationsPageService;
import com.viajescarolina.content.domain.home.HeroSection;
import com.viajescarolina.destinations.domain.Destination;
import com.viajescarolina.destinations.domain.DestinationCategory;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;
import java.util.Optional;

/**
 * Resource REST que expone los endpoints públicos de la página de Destinos.
 */
@Path("/api/pages/destinations")
@Produces(MediaType.APPLICATION_JSON)
public class DestinationsPageResource {

    @Inject
    DestinationsPageService destinationsPageService;

    /**
     * Obtiene la página de destinos con hero, categorías y lista paginada.
     *
     * @param page número de página (1-based para el usuario, internamente 0-based)
     * @param size tamaño de página
     * @param category slug de categoría para filtrar (opcional)
     * @param search texto de búsqueda (opcional)
     * @return DestinationsPageResponse
     */
    @GET
    public Response getDestinationsPage(
            @QueryParam("page") Integer page,
            @QueryParam("size") Integer size,
            @QueryParam("category") String category,
            @QueryParam("search") String search) {

        // Valores por defecto
        int pageNumber = (page != null && page > 0) ? page - 1 : 0; // Convertir a 0-based
        int pageSize = (size != null && size > 0) ? size : destinationsPageService.getDefaultPageSize();

        // Obtener hero
        HeroSection hero = destinationsPageService.getHero();

        // Obtener categorías
        List<DestinationCategory> categories = destinationsPageService.getCategories();

        // Obtener destinos según filtros
        List<Destination> destinations;
        long total;

        if (search != null && !search.trim().isEmpty()) {
            // Búsqueda por texto
            destinations = destinationsPageService.searchDestinations(search.trim());
            total = destinations.size();
        } else if (category != null && !category.trim().isEmpty()) {
            // Filtrar por categoría
            destinations = destinationsPageService.getDestinationsByCategory(category.trim(), pageNumber, pageSize);
            total = destinationsPageService.getTotalByCategory(category.trim());
        } else {
            // Todos los destinos
            destinations = destinationsPageService.getDestinations(pageNumber, pageSize);
            total = destinationsPageService.getTotalDestinations();
        }

        // Construir respuesta
        DestinationsPageResponse response = DestinationsPageDtoMapper.toPageResponse(
                hero,
                categories,
                destinations,
                pageNumber + 1, // Devolver 1-based al usuario
                pageSize,
                total
        );

        return Response.ok(response).build();
    }

    /**
     * Obtiene el detalle de un destino por su slug.
     *
     * @param slug identificador único del destino
     * @return DestinationDetailResponse o 404
     */
    @GET
    @Path("/{slug}")
    public Response getDestinationDetail(@PathParam("slug") String slug) {
        Optional<Destination> destinationOpt = destinationsPageService.getDestinationBySlug(slug);

        if (destinationOpt.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(new ErrorResponse("Destino no encontrado", 404))
                    .build();
        }

        Destination destination = destinationOpt.get();

        // Obtener destinos relacionados (misma categoría)
        List<Destination> relatedDestinations = destinationsPageService.getRelatedDestinations(destination, 4);

        // Construir respuesta
        DestinationDetailResponse response = DestinationsPageDtoMapper.toDetailResponse(
                destination,
                relatedDestinations
        );

        return Response.ok(response).build();
    }

    /**
     * Obtiene solo las categorías de destinos.
     *
     * @return Lista de categorías con conteo
     */
    @GET
    @Path("/categories")
    public Response getCategories() {
        List<DestinationCategory> categories = destinationsPageService.getCategories();

        List<DestinationsPageResponse.CategoryDto> categoryDtos = categories.stream()
                .map(cat -> new DestinationsPageResponse.CategoryDto(
                        cat.getId(),
                        cat.getName(),
                        cat.getSlug(),
                        cat.getCount()
                ))
                .toList();

        return Response.ok(categoryDtos).build();
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
