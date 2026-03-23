package com.jf.mcp.discogs.tools;

import com.jf.mcp.discogs.api.DiscogsApi;
import com.jf.mcp.discogs.model.*;
import org.springframework.ai.mcp.annotation.McpTool;
import org.springframework.ai.mcp.annotation.McpToolParam;
import org.springframework.stereotype.Component;

@Component
public class DiscogsDatabaseTools {

    private final DiscogsApi api;

    public DiscogsDatabaseTools(DiscogsApi api) {
        this.api = api;
    }

    @McpTool(name = "discogs_database_master_release", description = "Get a master release by its id. A Master represents a set of similar Releases. Masters (also known as “master releases”) have a “main release” which is often the chronologically earliest.")
    public MasterRelease getMasterReleaseById(@McpToolParam(description = "The id of the master release") String id) {
        return api.getMasterReleaseById(id);
    }

    @McpTool(name = "discogs_database_release", description = "Get a release by its id. A Release represents a particular physical or digital object released by one or more Artists")
    public Release getReleaseById(@McpToolParam(description = "The id of the release") String id) {
        return api.getReleaseById(id);
    }

    @McpTool(name = "discogs_database_master_release_versions", description = "Retrieves a list of all Releases that are versions of this master.")
    public MasterReleaseVersions getMasterReleaseVersions(
            @McpToolParam(description = "The id of the master release") String id,
            @McpToolParam(description = "Search criteria") MasterReleaseVersionsCriteria masterReleaseVersionsCriteria,
            @McpToolParam(description = "Pagination criteria") PaginationCriteria paginationCriteria) {
        return api.getMasterReleaseVersions(id, masterReleaseVersionsCriteria, paginationCriteria);
    }

    @McpTool(name = "discogs_database_search", description = "Search for releases, masters, labels, artists, or users.")
    public SearchResults search(
            @McpToolParam(description = "Search criteria") SearchCriteria searchCriteria,
            @McpToolParam(description = "Pagination criteria") PaginationCriteria paginationCriteria) {
        return api.search(searchCriteria, paginationCriteria);
    }

    @McpTool(name = "discogs_database_artist", description = "Get an artist by its id. The Artist represents a person in the Discogs database who contributed to a Release in some capacity.")
    public Artist getArtist(@McpToolParam(description = "The id of the artist. Example: 108713") String id) {
        return api.getArtist(id);
    }

    @McpTool(name = "discogs_database_label_releases", description = "Returns a list of Releases associated with the label. Accepts Pagination parameters.")
    public LabelReleases getLabelReleases(
            @McpToolParam(description = "The id of the label. Example: 20171") String id,
            @McpToolParam(description = "Pagination criteria") PaginationCriteria paginationCriteria) {
        return api.getLabelReleases(id, paginationCriteria);
    }
}
