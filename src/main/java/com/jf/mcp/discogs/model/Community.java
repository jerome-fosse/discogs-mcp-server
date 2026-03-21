package com.jf.mcp.discogs.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public record Community(
    Integer have,
    Integer want,
    String status,
    Rating rating,
    Contributor submitter,
    List<Contributor> contributors,
    @JsonProperty("data_quality") String dataQuality
) {}
