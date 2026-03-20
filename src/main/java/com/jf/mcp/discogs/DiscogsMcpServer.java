package com.jf.mcp.discogs;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class DiscogsMcpServer {

    public static void main(String[] args) {
        new SpringApplicationBuilder()
                .sources(DiscogsMcpServer.class)
                .run(args);
    }
}
