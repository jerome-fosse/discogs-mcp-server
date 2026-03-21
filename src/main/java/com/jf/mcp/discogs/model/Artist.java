package com.jf.mcp.discogs.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public record Artist(
    Integer id,
    String profile,
    String uri,
    List<String> namevariations,
    List<String> urls,
    List<Image> images,
    List<ArtistMember> members,
    @JsonProperty("data_quality") String dataQuality,
    @JsonProperty("releases_url") String releasesUrl,
    @JsonProperty("resource_url") String resourceUrl
) {}
