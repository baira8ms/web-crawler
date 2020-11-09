package com.wipro.digital.webcrawler;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import com.wipro.digital.webcrawler.service.WebCrawlerExecutorService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;

@SpringBootTest
class WebCrawlerApplicationTests {

  @SpyBean
  WebCrawlerExecutorService webCrawlerExecutorService;

  @Test
  void whenContextLoads_thenRunnersRun() throws Exception {
    verify(webCrawlerExecutorService, times(1)).run(any());
  }

}
