package com.jf.mcp.discogs.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public record Label(
    Integer id,
    String catno,
    String name,
    @JsonProperty("entity_type") String entityType,
    @JsonProperty("entity_type_name") String entityTypeName,
    @JsonProperty("resource_url") String resourceUrl
) {}
