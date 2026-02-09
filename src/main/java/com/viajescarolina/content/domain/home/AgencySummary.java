package com.viajescarolina.content.domain.home;

/**
 * Representa la información resumida de la agencia en el home.
 */
public class AgencySummary {
    private String id;
    private String name;
    private String description;
    private AgencyContact contact;
    private AgencySocial social;
    private AgencyBusinessHours businessHours;

    public AgencySummary() {
    }

    public AgencySummary(String id, String name, String description,
                        AgencyContact contact, AgencySocial social,
                        AgencyBusinessHours businessHours) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.contact = contact;
        this.social = social;
        this.businessHours = businessHours;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public AgencyContact getContact() {
        return contact;
    }

    public void setContact(AgencyContact contact) {
        this.contact = contact;
    }

    public AgencySocial getSocial() {
        return social;
    }

    public void setSocial(AgencySocial social) {
        this.social = social;
    }

    public AgencyBusinessHours getBusinessHours() {
        return businessHours;
    }

    public void setBusinessHours(AgencyBusinessHours businessHours) {
        this.businessHours = businessHours;
    }

    public static class AgencyContact {
        private String email;
        private String phone;
        private String whatsapp;
        private String address;
        private String city;
        private String country;

        public AgencyContact() {
        }

        public AgencyContact(String email, String phone, String whatsapp,
                           String address, String city, String country) {
            this.email = email;
            this.phone = phone;
            this.whatsapp = whatsapp;
            this.address = address;
            this.city = city;
            this.country = country;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getWhatsapp() {
            return whatsapp;
        }

        public void setWhatsapp(String whatsapp) {
            this.whatsapp = whatsapp;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }
    }

    public static class AgencySocial {
        private String facebook;
        private String instagram;
        private String twitter;
        private String youtube;

        public AgencySocial() {
        }

        public AgencySocial(String facebook, String instagram, String twitter, String youtube) {
            this.facebook = facebook;
            this.instagram = instagram;
            this.twitter = twitter;
            this.youtube = youtube;
        }

        public String getFacebook() {
            return facebook;
        }

        public void setFacebook(String facebook) {
            this.facebook = facebook;
        }

        public String getInstagram() {
            return instagram;
        }

        public void setInstagram(String instagram) {
            this.instagram = instagram;
        }

        public String getTwitter() {
            return twitter;
        }

        public void setTwitter(String twitter) {
            this.twitter = twitter;
        }

        public String getYoutube() {
            return youtube;
        }

        public void setYoutube(String youtube) {
            this.youtube = youtube;
        }
    }

    public static class AgencyBusinessHours {
        private String weekdays;
        private String saturday;
        private String sunday;

        public AgencyBusinessHours() {
        }

        public AgencyBusinessHours(String weekdays, String saturday, String sunday) {
            this.weekdays = weekdays;
            this.saturday = saturday;
            this.sunday = sunday;
        }

        public String getWeekdays() {
            return weekdays;
        }

        public void setWeekdays(String weekdays) {
            this.weekdays = weekdays;
        }

        public String getSaturday() {
            return saturday;
        }

        public void setSaturday(String saturday) {
            this.saturday = saturday;
        }

        public String getSunday() {
            return sunday;
        }

        public void setSunday(String sunday) {
            this.sunday = sunday;
        }
    }
}

