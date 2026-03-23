package com.jf.mcp.discogs.model;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonCreator;
import java.util.LinkedHashMap;
import java.util.Map;

public record PriceSuggestions(Map<String, Price> prices) {

    @JsonCreator
    public PriceSuggestions() {
        this(new LinkedHashMap<>());
    }

    @JsonAnyGetter
    @Override
    public Map<String, Price> prices() {
        return prices;
    }

    @JsonAnySetter
    public void add(String condition, Price price) {
        prices.put(condition, price);
    }
}
