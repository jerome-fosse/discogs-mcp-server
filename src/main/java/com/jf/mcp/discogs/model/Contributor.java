package com.jf.mcp.discogs.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public record Contributor(
    String username,
    @JsonProperty("resource_url") String resourceUrl
) {}
