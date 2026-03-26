package com.jf.mcp.discogs.api;

import org.springframework.http.client.ClientHttpResponse;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class DiscogsApiException extends RuntimeException {
    public DiscogsApiException(String message) {
        super(message);
    }

    public DiscogsApiException(ClientHttpResponse response) throws IOException {
        super("Discogs API error " + response.getStatusCode().value() + ": " + new String(response.getBody().readAllBytes(), StandardCharsets.UTF_8));
    }

}
