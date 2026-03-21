package com.jf.mcp.discogs.model;

import java.util.List;

public record SearchResults(
    Pagination pagination,
    List<SearchResultItem> results
) {}
