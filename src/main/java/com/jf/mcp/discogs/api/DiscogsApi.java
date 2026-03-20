package com.jf.mcp.discogs.api;

import com.jf.mcp.discogs.config.DiscogsApiConfig;
import com.jf.mcp.discogs.model.MasterRelease;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class DiscogsApi {
    private final DiscogsApiConfig config;
    private final WebClient client;

    public DiscogsApi(DiscogsApiConfig config, WebClient client) {
        this.config = config;
        this.client = client;
    }

    public Mono<MasterRelease> getMasterReleaseById(String id) {
        return client.get()
                .uri("/masters/" + id)
                .retrieve()
                .bodyToMono(MasterRelease.class);
    }
}
