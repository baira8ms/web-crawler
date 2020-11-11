package com.wipro.digital.webcrawler.service;

import com.wipro.digital.webcrawler.dto.WebPageDetails;

import java.util.Set;

public interface WebCrawlerService {

    Set<WebPageDetails> extractHtmlDetails();

}
