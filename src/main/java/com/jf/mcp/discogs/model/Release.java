package com.jf.mcp.discogs.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.OffsetDateTime;
import java.util.List;

public record Release(
    Integer id,
    String title,
    String thumb,
    String country,
    String released,
    String notes,
    String status,
    String uri,
    Integer year,
    List<String> genres,
    List<String> styles,
    List<ArtistCredit> artists,
    List<ArtistCredit> extraartists,
    List<Format> formats,
    List<Track> tracklist,
    List<Label> labels,
    List<Company> companies,
    List<Image> images,
    List<Identifier> identifiers,
    List<Video> videos,
    Community community,
    @JsonProperty("data_quality") String dataQuality,
    @JsonProperty("date_added") OffsetDateTime dateAdded,
    @JsonProperty("date_changed") OffsetDateTime dateChanged,
    @JsonProperty("estimated_weight") Integer estimatedWeight,
    @JsonProperty("format_quantity") Integer formatQuantity,
    @JsonProperty("lowest_price") Double lowestPrice,
    @JsonProperty("master_id") Integer masterId,
    @JsonProperty("master_url") String masterUrl,
    @JsonProperty("num_for_sale") Integer numForSale,
    @JsonProperty("released_formatted") String releasedFormatted,
    @JsonProperty("resource_url") String resourceUrl
) {}
