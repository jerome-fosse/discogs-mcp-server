package com.jf.mcp.discogs.tools;

import org.springframework.ai.mcp.annotation.McpToolParam;

import java.util.Optional;

public class CollectionItemsCriteria {
    @McpToolParam(description = "The ID of the folder to request. Example: 3")
    private final String folderId;
    @McpToolParam(description = "Sort collection items by : (added, rating, title, year, format, label, catno or artist)", required = false)
    private final CollectionItemsSortingFields sortingField;
    @McpToolParam(description = "Sorting items order (asc or desc)", required = false)
    private final SortingOrder sortingOrder;

    public CollectionItemsCriteria(String folderId, CollectionItemsSortingFields sortingField, SortingOrder sortingOrder) {
        this.folderId = folderId;
        this.sortingField = sortingField;
        this.sortingOrder = sortingOrder;
    }

    public String getFolderId() {
        return folderId;
    }

    public Optional<CollectionItemsSortingFields> getSortingField() {
        return Optional.ofNullable(sortingField);
    }

    public Optional<SortingOrder> getSortingOrder() {
        return Optional.ofNullable(sortingOrder);
    }

    @Override
    public String toString() {
        return "CollectionItemsCriteria{" +
                ", folderId='" + folderId + '\'' +
                ", sortingField=" + sortingField +
                ", sortingOrder=" + sortingOrder +
                '}';
    }
}
