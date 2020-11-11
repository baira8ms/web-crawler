package com.wipro.digital.webcrawler.service;

import com.wipro.digital.webcrawler.dto.WebPageDetails;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static com.wipro.digital.webcrawler.dto.HtmlLinkType.CONTENT_LINK;
import static com.wipro.digital.webcrawler.dto.HtmlLinkType.INTERNAL_LINK;
import static java.util.Objects.nonNull;

@Slf4j
@Component
public class WebPageParser {

    public static final String ANCHOR_STYLE_CSS_SELECTOR = "a[style]";
    public static final String ANCHOR_HREF_CSS_SELECTOR = "a[href]";
    public static final String DOMAIN_PLACE_HOLDER = "DOMAIN_NAME";
    public static final String STYLE_ATTRIBUTE = "style";
    public static final String HREF_ATTRIBUTE = "href";
    private String domain;

    public WebPageParser(@Value("${web.domain}") final String domain) {
        this.domain = domain;
    }

    public WebPageDetails extractLinks(String url) {

        WebPageDetails webPageDetails = null;

        try {
            Document document = Jsoup.connect(url).get();

            webPageDetails = loadInternalAndExternalLinks(document);

            Set<String> contentLinks = loadContentLinks(document);

            webPageDetails.setContentLinks(contentLinks);
            webPageDetails.setUrl(url);

        } catch (IOException | IllegalArgumentException e) {

            log.error("Unable to read the html content from the url {}, error : {}", url,
                    e.getMessage());
        }
        return webPageDetails;
    }

    private Set<String> loadContentLinks(Document document) {
        Elements links = document.select(ANCHOR_STYLE_CSS_SELECTOR);
        String contentLinkRegExp = CONTENT_LINK.getRegExp();

        return links.stream()
                .map(link ->
                        extractContentUrl(contentLinkRegExp, link.attr(STYLE_ATTRIBUTE)))
                .filter(Objects::nonNull)
                .collect(Collectors.toSet());
    }

    private WebPageDetails loadInternalAndExternalLinks(Document document) {
        Set<String> externalUrls = new HashSet<>();
        Set<String> internalPages = new HashSet<>();

        Elements links = document.select(ANCHOR_HREF_CSS_SELECTOR);

        for (Element link : links) {
            String href = link.attr(HREF_ATTRIBUTE);

            if (nonNull(href)) {
                String internalLinkRegExp = INTERNAL_LINK.getRegExp().replace(DOMAIN_PLACE_HOLDER, domain);
                if (nonNull(extractContentUrl(internalLinkRegExp, href))) {
                    internalPages.add(href);
                } else {
                    externalUrls.add(href);
                }
            }
        }
        return WebPageDetails.builder()
                .internalPages(internalPages)
                .externalUrls(externalUrls)
                .build();
    }

    public String extractContentUrl(String linkRegExp, String htmlContent) {
        String contentLink = null;

        //Creating a pattern object
        Pattern pattern = Pattern.compile(linkRegExp);

        //Matching the compiled pattern in the String
        Matcher matcher = pattern.matcher(htmlContent);

        if (matcher.find()) {
            contentLink = matcher.group();
        }
        return contentLink;
    }
}
