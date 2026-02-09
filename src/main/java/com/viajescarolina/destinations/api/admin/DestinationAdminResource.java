package com.viajescarolina.destinations.api.admin;

import com.viajescarolina.destinations.app.HtmlSanitizerService;
import com.viajescarolina.destinations.infra.jpa.DestinationRepository;
import com.viajescarolina.destinations.infra.jpa.DestinationCategoryRepository;
import com.viajescarolina.destinations.infra.jpa.DestinationImageRepository;
import com.viajescarolina.destinations.infra.jpa.DestinationSectionRepository;
import com.viajescarolina.destinations.infra.jpa.DestinationFaqRepository;
import com.viajescarolina.destinations.infra.jpa.DestinationTagRepository;
import com.viajescarolina.destinations.infra.jpa.DestinationTagMappingRepository;
import com.viajescarolina.destinations.infra.jpa.entity.DestinationEntity;
import com.viajescarolina.destinations.infra.jpa.entity.DestinationCategoryEntity;
import com.viajescarolina.destinations.infra.jpa.entity.DestinationImageEntity;
import com.viajescarolina.destinations.infra.jpa.entity.DestinationSectionEntity;
import com.viajescarolina.destinations.infra.jpa.entity.DestinationFaqEntity;
import com.viajescarolina.destinations.infra.jpa.entity.DestinationTagEntity;
import com.viajescarolina.destinations.infra.jpa.entity.DestinationTagMappingEntity;
import com.viajescarolina.destinations.infra.jpa.entity.SectionType;
import com.viajescarolina.destinations.infra.jpa.entity.ImageType;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Resource REST para administración de destinos (CRUD).
 */
@Path("/api/admin/destinations")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class DestinationAdminResource {

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
    DestinationTagRepository tagRepository;

    @Inject
    DestinationTagMappingRepository tagMappingRepository;

    @Inject
    HtmlSanitizerService htmlSanitizer;

    /**
     * Lista todos los destinos (incluyendo inactivos).
     */
    @GET
    public Response getAllDestinations() {
        List<DestinationEntity> entities = destinationRepository.findAllForAdmin();
        List<DestinationAdminDto> dtos = entities.stream()
                .map(this::toAdminDto)
                .collect(Collectors.toList());
        return Response.ok(dtos).build();
    }

    /**
     * Obtiene un destino por ID.
     */
    @GET
    @Path("/{id}")
    public Response getDestinationById(@PathParam("id") Long id) {
        DestinationEntity entity = destinationRepository.findById(id);
        if (entity == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(new ErrorResponse("Destino no encontrado", 404))
                    .build();
        }
        return Response.ok(toAdminDto(entity)).build();
    }

    /**
     * Crea un nuevo destino.
     */
    @POST
    @Transactional
    public Response createDestination(DestinationAdminDto dto) {
        // Validar que no exista el slug
        if (destinationRepository.find("slug", dto.slug).firstResultOptional().isPresent()) {
            return Response.status(Response.Status.CONFLICT)
                    .entity(new ErrorResponse("Ya existe un destino con ese slug", 409))
                    .build();
        }

        DestinationEntity entity = new DestinationEntity();
        updateEntityFromDto(entity, dto);
        destinationRepository.persist(entity);

        // Crear imágenes si vienen
        if (dto.images != null) {
            for (int i = 0; i < dto.images.size(); i++) {
                ImageDto imgDto = dto.images.get(i);
                DestinationImageEntity imgEntity = new DestinationImageEntity();
                imgEntity.destination = entity;
                imgEntity.imageUrl = imgDto.url;
                imgEntity.altText = imgDto.alt;
                imgEntity.sortOrder = i + 1;
                imageRepository.persist(imgEntity);
            }
        }

        return Response.status(Response.Status.CREATED)
                .entity(toAdminDto(entity))
                .build();
    }

    /**
     * Actualiza un destino existente.
     */
    @PUT
    @Path("/{id}")
    @Transactional
    public Response updateDestination(@PathParam("id") Long id, DestinationAdminDto dto) {
        DestinationEntity entity = destinationRepository.findById(id);
        if (entity == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(new ErrorResponse("Destino no encontrado", 404))
                    .build();
        }

        // Validar slug único (si cambió)
        if (!entity.slug.equals(dto.slug)) {
            if (destinationRepository.find("slug", dto.slug).firstResultOptional().isPresent()) {
                return Response.status(Response.Status.CONFLICT)
                        .entity(new ErrorResponse("Ya existe un destino con ese slug", 409))
                        .build();
            }
        }

        updateEntityFromDto(entity, dto);

        // Actualizar imágenes (eliminar y recrear)
        if (dto.images != null) {
            imageRepository.deleteByDestinationId(id);
            for (int i = 0; i < dto.images.size(); i++) {
                ImageDto imgDto = dto.images.get(i);
                DestinationImageEntity imgEntity = new DestinationImageEntity();
                imgEntity.destination = entity;
                imgEntity.imageUrl = imgDto.url;
                imgEntity.altText = imgDto.alt;
                imgEntity.sortOrder = i + 1;
                imageRepository.persist(imgEntity);
            }
        }

        return Response.ok(toAdminDto(entity)).build();
    }

    /**
     * Elimina un destino (soft delete).
     */
    @DELETE
    @Path("/{id}")
    @Transactional
    public Response deleteDestination(@PathParam("id") Long id) {
        DestinationEntity entity = destinationRepository.findById(id);
        if (entity == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(new ErrorResponse("Destino no encontrado", 404))
                    .build();
        }

        // Soft delete
        entity.isActive = false;

        return Response.noContent().build();
    }

    /**
     * Lista todas las categorías.
     */
    @GET
    @Path("/categories")
    public Response getCategories() {
        List<DestinationCategoryEntity> categories = categoryRepository.findAllOrdered();
        List<CategoryAdminDto> dtos = categories.stream()
                .map(this::toCategoryDto)
                .collect(Collectors.toList());
        return Response.ok(dtos).build();
    }

    /**
     * Crea una nueva categoría.
     */
    @POST
    @Path("/categories")
    @Transactional
    public Response createCategory(CategoryAdminDto dto) {
        if (categoryRepository.findBySlug(dto.slug).isPresent()) {
            return Response.status(Response.Status.CONFLICT)
                    .entity(new ErrorResponse("Ya existe una categoría con ese slug", 409))
                    .build();
        }

        DestinationCategoryEntity entity = new DestinationCategoryEntity();
        entity.name = dto.name;
        entity.slug = dto.slug;
        entity.description = dto.description;
        categoryRepository.persist(entity);

        return Response.status(Response.Status.CREATED)
                .entity(toCategoryDto(entity))
                .build();
    }

    // =====================================================
    // ENDPOINTS PARA SECCIONES
    // =====================================================

    /**
     * Lista secciones de un destino.
     */
    @GET
    @Path("/{id}/sections")
    public Response getSections(@PathParam("id") Long id) {
        DestinationEntity destination = destinationRepository.findById(id);
        if (destination == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(new ErrorResponse("Destino no encontrado", 404))
                    .build();
        }

        List<DestinationSectionEntity> sections = sectionRepository.findByDestinationId(id);
        List<SectionDto> dtos = sections.stream()
                .map(this::toSectionDto)
                .collect(Collectors.toList());
        return Response.ok(dtos).build();
    }

    /**
     * Crea una sección para un destino.
     */
    @POST
    @Path("/{id}/sections")
    @Transactional
    public Response createSection(@PathParam("id") Long id, SectionDto dto) {
        DestinationEntity destination = destinationRepository.findById(id);
        if (destination == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(new ErrorResponse("Destino no encontrado", 404))
                    .build();
        }

        DestinationSectionEntity entity = new DestinationSectionEntity();
        entity.destination = destination;
        entity.title = dto.title;
        entity.contentHtml = htmlSanitizer.sanitize(dto.contentHtml);
        entity.sectionType = SectionType.valueOf(dto.sectionType);
        entity.icon = dto.icon;
        entity.sortOrder = dto.sortOrder != null ? dto.sortOrder : 0;
        entity.isVisible = dto.isVisible != null ? dto.isVisible : true;
        sectionRepository.persist(entity);

        return Response.status(Response.Status.CREATED)
                .entity(toSectionDto(entity))
                .build();
    }

    /**
     * Actualiza una sección.
     */
    @PUT
    @Path("/{id}/sections/{sectionId}")
    @Transactional
    public Response updateSection(@PathParam("id") Long id, @PathParam("sectionId") Long sectionId, SectionDto dto) {
        DestinationSectionEntity entity = sectionRepository.findById(sectionId);
        if (entity == null || !entity.destination.id.equals(id)) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(new ErrorResponse("Sección no encontrada", 404))
                    .build();
        }

        entity.title = dto.title;
        entity.contentHtml = htmlSanitizer.sanitize(dto.contentHtml);
        entity.sectionType = SectionType.valueOf(dto.sectionType);
        entity.icon = dto.icon;
        entity.sortOrder = dto.sortOrder != null ? dto.sortOrder : entity.sortOrder;
        entity.isVisible = dto.isVisible != null ? dto.isVisible : entity.isVisible;

        return Response.ok(toSectionDto(entity)).build();
    }

    /**
     * Elimina una sección.
     */
    @DELETE
    @Path("/{id}/sections/{sectionId}")
    @Transactional
    public Response deleteSection(@PathParam("id") Long id, @PathParam("sectionId") Long sectionId) {
        DestinationSectionEntity entity = sectionRepository.findById(sectionId);
        if (entity == null || !entity.destination.id.equals(id)) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(new ErrorResponse("Sección no encontrada", 404))
                    .build();
        }

        sectionRepository.delete(entity);
        return Response.noContent().build();
    }

    /**
     * Lista tipos de sección disponibles.
     */
    @GET
    @Path("/section-types")
    public Response getSectionTypes() {
        List<SectionTypeDto> types = java.util.Arrays.stream(SectionType.values())
                .map(t -> new SectionTypeDto(t.name(), t.getDisplayName()))
                .collect(Collectors.toList());
        return Response.ok(types).build();
    }

    // =====================================================
    // ENDPOINTS PARA FAQS
    // =====================================================

    /**
     * Lista FAQs de un destino.
     */
    @GET
    @Path("/{id}/faqs")
    public Response getFaqs(@PathParam("id") Long id) {
        DestinationEntity destination = destinationRepository.findById(id);
        if (destination == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(new ErrorResponse("Destino no encontrado", 404))
                    .build();
        }

        List<DestinationFaqEntity> faqs = faqRepository.findByDestinationId(id);
        List<FaqDto> dtos = faqs.stream()
                .map(this::toFaqDto)
                .collect(Collectors.toList());
        return Response.ok(dtos).build();
    }

    /**
     * Crea un FAQ para un destino.
     */
    @POST
    @Path("/{id}/faqs")
    @Transactional
    public Response createFaq(@PathParam("id") Long id, FaqDto dto) {
        DestinationEntity destination = destinationRepository.findById(id);
        if (destination == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(new ErrorResponse("Destino no encontrado", 404))
                    .build();
        }

        DestinationFaqEntity entity = new DestinationFaqEntity();
        entity.destination = destination;
        entity.question = dto.question;
        entity.answer = htmlSanitizer.sanitize(dto.answer);
        entity.sortOrder = dto.sortOrder != null ? dto.sortOrder : 0;
        entity.isVisible = dto.isVisible != null ? dto.isVisible : true;
        faqRepository.persist(entity);

        return Response.status(Response.Status.CREATED)
                .entity(toFaqDto(entity))
                .build();
    }

    /**
     * Actualiza un FAQ.
     */
    @PUT
    @Path("/{id}/faqs/{faqId}")
    @Transactional
    public Response updateFaq(@PathParam("id") Long id, @PathParam("faqId") Long faqId, FaqDto dto) {
        DestinationFaqEntity entity = faqRepository.findById(faqId);
        if (entity == null || !entity.destination.id.equals(id)) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(new ErrorResponse("FAQ no encontrado", 404))
                    .build();
        }

        entity.question = dto.question;
        entity.answer = htmlSanitizer.sanitize(dto.answer);
        entity.sortOrder = dto.sortOrder != null ? dto.sortOrder : entity.sortOrder;
        entity.isVisible = dto.isVisible != null ? dto.isVisible : entity.isVisible;

        return Response.ok(toFaqDto(entity)).build();
    }

    /**
     * Elimina un FAQ.
     */
    @DELETE
    @Path("/{id}/faqs/{faqId}")
    @Transactional
    public Response deleteFaq(@PathParam("id") Long id, @PathParam("faqId") Long faqId) {
        DestinationFaqEntity entity = faqRepository.findById(faqId);
        if (entity == null || !entity.destination.id.equals(id)) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(new ErrorResponse("FAQ no encontrado", 404))
                    .build();
        }

        faqRepository.delete(entity);
        return Response.noContent().build();
    }

    // =====================================================
    // ENDPOINTS PARA TAGS
    // =====================================================

    /**
     * Lista todos los tags disponibles.
     */
    @GET
    @Path("/tags")
    public Response getAllTags() {
        List<DestinationTagEntity> tags = tagRepository.findAllOrdered();
        List<TagDto> dtos = tags.stream()
                .map(this::toTagDto)
                .collect(Collectors.toList());
        return Response.ok(dtos).build();
    }

    /**
     * Crea un nuevo tag.
     */
    @POST
    @Path("/tags")
    @Transactional
    public Response createTag(TagDto dto) {
        if (tagRepository.findBySlug(dto.slug).isPresent()) {
            return Response.status(Response.Status.CONFLICT)
                    .entity(new ErrorResponse("Ya existe un tag con ese slug", 409))
                    .build();
        }

        DestinationTagEntity entity = new DestinationTagEntity();
        entity.name = dto.name;
        entity.slug = dto.slug;
        entity.icon = dto.icon;
        entity.color = dto.color;
        entity.description = dto.description;
        entity.sortOrder = dto.sortOrder != null ? dto.sortOrder : 0;
        entity.isActive = dto.isActive != null ? dto.isActive : true;
        tagRepository.persist(entity);

        return Response.status(Response.Status.CREATED)
                .entity(toTagDto(entity))
                .build();
    }

    /**
     * Actualiza un tag.
     */
    @PUT
    @Path("/tags/{tagId}")
    @Transactional
    public Response updateTag(@PathParam("tagId") Long tagId, TagDto dto) {
        DestinationTagEntity entity = tagRepository.findById(tagId);
        if (entity == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(new ErrorResponse("Tag no encontrado", 404))
                    .build();
        }

        entity.name = dto.name;
        entity.slug = dto.slug;
        entity.icon = dto.icon;
        entity.color = dto.color;
        entity.description = dto.description;
        entity.sortOrder = dto.sortOrder != null ? dto.sortOrder : entity.sortOrder;
        entity.isActive = dto.isActive != null ? dto.isActive : entity.isActive;

        return Response.ok(toTagDto(entity)).build();
    }

    /**
     * Lista tags asignados a un destino.
     */
    @GET
    @Path("/{id}/tags")
    public Response getDestinationTags(@PathParam("id") Long id) {
        DestinationEntity destination = destinationRepository.findById(id);
        if (destination == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(new ErrorResponse("Destino no encontrado", 404))
                    .build();
        }

        List<DestinationTagMappingEntity> mappings = tagMappingRepository.findByDestinationId(id);
        List<TagDto> dtos = mappings.stream()
                .map(m -> toTagDto(m.tag))
                .collect(Collectors.toList());
        return Response.ok(dtos).build();
    }

    /**
     * Asigna tags a un destino (reemplaza los existentes).
     */
    @PUT
    @Path("/{id}/tags")
    @Transactional
    public Response updateDestinationTags(@PathParam("id") Long id, List<Long> tagIds) {
        DestinationEntity destination = destinationRepository.findById(id);
        if (destination == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(new ErrorResponse("Destino no encontrado", 404))
                    .build();
        }

        // Eliminar mappings existentes
        tagMappingRepository.deleteByDestinationId(id);

        // Crear nuevos mappings
        if (tagIds != null) {
            for (Long tagId : tagIds) {
                DestinationTagEntity tag = tagRepository.findById(tagId);
                if (tag != null) {
                    DestinationTagMappingEntity mapping = new DestinationTagMappingEntity();
                    mapping.destination = destination;
                    mapping.tag = tag;
                    tagMappingRepository.persist(mapping);
                }
            }
        }

        // Retornar tags actualizados
        List<DestinationTagMappingEntity> mappings = tagMappingRepository.findByDestinationId(id);
        List<TagDto> dtos = mappings.stream()
                .map(m -> toTagDto(m.tag))
                .collect(Collectors.toList());
        return Response.ok(dtos).build();
    }

    // Métodos auxiliares

    private void updateEntityFromDto(DestinationEntity entity, DestinationAdminDto dto) {
        entity.slug = dto.slug;
        entity.name = dto.name;
        entity.country = dto.country;
        entity.region = dto.region;
        entity.city = dto.city;
        entity.summaryTitle = dto.summaryTitle;
        entity.summaryExcerpt = dto.summaryExcerpt;
        entity.summaryBadge = dto.summaryBadge;
        entity.summaryImageUrl = dto.summaryImageUrl;

        // SEO
        entity.metaTitle = dto.metaTitle;
        entity.metaDescription = dto.metaDescription;
        entity.featuredImageUrl = dto.featuredImageUrl;
        entity.readingTimeMinutes = dto.readingTimeMinutes;
        if (dto.publishedAt != null) {
            entity.publishedAt = LocalDateTime.parse(dto.publishedAt);
        }

        // Contenido legacy (sanitizado)
        entity.introText = dto.introText != null ? htmlSanitizer.sanitize(dto.introText) : null;
        entity.content = dto.content != null ? htmlSanitizer.sanitize(dto.content) : null;
        entity.highlightTips = dto.highlightTips;

        entity.isRecommended = dto.isRecommended != null ? dto.isRecommended : false;
        entity.recommendedOrder = dto.recommendedOrder != null ? dto.recommendedOrder : 0;
        entity.isActive = dto.isActive != null ? dto.isActive : true;

        // Categoría
        if (dto.categoryId != null) {
            entity.category = categoryRepository.findById(dto.categoryId);
        }
    }

    private DestinationAdminDto toAdminDto(DestinationEntity entity) {
        DestinationAdminDto dto = new DestinationAdminDto();
        dto.id = entity.id;
        dto.slug = entity.slug;
        dto.name = entity.name;
        dto.country = entity.country;
        dto.region = entity.region;
        dto.city = entity.city;
        dto.summaryTitle = entity.summaryTitle;
        dto.summaryExcerpt = entity.summaryExcerpt;
        dto.summaryBadge = entity.summaryBadge;
        dto.summaryImageUrl = entity.summaryImageUrl;

        // SEO
        dto.metaTitle = entity.metaTitle;
        dto.metaDescription = entity.metaDescription;
        dto.featuredImageUrl = entity.featuredImageUrl;
        dto.readingTimeMinutes = entity.readingTimeMinutes;
        dto.publishedAt = entity.publishedAt != null ? entity.publishedAt.toString() : null;

        dto.introText = entity.introText;
        dto.content = entity.content;
        dto.highlightTips = entity.highlightTips;
        dto.isRecommended = entity.isRecommended;
        dto.recommendedOrder = entity.recommendedOrder;
        dto.isActive = entity.isActive;
        dto.createdAt = entity.createdAt != null ? entity.createdAt.toString() : null;
        dto.updatedAt = entity.updatedAt != null ? entity.updatedAt.toString() : null;

        if (entity.category != null) {
            dto.categoryId = entity.category.id;
            dto.categoryName = entity.category.name;
        }

        // Imágenes
        List<DestinationImageEntity> images = imageRepository.findByDestinationId(entity.id);
        dto.images = images.stream()
                .map(img -> new ImageDto(img.id, img.imageUrl, img.altText, img.caption,
                        img.imageType != null ? img.imageType.name() : null,
                        img.isFeatured != null ? img.isFeatured : false))
                .collect(Collectors.toList());

        // Secciones
        List<DestinationSectionEntity> sections = sectionRepository.findByDestinationId(entity.id);
        dto.sections = sections.stream()
                .map(this::toSectionDto)
                .collect(Collectors.toList());

        // FAQs
        List<DestinationFaqEntity> faqs = faqRepository.findByDestinationId(entity.id);
        dto.faqs = faqs.stream()
                .map(this::toFaqDto)
                .collect(Collectors.toList());

        // Tags
        List<DestinationTagMappingEntity> tagMappings = tagMappingRepository.findByDestinationId(entity.id);
        dto.tags = tagMappings.stream()
                .map(m -> toTagDto(m.tag))
                .collect(Collectors.toList());

        return dto;
    }

    private SectionDto toSectionDto(DestinationSectionEntity entity) {
        SectionDto dto = new SectionDto();
        dto.id = entity.id;
        dto.title = entity.title;
        dto.contentHtml = entity.contentHtml;
        dto.sectionType = entity.sectionType.name();
        dto.sectionTypeDisplayName = entity.sectionType.getDisplayName();
        dto.icon = entity.icon;
        dto.sortOrder = entity.sortOrder;
        dto.isVisible = entity.isVisible;
        return dto;
    }

    private FaqDto toFaqDto(DestinationFaqEntity entity) {
        FaqDto dto = new FaqDto();
        dto.id = entity.id;
        dto.question = entity.question;
        dto.answer = entity.answer;
        dto.sortOrder = entity.sortOrder;
        dto.isVisible = entity.isVisible;
        return dto;
    }

    private TagDto toTagDto(DestinationTagEntity entity) {
        TagDto dto = new TagDto();
        dto.id = entity.id;
        dto.name = entity.name;
        dto.slug = entity.slug;
        dto.icon = entity.icon;
        dto.color = entity.color;
        dto.description = entity.description;
        dto.sortOrder = entity.sortOrder;
        dto.isActive = entity.isActive;
        return dto;
    }

    private CategoryAdminDto toCategoryDto(DestinationCategoryEntity entity) {
        CategoryAdminDto dto = new CategoryAdminDto();
        dto.id = entity.id;
        dto.name = entity.name;
        dto.slug = entity.slug;
        dto.description = entity.description;
        return dto;
    }

    // DTOs internos

    public static class DestinationAdminDto {
        public Long id;
        public String slug;
        public String name;
        public String country;
        public String region;
        public String city;
        public Long categoryId;
        public String categoryName;
        public String summaryTitle;
        public String summaryExcerpt;
        public String summaryBadge;
        public String summaryImageUrl;
        // SEO
        public String metaTitle;
        public String metaDescription;
        public String featuredImageUrl;
        public Integer readingTimeMinutes;
        public String publishedAt;
        // Contenido legacy
        public String introText;
        public String content;
        public String highlightTips;
        // Estado
        public Boolean isRecommended;
        public Integer recommendedOrder;
        public Boolean isActive;
        public String createdAt;
        public String updatedAt;
        // Relaciones
        public List<ImageDto> images;
        public List<SectionDto> sections;
        public List<FaqDto> faqs;
        public List<TagDto> tags;
    }

    public static class ImageDto {
        public Long id;
        public String url;
        public String alt;
        public String caption;
        public String imageType;
        public Boolean isFeatured;

        public ImageDto() {
        }

        public ImageDto(Long id, String url, String alt) {
            this.id = id;
            this.url = url;
            this.alt = alt;
        }

        public ImageDto(Long id, String url, String alt, String caption, String imageType, Boolean isFeatured) {
            this.id = id;
            this.url = url;
            this.alt = alt;
            this.caption = caption;
            this.imageType = imageType;
            this.isFeatured = isFeatured;
        }
    }

    public static class SectionDto {
        public Long id;
        public String title;
        public String contentHtml;
        public String sectionType;
        public String sectionTypeDisplayName;
        public String icon;
        public Integer sortOrder;
        public Boolean isVisible;
    }

    public static class SectionTypeDto {
        public String value;
        public String displayName;

        public SectionTypeDto(String value, String displayName) {
            this.value = value;
            this.displayName = displayName;
        }
    }

    public static class FaqDto {
        public Long id;
        public String question;
        public String answer;
        public Integer sortOrder;
        public Boolean isVisible;
    }

    public static class TagDto {
        public Long id;
        public String name;
        public String slug;
        public String icon;
        public String color;
        public String description;
        public Integer sortOrder;
        public Boolean isActive;
    }

    public static class CategoryAdminDto {
        public Long id;
        public String name;
        public String slug;
        public String description;
    }

    public static class ErrorResponse {
        public String message;
        public int code;

        public ErrorResponse(String message, int code) {
            this.message = message;
            this.code = code;
        }
    }
}
