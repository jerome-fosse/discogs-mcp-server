package com.jf.mcp.discogs.api;

import com.jf.mcp.discogs.config.DiscogsApiConfig;
import com.jf.mcp.discogs.model.MasterRelease;
import com.jf.mcp.discogs.model.MasterReleaseVersions;
import com.jf.mcp.discogs.model.Release;
import com.jf.mcp.discogs.model.SearchResults;
import com.jf.mcp.discogs.tools.MasterReleaseVersionsCriteria;
import com.jf.mcp.discogs.tools.PaginationCriteria;
import com.jf.mcp.discogs.tools.SearchCriteria;
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

    public Mono<MasterReleaseVersions> getMasterReleaseVersions(String masterId, MasterReleaseVersionsCriteria criteria, PaginationCriteria pagination) {
        LOGGER.info("Getting master release versions for master id: {}, criteria: {}, pagination: {}", masterId, criteria, pagination);

        return client.get()
                .uri(uriBuilder -> uriBuilder.path("/masters/{id}/versions")
                        .queryParam("page", pagination.getPage().orElse(1))
                        .queryParam("per_page", pagination.getPageSize().orElse(config.getPageSize()))
                        .queryParamIfPresent("format", criteria.getFormat())
                        .queryParamIfPresent("label", criteria.getLabel())
                        .queryParamIfPresent("released", criteria.getReleased())
                        .queryParamIfPresent("country", criteria.getCountry())
                        .queryParamIfPresent("sort", criteria.getSort())
                        .queryParamIfPresent("sort_order", criteria.getOrder())
                        .build(masterId))
                .retrieve()
                .bodyToMono(MasterReleaseVersions.class);
    }

    public Mono<SearchResults> search(SearchCriteria criteria, PaginationCriteria pagination) {
        LOGGER.info("Searching with criteria: {}, pagination: {}", criteria, pagination);

        return client.get()
                .uri(uriBuilder -> uriBuilder.path("/database/search")
                        .queryParamIfPresent("q", criteria.getQuery())
                        .queryParamIfPresent("type", criteria.getType())
                        .queryParamIfPresent("title", criteria.getTitle())
                        .queryParamIfPresent("release_title", criteria.getReleaseTitle())
                        .queryParamIfPresent("credit", criteria.getCredit())
                        .queryParamIfPresent("artist", criteria.getArtist())
                        .queryParamIfPresent("anv", criteria.getAnv())
                        .queryParamIfPresent("label", criteria.getLabel())
                        .queryParamIfPresent("genre", criteria.getGenre())
                        .queryParamIfPresent("style", criteria.getStyle())
                        .queryParamIfPresent("country", criteria.getCountry())
                        .queryParamIfPresent("year", criteria.getYear())
                        .queryParamIfPresent("format", criteria.getFormat())
                        .queryParamIfPresent("catno", criteria.getCatalogNumber())
                        .queryParamIfPresent("barcode", criteria.getBarCode())
                        .queryParamIfPresent("track", criteria.getTrack())
                        .queryParamIfPresent("submitter", criteria.getSubmitter())
                        .queryParamIfPresent("contributor", criteria.getContributor())
                        .queryParam("page", pagination.getPage().orElse(1))
                        .queryParam("per_page", pagination.getPageSize().orElse(config.getPageSize()))
                        .build())
                .retrieve()
                .bodyToMono(SearchResults.class);
    }
}
