package com.jf.mcp.discogs.tools;

import org.springframework.ai.mcp.annotation.McpToolParam;

public record ChangeRatingOfReleaseCriteria(
        @McpToolParam(description = "The ID of the folder to modify. Exemple 3") Integer folderId,
        @McpToolParam(description = "The ID of the release you are modifying. Example: 130076") Integer releaseId,
        @McpToolParam(description = "The ID of the instance. Example: 1") Integer instanceId,
        @McpToolParam(description = "The rating of the instance you are supplying from 1 to 5. Example: 5") Integer rating) {
}
