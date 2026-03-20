package com.jf.mcp.discogs.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ArtistMember(
    int id,
    String name,
    boolean active,
    @JsonProperty("resource_url") String resourceUrl
) {}
