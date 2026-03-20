package com.jf.mcp.discogs.config;

import com.jf.mcp.discogs.tools.DiscogsDatabaseService;
import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.ai.tool.method.MethodToolCallbackProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServerConfig {
    private DiscogsApiConfig config;

    public ServerConfig(DiscogsApiConfig config) {
        this.config = config;
        System.out.println(this);
    }

    @Bean
    public ToolCallbackProvider discogsDatabaseTools(DiscogsDatabaseService discogsDatabaseService) {
        return  MethodToolCallbackProvider.builder()
                .toolObjects(discogsDatabaseService)
                .build();
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
