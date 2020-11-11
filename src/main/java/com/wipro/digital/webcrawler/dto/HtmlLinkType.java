package com.wipro.digital.webcrawler.dto;

public enum HtmlLinkType {

    INTERNAL_LINK("\\(?\\b(https?:\\/\\/DOMAIN_NAME)[-A-Za-z0-9+&amp;@#\\/%?=~_()|!:,.;]*[-A-Za-z0-9+&amp;@#\\/%=~_()|]"),
    CONTENT_LINK("\\(?\\b(https?:\\/\\/)[-A-Za-z0-9+&amp;@#\\/%?=~_()|!:,.;]*[-A-Za-z0-9+&amp;@#\\/%=~_()|]");

    private String regexp;

    HtmlLinkType(String regexp) {
        this.regexp = regexp;
    }

    public String getRegExp() {
        return this.regexp;
    }
}
