package com.jf.mcp.discogs.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Map;

public record Pagination(
    @JsonProperty("per_page") int perPage,
    int items,
    int page,
    int pages,
    Map<String, String> urls
) {}
