package com.jf.mcp.discogs.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public record SearchResultItem(
    int id,
    String title,
    String thumb,
    String uri,
    String country,
    String catno,
    String year,
    String type,
    List<String> style,
    List<String> format,
    List<String> label,
    List<String> genre,
    List<String> barcode,
    Community community,
    @JsonProperty("resource_url") String resourceUrl
) {}
