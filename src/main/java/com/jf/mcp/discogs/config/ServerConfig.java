package com.jf.mcp.discogs.config;

import org.springframework.context.annotation.Configuration;

@Configuration
public class ServerConfig {
    private DiscogsApiConfig config;

    public ServerConfig(DiscogsApiConfig config) {
        this.config = config;
    }

    public String toString() {
        return """
                Discogs MCP Server Configuration :
                ================================
                
                %s
                """
                .formatted(config)
                ;
    }
}
