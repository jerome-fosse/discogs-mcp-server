package com.jf.mcp.discogs.config;

import jakarta.annotation.PostConstruct;
import jakarta.validation.constraints.NotBlank;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;
import org.springframework.validation.annotation.Validated;

import java.util.Arrays;

@Configuration
@ConfigurationProperties(prefix = "discogs.api")
@Validated
public class DiscogsApiConfig implements EnvironmentAware {
    private static final Logger LOGGER = LoggerFactory.getLogger(DiscogsApiConfig.class);

    @NotBlank
    private String token;
    @NotBlank
    private String username;
    private String baseUrl;
    private Integer timeout;
    private Integer pageSize;
    private Environment environment;

    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

    @PostConstruct
    public void logConfig() {
        if (!Arrays.asList(environment.getActiveProfiles()).contains("stdio")) {
            LOGGER.info(toString());
        }
    }

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

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
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
        return "DISCOGS API CONFIGURATION: token: %s, username: %s, baseUrl: %s, timeout: %s, pageSize: %s"
                .formatted("***************", username, baseUrl, timeout, pageSize);
    }
}
