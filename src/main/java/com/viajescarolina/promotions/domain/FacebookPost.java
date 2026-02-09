package com.viajescarolina.promotions.domain;

import java.time.ZonedDateTime;

/**
 * Modelo de dominio que representa una publicación de Facebook.
 */
public class FacebookPost {
    private String id;
    private String message;
    private ZonedDateTime createdTime;
    private String fullPicture;
    private String permalinkUrl;

    public FacebookPost(String id, String message, ZonedDateTime createdTime, String fullPicture, String permalinkUrl) {
        this.id = id;
        this.message = message;
        this.createdTime = createdTime;
        this.fullPicture = fullPicture;
        this.permalinkUrl = permalinkUrl;
    }

    // Getters
    public String getId() {
        return id;
    }

    public String getMessage() {
        return message;
    }

    public ZonedDateTime getCreatedTime() {
        return createdTime;
    }

    public String getFullPicture() {
        return fullPicture;
    }

    public String getPermalinkUrl() {
        return permalinkUrl;
    }

    // Setters
    public void setId(String id) {
        this.id = id;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setCreatedTime(ZonedDateTime createdTime) {
        this.createdTime = createdTime;
    }

    public void setFullPicture(String fullPicture) {
        this.fullPicture = fullPicture;
    }

    public void setPermalinkUrl(String permalinkUrl) {
        this.permalinkUrl = permalinkUrl;
    }

    @Override
    public String toString() {
        return "FacebookPost{" +
                "id='" + id + '\'' +
                ", message='" + message + '\'' +
                ", createdTime=" + createdTime +
                ", fullPicture='" + fullPicture + '\'' +
                ", permalinkUrl='" + permalinkUrl + '\'' +
                '}';
    }
}

