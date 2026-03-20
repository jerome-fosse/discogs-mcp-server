package com.jf.mcp.discogs.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.OffsetDateTime;
import java.util.List;

public record Release(
    int id,
    String title,
    String thumb,
    String country,
    String released,
    String notes,
    String status,
    String uri,
    int year,
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
    @JsonProperty("estimated_weight") int estimatedWeight,
    @JsonProperty("format_quantity") int formatQuantity,
    @JsonProperty("lowest_price") double lowestPrice,
    @JsonProperty("master_id") int masterId,
    @JsonProperty("master_url") String masterUrl,
    @JsonProperty("num_for_sale") int numForSale,
    @JsonProperty("released_formatted") String releasedFormatted,
    @JsonProperty("resource_url") String resourceUrl
) {}
