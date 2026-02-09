package com.viajescarolina.agency.app;

import com.viajescarolina.agency.domain.AboutUsPage;
import com.viajescarolina.agency.domain.AboutUsPage.*;
import com.viajescarolina.agency.infra.jpa.entity.*;
import com.viajescarolina.content.infra.jpa.entity.PageHeroEntity;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Servicio para la página "Nosotros".
 */
@ApplicationScoped
public class AboutUsPageService {

    @Inject
    EntityManager entityManager;

    /**
     * Obtiene todos los datos para la página Nosotros.
     */
    public AboutUsPage getAboutUsPage() {
        AboutUsPage page = new AboutUsPage();

        // Obtener agencia principal
        AgencyInfoEntity agencyEntity = getMainAgency();
        if (agencyEntity == null) {
            return page;
        }

        // Hero
        page.setHero(getHero());

        // Info de la agencia
        page.setAgencyInfo(mapAgencyInfo(agencyEntity));

        // Misión y Visión
        page.setMissionVision(getMissionVision(agencyEntity.id));

        // Valores
        page.setValues(getValues(agencyEntity.id));

        // Historia
        page.setHistory(getHistory(agencyEntity.id));

        // Equipo
        page.setTeam(getTeam(agencyEntity.id));

        // Certificaciones
        page.setCertifications(getCertifications(agencyEntity.id));

        // Trust Section
        page.setTrustSection(getTrustSection(agencyEntity.id));

        // Galería
        page.setGallery(getGallery(agencyEntity.id));

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
                        "SELECT h FROM PageHeroEntity h JOIN h.page p WHERE p.slug = 'about-us' AND h.isActive = true",
                        PageHeroEntity.class
                )
                .setMaxResults(1)
                .getResultList();

        if (heroes.isEmpty()) {
            return null;
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

    private AgencyInfo mapAgencyInfo(AgencyInfoEntity entity) {
        AgencyInfo info = new AgencyInfo();
        info.setId(String.valueOf(entity.id));
        info.setName(entity.name);
        info.setSlogan(entity.slogan);
        info.setDescription(entity.description);
        info.setLogoUrl(entity.logoUrl);

        // Contact
        Contact contact = new Contact();
        contact.setEmail(entity.primaryEmail);
        contact.setPhone(entity.primaryPhone);
        contact.setWhatsapp(entity.whatsappNumber);
        contact.setAddress(entity.mainAddress);
        contact.setCity(entity.city);
        contact.setCountry(entity.country);
        info.setContact(contact);

        // Social
        Social social = new Social();
        List<AgencySocialLinkEntity> socialLinks = entityManager
                .createQuery(
                        "SELECT s FROM AgencySocialLinkEntity s WHERE s.agency.id = :agencyId AND s.isActive = true ORDER BY s.sortOrder ASC",
                        AgencySocialLinkEntity.class
                )
                .setParameter("agencyId", entity.id)
                .getResultList();

        for (AgencySocialLinkEntity link : socialLinks) {
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
        info.setSocial(social);

        return info;
    }

    private MissionVision getMissionVision(Long agencyId) {
        List<AgencyMissionVisionEntity> entities = entityManager
                .createQuery(
                        "SELECT m FROM AgencyMissionVisionEntity m WHERE m.agency.id = :agencyId AND m.isActive = true",
                        AgencyMissionVisionEntity.class
                )
                .setParameter("agencyId", agencyId)
                .setMaxResults(1)
                .getResultList();

        if (entities.isEmpty()) {
            return null;
        }

        AgencyMissionVisionEntity entity = entities.get(0);
        MissionVision mv = new MissionVision();
        mv.setMissionTitle(entity.missionTitle);
        mv.setMissionText(entity.missionText);
        mv.setMissionIcon(entity.missionIcon);
        mv.setVisionTitle(entity.visionTitle);
        mv.setVisionText(entity.visionText);
        mv.setVisionIcon(entity.visionIcon);
        return mv;
    }

    private List<Value> getValues(Long agencyId) {
        List<AgencyValueEntity> entities = entityManager
                .createQuery(
                        "SELECT v FROM AgencyValueEntity v WHERE v.agency.id = :agencyId AND v.isActive = true ORDER BY v.sortOrder ASC",
                        AgencyValueEntity.class
                )
                .setParameter("agencyId", agencyId)
                .getResultList();

        return entities.stream()
                .map(e -> new Value(String.valueOf(e.id), e.title, e.description, e.icon))
                .collect(Collectors.toList());
    }

    private History getHistory(Long agencyId) {
        List<AgencyHistoryEntity> entities = entityManager
                .createQuery(
                        "SELECT h FROM AgencyHistoryEntity h WHERE h.agency.id = :agencyId AND h.isActive = true",
                        AgencyHistoryEntity.class
                )
                .setParameter("agencyId", agencyId)
                .setMaxResults(1)
                .getResultList();

        if (entities.isEmpty()) {
            return null;
        }

        AgencyHistoryEntity entity = entities.get(0);
        History history = new History();
        history.setTitle(entity.introTitle);
        history.setIntroText(entity.introText);

        // Milestones
        List<AgencyHistoryMilestoneEntity> milestoneEntities = entityManager
                .createQuery(
                        "SELECT m FROM AgencyHistoryMilestoneEntity m WHERE m.history.id = :historyId AND m.isActive = true ORDER BY m.sortOrder ASC",
                        AgencyHistoryMilestoneEntity.class
                )
                .setParameter("historyId", entity.id)
                .getResultList();

        List<Milestone> milestones = milestoneEntities.stream()
                .map(m -> new Milestone(String.valueOf(m.id), m.year, m.title, m.description, m.imageUrl))
                .collect(Collectors.toList());
        history.setMilestones(milestones);

        return history;
    }

    private Team getTeam(Long agencyId) {
        List<AgencyTeamSectionEntity> entities = entityManager
                .createQuery(
                        "SELECT t FROM AgencyTeamSectionEntity t WHERE t.agency.id = :agencyId AND t.isActive = true",
                        AgencyTeamSectionEntity.class
                )
                .setParameter("agencyId", agencyId)
                .setMaxResults(1)
                .getResultList();

        if (entities.isEmpty()) {
            return null;
        }

        AgencyTeamSectionEntity entity = entities.get(0);
        Team team = new Team();
        team.setTitle(entity.title);
        team.setSubtitle(entity.subtitle);

        // Members
        List<AgencyTeamMemberEntity> memberEntities = entityManager
                .createQuery(
                        "SELECT m FROM AgencyTeamMemberEntity m WHERE m.teamSection.id = :sectionId AND m.isActive = true ORDER BY m.sortOrder ASC",
                        AgencyTeamMemberEntity.class
                )
                .setParameter("sectionId", entity.id)
                .getResultList();

        List<TeamMember> members = memberEntities.stream()
                .map(m -> {
                    TeamMember member = new TeamMember(String.valueOf(m.id), m.name, m.position, m.bio, m.photoUrl);
                    member.setEmail(m.email);
                    member.setLinkedinUrl(m.linkedinUrl);
                    return member;
                })
                .collect(Collectors.toList());
        team.setMembers(members);

        return team;
    }

    private List<Certification> getCertifications(Long agencyId) {
        List<AgencyCertificationEntity> entities = entityManager
                .createQuery(
                        "SELECT c FROM AgencyCertificationEntity c WHERE c.agency.id = :agencyId AND c.isActive = true ORDER BY c.sortOrder ASC",
                        AgencyCertificationEntity.class
                )
                .setParameter("agencyId", agencyId)
                .getResultList();

        return entities.stream()
                .map(e -> new Certification(String.valueOf(e.id), e.name, e.issuer, e.description, e.logoUrl))
                .collect(Collectors.toList());
    }

    private TrustSection getTrustSection(Long agencyId) {
        List<TrustSectionEntity> entities = entityManager
                .createQuery(
                        "SELECT t FROM TrustSectionEntity t WHERE t.agency.id = :agencyId AND t.isActive = true",
                        TrustSectionEntity.class
                )
                .setParameter("agencyId", agencyId)
                .setMaxResults(1)
                .getResultList();

        if (entities.isEmpty()) {
            // Fallback: buscar sin filtrar por agency
            entities = entityManager
                    .createQuery(
                            "SELECT t FROM TrustSectionEntity t WHERE t.isActive = true ORDER BY t.id ASC",
                            TrustSectionEntity.class
                    )
                    .setMaxResults(1)
                    .getResultList();
        }

        if (entities.isEmpty()) {
            return null;
        }

        TrustSectionEntity entity = entities.get(0);
        TrustSection section = new TrustSection();
        section.setTitle(entity.title);
        section.setSubtitle(entity.subtitle);
        section.setAgencyImageUrl(entity.agencyImageUrl);
        section.setAgencyDescription(entity.agencyDescription);

        // Badges
        if (entity.badges != null) {
            List<AboutUsPage.TrustBadge> badges = entity.badges.stream()
                    .map(b -> new AboutUsPage.TrustBadge(String.valueOf(b.id), b.icon, b.title, b.description))
                    .collect(Collectors.toList());
            section.setBadges(badges);
        } else {
            section.setBadges(new ArrayList<>());
        }

        return section;
    }

    private List<GalleryImage> getGallery(Long agencyId) {
        List<AgencyGalleryImageEntity> entities = entityManager
                .createQuery(
                        "SELECT g FROM AgencyGalleryImageEntity g WHERE g.agency.id = :agencyId AND g.isActive = true ORDER BY g.sortOrder ASC",
                        AgencyGalleryImageEntity.class
                )
                .setParameter("agencyId", agencyId)
                .getResultList();

        return entities.stream()
                .map(e -> {
                    GalleryImage img = new GalleryImage(String.valueOf(e.id), e.imageUrl, e.altText, e.caption, e.category);
                    img.setThumbnailUrl(e.thumbnailUrl);
                    return img;
                })
                .collect(Collectors.toList());
    }
}
