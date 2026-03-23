package com.jf.mcp.discogs.completion;

import org.springframework.ai.mcp.annotation.McpComplete;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Component
public class ReferenceCompletions {
    private final ResourcePatternResolver resourceLoader;
    private final List<String> rightSocietiesNames;

    public ReferenceCompletions(ResourcePatternResolver resourceLoader) throws IOException {
        this.resourceLoader = resourceLoader;

        var resources = resourceLoader.getResources("classpath:mcp/logos/*.txt");
        rightSocietiesNames = Arrays.stream(resources)
                .map(resource -> resource.getFilename().replace(".txt", ""))
                .toList();

    }

    @McpComplete(uri = "reference://rights-societies/{societyName}/logo")
    public List<String> completeRightSocietiesNames() {
        return rightSocietiesNames;
    }
}
