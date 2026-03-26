package com.jf.mcp.discogs.tools;

import org.springframework.ai.mcp.annotation.McpToolParam;

public record DeleteInstanceFromFolderCriteria(
        @McpToolParam(description = "The ID of the folder to modify. Example 3") Integer folderId,
        @McpToolParam(description = "The ID of the release you are modifying.. Example 130076") Integer releaseId,
        @McpToolParam(description = "The ID of the instance.") Integer instanceId) { }
