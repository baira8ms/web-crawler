package com.wipro.digital.webcrawler;

import com.wipro.digital.webcrawler.service.WebCrawlerStarter;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@SpringBootTest
class WebCrawlerApplicationTest {

    @SpyBean
    WebCrawlerStarter webCrawlerStarter;

    @Test
    void whenContextLoads_thenRunnersRun() throws Exception {
        verify(webCrawlerStarter, times(1)).run(any());
    }

}
