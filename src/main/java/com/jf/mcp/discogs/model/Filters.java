package com.jf.mcp.discogs.model;

import java.util.List;
import java.util.Map;

public record Filters(
    Map<String, List<String>> applied,
    Map<String, Map<String, Integer>> available
) {}
