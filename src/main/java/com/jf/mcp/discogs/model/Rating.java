package com.jf.mcp.discogs.model;

public record Rating(
    double average,
    int count
) {}
