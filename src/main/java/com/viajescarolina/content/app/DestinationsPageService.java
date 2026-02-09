package com.viajescarolina.content.app;

import com.viajescarolina.content.domain.home.HeroSection;
import com.viajescarolina.content.domain.port.PageHeroRepository;
import com.viajescarolina.destinations.app.DestinationService;
import com.viajescarolina.destinations.domain.Destination;
import com.viajescarolina.destinations.domain.DestinationCategory;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Servicio de aplicación que orquesta la construcción de la página de Destinos.
 */
@ApplicationScoped
public class DestinationsPageService {

    private static final int DEFAULT_PAGE_SIZE = 12;

    @Inject
    PageHeroRepository pageHeroRepository;

    @Inject
    DestinationService destinationService;

    /**
     * Obtiene el hero de la página de destinos.
     */
    public HeroSection getHero() {
        return pageHeroRepository.findHeroByPageSlug("destinations").orElse(null);
    }

    /**
     * Obtiene todas las categorías con conteo.
     */
    public List<DestinationCategory> getCategories() {
        return destinationService.getAllCategoriesWithCount();
    }

    /**
     * Obtiene destinos paginados.
     */
    public List<Destination> getDestinations(int page, int size) {
        return destinationService.getAllActivePaginated(page, size);
    }

    /**
     * Obtiene destinos filtrados por categoría.
     */
    public List<Destination> getDestinationsByCategory(String categorySlug, int page, int size) {
        return destinationService.getByCategorySlugPaginated(categorySlug, page, size);
    }

    /**
     * Obtiene el total de destinos activos.
     */
    public long getTotalDestinations() {
        return destinationService.countActive();
    }

    /**
     * Obtiene el total de destinos por categoría.
     */
    public long getTotalByCategory(String categorySlug) {
        return destinationService.countByCategorySlug(categorySlug);
    }

    /**
     * Obtiene un destino por su slug.
     */
    public Optional<Destination> getDestinationBySlug(String slug) {
        return destinationService.getBySlug(slug);
    }

    /**
     * Obtiene destinos relacionados (misma categoría, excluyendo el actual).
     */
    public List<Destination> getRelatedDestinations(Destination destination, int limit) {
        if (destination.getCategory() == null) {
            return List.of();
        }

        return destinationService.getByCategorySlug(destination.getCategory().getSlug())
                .stream()
                .filter(d -> !d.getId().equals(destination.getId()))
                .limit(limit)
                .collect(Collectors.toList());
    }

    /**
     * Busca destinos por texto.
     */
    public List<Destination> searchDestinations(String query) {
        return destinationService.search(query);
    }

    /**
     * Obtiene el tamaño de página por defecto.
     */
    public int getDefaultPageSize() {
        return DEFAULT_PAGE_SIZE;
    }
}
