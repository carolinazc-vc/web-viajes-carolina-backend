package com.viajescarolina.content.infra.jpa.mapper;

import com.viajescarolina.agency.infra.jpa.entity.TrustBadgeEntity;
import com.viajescarolina.agency.infra.jpa.entity.TrustSectionEntity;
import com.viajescarolina.content.domain.home.*;
import com.viajescarolina.content.domain.home.TravelersTestimonialsSection.TravelerTestimonialItem;
import com.viajescarolina.content.infra.jpa.entity.*;
import com.viajescarolina.content.infra.storage.ImageStorageService;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Mapper para convertir entities JPA a modelos de dominio.
 */
@ApplicationScoped
public class HomeContentMapper {

    @Inject
    ImageStorageService imageStorageService;

    /**
     * Convierte PageHeroEntity a HeroSection del dominio.
     */
    public static HeroSection toHeroSection(PageHeroEntity entity) {
        if (entity == null) {
            return null;
        }

        HeroSection section = new HeroSection();
        section.setId(String.valueOf(entity.id));
        section.setBadgeIcon(entity.badgeIcon);
        section.setBadgeText(entity.badgeText);
        section.setTitleLine1(entity.titleLine1);
        section.setTitleLine2(entity.titleLine2);
        section.setSubtitle(entity.subtitle);

        // Mapear stats
        if (entity.stats != null && !entity.stats.isEmpty()) {
            section.setStats(entity.stats.stream()
                    .map(HomeContentMapper::toHeroStat)
                    .collect(Collectors.toList()));
        } else {
            section.setStats(new ArrayList<>());
        }

        // Mapear CTAs
        if (entity.ctas != null && !entity.ctas.isEmpty()) {
            section.setCtas(entity.ctas.stream()
                    .map(HomeContentMapper::toHeroCta)
                    .collect(Collectors.toList()));
        } else {
            section.setCtas(new ArrayList<>());
        }

        // Mapear trust indicators
        if (entity.trustIndicators != null && !entity.trustIndicators.isEmpty()) {
            section.setTrustIndicators(entity.trustIndicators.stream()
                    .map(HomeContentMapper::toHeroTrustIndicator)
                    .collect(Collectors.toList()));
        } else {
            section.setTrustIndicators(new ArrayList<>());
        }

        // Mapear imagen
        if (entity.imageUrl != null) {
            HeroSection.HeroImage image = new HeroSection.HeroImage(entity.imageUrl, entity.imageAlt);
            section.setImage(image);
        }

        // Mapear floating badge
        if (entity.floatingIcon != null) {
            HeroSection.HeroFloatingBadge badge = new HeroSection.HeroFloatingBadge(
                    entity.floatingIcon,
                    entity.floatingLabel,
                    entity.floatingText
            );
            section.setFloatingBadge(badge);
        }

        return section;
    }

    /**
     * Convierte PageHeroStatEntity a HeroStat.
     */
    public static HeroSection.HeroStat toHeroStat(PageHeroStatEntity entity) {
        if (entity == null) {
            return null;
        }
        return new HeroSection.HeroStat(entity.value, entity.label);
    }

    /**
     * Convierte PageHeroCtaEntity a HeroCta.
     */
    public static HeroSection.HeroCta toHeroCta(PageHeroCtaEntity entity) {
        if (entity == null) {
            return null;
        }
        return new HeroSection.HeroCta(
                entity.text,
                entity.link,
                entity.icon,
                entity.style,
                entity.isExternal
        );
    }

    /**
     * Convierte PageHeroTrustIndicatorEntity a HeroTrustIndicator.
     */
    public static HeroSection.HeroTrustIndicator toHeroTrustIndicator(PageHeroTrustIndicatorEntity entity) {
        if (entity == null) {
            return null;
        }
        return new HeroSection.HeroTrustIndicator(
                entity.icon,
                entity.highlight,
                entity.text
        );
    }

    /**
     * Convierte TrustSectionEntity a TrustSection del dominio.
     */
    public TrustSection toTrustSection(TrustSectionEntity entity) {
        if (entity == null) {
            return null;
        }

        TrustSection section = new TrustSection();
        section.setId(String.valueOf(entity.id));
        section.setTitle(entity.title);
        section.setSubtitle(entity.subtitle);

        // Mapear badges
        if (entity.badges != null && !entity.badges.isEmpty()) {
            section.setBadges(entity.badges.stream()
                    .map(this::toTrustBadge)
                    .collect(Collectors.toList()));
        } else {
            section.setBadges(new ArrayList<>());
        }

        // Mapear imagen y descripción de la agencia
        section.setAgencyImageUrl(entity.agencyImageUrl);
        section.setAgencyImageAlt(entity.agencyImageAlt);
        section.setAgencyDescription(entity.agencyDescription);

        return section;
    }

    private String loadImageAsBase64(String imageId) {
        if (imageId == null || imageId.isEmpty()) {
            return null;
        }
        // Para desarrollo, usamos almacenamiento local (useGcp = false)
        return imageStorageService.getImageAsBase64(imageId, false);
    }

    /**
     * Convierte TrustBadgeEntity a TrustBadge.
     */
    public TrustSection.TrustBadge toTrustBadge(TrustBadgeEntity entity) {
        if (entity == null) {
            return null;
        }
        return new TrustSection.TrustBadge(
                String.valueOf(entity.id),
                entity.icon,
                entity.title,
                entity.description,
                entity.sortOrder
        );
    }

    /**
     * Convierte CtaEntity a CtaBlock del dominio.
     */
    public static CtaBlock toCtaBlock(CtaEntity entity) {
        if (entity == null) {
            return null;
        }

        CtaBlock block = new CtaBlock();
        block.setHeadline(entity.headline);
        block.setDescription(entity.description);

        // Crear un único botón desde los datos de la entity
        List<CtaBlock.CtaButton> ctas = new ArrayList<>();
        ctas.add(new CtaBlock.CtaButton(
                entity.buttonText,
                entity.linkBase,
                null, // style no está en CtaEntity para el botón
                false // external link
        ));
        block.setCtas(ctas);

        return block;
    }

    /**
     * Convierte TravelersTestimonialsSectionEntity y sus items a TravelersTestimonialsSection del dominio.
     */
    public TravelersTestimonialsSection toTravelersTestimonialsSection(
            TravelersTestimonialsSectionEntity sectionEntity,
            List<TravelerTestimonialItemEntity> itemEntities) {

        if (sectionEntity == null) {
            return null;
        }

        TravelersTestimonialsSection section = new TravelersTestimonialsSection();
        section.setId(String.valueOf(sectionEntity.id));
        section.setTitle(sectionEntity.title);
        section.setSubtitle(sectionEntity.subtitle);

        if (itemEntities != null && !itemEntities.isEmpty()) {
            section.setItems(itemEntities.stream()
                    .map(this::toTravelerTestimonialItem)
                    .collect(Collectors.toList()));
        } else {
            section.setItems(new ArrayList<>());
        }

        return section;
    }

    /**
     * Convierte TravelerTestimonialItemEntity a TravelerTestimonialItem.
     */
    public TravelerTestimonialItem toTravelerTestimonialItem(TravelerTestimonialItemEntity entity) {
        if (entity == null) {
            return null;
        }
        return new TravelerTestimonialItem(
                String.valueOf(entity.id),
                entity.imageUrl,
                entity.imageAlt,
                entity.customerName,
                entity.shortQuote,
                entity.destination
        );
    }
    // ...existing code...
}

