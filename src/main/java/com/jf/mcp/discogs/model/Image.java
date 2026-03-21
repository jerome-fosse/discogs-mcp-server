package com.jf.mcp.discogs.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public record Image(
    Integer height,
    Integer width,
    ImageType type,
    String uri,
    String uri150,
    @JsonProperty("resource_url") String resourceUrl
) {}
