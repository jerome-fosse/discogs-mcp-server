package com.jf.mcp.discogs.model;

import java.util.List;

public record LabelReleases(
    Pagination pagination,
    List<LabelRelease> releases
) {}
