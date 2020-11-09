package com.wipro.digital.webcrawler.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class WebCrawlerExecutorService implements CommandLineRunner {

  @Override
  public void run(String... args) {
    log.info("Executing WebCrawler application.");
  }
}