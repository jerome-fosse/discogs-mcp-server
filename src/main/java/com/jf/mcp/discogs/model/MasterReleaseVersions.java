package com.jf.mcp.discogs.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public record MasterReleaseVersions(
    Pagination pagination,
    Filters filters,
    @JsonProperty("filter_facets") List<FilterFacet> filterFacets,
    List<ReleaseVersion> versions
) {}
