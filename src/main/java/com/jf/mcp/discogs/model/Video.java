package com.jf.mcp.discogs.model;

public record Video(
    String title,
    String description,
    String uri,
    int duration,
    boolean embed
) {}
