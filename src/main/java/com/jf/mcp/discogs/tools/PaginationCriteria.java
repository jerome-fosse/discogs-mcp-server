package com.jf.mcp.discogs.tools;

import org.springframework.ai.mcp.annotation.McpToolParam;

import java.util.Optional;

public class PaginationCriteria {
    @McpToolParam(description = "The page you want to request. Example: 3", required = false)
    private final Integer page;
    @McpToolParam(description = "The number of items per page. Example: 25,. Default is 30", required = false)
    private final Integer pageSize;

    public PaginationCriteria(Integer page, Integer pageSize) {
        this.page = page;
        this.pageSize = pageSize;
    }

    public Optional<Integer> getPage() {
        return Optional.ofNullable(page);
    }
    public Optional<Integer> getPageSize() {
        return Optional.ofNullable(pageSize);
    }

    @Override
    public String toString() {
        return "PaginationCriteria{" +
                "page=" + page +
                ", pageSize=" + pageSize +
                '}';
    }
}
