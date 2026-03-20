package com.jf.mcp.discogs.tools;

import com.jf.mcp.discogs.api.DiscogsApi;
import com.jf.mcp.discogs.api.MasterReleaseVersionsParams;
import com.jf.mcp.discogs.api.SortingField;
import com.jf.mcp.discogs.api.SortingOrder;
import com.jf.mcp.discogs.model.MasterRelease;
import com.jf.mcp.discogs.model.MasterReleaseVersions;
import com.jf.mcp.discogs.model.Release;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.stereotype.Service;

@Service
public class DiscogsDatabaseService {

    private final DiscogsApi api;

    public DiscogsDatabaseService(DiscogsApi api) {
        this.api = api;
    }

    @Tool(name = "database_master_release", description = "Get a master release by its id")
    public MasterRelease getMasterReleaseById(@ToolParam(description = "The id of the master release") String id) {
        return api.getMasterReleaseById(id).block();
    }

    @Tool(name = "database_release", description = "Get a release by its id")
    public Release getReleaseById(@ToolParam(description = "The id of the release") String id) {
        return api.getReleaseById(id).block();
    }

    @Tool(name = "database_master_release_versions", description = "Get the versions of a master release")
    public MasterReleaseVersions getMasterReleaseVersions(
            @ToolParam(description = "The id of the master release") String id,
            @ToolParam(description = "The page you want to request. Example: 3", required = false) Integer page,
            @ToolParam(description = "The number of items per page. Example: 25,. Default is 30", required = false) Integer pageSize,
            @ToolParam(description = "The format to filter (Vinyl, CD, Cassette, EP....)", required = false) String format,
            @ToolParam(description = "The label to filter. Example: Columbia", required = false) String label,
            @ToolParam(description = "The release year to filter. Example: 1992", required = false) Integer released,
            @ToolParam(description = "The country to filter. Example: France", required = false) String country,
            @ToolParam(description = "Sort release versions by : (released, title, format, label, catno or country)", required = false) SortingField sort,
            @ToolParam(description = "Sorting items order (asc or desc)", required = false) SortingOrder order
    ) {
        return api.getMasterReleaseVersions(id, MasterReleaseVersionsParams
                .build(builder -> builder.page(page).pageSize(pageSize).format(format).label(label)
                        .released(released).country(country).sort(sort).order(order))).block();
    }

}
