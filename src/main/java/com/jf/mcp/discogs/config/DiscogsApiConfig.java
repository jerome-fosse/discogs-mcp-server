package com.jf.mcp.discogs.config;

import jakarta.validation.constraints.NotBlank;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;

@Configuration
@ConfigurationProperties(prefix = "discogs.api")
@Validated
public class DiscogsApiConfig {
    @NotBlank
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
                .formatted("***************", baseUrl, timeout, pageSize);
    }
}
