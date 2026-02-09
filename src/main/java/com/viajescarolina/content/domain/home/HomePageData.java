package com.viajescarolina.content.domain.home;

/**
 * Modelo de dominio del Home completo.
 * Contiene todos los bloques principales del página de inicio.
 */
public class HomePageData {
    private HeroSection hero;
    private TrustSection trustSection;
    private PromotionsBlock promotions;
    private TopDestinationsBlock topDestinations;
    private TravelersTestimonialsSection travelersTestimonials;
    private CtaBlock cta;
    private AgencySummary agencyInfo;

    public HomePageData() {
    }

    public HomePageData(
            HeroSection hero,
            TrustSection trustSection,
            PromotionsBlock promotions,
            TopDestinationsBlock topDestinations,
            TravelersTestimonialsSection travelersTestimonials,
            CtaBlock cta,
            AgencySummary agencyInfo) {
        this.hero = hero;
        this.trustSection = trustSection;
        this.promotions = promotions;
        this.topDestinations = topDestinations;
        this.travelersTestimonials = travelersTestimonials;
        this.cta = cta;
        this.agencyInfo = agencyInfo;
    }

    // Getters y Setters
    public HeroSection getHero() {
        return hero;
    }

    public void setHero(HeroSection hero) {
        this.hero = hero;
    }

    public TrustSection getTrustSection() {
        return trustSection;
    }

    public void setTrustSection(TrustSection trustSection) {
        this.trustSection = trustSection;
    }

    public PromotionsBlock getPromotions() {
        return promotions;
    }

    public void setPromotions(PromotionsBlock promotions) {
        this.promotions = promotions;
    }

    public TopDestinationsBlock getTopDestinations() {
        return topDestinations;
    }

    public void setTopDestinations(TopDestinationsBlock topDestinations) {
        this.topDestinations = topDestinations;
    }

    public TravelersTestimonialsSection getTravelersTestimonials() {
        return travelersTestimonials;
    }

    public void setTravelersTestimonials(TravelersTestimonialsSection travelersTestimonials) {
        this.travelersTestimonials = travelersTestimonials;
    }

    public CtaBlock getCta() {
        return cta;
    }

    public void setCta(CtaBlock cta) {
        this.cta = cta;
    }

    public AgencySummary getAgencyInfo() {
        return agencyInfo;
    }

    public void setAgencyInfo(AgencySummary agencyInfo) {
        this.agencyInfo = agencyInfo;
    }
}

