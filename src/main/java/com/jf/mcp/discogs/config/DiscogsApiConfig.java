package com.jf.mcp.discogs.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "discogs.api")
public class DiscogsApiConfig {
    private String token;
    private String baseUrl;
    private Integer timeout;
    private Integer pageSize;

    public void setToken(String token) {
        this.token = token;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public void setTimeout(Integer timeout) {
        this.timeout = timeout;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public String getToken() {
        return token;
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public Integer getTimeout() {
        return timeout;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public String toString() {
        return """
                DiscogsApiConfig:
                   token: %s
                   baseUrl: %s
                   timeout: %s
                   pageSize: %s
                """
                .formatted("**********", baseUrl, timeout, pageSize);
    }
}
