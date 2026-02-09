package com.viajescarolina.agency.app;

import com.viajescarolina.agency.domain.ContactPage;
import com.viajescarolina.agency.domain.ContactPage.*;
import com.viajescarolina.agency.infra.jpa.entity.*;
import com.viajescarolina.content.infra.jpa.entity.PageHeroEntity;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Servicio para la página "Contacto".
 */
@ApplicationScoped
public class ContactPageService {

    @Inject
    EntityManager entityManager;

    /**
     * Obtiene todos los datos para la página Contacto.
     */
    public ContactPage getContactPage() {
        ContactPage page = new ContactPage();

        // Obtener agencia principal
        AgencyInfoEntity agencyEntity = getMainAgency();
        if (agencyEntity == null) {
            return page;
        }

        // Hero
        page.setHero(getHero());

        // Info de contacto de la agencia
        page.setAgencyInfo(mapAgencyContactInfo(agencyEntity));

        // Oficinas con horarios
        page.setOffices(getOffices(agencyEntity.id));

        // Configuración del formulario
        page.setContactForm(getContactFormConfig());

        // Redes sociales
        page.setSocialLinks(getSocialLinks(agencyEntity.id));

        return page;
    }

    private AgencyInfoEntity getMainAgency() {
        List<AgencyInfoEntity> agencies = entityManager
                .createQuery("SELECT a FROM AgencyInfoEntity a ORDER BY a.id ASC", AgencyInfoEntity.class)
                .setMaxResults(1)
                .getResultList();
        return agencies.isEmpty() ? null : agencies.get(0);
    }

    private HeroSection getHero() {
        List<PageHeroEntity> heroes = entityManager
                .createQuery(
                        "SELECT h FROM PageHeroEntity h JOIN h.page p WHERE p.slug = 'contact' AND h.isActive = true",
                        PageHeroEntity.class
                )
                .setMaxResults(1)
                .getResultList();

        if (heroes.isEmpty()) {
            // Hero por defecto si no existe en BD
            HeroSection hero = new HeroSection();
            hero.setBadgeIcon("📞");
            hero.setBadgeText("Estamos para ayudarte");
            hero.setTitleLine1("Contáctanos");
            hero.setTitleLine2("");
            hero.setSubtitle("Estamos listos para ayudarte a planificar tu próxima aventura");
            return hero;
        }

        PageHeroEntity entity = heroes.get(0);
        HeroSection hero = new HeroSection();
        hero.setBadgeIcon(entity.badgeIcon);
        hero.setBadgeText(entity.badgeText);
        hero.setTitleLine1(entity.titleLine1);
        hero.setTitleLine2(entity.titleLine2);
        hero.setSubtitle(entity.subtitle);
        hero.setImageUrl(entity.imageUrl);
        hero.setImageAlt(entity.imageAlt);
        return hero;
    }

    private AgencyContactInfo mapAgencyContactInfo(AgencyInfoEntity entity) {
        AgencyContactInfo info = new AgencyContactInfo();
        info.setName(entity.name);
        info.setPrimaryEmail(entity.primaryEmail);
        info.setPrimaryPhone(entity.primaryPhone);
        info.setWhatsappNumber(entity.whatsappNumber);
        info.setWhatsappMessage("Hola! Me gustaría obtener información sobre sus servicios de viaje.");
        return info;
    }

    private List<Office> getOffices(Long agencyId) {
        List<OfficeEntity> officeEntities = entityManager
                .createQuery(
                        "SELECT o FROM OfficeEntity o WHERE o.agency.id = :agencyId AND o.isActive = true ORDER BY o.isMainOffice DESC, o.name ASC",
                        OfficeEntity.class
                )
                .setParameter("agencyId", agencyId)
                .getResultList();

        return officeEntities.stream()
                .map(this::mapOffice)
                .collect(Collectors.toList());
    }

    private Office mapOffice(OfficeEntity entity) {
        Office office = new Office();
        office.setId(String.valueOf(entity.id));
        office.setName(entity.name);
        office.setAddress(entity.address);
        office.setCity(entity.city);
        office.setCountry(entity.country);
        office.setPhone(entity.phone);
        office.setEmail(entity.email);
        office.setLatitude(entity.latitude != null ? entity.latitude.doubleValue() : null);
        office.setLongitude(entity.longitude != null ? entity.longitude.doubleValue() : null);
        office.setMainOffice(entity.isMainOffice != null && entity.isMainOffice);

        // Horarios de la oficina
        List<OfficeBusinessHourEntity> hourEntities = entityManager
                .createQuery(
                        "SELECT h FROM OfficeBusinessHourEntity h WHERE h.office.id = :officeId ORDER BY h.id ASC",
                        OfficeBusinessHourEntity.class
                )
                .setParameter("officeId", entity.id)
                .getResultList();

        List<BusinessHour> businessHours = hourEntities.stream()
                .map(h -> new BusinessHour(
                        h.dayOfWeek,
                        h.openTime != null ? h.openTime.toString() : null,
                        h.closeTime != null ? h.closeTime.toString() : null,
                        h.isClosed != null && h.isClosed,
                        h.notes
                ))
                .collect(Collectors.toList());
        office.setBusinessHours(businessHours);

        return office;
    }

    private ContactFormConfig getContactFormConfig() {
        // Configuración por defecto del formulario
        ContactFormConfig config = new ContactFormConfig();
        config.setTitle("Envíanos un mensaje");
        config.setSubtitle("Completa el formulario y te responderemos a la brevedad");
        config.setSubmitButtonText("Enviar mensaje");
        config.setSuccessMessage("¡Gracias por contactarnos! Te responderemos pronto.");
        return config;
    }

    private SocialLinks getSocialLinks(Long agencyId) {
        List<AgencySocialLinkEntity> socialEntities = entityManager
                .createQuery(
                        "SELECT s FROM AgencySocialLinkEntity s WHERE s.agency.id = :agencyId AND s.isActive = true ORDER BY s.sortOrder ASC",
                        AgencySocialLinkEntity.class
                )
                .setParameter("agencyId", agencyId)
                .getResultList();

        SocialLinks social = new SocialLinks();
        for (AgencySocialLinkEntity link : socialEntities) {
            String platform = link.platform != null ? link.platform.toUpperCase() : "";
            switch (platform) {
                case "FACEBOOK" -> social.setFacebook(link.url);
                case "INSTAGRAM" -> social.setInstagram(link.url);
                case "TWITTER" -> social.setTwitter(link.url);
                case "YOUTUBE" -> social.setYoutube(link.url);
                case "LINKEDIN" -> social.setLinkedin(link.url);
                case "TIKTOK" -> social.setTiktok(link.url);
            }
        }
        return social;
    }
}
