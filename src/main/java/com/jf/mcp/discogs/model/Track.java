package com.jf.mcp.discogs.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public record Track(
    String position,
    String title,
    String duration,
    List<ArtistCredit> extraartists,
    @JsonProperty("type_") String type
) {}
