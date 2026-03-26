package com.jf.mcp.discogs.tools;

import org.springframework.ai.mcp.annotation.McpToolParam;

public record EditFieldInstanceCriteria(
        @McpToolParam(description = "The ID of the folder to modify. Example 3") Integer folderId,
        @McpToolParam(description = "The ID of the release you are modifying. Example 130076") Integer releaseId,
        @McpToolParam(description = "The ID of the instance. Example 1") Integer instanceId,
        @McpToolParam(description = "The ID of the instance. Example 8") Integer fieldId,
        @McpToolParam(description = "The new value of the field. If the field’s type is dropdown, the value must match one of the values in the field’s list of options.") String value) {
}
