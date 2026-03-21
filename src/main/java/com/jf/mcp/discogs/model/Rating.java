package com.jf.mcp.discogs.model;

public record Rating(
    Double average,
    Integer count
) {}
