package com.jf.mcp.discogs.api;

import com.jf.mcp.discogs.config.DiscogsApiConfig;
import com.jf.mcp.discogs.model.PriceSuggestions;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
public class DiscogsMarketplaceApi {
    private final DiscogsApiConfig config;
    private final RestClient client;

    private static final Logger LOGGER = org.slf4j.LoggerFactory.getLogger(DiscogsMarketplaceApi.class);

    public DiscogsMarketplaceApi(DiscogsApiConfig config, RestClient client) {
        this.config = config;
        this.client = client;
    }

    public PriceSuggestions getPriceSuggestions(String releaseId) {
        LOGGER.info("Getting price suggestions for release id: {}", releaseId);

        return client.get()
                .uri("/marketplace/price_suggestions/{releaseId}", releaseId)
                .retrieve()
                .body(PriceSuggestions.class);
    }
}
