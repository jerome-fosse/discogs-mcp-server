package com.jf.mcp.discogs.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public record Instance(
        @JsonProperty("instance_id") Integer id,
        @JsonProperty("resource_url") String resource_url
) { }
