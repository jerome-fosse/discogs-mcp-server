package com.jf.mcp.discogs.tools;

public enum SortingField {
    RELEASED_DATE( "released"),
    TITLE("title"),
    FORMAT("format"),
    LABEL("label"),
    CATALOG_NUMBER("catno"),
    COUNTRY("country"),;

    private final String field;

    SortingField(String field) {
        this.field = field;
    }

    public String getField() {
        return field;
    }
}
