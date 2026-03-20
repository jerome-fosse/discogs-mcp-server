package com.jf.mcp.discogs.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public record Label(
    int id,
    String catno,
    String name,
    @JsonProperty("entity_type") String entityType,
    @JsonProperty("resource_url") String resourceUrl
) {}
