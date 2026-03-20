package com.jf.mcp.discogs.model;

public record FilterFacetValue(
    String title,
    String value,
    int count
) {}
