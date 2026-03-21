package com.jf.mcp.discogs.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public record ReleaseVersion(
    Integer id,
    String title,
    String thumb,
    String country,
    String released,
    String status,
    String format,
    String label,
    String catno,
    @JsonProperty("major_formats") List<String> majorFormats,
    @JsonProperty("resource_url") String resourceUrl,
    VersionStats stats
) {}
