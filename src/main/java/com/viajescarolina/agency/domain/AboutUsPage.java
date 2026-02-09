package com.viajescarolina.agency.domain;

import java.util.List;

/**
 * Modelo de dominio para la página "Nosotros" completa.
 */
public class AboutUsPage {

    private HeroSection hero;
    private AgencyInfo agencyInfo;
    private MissionVision missionVision;
    private List<Value> values;
    private History history;
    private Team team;
    private List<Certification> certifications;
    private TrustSection trustSection;
    private List<GalleryImage> gallery;

    public AboutUsPage() {
    }

    // Getters y Setters
    public HeroSection getHero() { return hero; }
    public void setHero(HeroSection hero) { this.hero = hero; }

    public AgencyInfo getAgencyInfo() { return agencyInfo; }
    public void setAgencyInfo(AgencyInfo agencyInfo) { this.agencyInfo = agencyInfo; }

    public MissionVision getMissionVision() { return missionVision; }
    public void setMissionVision(MissionVision missionVision) { this.missionVision = missionVision; }

    public List<Value> getValues() { return values; }
    public void setValues(List<Value> values) { this.values = values; }

    public History getHistory() { return history; }
    public void setHistory(History history) { this.history = history; }

    public Team getTeam() { return team; }
    public void setTeam(Team team) { this.team = team; }

    public List<Certification> getCertifications() { return certifications; }
    public void setCertifications(List<Certification> certifications) { this.certifications = certifications; }

    public TrustSection getTrustSection() { return trustSection; }
    public void setTrustSection(TrustSection trustSection) { this.trustSection = trustSection; }

    public List<GalleryImage> getGallery() { return gallery; }
    public void setGallery(List<GalleryImage> gallery) { this.gallery = gallery; }

    // Clases internas
    public static class HeroSection {
        private String badgeIcon;
        private String badgeText;
        private String titleLine1;
        private String titleLine2;
        private String subtitle;
        private String imageUrl;
        private String imageAlt;

        public HeroSection() {}

        public String getBadgeIcon() { return badgeIcon; }
        public void setBadgeIcon(String badgeIcon) { this.badgeIcon = badgeIcon; }
        public String getBadgeText() { return badgeText; }
        public void setBadgeText(String badgeText) { this.badgeText = badgeText; }
        public String getTitleLine1() { return titleLine1; }
        public void setTitleLine1(String titleLine1) { this.titleLine1 = titleLine1; }
        public String getTitleLine2() { return titleLine2; }
        public void setTitleLine2(String titleLine2) { this.titleLine2 = titleLine2; }
        public String getSubtitle() { return subtitle; }
        public void setSubtitle(String subtitle) { this.subtitle = subtitle; }
        public String getImageUrl() { return imageUrl; }
        public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }
        public String getImageAlt() { return imageAlt; }
        public void setImageAlt(String imageAlt) { this.imageAlt = imageAlt; }
    }

    public static class AgencyInfo {
        private String id;
        private String name;
        private String slogan;
        private String description;
        private String logoUrl;
        private Contact contact;
        private Social social;

        public AgencyInfo() {}

        public String getId() { return id; }
        public void setId(String id) { this.id = id; }
        public String getName() { return name; }
        public void setName(String name) { this.name = name; }
        public String getSlogan() { return slogan; }
        public void setSlogan(String slogan) { this.slogan = slogan; }
        public String getDescription() { return description; }
        public void setDescription(String description) { this.description = description; }
        public String getLogoUrl() { return logoUrl; }
        public void setLogoUrl(String logoUrl) { this.logoUrl = logoUrl; }
        public Contact getContact() { return contact; }
        public void setContact(Contact contact) { this.contact = contact; }
        public Social getSocial() { return social; }
        public void setSocial(Social social) { this.social = social; }
    }

    public static class Contact {
        private String email;
        private String phone;
        private String whatsapp;
        private String address;
        private String city;
        private String country;

        public Contact() {}

        public String getEmail() { return email; }
        public void setEmail(String email) { this.email = email; }
        public String getPhone() { return phone; }
        public void setPhone(String phone) { this.phone = phone; }
        public String getWhatsapp() { return whatsapp; }
        public void setWhatsapp(String whatsapp) { this.whatsapp = whatsapp; }
        public String getAddress() { return address; }
        public void setAddress(String address) { this.address = address; }
        public String getCity() { return city; }
        public void setCity(String city) { this.city = city; }
        public String getCountry() { return country; }
        public void setCountry(String country) { this.country = country; }
    }

    public static class Social {
        private String facebook;
        private String instagram;
        private String twitter;
        private String youtube;
        private String linkedin;
        private String tiktok;

        public Social() {}

        public String getFacebook() { return facebook; }
        public void setFacebook(String facebook) { this.facebook = facebook; }
        public String getInstagram() { return instagram; }
        public void setInstagram(String instagram) { this.instagram = instagram; }
        public String getTwitter() { return twitter; }
        public void setTwitter(String twitter) { this.twitter = twitter; }
        public String getYoutube() { return youtube; }
        public void setYoutube(String youtube) { this.youtube = youtube; }
        public String getLinkedin() { return linkedin; }
        public void setLinkedin(String linkedin) { this.linkedin = linkedin; }
        public String getTiktok() { return tiktok; }
        public void setTiktok(String tiktok) { this.tiktok = tiktok; }
    }

    public static class MissionVision {
        private String missionTitle;
        private String missionText;
        private String missionIcon;
        private String visionTitle;
        private String visionText;
        private String visionIcon;

        public MissionVision() {}

        public String getMissionTitle() { return missionTitle; }
        public void setMissionTitle(String missionTitle) { this.missionTitle = missionTitle; }
        public String getMissionText() { return missionText; }
        public void setMissionText(String missionText) { this.missionText = missionText; }
        public String getMissionIcon() { return missionIcon; }
        public void setMissionIcon(String missionIcon) { this.missionIcon = missionIcon; }
        public String getVisionTitle() { return visionTitle; }
        public void setVisionTitle(String visionTitle) { this.visionTitle = visionTitle; }
        public String getVisionText() { return visionText; }
        public void setVisionText(String visionText) { this.visionText = visionText; }
        public String getVisionIcon() { return visionIcon; }
        public void setVisionIcon(String visionIcon) { this.visionIcon = visionIcon; }
    }

    public static class Value {
        private String id;
        private String title;
        private String description;
        private String icon;

        public Value() {}

        public Value(String id, String title, String description, String icon) {
            this.id = id;
            this.title = title;
            this.description = description;
            this.icon = icon;
        }

        public String getId() { return id; }
        public void setId(String id) { this.id = id; }
        public String getTitle() { return title; }
        public void setTitle(String title) { this.title = title; }
        public String getDescription() { return description; }
        public void setDescription(String description) { this.description = description; }
        public String getIcon() { return icon; }
        public void setIcon(String icon) { this.icon = icon; }
    }

    public static class History {
        private String title;
        private String introText;
        private List<Milestone> milestones;

        public History() {}

        public String getTitle() { return title; }
        public void setTitle(String title) { this.title = title; }
        public String getIntroText() { return introText; }
        public void setIntroText(String introText) { this.introText = introText; }
        public List<Milestone> getMilestones() { return milestones; }
        public void setMilestones(List<Milestone> milestones) { this.milestones = milestones; }
    }

    public static class Milestone {
        private String id;
        private String year;
        private String title;
        private String description;
        private String imageUrl;

        public Milestone() {}

        public Milestone(String id, String year, String title, String description, String imageUrl) {
            this.id = id;
            this.year = year;
            this.title = title;
            this.description = description;
            this.imageUrl = imageUrl;
        }

        public String getId() { return id; }
        public void setId(String id) { this.id = id; }
        public String getYear() { return year; }
        public void setYear(String year) { this.year = year; }
        public String getTitle() { return title; }
        public void setTitle(String title) { this.title = title; }
        public String getDescription() { return description; }
        public void setDescription(String description) { this.description = description; }
        public String getImageUrl() { return imageUrl; }
        public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }
    }

    public static class Team {
        private String title;
        private String subtitle;
        private List<TeamMember> members;

        public Team() {}

        public String getTitle() { return title; }
        public void setTitle(String title) { this.title = title; }
        public String getSubtitle() { return subtitle; }
        public void setSubtitle(String subtitle) { this.subtitle = subtitle; }
        public List<TeamMember> getMembers() { return members; }
        public void setMembers(List<TeamMember> members) { this.members = members; }
    }

    public static class TeamMember {
        private String id;
        private String name;
        private String position;
        private String bio;
        private String photoUrl;
        private String email;
        private String linkedinUrl;

        public TeamMember() {}

        public TeamMember(String id, String name, String position, String bio, String photoUrl) {
            this.id = id;
            this.name = name;
            this.position = position;
            this.bio = bio;
            this.photoUrl = photoUrl;
        }

        public String getId() { return id; }
        public void setId(String id) { this.id = id; }
        public String getName() { return name; }
        public void setName(String name) { this.name = name; }
        public String getPosition() { return position; }
        public void setPosition(String position) { this.position = position; }
        public String getBio() { return bio; }
        public void setBio(String bio) { this.bio = bio; }
        public String getPhotoUrl() { return photoUrl; }
        public void setPhotoUrl(String photoUrl) { this.photoUrl = photoUrl; }
        public String getEmail() { return email; }
        public void setEmail(String email) { this.email = email; }
        public String getLinkedinUrl() { return linkedinUrl; }
        public void setLinkedinUrl(String linkedinUrl) { this.linkedinUrl = linkedinUrl; }
    }

    public static class Certification {
        private String id;
        private String name;
        private String issuer;
        private String description;
        private String logoUrl;

        public Certification() {}

        public Certification(String id, String name, String issuer, String description, String logoUrl) {
            this.id = id;
            this.name = name;
            this.issuer = issuer;
            this.description = description;
            this.logoUrl = logoUrl;
        }

        public String getId() { return id; }
        public void setId(String id) { this.id = id; }
        public String getName() { return name; }
        public void setName(String name) { this.name = name; }
        public String getIssuer() { return issuer; }
        public void setIssuer(String issuer) { this.issuer = issuer; }
        public String getDescription() { return description; }
        public void setDescription(String description) { this.description = description; }
        public String getLogoUrl() { return logoUrl; }
        public void setLogoUrl(String logoUrl) { this.logoUrl = logoUrl; }
    }

    public static class TrustSection {
        private String title;
        private String subtitle;
        private String agencyImageUrl;
        private String agencyDescription;
        private List<TrustBadge> badges;

        public TrustSection() {}

        public String getTitle() { return title; }
        public void setTitle(String title) { this.title = title; }
        public String getSubtitle() { return subtitle; }
        public void setSubtitle(String subtitle) { this.subtitle = subtitle; }
        public String getAgencyImageUrl() { return agencyImageUrl; }
        public void setAgencyImageUrl(String agencyImageUrl) { this.agencyImageUrl = agencyImageUrl; }
        public String getAgencyDescription() { return agencyDescription; }
        public void setAgencyDescription(String agencyDescription) { this.agencyDescription = agencyDescription; }
        public List<TrustBadge> getBadges() { return badges; }
        public void setBadges(List<TrustBadge> badges) { this.badges = badges; }
    }

    public static class TrustBadge {
        private String id;
        private String icon;
        private String title;
        private String description;

        public TrustBadge() {}

        public TrustBadge(String id, String icon, String title, String description) {
            this.id = id;
            this.icon = icon;
            this.title = title;
            this.description = description;
        }

        public String getId() { return id; }
        public void setId(String id) { this.id = id; }
        public String getIcon() { return icon; }
        public void setIcon(String icon) { this.icon = icon; }
        public String getTitle() { return title; }
        public void setTitle(String title) { this.title = title; }
        public String getDescription() { return description; }
        public void setDescription(String description) { this.description = description; }
    }

    public static class GalleryImage {
        private String id;
        private String imageUrl;
        private String thumbnailUrl;
        private String altText;
        private String caption;
        private String category;

        public GalleryImage() {}

        public GalleryImage(String id, String imageUrl, String altText, String caption, String category) {
            this.id = id;
            this.imageUrl = imageUrl;
            this.altText = altText;
            this.caption = caption;
            this.category = category;
        }

        public String getId() { return id; }
        public void setId(String id) { this.id = id; }
        public String getImageUrl() { return imageUrl; }
        public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }
        public String getThumbnailUrl() { return thumbnailUrl; }
        public void setThumbnailUrl(String thumbnailUrl) { this.thumbnailUrl = thumbnailUrl; }
        public String getAltText() { return altText; }
        public void setAltText(String altText) { this.altText = altText; }
        public String getCaption() { return caption; }
        public void setCaption(String caption) { this.caption = caption; }
        public String getCategory() { return category; }
        public void setCategory(String category) { this.category = category; }
    }
}
