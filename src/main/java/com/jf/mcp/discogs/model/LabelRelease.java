package com.jf.mcp.discogs.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public record LabelRelease(
    Integer id,
    String title,
    String artist,
    String thumb,
    String status,
    String format,
    String catno,
    Integer year,
    @JsonProperty("resource_url") String resourceUrl
) {}
