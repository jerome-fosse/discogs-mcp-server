package com.jf.mcp.discogs.model;

public record Price(
    Currency currency,
    Double value
) {}
