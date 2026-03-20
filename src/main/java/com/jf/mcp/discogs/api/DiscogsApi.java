package com.jf.mcp.discogs.api;

import com.jf.mcp.discogs.config.DiscogsApiConfig;
import com.jf.mcp.discogs.model.MasterRelease;
import com.jf.mcp.discogs.model.MasterReleaseVersions;
import com.jf.mcp.discogs.model.Release;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class DiscogsApi {
    private final DiscogsApiConfig config;
    private final WebClient client;

    private static final Logger LOGGER = org.slf4j.LoggerFactory.getLogger(DiscogsApi.class);

    public DiscogsApi(DiscogsApiConfig config, WebClient client) {
        this.config = config;
        this.client = client;
    }

    public Mono<MasterRelease> getMasterReleaseById(String id) {
        LOGGER.info("Getting master release by id: {}", id);

        return client.get()
                .uri("/masters/{id}", id)
                .retrieve()
                .bodyToMono(MasterRelease.class);
    }

    public Mono<Release> getReleaseById(String id) {
        LOGGER.info("Getting release by id: {}", id);
        return client.get()
                .uri("/releases/{id}", id)
                .retrieve()
                .bodyToMono(Release.class);
    }

    public Mono<MasterReleaseVersions> getMasterReleaseVersions(String masterId, MasterReleaseVersionsParams params) {
        LOGGER.info("Getting master release versions for master id: {}", masterId);

        return client.get()
                .uri(uriBuilder -> uriBuilder.path("/masters/{id}/versions")
                        .queryParam("page", params.getPage().orElse(1))
                        .queryParam("per_page", params.getPageSize().orElse(config.getPageSize()))
                        .queryParamIfPresent("format", params.getFormat())
                        .queryParamIfPresent("label", params.getLabel())
                        .queryParamIfPresent("released", params.getReleased())
                        .queryParamIfPresent("country", params.getCountry())
                        .queryParamIfPresent("sort", params.getSort())
                        .queryParamIfPresent("sort_order", params.getOrder())
                        .build(masterId))
                .retrieve()
                .bodyToMono(MasterReleaseVersions.class);
    }
}
