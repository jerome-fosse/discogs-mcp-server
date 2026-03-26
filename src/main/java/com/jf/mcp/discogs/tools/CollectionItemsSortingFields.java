package com.jf.mcp.discogs.tools;

public enum CollectionItemsSortingFields {
    LABEL("label"),
    ARTIST("artist"),
    TITLE("title"),
    CATALOG_NUMBER("catno"),
    FORMAT("format"),
    RATING(""),
    DATE_ADDED("added"),
    YEAR("year"),;

    private final String field;

    CollectionItemsSortingFields(String field) {
        this.field = field;
    }

    public String getField() {
        return field;
    }
}
