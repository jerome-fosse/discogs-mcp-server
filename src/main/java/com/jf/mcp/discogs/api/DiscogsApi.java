package com.jf.mcp.discogs.api;

import com.jf.mcp.discogs.config.DiscogsApiConfig;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class DiscogsApi {
    private final DiscogsApiConfig config;
    private final WebClient client;

    public DiscogsApi(DiscogsApiConfig config, WebClient client) {
        this.config = config;
        this.client = client;
    }
}
