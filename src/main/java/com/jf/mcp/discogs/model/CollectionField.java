package com.jf.mcp.discogs.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public record CollectionField(
    String name,
    Integer id,
    Integer position,
    String type,
    @JsonProperty("public") Boolean isPublic,
    List<String> options,
    Integer lines
) {}
