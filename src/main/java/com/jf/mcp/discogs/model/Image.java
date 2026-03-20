package com.jf.mcp.discogs.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public record Image(
    int height,
    int width,
    ImageType type,
    String uri,
    String uri150,
    @JsonProperty("resource_url") String resourceUrl
) {}
