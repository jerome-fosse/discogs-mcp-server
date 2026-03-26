package com.jf.mcp.discogs.resources;

import com.jf.mcp.discogs.config.DiscogsApiConfig;
import org.springframework.ai.mcp.annotation.McpResource;
import org.springframework.stereotype.Component;

@Component
public class DiscogsResources {

    private final DiscogsApiConfig config;

    public DiscogsResources(DiscogsApiConfig config) {
        this.config = config;
    }

    @McpResource(
            uri = "discogs://Username",
            name = "Discogs username",
            description = "This is the username of the authenticated user. It can be used, if no other username is provided, to identify the user in Discogs API requests."
    )
    public String getUsername() {
        return config.getUsername();
    }
}
