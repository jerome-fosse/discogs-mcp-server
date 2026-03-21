package com.jf.mcp.discogs.config;

import org.springframework.context.annotation.Configuration;

@Configuration
public class ServerConfig {
    private DiscogsApiConfig config;

    public ServerConfig(DiscogsApiConfig config) {
        this.config = config;
        System.out.println(this);
    }

    public String toString() {
        return """
                Discogs MCp Server Configuration :
                ================================
                
                %s
                """
                .formatted(config)
                ;
    }
}
