package com.jf.mcp.discogs.config;

import com.jf.mcp.discogs.api.DiscogsApiException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestClient;

@Configuration
public class WebClientConfig {

    @Bean
    public RestClient discogsRestClient(DiscogsApiConfig config) {
        var factory = new SimpleClientHttpRequestFactory();
        factory.setConnectTimeout(config.getTimeout());
        factory.setReadTimeout(config.getTimeout());

        return RestClient.builder()
                .baseUrl(config.getBaseUrl())
                .requestFactory(factory)
                .defaultHeader("Content-Type", "application/json")
                .defaultHeader("Accept", "application/json")
                .defaultHeader("User-Agent", "DiscogsMcpServer")
                .defaultHeader("Authorization", "Discogs token=" + config.getToken())
                .defaultStatusHandler(HttpStatusCode::isError, (request, response) -> {
                    throw switch (response.getStatusCode().value()) {
                        case 401 -> new DiscogsApiException("Invalid or missing Discogs API token. Check your DISCOGS_TOKEN.");
                        case 404 -> new DiscogsApiException("Resource not found on Discogs (404): " + request.getURI().getPath());
                        case 429 -> new DiscogsApiException("Discogs API rate limit exceeded. Please retry later.");
                        default -> new DiscogsApiException("Discogs API error " + response.getStatusCode().value() + ": " + request.getURI().getPath());
                    };
                })
                .build();
    }
}
