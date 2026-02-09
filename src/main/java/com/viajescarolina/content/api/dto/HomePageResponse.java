package com.viajescarolina.content.api.dto;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * DTO principal de respuesta para el endpoint GET /api/pages/home
 */
public class HomePageResponse {

    @JsonProperty("hero")
    private HeroDto hero;

    @JsonProperty("trustBadges")
    private TrustBadgesSectionDto trustBadges;

    @JsonProperty("promotions")
    private PromotionsSectionDto promotions;

    @JsonProperty("topDestinations")
    private TopDestinationsSectionDto topDestinations;

    @JsonProperty("travelersTestimonials")
    private TravelersTestimonialsSectionDto travelersTestimonials;

    @JsonProperty("cta")
    private CtaSectionDto cta;

    @JsonProperty("agencyInfo")
    private AgencyInfoDto agencyInfo;

    public HomePageResponse() {
    }

    public HomePageResponse(HeroDto hero, TrustBadgesSectionDto trustBadges,
                           PromotionsSectionDto promotions, TopDestinationsSectionDto topDestinations,
                           TravelersTestimonialsSectionDto travelersTestimonials,
                           CtaSectionDto cta, AgencyInfoDto agencyInfo) {
        this.hero = hero;
        this.trustBadges = trustBadges;
        this.promotions = promotions;
        this.topDestinations = topDestinations;
        this.travelersTestimonials = travelersTestimonials;
        this.cta = cta;
        this.agencyInfo = agencyInfo;
    }

    // Getters y Setters
    public HeroDto getHero() {
        return hero;
    }

    public void setHero(HeroDto hero) {
        this.hero = hero;
    }

    public TrustBadgesSectionDto getTrustBadges() {
        return trustBadges;
    }

    public void setTrustBadges(TrustBadgesSectionDto trustBadges) {
        this.trustBadges = trustBadges;
    }

    public PromotionsSectionDto getPromotions() {
        return promotions;
    }

    public void setPromotions(PromotionsSectionDto promotions) {
        this.promotions = promotions;
    }

    public TopDestinationsSectionDto getTopDestinations() {
        return topDestinations;
    }

    public void setTopDestinations(TopDestinationsSectionDto topDestinations) {
        this.topDestinations = topDestinations;
    }

    public TravelersTestimonialsSectionDto getTravelersTestimonials() {
        return travelersTestimonials;
    }

    public void setTravelersTestimonials(TravelersTestimonialsSectionDto travelersTestimonials) {
        this.travelersTestimonials = travelersTestimonials;
    }

    public CtaSectionDto getCta() {
        return cta;
    }

    public void setCta(CtaSectionDto cta) {
        this.cta = cta;
    }

    public AgencyInfoDto getAgencyInfo() {
        return agencyInfo;
    }

    public void setAgencyInfo(AgencyInfoDto agencyInfo) {
        this.agencyInfo = agencyInfo;
    }

    // DTOs internas
    public static class HeroDto {
        @JsonProperty("badge")
        private BadgeDto badge;

        @JsonProperty("title")
        private TitleDto title;

        @JsonProperty("subtitle")
        private String subtitle;

        @JsonProperty("stats")
        private List<StatDto> stats;

        @JsonProperty("ctas")
        private List<CtaDto> ctas;

        @JsonProperty("trustIndicators")
        private List<TrustIndicatorDto> trustIndicators;

        @JsonProperty("image")
        private ImageDto image;

        @JsonProperty("floatingBadge")
        private FloatingBadgeDto floatingBadge;

        public HeroDto() {
        }

        public BadgeDto getBadge() {
            return badge;
        }

        public void setBadge(BadgeDto badge) {
            this.badge = badge;
        }

        public TitleDto getTitle() {
            return title;
        }

        public void setTitle(TitleDto title) {
            this.title = title;
        }

        public String getSubtitle() {
            return subtitle;
        }

        public void setSubtitle(String subtitle) {
            this.subtitle = subtitle;
        }

        public List<StatDto> getStats() {
            return stats;
        }

        public void setStats(List<StatDto> stats) {
            this.stats = stats;
        }

        public List<CtaDto> getCtas() {
            return ctas;
        }

        public void setCtas(List<CtaDto> ctas) {
            this.ctas = ctas;
        }

        public List<TrustIndicatorDto> getTrustIndicators() {
            return trustIndicators;
        }

        public void setTrustIndicators(List<TrustIndicatorDto> trustIndicators) {
            this.trustIndicators = trustIndicators;
        }

        public ImageDto getImage() {
            return image;
        }

        public void setImage(ImageDto image) {
            this.image = image;
        }

        public FloatingBadgeDto getFloatingBadge() {
            return floatingBadge;
        }

        public void setFloatingBadge(FloatingBadgeDto floatingBadge) {
            this.floatingBadge = floatingBadge;
        }
    }

    public static class BadgeDto {
        @JsonProperty("icon")
        private String icon;

        @JsonProperty("text")
        private String text;

        public BadgeDto() {
        }

        public BadgeDto(String icon, String text) {
            this.icon = icon;
            this.text = text;
        }

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }
    }

    public static class TitleDto {
        @JsonProperty("line1")
        private String line1;

        @JsonProperty("line2")
        private String line2;

        public TitleDto() {
        }

        public TitleDto(String line1, String line2) {
            this.line1 = line1;
            this.line2 = line2;
        }

        public String getLine1() {
            return line1;
        }

        public void setLine1(String line1) {
            this.line1 = line1;
        }

        public String getLine2() {
            return line2;
        }

        public void setLine2(String line2) {
            this.line2 = line2;
        }
    }

    public static class StatDto {
        @JsonProperty("value")
        private String value;

        @JsonProperty("label")
        private String label;

        public StatDto() {
        }

        public StatDto(String value, String label) {
            this.value = value;
            this.label = label;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public String getLabel() {
            return label;
        }

        public void setLabel(String label) {
            this.label = label;
        }
    }

    public static class CtaDto {
        @JsonProperty("text")
        private String text;

        @JsonProperty("link")
        private String link;

        @JsonProperty("icon")
        private String icon;

        @JsonProperty("style")
        private String style;

        @JsonProperty("external")
        private Boolean external;

        public CtaDto() {
        }

        public CtaDto(String text, String link, String icon, String style, Boolean external) {
            this.text = text;
            this.link = link;
            this.icon = icon;
            this.style = style;
            this.external = external;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public String getLink() {
            return link;
        }

        public void setLink(String link) {
            this.link = link;
        }

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

        public String getStyle() {
            return style;
        }

        public void setStyle(String style) {
            this.style = style;
        }

        public Boolean getExternal() {
            return external;
        }

        public void setExternal(Boolean external) {
            this.external = external;
        }
    }

    public static class TrustIndicatorDto {
        @JsonProperty("icon")
        private String icon;

        @JsonProperty("highlight")
        private String highlight;

        @JsonProperty("text")
        private String text;

        public TrustIndicatorDto() {
        }

        public TrustIndicatorDto(String icon, String highlight, String text) {
            this.icon = icon;
            this.highlight = highlight;
            this.text = text;
        }

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

        public String getHighlight() {
            return highlight;
        }

        public void setHighlight(String highlight) {
            this.highlight = highlight;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }
    }

    public static class ImageDto {
        @JsonProperty("url")
        private String url;

        @JsonProperty("alt")
        private String alt;

        public ImageDto() {
        }

        public ImageDto(String url, String alt) {
            this.url = url;
            this.alt = alt;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getAlt() {
            return alt;
        }

        public void setAlt(String alt) {
            this.alt = alt;
        }
    }

    public static class FloatingBadgeDto {
        @JsonProperty("icon")
        private String icon;

        @JsonProperty("label")
        private String label;

        @JsonProperty("text")
        private String text;

        public FloatingBadgeDto() {
        }

        public FloatingBadgeDto(String icon, String label, String text) {
            this.icon = icon;
            this.label = label;
            this.text = text;
        }

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

        public String getLabel() {
            return label;
        }

        public void setLabel(String label) {
            this.label = label;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }
    }

    public static class TrustBadgesSectionDto {
        @JsonProperty("title")
        private String title;

        @JsonProperty("subtitle")
        private String subtitle;

        @JsonProperty("badges")
        private List<BadgeItemDto> badges;

        @JsonProperty("agencyImageUrl")
        private String agencyImageUrl;

        @JsonProperty("agencyImageAlt")
        private String agencyImageAlt;

        @JsonProperty("agencyDescription")
        private String agencyDescription;

        public TrustBadgesSectionDto() {
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getSubtitle() {
            return subtitle;
        }

        public void setSubtitle(String subtitle) {
            this.subtitle = subtitle;
        }

        public List<BadgeItemDto> getBadges() {
            return badges;
        }

        public void setBadges(List<BadgeItemDto> badges) {
            this.badges = badges;
        }

        public String getAgencyImageUrl() {
            return agencyImageUrl;
        }

        public void setAgencyImageUrl(String agencyImageUrl) {
            this.agencyImageUrl = agencyImageUrl;
        }

        public String getAgencyImageAlt() {
            return agencyImageAlt;
        }

        public void setAgencyImageAlt(String agencyImageAlt) {
            this.agencyImageAlt = agencyImageAlt;
        }

        public String getAgencyDescription() {
            return agencyDescription;
        }

        public void setAgencyDescription(String agencyDescription) {
            this.agencyDescription = agencyDescription;
        }
    }

    public static class BadgeItemDto {
        @JsonProperty("id")
        private String id;

        @JsonProperty("icon")
        private String icon;

        @JsonProperty("title")
        private String title;

        @JsonProperty("description")
        private String description;

        public BadgeItemDto() {
        }

        public BadgeItemDto(String id, String icon, String title, String description) {
            this.id = id;
            this.icon = icon;
            this.title = title;
            this.description = description;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }
    }

    public static class PromotionsSectionDto {
        @JsonProperty("title")
        private String title;

        @JsonProperty("subtitle")
        private String subtitle;

        @JsonProperty("items")
        private List<PromotionItemDto> items;

        @JsonProperty("viewAllLink")
        private String viewAllLink;

        @JsonProperty("viewAllText")
        private String viewAllText;

        public PromotionsSectionDto() {
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getSubtitle() {
            return subtitle;
        }

        public void setSubtitle(String subtitle) {
            this.subtitle = subtitle;
        }

        public List<PromotionItemDto> getItems() {
            return items;
        }

        public void setItems(List<PromotionItemDto> items) {
            this.items = items;
        }

        public String getViewAllLink() {
            return viewAllLink;
        }

        public void setViewAllLink(String viewAllLink) {
            this.viewAllLink = viewAllLink;
        }

        public String getViewAllText() {
            return viewAllText;
        }

        public void setViewAllText(String viewAllText) {
            this.viewAllText = viewAllText;
        }
    }

    public static class PromotionItemDto {
        @JsonProperty("id")
        private String id;

        @JsonProperty("title")
        private String title;

        @JsonProperty("description")
        private String description;

        @JsonProperty("imageUrl")
        private String imageUrl;

        @JsonProperty("badgeText")
        private String badgeText;

        @JsonProperty("badgeType")
        private String badgeType;

        @JsonProperty("validity")
        private String validity;

        @JsonProperty("originalPrice")
        private Double originalPrice;

        @JsonProperty("currentPrice")
        private Double currentPrice;

        @JsonProperty("currency")
        private String currency;

        @JsonProperty("link")
        private String link;

        public PromotionItemDto() {
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getImageUrl() {
            return imageUrl;
        }

        public void setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
        }

        public String getBadgeText() {
            return badgeText;
        }

        public void setBadgeText(String badgeText) {
            this.badgeText = badgeText;
        }

        public String getBadgeType() {
            return badgeType;
        }

        public void setBadgeType(String badgeType) {
            this.badgeType = badgeType;
        }

        public String getValidity() {
            return validity;
        }

        public void setValidity(String validity) {
            this.validity = validity;
        }

        public Double getOriginalPrice() {
            return originalPrice;
        }

        public void setOriginalPrice(Double originalPrice) {
            this.originalPrice = originalPrice;
        }

        public Double getCurrentPrice() {
            return currentPrice;
        }

        public void setCurrentPrice(Double currentPrice) {
            this.currentPrice = currentPrice;
        }

        public String getCurrency() {
            return currency;
        }

        public void setCurrency(String currency) {
            this.currency = currency;
        }

        public String getLink() {
            return link;
        }

        public void setLink(String link) {
            this.link = link;
        }
    }

    public static class TopDestinationsSectionDto {
        @JsonProperty("title")
        private String title;

        @JsonProperty("subtitle")
        private String subtitle;

        @JsonProperty("items")
        private List<DestinationItemDto> items;

        public TopDestinationsSectionDto() {
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getSubtitle() {
            return subtitle;
        }

        public void setSubtitle(String subtitle) {
            this.subtitle = subtitle;
        }

        public List<DestinationItemDto> getItems() {
            return items;
        }

        public void setItems(List<DestinationItemDto> items) {
            this.items = items;
        }
    }

    public static class DestinationItemDto {
        @JsonProperty("id")
        private String id;

        @JsonProperty("name")
        private String name;

        @JsonProperty("description")
        private String description;

        @JsonProperty("imageUrl")
        private String imageUrl;

        @JsonProperty("link")
        private String link;

        public DestinationItemDto() {
        }

        public DestinationItemDto(String id, String name, String description, String imageUrl, String link) {
            this.id = id;
            this.name = name;
            this.description = description;
            this.imageUrl = imageUrl;
            this.link = link;
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

        public String getImageUrl() {
            return imageUrl;
        }

        public void setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
        }

        public String getLink() {
            return link;
        }

        public void setLink(String link) {
            this.link = link;
        }
    }

    public static class CtaSectionDto {
        @JsonProperty("headline")
        private String headline;

        @JsonProperty("description")
        private String description;

        @JsonProperty("ctas")
        private List<CtaDto> ctas;

        public CtaSectionDto() {
        }

        public String getHeadline() {
            return headline;
        }

        public void setHeadline(String headline) {
            this.headline = headline;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public List<CtaDto> getCtas() {
            return ctas;
        }

        public void setCtas(List<CtaDto> ctas) {
            this.ctas = ctas;
        }
    }

    public static class AgencyInfoDto {
        @JsonProperty("id")
        private String id;

        @JsonProperty("name")
        private String name;

        @JsonProperty("description")
        private String description;

        @JsonProperty("contact")
        private ContactDto contact;

        @JsonProperty("social")
        private SocialDto social;

        @JsonProperty("businessHours")
        private BusinessHoursDto businessHours;

        public AgencyInfoDto() {
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

        public ContactDto getContact() {
            return contact;
        }

        public void setContact(ContactDto contact) {
            this.contact = contact;
        }

        public SocialDto getSocial() {
            return social;
        }

        public void setSocial(SocialDto social) {
            this.social = social;
        }

        public BusinessHoursDto getBusinessHours() {
            return businessHours;
        }

        public void setBusinessHours(BusinessHoursDto businessHours) {
            this.businessHours = businessHours;
        }
    }

    public static class ContactDto {
        @JsonProperty("email")
        private String email;

        @JsonProperty("phone")
        private String phone;

        @JsonProperty("whatsapp")
        private String whatsapp;

        @JsonProperty("address")
        private String address;

        @JsonProperty("city")
        private String city;

        @JsonProperty("country")
        private String country;

        public ContactDto() {
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

    public static class SocialDto {
        @JsonProperty("facebook")
        private String facebook;

        @JsonProperty("instagram")
        private String instagram;

        @JsonProperty("twitter")
        private String twitter;

        @JsonProperty("youtube")
        private String youtube;

        public SocialDto() {
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

    public static class BusinessHoursDto {
        @JsonProperty("weekdays")
        private String weekdays;

        @JsonProperty("saturday")
        private String saturday;

        @JsonProperty("sunday")
        private String sunday;

        public BusinessHoursDto() {
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

    /**
     * DTO para la sección de testimonios de viajeros.
     */
    public static class TravelersTestimonialsSectionDto {
        @JsonProperty("title")
        private String title;

        @JsonProperty("subtitle")
        private String subtitle;

        @JsonProperty("items")
        private List<TravelerTestimonialItemDto> items;

        public TravelersTestimonialsSectionDto() {
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getSubtitle() {
            return subtitle;
        }

        public void setSubtitle(String subtitle) {
            this.subtitle = subtitle;
        }

        public List<TravelerTestimonialItemDto> getItems() {
            return items;
        }

        public void setItems(List<TravelerTestimonialItemDto> items) {
            this.items = items;
        }
    }

    /**
     * DTO para un item individual de testimonio de viajero.
     */
    public static class TravelerTestimonialItemDto {
        @JsonProperty("id")
        private String id;

        @JsonProperty("imageUrl")
        private String imageUrl;

        @JsonProperty("imageAlt")
        private String imageAlt;

        @JsonProperty("customerName")
        private String customerName;

        @JsonProperty("shortQuote")
        private String shortQuote;

        @JsonProperty("destination")
        private String destination;

        public TravelerTestimonialItemDto() {
        }

        public TravelerTestimonialItemDto(String id, String imageUrl, String imageAlt,
                                          String customerName, String shortQuote, String destination) {
            this.id = id;
            this.imageUrl = imageUrl;
            this.imageAlt = imageAlt;
            this.customerName = customerName;
            this.shortQuote = shortQuote;
            this.destination = destination;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getImageUrl() {
            return imageUrl;
        }

        public void setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
        }

        public String getImageAlt() {
            return imageAlt;
        }

        public void setImageAlt(String imageAlt) {
            this.imageAlt = imageAlt;
        }

        public String getCustomerName() {
            return customerName;
        }

        public void setCustomerName(String customerName) {
            this.customerName = customerName;
        }

        public String getShortQuote() {
            return shortQuote;
        }

        public void setShortQuote(String shortQuote) {
            this.shortQuote = shortQuote;
        }

        public String getDestination() {
            return destination;
        }

        public void setDestination(String destination) {
            this.destination = destination;
        }
    }
}

