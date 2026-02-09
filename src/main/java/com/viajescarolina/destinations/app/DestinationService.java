package com.viajescarolina.destinations.app;

import com.viajescarolina.content.domain.home.TopDestinationsBlock;
import com.viajescarolina.destinations.domain.Destination;
import com.viajescarolina.destinations.domain.DestinationCategory;

import java.util.List;
import java.util.Optional;

/**
 * Servicio de aplicación para destinos.
 */
public interface DestinationService {

    /**
     * Obtiene los destinos recomendados para mostrar en el home.
     * @param limit número máximo de destinos a retornar
     * @return TopDestinationsBlock con los destinos recomendados
     */
    TopDestinationsBlock getRecommendedForHome(int limit);

    /**
     * Obtiene todos los destinos activos.
     * @return Lista de destinos
     */
    List<Destination> getAllActive();

    /**
     * Obtiene destinos activos con paginación.
     * @param page número de página (0-based)
     * @param size tamaño de página
     * @return Lista de destinos
     */
    List<Destination> getAllActivePaginated(int page, int size);

    /**
     * Obtiene un destino por su slug.
     * @param slug identificador único del destino
     * @return Optional con el destino si existe
     */
    Optional<Destination> getBySlug(String slug);

    /**
     * Obtiene destinos por categoría.
     * @param categorySlug slug de la categoría
     * @return Lista de destinos
     */
    List<Destination> getByCategorySlug(String categorySlug);

    /**
     * Obtiene destinos por categoría con paginación.
     * @param categorySlug slug de la categoría
     * @param page número de página (0-based)
     * @param size tamaño de página
     * @return Lista de destinos
     */
    List<Destination> getByCategorySlugPaginated(String categorySlug, int page, int size);

    /**
     * Obtiene todas las categorías con conteo de destinos.
     * @return Lista de categorías con count
     */
    List<DestinationCategory> getAllCategoriesWithCount();

    /**
     * Cuenta el total de destinos activos.
     * @return cantidad de destinos
     */
    long countActive();

    /**
     * Cuenta destinos por categoría.
     * @param categorySlug slug de la categoría
     * @return cantidad de destinos
     */
    long countByCategorySlug(String categorySlug);

    /**
     * Busca destinos por texto.
     * @param query texto de búsqueda
     * @return Lista de destinos
     */
    List<Destination> search(String query);
}

