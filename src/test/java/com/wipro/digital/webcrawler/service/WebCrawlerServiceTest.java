package com.wipro.digital.webcrawler.service;

import com.wipro.digital.webcrawler.dto.WebPageDetails;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Set;

import static org.assertj.core.api.BDDAssertions.then;

@ExtendWith(MockitoExtension.class)
class WebCrawlerServiceTest {

    private final String CONTENT_IMAGE_LINK = "https://s17776.pcdn.co/wp-content/uploads/2018/07/Insights-How-Design-Thinking-Can-Help-Improve-Your-Organizations-Customer-Experience-1024x626.jpg";
    private final String WIPRO_DIGITAL_URL = "https://wiprodigital.com/";
    private final String WIPRO_DIGITAL_DOMAIN = "wiprodigital.com";

    private final String[] EXTERNAL_SOCIAL_WEB_LINKS = {
            "https://www.facebook.com/WiproDigital/",
            "https://twitter.com/wiprodigital",
            "https://www.linkedin.com/company/wipro-digital"};

    private final String[] INTERNAL_URLS = {
            "https://wiprodigital.com/get-in-touch/",
            "https://wiprodigital.com/join-our-team/",
            "https://wiprodigital.com/what-we-think/"};

    private WebCrawlerService webCrawlerService;

    @BeforeEach
    public void init() {
        WebPageParser webPageParser = new WebPageParser(WIPRO_DIGITAL_DOMAIN);
        webCrawlerService = new WebCrawlerServiceImpl(webPageParser, WIPRO_DIGITAL_URL);
    }

    @Test
    public void givenURL_whenCrawlerExtractsExternalLinks_thenExternalSocialMediaLinksMustPresent() {
        Set<WebPageDetails> listOfWebPages = webCrawlerService.extractHtmlDetails();

        then(listOfWebPages)
                .isNotNull()
                .hasSizeGreaterThan(0)
                .flatExtracting("externalUrls")
                .contains(EXTERNAL_SOCIAL_WEB_LINKS);
    }

    @Test
    public void givenURL_whenCrawlerExtractsContentLinks_thenWiproDigitalImageLinkMustPresent() {
        Set<WebPageDetails> listOfWebPages = webCrawlerService.extractHtmlDetails();

        then(listOfWebPages)
                .isNotNull()
                .hasSizeGreaterThan(0)
                .flatExtracting("contentLinks")
                .contains(CONTENT_IMAGE_LINK);
    }

    @Test
    public void givenURL_whenCrawlerExtractsInternalLinks_thenWiproDigitalInternalLinkMustPresent() {
        Set<WebPageDetails> listOfWebPages = webCrawlerService.extractHtmlDetails();

        then(listOfWebPages)
                .isNotNull()
                .hasSizeGreaterThan(0)
                .flatExtracting("internalPages")
                .contains(INTERNAL_URLS);
    }

}