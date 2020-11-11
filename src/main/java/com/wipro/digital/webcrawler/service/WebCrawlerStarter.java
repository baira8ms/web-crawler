package com.wipro.digital.webcrawler.service;

import com.wipro.digital.webcrawler.dto.WebPageDetails;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Set;

@Slf4j
@RequiredArgsConstructor
@Component
public class WebCrawlerStarter implements CommandLineRunner {

    private final WebCrawlerService webCrawlerService;

    @Override
    public void run(String... args) {
        log.info("Initiating WebCrawler application to extract html links and content details.");
        Set<WebPageDetails> listOfWebPages = webCrawlerService.extractHtmlDetails();
        log.info("Completed with WebCrawler execution.\n" + listOfWebPages);
    }
}