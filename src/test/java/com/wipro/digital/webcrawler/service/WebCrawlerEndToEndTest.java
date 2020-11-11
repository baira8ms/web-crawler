package com.wipro.digital.webcrawler.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class WebCrawlerEndToEndTest {

    private WebCrawlerStarter webCrawlerStarter;

    @Mock
    private WebCrawlerService webCrawlerService;

    @BeforeEach
    private void init() {
        webCrawlerStarter = new WebCrawlerStarter(webCrawlerService);
    }

    @DisplayName("Test for WebCrawler CommandLineRunner invocation.")
    @Test
    public void whenWebCrawlerExecutes_thenDiscoversEveryLinkFromUrl() {

        webCrawlerStarter.run();

        verify(webCrawlerService, times(1)).extractHtmlDetails();
    }
}
