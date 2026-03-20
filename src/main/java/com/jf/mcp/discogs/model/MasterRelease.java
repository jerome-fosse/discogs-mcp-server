package com.jf.mcp.discogs.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public record MasterRelease(
    int id,
    String title,
    String uri,
    int year,
    List<String> genres,
    List<String> styles,
    List<ArtistCredit> artists,
    List<Track> tracklist,
    List<Image> images,
    List<Video> videos,
    @JsonProperty("main_release") int mainRelease,
    @JsonProperty("main_release_url") String mainReleaseUrl,
    @JsonProperty("versions_url") String versionsUrl,
    @JsonProperty("resource_url") String resourceUrl,
    @JsonProperty("num_for_sale") int numForSale,
    @JsonProperty("lowest_price") double lowestPrice,
    @JsonProperty("data_quality") String dataQuality
) {}
