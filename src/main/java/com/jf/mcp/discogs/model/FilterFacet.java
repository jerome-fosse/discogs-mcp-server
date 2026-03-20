package com.jf.mcp.discogs.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public record FilterFacet(
    String title,
    String id,
    List<FilterFacetValue> values,
    @JsonProperty("allows_multiple_values") boolean allowsMultipleValues
) {}
