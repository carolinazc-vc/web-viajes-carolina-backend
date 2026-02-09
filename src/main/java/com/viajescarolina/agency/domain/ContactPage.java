package com.viajescarolina.agency.domain;

import java.util.List;

/**
 * Modelo de dominio para la página "Contacto".
 */
public class ContactPage {

    private HeroSection hero;
    private AgencyContactInfo agencyInfo;
    private List<Office> offices;
    private ContactFormConfig contactForm;
    private SocialLinks socialLinks;

    public ContactPage() {
    }

    // Getters y Setters
    public HeroSection getHero() { return hero; }
    public void setHero(HeroSection hero) { this.hero = hero; }

    public AgencyContactInfo getAgencyInfo() { return agencyInfo; }
    public void setAgencyInfo(AgencyContactInfo agencyInfo) { this.agencyInfo = agencyInfo; }

    public List<Office> getOffices() { return offices; }
    public void setOffices(List<Office> offices) { this.offices = offices; }

    public ContactFormConfig getContactForm() { return contactForm; }
    public void setContactForm(ContactFormConfig contactForm) { this.contactForm = contactForm; }

    public SocialLinks getSocialLinks() { return socialLinks; }
    public void setSocialLinks(SocialLinks socialLinks) { this.socialLinks = socialLinks; }

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

    public static class AgencyContactInfo {
        private String name;
        private String primaryEmail;
        private String primaryPhone;
        private String whatsappNumber;
        private String whatsappMessage;

        public AgencyContactInfo() {}

        public String getName() { return name; }
        public void setName(String name) { this.name = name; }
        public String getPrimaryEmail() { return primaryEmail; }
        public void setPrimaryEmail(String primaryEmail) { this.primaryEmail = primaryEmail; }
        public String getPrimaryPhone() { return primaryPhone; }
        public void setPrimaryPhone(String primaryPhone) { this.primaryPhone = primaryPhone; }
        public String getWhatsappNumber() { return whatsappNumber; }
        public void setWhatsappNumber(String whatsappNumber) { this.whatsappNumber = whatsappNumber; }
        public String getWhatsappMessage() { return whatsappMessage; }
        public void setWhatsappMessage(String whatsappMessage) { this.whatsappMessage = whatsappMessage; }
    }

    public static class Office {
        private String id;
        private String name;
        private String address;
        private String city;
        private String country;
        private String phone;
        private String email;
        private Double latitude;
        private Double longitude;
        private boolean isMainOffice;
        private List<BusinessHour> businessHours;

        public Office() {}

        public String getId() { return id; }
        public void setId(String id) { this.id = id; }
        public String getName() { return name; }
        public void setName(String name) { this.name = name; }
        public String getAddress() { return address; }
        public void setAddress(String address) { this.address = address; }
        public String getCity() { return city; }
        public void setCity(String city) { this.city = city; }
        public String getCountry() { return country; }
        public void setCountry(String country) { this.country = country; }
        public String getPhone() { return phone; }
        public void setPhone(String phone) { this.phone = phone; }
        public String getEmail() { return email; }
        public void setEmail(String email) { this.email = email; }
        public Double getLatitude() { return latitude; }
        public void setLatitude(Double latitude) { this.latitude = latitude; }
        public Double getLongitude() { return longitude; }
        public void setLongitude(Double longitude) { this.longitude = longitude; }
        public boolean isMainOffice() { return isMainOffice; }
        public void setMainOffice(boolean mainOffice) { isMainOffice = mainOffice; }
        public List<BusinessHour> getBusinessHours() { return businessHours; }
        public void setBusinessHours(List<BusinessHour> businessHours) { this.businessHours = businessHours; }
    }

    public static class BusinessHour {
        private String dayOfWeek;
        private String openTime;
        private String closeTime;
        private boolean isClosed;
        private String notes;

        public BusinessHour() {}

        public BusinessHour(String dayOfWeek, String openTime, String closeTime, boolean isClosed, String notes) {
            this.dayOfWeek = dayOfWeek;
            this.openTime = openTime;
            this.closeTime = closeTime;
            this.isClosed = isClosed;
            this.notes = notes;
        }

        public String getDayOfWeek() { return dayOfWeek; }
        public void setDayOfWeek(String dayOfWeek) { this.dayOfWeek = dayOfWeek; }
        public String getOpenTime() { return openTime; }
        public void setOpenTime(String openTime) { this.openTime = openTime; }
        public String getCloseTime() { return closeTime; }
        public void setCloseTime(String closeTime) { this.closeTime = closeTime; }
        public boolean isClosed() { return isClosed; }
        public void setClosed(boolean closed) { isClosed = closed; }
        public String getNotes() { return notes; }
        public void setNotes(String notes) { this.notes = notes; }
    }

    public static class ContactFormConfig {
        private String title;
        private String subtitle;
        private String submitButtonText;
        private String successMessage;

        public ContactFormConfig() {}

        public String getTitle() { return title; }
        public void setTitle(String title) { this.title = title; }
        public String getSubtitle() { return subtitle; }
        public void setSubtitle(String subtitle) { this.subtitle = subtitle; }
        public String getSubmitButtonText() { return submitButtonText; }
        public void setSubmitButtonText(String submitButtonText) { this.submitButtonText = submitButtonText; }
        public String getSuccessMessage() { return successMessage; }
        public void setSuccessMessage(String successMessage) { this.successMessage = successMessage; }
    }

    public static class SocialLinks {
        private String facebook;
        private String instagram;
        private String twitter;
        private String youtube;
        private String linkedin;
        private String tiktok;

        public SocialLinks() {}

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
}
