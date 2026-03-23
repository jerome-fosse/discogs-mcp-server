package com.jf.mcp.discogs.tools;

import com.jf.mcp.discogs.api.DiscogsApi;
import com.jf.mcp.discogs.model.PriceSuggestions;
import org.springframework.ai.mcp.annotation.McpTool;
import org.springframework.ai.mcp.annotation.McpToolParam;
import org.springframework.stereotype.Component;

@Component
public class DiscogsMarketplaceTools {

    private final DiscogsApi api;


    public DiscogsMarketplaceTools(DiscogsApi api) {
        this.api = api;
    }

    @McpTool(name = "discogs_marketplace_price_suggestions", description = "Retrieve price suggestions for the provided Release ID. If no suggestions are available, an empty object will be returned. Suggested prices will be denominated in the user’s selling currency and the user needs to have filled out their seller settings.")
    public PriceSuggestions getPriceSuggestions(@McpToolParam(description = "The release ID to calculate a price from.") String releaseId) {
        return api.getPriceSuggestions(releaseId);
    }
}
