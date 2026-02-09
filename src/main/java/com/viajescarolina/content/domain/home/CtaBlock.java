package com.viajescarolina.content.domain.home;

import java.util.List;

/**
 * Representa el bloque CTA (Call To Action) del home.
 */
public class CtaBlock {
    private String headline;
    private String description;
    private List<CtaButton> ctas;

    public CtaBlock() {
    }

    public CtaBlock(String headline, String description, List<CtaButton> ctas) {
        this.headline = headline;
        this.description = description;
        this.ctas = ctas;
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

    public List<CtaButton> getCtas() {
        return ctas;
    }

    public void setCtas(List<CtaButton> ctas) {
        this.ctas = ctas;
    }

    public static class CtaButton {
        private String text;
        private String link;
        private String style;
        private Boolean external;

        public CtaButton() {
        }

        public CtaButton(String text, String link, String style, Boolean external) {
            this.text = text;
            this.link = link;
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
}

