package com.viajescarolina.destinations.app;

import com.viajescarolina.content.domain.home.TopDestinationsBlock;
import com.viajescarolina.content.domain.home.TopDestinationsBlock.DestinationSummary;
import com.viajescarolina.destinations.domain.Destination;
import com.viajescarolina.destinations.domain.Destination.DestinationImage;
import com.viajescarolina.destinations.domain.Destination.DestinationSection;
import com.viajescarolina.destinations.domain.Destination.DestinationFaq;
import com.viajescarolina.destinations.domain.Destination.DestinationTag;
import com.viajescarolina.destinations.domain.DestinationCategory;
import com.viajescarolina.destinations.infra.jpa.DestinationRepository;
import com.viajescarolina.destinations.infra.jpa.DestinationCategoryRepository;
import com.viajescarolina.destinations.infra.jpa.DestinationImageRepository;
import com.viajescarolina.destinations.infra.jpa.DestinationSectionRepository;
import com.viajescarolina.destinations.infra.jpa.DestinationFaqRepository;
import com.viajescarolina.destinations.infra.jpa.DestinationTagMappingRepository;
import com.viajescarolina.destinations.infra.jpa.entity.DestinationEntity;
import com.viajescarolina.destinations.infra.jpa.entity.DestinationCategoryEntity;
import com.viajescarolina.destinations.infra.jpa.entity.DestinationImageEntity;
import com.viajescarolina.destinations.infra.jpa.entity.DestinationSectionEntity;
import com.viajescarolina.destinations.infra.jpa.entity.DestinationFaqEntity;
import com.viajescarolina.destinations.infra.jpa.entity.DestinationTagMappingEntity;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Implementación del servicio de destinos.
 */
@ApplicationScoped
public class DestinationServiceImpl implements DestinationService {

    @Inject
    DestinationRepository destinationRepository;

    @Inject
    DestinationCategoryRepository categoryRepository;

    @Inject
    DestinationImageRepository imageRepository;

    @Inject
    DestinationSectionRepository sectionRepository;

    @Inject
    DestinationFaqRepository faqRepository;

    @Inject
    DestinationTagMappingRepository tagMappingRepository;

    @Override
    public TopDestinationsBlock getRecommendedForHome(int limit) {
        List<DestinationEntity> entities = destinationRepository.findRecommended(limit);

        List<DestinationSummary> destinations = entities.stream()
                .map(entity -> {
                    DestinationImageEntity firstImage = imageRepository.findFirstByDestinationId(entity.id);
                    String imageUrl = firstImage != null ? firstImage.imageUrl : null;

                    return new DestinationSummary(
                            String.valueOf(entity.id),
                            entity.name,
                            entity.summaryExcerpt != null ? entity.summaryExcerpt : entity.introText,
                            imageUrl,
                            "/destinations/" + entity.slug
                    );
                })
                .collect(Collectors.toList());

        return new TopDestinationsBlock(
                "Destinos Recomendados",
                "Los mejores lugares para viajar en Colombia",
                destinations
        );
    }

    @Override
    public List<Destination> getAllActive() {
        return destinationRepository.findAllActive().stream()
                .map(this::toDestination)
                .collect(Collectors.toList());
    }

    @Override
    public List<Destination> getAllActivePaginated(int page, int size) {
        return destinationRepository.findAllActivePaginated(page, size).stream()
                .map(this::toDestination)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Destination> getBySlug(String slug) {
        return destinationRepository.findBySlug(slug)
                .map(this::toDestinationWithDetails);
    }

    @Override
    public List<Destination> getByCategorySlug(String categorySlug) {
        return destinationRepository.findByCategorySlug(categorySlug).stream()
                .map(this::toDestination)
                .collect(Collectors.toList());
    }

    @Override
    public List<Destination> getByCategorySlugPaginated(String categorySlug, int page, int size) {
        return destinationRepository.findByCategorySlugPaginated(categorySlug, page, size).stream()
                .map(this::toDestination)
                .collect(Collectors.toList());
    }

    @Override
    public List<DestinationCategory> getAllCategoriesWithCount() {
        List<DestinationCategoryEntity> categories = categoryRepository.findAllOrdered();

        return categories.stream()
                .map(entity -> {
                    long count = destinationRepository.countByCategorySlug(entity.slug);
                    return new DestinationCategory(
                            String.valueOf(entity.id),
                            entity.name,
                            entity.slug,
                            entity.description,
                            count
                    );
                })
                .collect(Collectors.toList());
    }

    @Override
    public long countActive() {
        return destinationRepository.countActive();
    }

    @Override
    public long countByCategorySlug(String categorySlug) {
        return destinationRepository.countByCategorySlug(categorySlug);
    }

    @Override
    public List<Destination> search(String query) {
        return destinationRepository.search(query).stream()
                .map(this::toDestination)
                .collect(Collectors.toList());
    }

    /**
     * Convierte entity a dominio (versión resumida para listados).
     */
    private Destination toDestination(DestinationEntity entity) {
        Destination destination = new Destination();
        destination.setId(String.valueOf(entity.id));
        destination.setSlug(entity.slug);
        destination.setName(entity.name);
        destination.setCountry(entity.country);
        destination.setRegion(entity.region);
        destination.setCity(entity.city);
        destination.setSummaryTitle(entity.summaryTitle);
        destination.setSummaryExcerpt(entity.summaryExcerpt);
        destination.setSummaryBadge(entity.summaryBadge);
        destination.setRecommended(entity.isRecommended);
        destination.setActive(entity.isActive);

        // Categoría
        if (entity.category != null) {
            destination.setCategory(new DestinationCategory(
                    String.valueOf(entity.category.id),
                    entity.category.name,
                    entity.category.slug,
                    entity.category.description
            ));
        }

        // Primera imagen
        DestinationImageEntity firstImage = imageRepository.findFirstByDestinationId(entity.id);
        if (firstImage != null) {
            destination.setSummaryImageUrl(firstImage.imageUrl);
        }

        return destination;
    }

    /**
     * Convierte entity a dominio (versión completa con detalles).
     */
    private Destination toDestinationWithDetails(DestinationEntity entity) {
        Destination destination = toDestination(entity);

        // Contenido detallado
        destination.setIntroText(entity.introText);
        destination.setContent(entity.content);
        destination.setHighlightTips(entity.highlightTips);
        destination.setRecommendedOrder(entity.recommendedOrder);

        // Metadatos SEO
        destination.setMetaTitle(entity.metaTitle);
        destination.setMetaDescription(entity.metaDescription);
        destination.setFeaturedImageUrl(entity.featuredImageUrl);
        destination.setReadingTimeMinutes(entity.readingTimeMinutes);
        destination.setPublishedAt(entity.publishedAt);

        // Todas las imágenes
        List<DestinationImageEntity> imageEntities = imageRepository.findByDestinationId(entity.id);
        List<DestinationImage> images = imageEntities.stream()
                .map(img -> new DestinationImage(
                        String.valueOf(img.id),
                        img.imageUrl,
                        img.altText,
                        img.caption,
                        img.imageType != null ? img.imageType.name() : null,
                        img.isFeatured != null ? img.isFeatured : false,
                        img.sortOrder
                ))
                .collect(Collectors.toList());
        destination.setImages(images);

        // Secciones
        List<DestinationSectionEntity> sectionEntities = sectionRepository.findVisibleByDestinationId(entity.id);
        List<DestinationSection> sections = sectionEntities.stream()
                .map(sec -> new DestinationSection(
                        String.valueOf(sec.id),
                        sec.title,
                        sec.contentHtml,
                        sec.sectionType != null ? sec.sectionType.name() : null,
                        sec.sectionType != null ? sec.sectionType.getDisplayName() : null,
                        sec.icon,
                        sec.sortOrder,
                        sec.isVisible
                ))
                .collect(Collectors.toList());
        destination.setSections(sections);

        // FAQs
        List<DestinationFaqEntity> faqEntities = faqRepository.findVisibleByDestinationId(entity.id);
        List<DestinationFaq> faqs = faqEntities.stream()
                .map(faq -> new DestinationFaq(
                        String.valueOf(faq.id),
                        faq.question,
                        faq.answer,
                        faq.sortOrder,
                        faq.isVisible
                ))
                .collect(Collectors.toList());
        destination.setFaqs(faqs);

        // Tags
        List<DestinationTagMappingEntity> tagMappings = tagMappingRepository.findByDestinationId(entity.id);
        List<DestinationTag> tags = tagMappings.stream()
                .filter(mapping -> mapping.tag != null && mapping.tag.isActive)
                .map(mapping -> new DestinationTag(
                        String.valueOf(mapping.tag.id),
                        mapping.tag.name,
                        mapping.tag.slug,
                        mapping.tag.icon,
                        mapping.tag.color,
                        mapping.tag.description
                ))
                .collect(Collectors.toList());
        destination.setTags(tags);

        return destination;
    }
}

