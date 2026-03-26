package com.jf.mcp.discogs.model;

import java.util.List;

public record CollectionItems(
    Pagination pagination,
    List<CollectionItem> releases
) {}
