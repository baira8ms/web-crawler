package com.wipro.digital.webcrawler.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@Data
public class WebPageDetails {

    private String url;
    private Set<String> externalUrls;
    private Set<String> internalPages;
    private Set<String> contentLinks;

    @Override
    public String toString() {
        return
                "\n*********************************************************************\n" +
                        "\n&&&&&&& WebPage :: [[" + url + "]] &&&&&&&" +
                        "\n externalUrls = " + externalUrls +
                        "\n internalPages = " + internalPages +
                        "\n image or content links = " + contentLinks +
                        "\n*********************************************************************\n";
    }
}
