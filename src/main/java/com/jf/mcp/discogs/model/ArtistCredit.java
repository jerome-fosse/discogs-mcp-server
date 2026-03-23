package com.jf.mcp.discogs.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ArtistCredit(
    Integer id,
    String anv,
    String join,
    String name,
    String role,
    String tracks,
    @JsonProperty("resource_url") String resourceUrl
) {}
