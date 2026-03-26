package com.jf.mcp.discogs.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public record Folder(
        Integer id,
        Integer count,
        String name,
        @JsonProperty("resource_url") String resourceUrl) {}
