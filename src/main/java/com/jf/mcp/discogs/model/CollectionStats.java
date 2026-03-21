package com.jf.mcp.discogs.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public record CollectionStats(
    @JsonProperty("in_collection") Integer inCollection,
    @JsonProperty("in_wantlist") Integer inWantlist
) {}
