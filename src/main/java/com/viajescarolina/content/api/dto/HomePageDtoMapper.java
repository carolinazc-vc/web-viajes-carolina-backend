package com.viajescarolina.content.api.dto;

import com.viajescarolina.content.domain.home.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Mapper para convertir modelos de dominio a DTOs de respuesta.
 */
public class HomePageDtoMapper {

    public static HomePageResponse toDto(HomePageData domainData) {
        if (domainData == null) {
            return null;
        }

        HomePageResponse response = new HomePageResponse();
        response.setHero(toHeroDto(domainData.getHero()));
        response.setTrustBadges(toTrustBadgesDto(domainData.getTrustSection()));
        response.setPromotions(toPromotionsDto(domainData.getPromotions()));
        response.setTopDestinations(toTopDestinationsDto(domainData.getTopDestinations()));
        response.setTravelersTestimonials(toTravelersTestimonialsDto(domainData.getTravelersTestimonials()));
        response.setCta(toCtaDto(domainData.getCta()));
        response.setAgencyInfo(toAgencyInfoDto(domainData.getAgencyInfo()));

        return response;
    }

    private static HomePageResponse.HeroDto toHeroDto(HeroSection domain) {
        if (domain == null) {
            return null;
        }

        HomePageResponse.HeroDto dto = new HomePageResponse.HeroDto();

        // Badge
        if (domain.getBadgeIcon() != null || domain.getBadgeText() != null) {
            dto.setBadge(new HomePageResponse.BadgeDto(domain.getBadgeIcon(), domain.getBadgeText()));
        }

        // Title
        if (domain.getTitleLine1() != null || domain.getTitleLine2() != null) {
            dto.setTitle(new HomePageResponse.TitleDto(domain.getTitleLine1(), domain.getTitleLine2()));
        }

        dto.setSubtitle(domain.getSubtitle());

        // Stats
        if (domain.getStats() != null && !domain.getStats().isEmpty()) {
            dto.setStats(domain.getStats().stream()
                    .map(stat -> new HomePageResponse.StatDto(stat.getValue(), stat.getLabel()))
                    .collect(Collectors.toList()));
        }

        // CTAs
        if (domain.getCtas() != null && !domain.getCtas().isEmpty()) {
            dto.setCtas(domain.getCtas().stream()
                    .map(cta -> new HomePageResponse.CtaDto(cta.getText(), cta.getLink(),
                            cta.getIcon(), cta.getStyle(), cta.getExternal()))
                    .collect(Collectors.toList()));
        }

        // Trust Indicators
        if (domain.getTrustIndicators() != null && !domain.getTrustIndicators().isEmpty()) {
            dto.setTrustIndicators(domain.getTrustIndicators().stream()
                    .map(ti -> new HomePageResponse.TrustIndicatorDto(ti.getIcon(), ti.getHighlight(), ti.getText()))
                    .collect(Collectors.toList()));
        }

        // Image
        if (domain.getImage() != null) {
            dto.setImage(new HomePageResponse.ImageDto(domain.getImage().getUrl(), domain.getImage().getAlt()));
        }

        // Floating Badge
        if (domain.getFloatingBadge() != null) {
            dto.setFloatingBadge(new HomePageResponse.FloatingBadgeDto(
                    domain.getFloatingBadge().getIcon(),
                    domain.getFloatingBadge().getLabel(),
                    domain.getFloatingBadge().getText()
            ));
        }

        return dto;
    }

    private static HomePageResponse.TrustBadgesSectionDto toTrustBadgesDto(TrustSection domain) {
        if (domain == null) {
            return null;
        }

        HomePageResponse.TrustBadgesSectionDto dto = new HomePageResponse.TrustBadgesSectionDto();
        dto.setTitle(domain.getTitle());
        dto.setSubtitle(domain.getSubtitle());

        if (domain.getBadges() != null && !domain.getBadges().isEmpty()) {
            dto.setBadges(domain.getBadges().stream()
                    .map(badge -> new HomePageResponse.BadgeItemDto(
                            badge.getId(),
                            badge.getIcon(),
                            badge.getTitle(),
                            badge.getDescription()
                    ))
                    .collect(Collectors.toList()));
        } else {
            dto.setBadges(new ArrayList<>());
        }

        // Mapear imagen y descripción de la agencia
        dto.setAgencyImageUrl(domain.getAgencyImageUrl());
        dto.setAgencyImageAlt(domain.getAgencyImageAlt());
        dto.setAgencyDescription(domain.getAgencyDescription());

        return dto;
    }

    private static HomePageResponse.PromotionsSectionDto toPromotionsDto(PromotionsBlock domain) {
        if (domain == null) {
            return null;
        }

        HomePageResponse.PromotionsSectionDto dto = new HomePageResponse.PromotionsSectionDto();
        dto.setTitle(domain.getTitle());
        dto.setSubtitle(domain.getSubtitle());
        dto.setViewAllLink(domain.getViewAllLink());
        dto.setViewAllText(domain.getViewAllText());

        if (domain.getItems() != null && !domain.getItems().isEmpty()) {
            dto.setItems(domain.getItems().stream()
                    .map(promo -> new HomePageResponse.PromotionItemDto() {{
                        setId(promo.getId());
                        setTitle(promo.getTitle());
                        setDescription(promo.getDescription());
                        setImageUrl(promo.getImageUrl());
                        setBadgeText(promo.getBadgeText());
                        setBadgeType(promo.getBadgeType());
                        setValidity(promo.getValidity());
                        setOriginalPrice(promo.getOriginalPrice() != null ? promo.getOriginalPrice().doubleValue() : null);
                        setCurrentPrice(promo.getCurrentPrice() != null ? promo.getCurrentPrice().doubleValue() : null);
                        setCurrency(promo.getCurrency());
                        setLink(promo.getLink());
                    }})
                    .collect(Collectors.toList()));
        } else {
            dto.setItems(new ArrayList<>());
        }

        return dto;
    }

    private static HomePageResponse.TopDestinationsSectionDto toTopDestinationsDto(TopDestinationsBlock domain) {
        if (domain == null) {
            return null;
        }

        HomePageResponse.TopDestinationsSectionDto dto = new HomePageResponse.TopDestinationsSectionDto();
        dto.setTitle(domain.getTitle());
        dto.setSubtitle(domain.getSubtitle());

        if (domain.getItems() != null && !domain.getItems().isEmpty()) {
            dto.setItems(domain.getItems().stream()
                    .map(dest -> new HomePageResponse.DestinationItemDto(
                            dest.getId(),
                            dest.getName(),
                            dest.getDescription(),
                            dest.getImageUrl(),
                            dest.getLink()
                    ))
                    .collect(Collectors.toList()));
        } else {
            dto.setItems(new ArrayList<>());
        }

        return dto;
    }

    private static HomePageResponse.CtaSectionDto toCtaDto(CtaBlock domain) {
        if (domain == null) {
            return null;
        }

        HomePageResponse.CtaSectionDto dto = new HomePageResponse.CtaSectionDto();
        dto.setHeadline(domain.getHeadline());
        dto.setDescription(domain.getDescription());

        if (domain.getCtas() != null && !domain.getCtas().isEmpty()) {
            dto.setCtas(domain.getCtas().stream()
                    .map(cta -> new HomePageResponse.CtaDto(
                            cta.getText(),
                            cta.getLink(),
                            null, // Los botones en CTA section no tienen icon por defecto
                            cta.getStyle(),
                            cta.getExternal()
                    ))
                    .collect(Collectors.toList()));
        } else {
            dto.setCtas(new ArrayList<>());
        }

        return dto;
    }

    private static HomePageResponse.AgencyInfoDto toAgencyInfoDto(AgencySummary domain) {
        if (domain == null) {
            return null;
        }

        HomePageResponse.AgencyInfoDto dto = new HomePageResponse.AgencyInfoDto();
        dto.setId(domain.getId());
        dto.setName(domain.getName());
        dto.setDescription(domain.getDescription());

        // Contact
        if (domain.getContact() != null) {
            HomePageResponse.ContactDto contactDto = new HomePageResponse.ContactDto();
            contactDto.setEmail(domain.getContact().getEmail());
            contactDto.setPhone(domain.getContact().getPhone());
            contactDto.setWhatsapp(domain.getContact().getWhatsapp());
            contactDto.setAddress(domain.getContact().getAddress());
            contactDto.setCity(domain.getContact().getCity());
            contactDto.setCountry(domain.getContact().getCountry());
            dto.setContact(contactDto);
        }

        // Social
        if (domain.getSocial() != null) {
            HomePageResponse.SocialDto socialDto = new HomePageResponse.SocialDto();
            socialDto.setFacebook(domain.getSocial().getFacebook());
            socialDto.setInstagram(domain.getSocial().getInstagram());
            socialDto.setTwitter(domain.getSocial().getTwitter());
            socialDto.setYoutube(domain.getSocial().getYoutube());
            dto.setSocial(socialDto);
        }

        // Business Hours
        if (domain.getBusinessHours() != null) {
            HomePageResponse.BusinessHoursDto hoursDto = new HomePageResponse.BusinessHoursDto();
            hoursDto.setWeekdays(domain.getBusinessHours().getWeekdays());
            hoursDto.setSaturday(domain.getBusinessHours().getSaturday());
            hoursDto.setSunday(domain.getBusinessHours().getSunday());
            dto.setBusinessHours(hoursDto);
        }

        return dto;
    }

    private static HomePageResponse.TravelersTestimonialsSectionDto toTravelersTestimonialsDto(TravelersTestimonialsSection domain) {
        if (domain == null) {
            return null;
        }

        HomePageResponse.TravelersTestimonialsSectionDto dto = new HomePageResponse.TravelersTestimonialsSectionDto();
        dto.setTitle(domain.getTitle());
        dto.setSubtitle(domain.getSubtitle());

        if (domain.getItems() != null && !domain.getItems().isEmpty()) {
            dto.setItems(domain.getItems().stream()
                    .map(item -> new HomePageResponse.TravelerTestimonialItemDto(
                            item.getId(),
                            item.getImageUrl(),
                            item.getImageAlt(),
                            item.getCustomerName(),
                            item.getShortQuote(),
                            item.getDestination()
                    ))
                    .collect(Collectors.toList()));
        } else {
            dto.setItems(new ArrayList<>());
        }

        return dto;
    }
}

