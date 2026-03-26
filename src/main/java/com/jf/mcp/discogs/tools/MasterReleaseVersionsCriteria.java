package com.jf.mcp.discogs.tools;

import org.springframework.ai.mcp.annotation.McpToolParam;

import java.util.Optional;

public class MasterReleaseVersionsCriteria {
    @McpToolParam(description = "The format to filter (Vinyl, CD, Cassette, EP....)", required = false)
    private final String format;
    @McpToolParam(description = "The label to filter. Example: Columbia", required = false)
    private final String label;
    @McpToolParam(description = "The release year to filter. Example: 1992", required = false)
    private final Integer released;
    @McpToolParam(description = "The country to filter. Example: France", required = false)
    private final String country;
    @McpToolParam(description = "Sort release versions by : (released, title, format, label, catno or country)", required = false)
    private final SortingField sortingField;
    @McpToolParam(description = "Sorting items order (asc or desc)", required = false)
    private final SortingOrder sortingOrder;

    public MasterReleaseVersionsCriteria(String format, String label, Integer released, String country, SortingField sortingField, SortingOrder sortingOrder) {
        this.format = format;
        this.label = label;
        this.released = released;
        this.country = country;
        this.sortingField = sortingField;
        this.sortingOrder = sortingOrder;
    }

    public Optional<String> getFormat() {
        return Optional.ofNullable(format);
    }

    public Optional<String> getLabel() {
        return Optional.ofNullable(label);
    }

    public Optional<Integer> getReleased() {
        return Optional.ofNullable(released);
    }

    public Optional<String> getCountry() {
        return Optional.ofNullable(country);
    }

    public Optional<SortingField> getSortingField() {
        return Optional.ofNullable(sortingField);
    }

    public Optional<SortingOrder> getSortingOrder() {
        return Optional.ofNullable(sortingOrder);
    }

    @Override
    public String toString() {
        return "MasterReleaseVersionsCriteria{" +
                "format='" + format + '\'' +
                ", label='" + label + '\'' +
                ", released=" + released +
                ", country='" + country + '\'' +
                ", sort=" + sortingField +
                ", order=" + sortingOrder +
                '}';
    }
}
