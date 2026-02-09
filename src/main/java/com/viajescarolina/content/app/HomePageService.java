package com.viajescarolina.content.app;

import com.viajescarolina.agency.app.AgencyService;
import com.viajescarolina.content.domain.home.*;
import com.viajescarolina.content.domain.port.CtaRepository;
import com.viajescarolina.content.domain.port.PageHeroRepository;
import com.viajescarolina.content.domain.port.TravelersTestimonialsRepository;
import com.viajescarolina.content.domain.port.TrustSectionRepository;
import com.viajescarolina.destinations.app.DestinationService;
import com.viajescarolina.promotions.app.PromotionService;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.ArrayList;

/**
 * Servicio de aplicación que orquesta la construcción del Home completo.
 */
@ApplicationScoped
public class HomePageService {

    @Inject
    PageHeroRepository pageHeroRepository;

    @Inject
    TrustSectionRepository trustSectionRepository;

    @Inject
    TravelersTestimonialsRepository travelersTestimonialsRepository;

    @Inject
    CtaRepository ctaRepository;

    @Inject
    AgencyService agencyService;

    @Inject
    PromotionService promotionService;

    @Inject
    DestinationService destinationService;

    /**
     * Construye el Home completo con todos los bloques.
     * @return HomePageData con todos los bloques del home
     */
    public HomePageData getHomePage() {
        return new HomePageData(
                loadHeroSection(),
                loadTrustSection(),
                loadPromotions(),
                loadDestinations(),
                loadTravelersTestimonials(),
                loadCtaBlock(),
                loadAgencyInfo()
        );
    }

    private HeroSection loadHeroSection() {
        return pageHeroRepository.findHeroByPageSlug("home")
                .orElseThrow(() -> new RuntimeException("Hero section for 'home' page not found"));
    }

    private TrustSection loadTrustSection() {
        return trustSectionRepository.findActiveSection().orElse(null);
    }

    private TravelersTestimonialsSection loadTravelersTestimonials() {
        return travelersTestimonialsRepository.findActiveSection().orElse(null);
    }

    private CtaBlock loadCtaBlock() {
        return ctaRepository.findActiveCta().orElse(null);
    }

    private PromotionsBlock loadPromotions() {
        return promotionService != null
            ? promotionService.getFeaturedForHome(3)
            : createEmptyPromotionsBlock();
    }

    private PromotionsBlock createEmptyPromotionsBlock() {
        return new PromotionsBlock(
                "Promociones Destacadas",
                "Descubre nuestras mejores ofertas",
                new ArrayList<>(),
                "/promotions",
                "Ver todas las promociones"
        );
    }

    private TopDestinationsBlock loadDestinations() {
        return destinationService != null
            ? destinationService.getRecommendedForHome(6)
            : createEmptyDestinationsBlock();
    }

    private TopDestinationsBlock createEmptyDestinationsBlock() {
        return new TopDestinationsBlock(
                "Destinos Recomendados",
                "Los mejores lugares para viajar",
                new ArrayList<>()
        );
    }

    private AgencySummary loadAgencyInfo() {
        return agencyService != null
            ? agencyService.getAgencySummaryForHome()
            : null;
    }
}

