package com.jf.mcp.discogs.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ArtistMember(
    Integer id,
    String name,
    Boolean active,
    @JsonProperty("resource_url") String resourceUrl
) {}
