package com.wipro.digital.webcrawler.service;

import com.wipro.digital.webcrawler.dto.WebPageDetails;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.*;

import static java.util.Optional.ofNullable;
import static java.util.stream.Collectors.toList;
import static org.springframework.util.CollectionUtils.contains;
import static org.springframework.util.CollectionUtils.isEmpty;

@Slf4j
@Service
public class WebCrawlerServiceImpl implements WebCrawlerService {

    private Set<String> visitedWebPagesList = new HashSet<>();
    private Set<WebPageDetails> listOfWebPages = new HashSet<>();
    private WebPageParser webPageParser;
    private String appUrl;

    public WebCrawlerServiceImpl(final WebPageParser webPageParser,
                                 @Value("${web.url}") final String appUrl) {
        this.webPageParser = webPageParser;
        this.appUrl = appUrl;
    }

    public Set<WebPageDetails> extractHtmlDetails() {
        populateDistinctInternalWebPages(appUrl);
        return listOfWebPages;
    }

    private void populateDistinctInternalWebPages(String appUrl) {

        Optional<WebPageDetails> webPageOptional = ofNullable(
                webPageParser.extractLinks(appUrl));

        webPageOptional.ifPresent(listOfWebPages::add);

        List<String> unvisitedWebPagesList = webPageOptional
                .filter(details -> !isEmpty(details.getInternalPages()))
                .map(details -> details.getInternalPages()
                        .stream()
                        .filter(internalUrl -> !contains(visitedWebPagesList.iterator(), internalUrl))
                        .collect(toList())
                )
                .orElseGet(Collections::emptyList);

        if (!isEmpty(unvisitedWebPagesList)) {

            visitedWebPagesList.addAll(unvisitedWebPagesList);

            log.info("Crawling into all internal web pages and so far indexed {} internal pages.",
                    visitedWebPagesList.size());

            unvisitedWebPagesList
                    .forEach(this::populateDistinctInternalWebPages);
        }
    }
}
