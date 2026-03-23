package com.jf.mcp.discogs.api;

import com.jf.mcp.discogs.config.DiscogsApiConfig;
import com.jf.mcp.discogs.model.*;
import com.jf.mcp.discogs.tools.MasterReleaseVersionsCriteria;
import com.jf.mcp.discogs.tools.PaginationCriteria;
import com.jf.mcp.discogs.tools.SearchCriteria;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
public class DiscogsApi {
    private final DiscogsApiConfig config;
    private final RestClient client;

    private static final Logger LOGGER = org.slf4j.LoggerFactory.getLogger(DiscogsApi.class);

    public DiscogsApi(DiscogsApiConfig config, RestClient client) {
        this.config = config;
        this.client = client;
    }

    public MasterRelease getMasterReleaseById(String id) {
        LOGGER.info("Getting master release by id: {}", id);

        return client.get()
                .uri("/masters/{id}", id)
                .retrieve()
                .body(MasterRelease.class);
    }

    public Release getReleaseById(String id) {
        LOGGER.info("Getting release by id: {}", id);
        return client.get()
                .uri("/releases/{id}", id)
                .retrieve()
                .body(Release.class);
    }

    public MasterReleaseVersions getMasterReleaseVersions(String masterId, MasterReleaseVersionsCriteria criteria, PaginationCriteria pagination) {
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
                .body(MasterReleaseVersions.class);
    }

    public SearchResults search(SearchCriteria criteria, PaginationCriteria pagination) {
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
                .body(SearchResults.class);
    }

    public Artist getArtist(String artistId) {
        LOGGER.info("Getting artist by id: {}", artistId);

        return client.get()
                .uri("/artists/{artistId}", artistId)
                .retrieve()
                .body(Artist.class);
    }

    public LabelReleases getLabelReleases(String labelId, PaginationCriteria pagination) {
        LOGGER.info("Getting label releases for label id: {}, pagination: {}", labelId, pagination);

        return client.get()
                .uri(uriBuilder -> uriBuilder.path("/labels/{labelId}/releases")
                        .queryParam("page", pagination.getPage().orElse(1))
                        .queryParam("per_page", pagination.getPageSize().orElse(config.getPageSize()))
                        .build(labelId))
                .retrieve()
                .body(LabelReleases.class);
    }

    public PriceSuggestions getPriceSuggestions(String releaseId) {
        LOGGER.info("Getting price suggestions for release id: {}", releaseId);

        return client.get()
                .uri("/marketplace/price_suggestions/{releaseId}", releaseId)
                .retrieve()
                .body(PriceSuggestions.class);
    }
}
