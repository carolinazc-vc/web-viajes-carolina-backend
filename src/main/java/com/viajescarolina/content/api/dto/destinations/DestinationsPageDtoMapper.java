package com.viajescarolina.content.api.dto.destinations;

import com.viajescarolina.content.domain.home.HeroSection;
import com.viajescarolina.destinations.domain.Destination;
import com.viajescarolina.destinations.domain.Destination.DestinationImage;
import com.viajescarolina.destinations.domain.DestinationCategory;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Mapper para convertir modelos de dominio a DTOs de respuesta de destinos.
 */
public class DestinationsPageDtoMapper {

    /**
     * Construye la respuesta de la página de destinos.
     */
    public static DestinationsPageResponse toPageResponse(
            HeroSection hero,
            List<DestinationCategory> categories,
            List<Destination> destinations,
            int page,
            int size,
            long total) {

        DestinationsPageResponse response = new DestinationsPageResponse();
        response.setHero(toHeroDto(hero));
        response.setCategories(toCategoryDtos(categories));
        response.setDestinations(toDestinationItemDtos(destinations));
        response.setPagination(new DestinationsPageResponse.PaginationDto(page, size, total));

        return response;
    }

    /**
     * Construye la respuesta de detalle de destino.
     */
    public static DestinationDetailResponse toDetailResponse(
            Destination destination,
            List<Destination> relatedDestinations) {

        DestinationDetailResponse response = new DestinationDetailResponse();
        response.setId(destination.getId());
        response.setSlug(destination.getSlug());
        response.setName(destination.getName());
        response.setCountry(destination.getCountry());
        response.setRegion(destination.getRegion());
        response.setCity(destination.getCity());
        response.setSummaryTitle(destination.getSummaryTitle());
        response.setSummaryExcerpt(destination.getSummaryExcerpt());
        response.setSummaryBadge(destination.getSummaryBadge());
        response.setIntroText(destination.getIntroText());
        response.setContent(destination.getContent());
        response.setHighlightTips(destination.getHighlightTips());
        response.setRecommended(destination.isRecommended());

        // Metadatos SEO
        response.setMetaTitle(destination.getMetaTitle());
        response.setMetaDescription(destination.getMetaDescription());
        response.setFeaturedImageUrl(destination.getFeaturedImageUrl());
        response.setReadingTimeMinutes(destination.getReadingTimeMinutes());
        if (destination.getPublishedAt() != null) {
            response.setPublishedAt(destination.getPublishedAt().toString());
        }

        // Categoría
        if (destination.getCategory() != null) {
            response.setCategory(new DestinationDetailResponse.CategoryDto(
                    destination.getCategory().getId(),
                    destination.getCategory().getName(),
                    destination.getCategory().getSlug()
            ));
        }

        // Imágenes
        if (destination.getImages() != null) {
            response.setImages(destination.getImages().stream()
                    .map(img -> new DestinationDetailResponse.ImageDto(
                            img.getId(),
                            img.getUrl(),
                            img.getAlt()
                    ))
                    .collect(Collectors.toList()));
        } else {
            response.setImages(new ArrayList<>());
        }

        // Secciones
        if (destination.getSections() != null) {
            response.setSections(destination.getSections().stream()
                    .map(sec -> new DestinationDetailResponse.SectionDto(
                            sec.getId(),
                            sec.getTitle(),
                            sec.getContentHtml(),
                            sec.getSectionType(),
                            sec.getSectionTypeDisplayName(),
                            sec.getIcon(),
                            sec.getSortOrder()
                    ))
                    .collect(Collectors.toList()));
        } else {
            response.setSections(new ArrayList<>());
        }

        // FAQs
        if (destination.getFaqs() != null) {
            response.setFaqs(destination.getFaqs().stream()
                    .map(faq -> new DestinationDetailResponse.FaqDto(
                            faq.getId(),
                            faq.getQuestion(),
                            faq.getAnswer(),
                            faq.getSortOrder()
                    ))
                    .collect(Collectors.toList()));
        } else {
            response.setFaqs(new ArrayList<>());
        }

        // Tags
        if (destination.getTags() != null) {
            response.setTags(destination.getTags().stream()
                    .map(tag -> new DestinationDetailResponse.TagDto(
                            tag.getId(),
                            tag.getName(),
                            tag.getSlug(),
                            tag.getIcon(),
                            tag.getColor(),
                            tag.getDescription()
                    ))
                    .collect(Collectors.toList()));
        } else {
            response.setTags(new ArrayList<>());
        }

        // Destinos relacionados
        if (relatedDestinations != null) {
            response.setRelatedDestinations(relatedDestinations.stream()
                    .map(d -> new DestinationDetailResponse.RelatedDestinationDto(
                            d.getId(),
                            d.getSlug(),
                            d.getName(),
                            d.getSummaryImageUrl()
                    ))
                    .collect(Collectors.toList()));
        } else {
            response.setRelatedDestinations(new ArrayList<>());
        }

        return response;
    }

    private static DestinationsPageResponse.HeroDto toHeroDto(HeroSection hero) {
        if (hero == null) {
            return null;
        }

        DestinationsPageResponse.HeroDto dto = new DestinationsPageResponse.HeroDto();

        if (hero.getBadgeIcon() != null || hero.getBadgeText() != null) {
            dto.setBadge(new DestinationsPageResponse.BadgeDto(hero.getBadgeIcon(), hero.getBadgeText()));
        }

        if (hero.getTitleLine1() != null || hero.getTitleLine2() != null) {
            dto.setTitle(new DestinationsPageResponse.TitleDto(hero.getTitleLine1(), hero.getTitleLine2()));
        }

        dto.setSubtitle(hero.getSubtitle());

        if (hero.getImage() != null) {
            dto.setImage(new DestinationsPageResponse.ImageDto(
                    hero.getImage().getUrl(),
                    hero.getImage().getAlt()
            ));
        }

        return dto;
    }

    private static List<DestinationsPageResponse.CategoryDto> toCategoryDtos(List<DestinationCategory> categories) {
        if (categories == null) {
            return new ArrayList<>();
        }

        return categories.stream()
                .map(cat -> new DestinationsPageResponse.CategoryDto(
                        cat.getId(),
                        cat.getName(),
                        cat.getSlug(),
                        cat.getCount()
                ))
                .collect(Collectors.toList());
    }

    private static List<DestinationsPageResponse.DestinationItemDto> toDestinationItemDtos(List<Destination> destinations) {
        if (destinations == null) {
            return new ArrayList<>();
        }

        return destinations.stream()
                .map(dest -> {
                    DestinationsPageResponse.DestinationItemDto dto = new DestinationsPageResponse.DestinationItemDto();
                    dto.setId(dest.getId());
                    dto.setSlug(dest.getSlug());
                    dto.setName(dest.getName());
                    dto.setCountry(dest.getCountry());
                    dto.setRegion(dest.getRegion());
                    dto.setSummaryExcerpt(dest.getSummaryExcerpt());
                    dto.setSummaryBadge(dest.getSummaryBadge());
                    dto.setImageUrl(dest.getSummaryImageUrl());

                    if (dest.getCategory() != null) {
                        dto.setCategory(new DestinationsPageResponse.CategoryDto(
                                dest.getCategory().getId(),
                                dest.getCategory().getName(),
                                dest.getCategory().getSlug(),
                                0 // No necesitamos count aquí
                        ));
                    }

                    return dto;
                })
                .collect(Collectors.toList());
    }
}
