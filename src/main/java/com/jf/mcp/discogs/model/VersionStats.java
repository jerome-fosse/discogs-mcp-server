package com.jf.mcp.discogs.model;

public record VersionStats(
    CollectionStats user,
    CollectionStats community
) {}
