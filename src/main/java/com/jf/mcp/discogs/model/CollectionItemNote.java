package com.jf.mcp.discogs.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public record CollectionItemNote(
    @JsonProperty("field_id") Integer fieldId,
    String value
) {}
