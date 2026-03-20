package com.jf.mcp.discogs.model;

import java.util.List;

public record Format(
    String name,
    String qty,
    List<String> descriptions
) {}
