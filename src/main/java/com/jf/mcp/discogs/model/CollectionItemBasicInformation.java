package com.jf.mcp.discogs.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public record CollectionItemBasicInformation(
    Integer id,
    String title,
    Integer year,
    String thumb,
    @JsonProperty("cover_image") String coverImage,
    @JsonProperty("resource_url") String resourceUrl,
    List<Format> formats,
    List<Label> labels,
    List<ArtistCredit> artists,
    List<String> genres,
    List<String> styles
) {}
