package com.jf.mcp.discogs.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Map;

public record Pagination(
    @JsonProperty("per_page") Integer perPage,
    Integer items,
    Integer page,
    Integer pages,
    Map<String, String> urls
) {}
