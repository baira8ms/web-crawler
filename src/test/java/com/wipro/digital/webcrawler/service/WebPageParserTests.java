package com.wipro.digital.webcrawler.service;

import com.wipro.digital.webcrawler.dto.WebPageDetails;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.BDDAssertions.then;

@ExtendWith(MockitoExtension.class)
public class WebPageParserTests {

    public static final String WIPRO_DIGITAL_URL = "https://wiprodigital.com/";
    public static final String WIPRO_DIGITAL_DOMAIN = "wiprodigital.com";
    private WebPageParser webPageParser;

    @BeforeEach
    public void init() {
        webPageParser = new WebPageParser(WIPRO_DIGITAL_DOMAIN);
    }

    @Test
    public void givenWebUrl_thenReadContentFromUrl() {
        WebPageDetails webPageDetails = webPageParser.extractLinks(WIPRO_DIGITAL_URL);

        then(webPageDetails)
                .as("Should contain internal/external/content urls")
                .isNotNull();

        then(webPageDetails.getExternalUrls())
                .isNotNull()
                .contains("https://www.linkedin.com/company/wipro-digital");

        then(webPageDetails.getInternalPages())
                .isNotNull()
                .contains("https://wiprodigital.com/who-we-are/");

        then(webPageDetails.getContentLinks())
                .isNotNull()
                .contains(
                        "https://s17776.pcdn.co/wp-content/uploads/2018/07/Insights-How-Design-Thinking-Can-Help-Improve-Your-Organizations-Customer-Experience-1024x626.jpg");

    }


}
