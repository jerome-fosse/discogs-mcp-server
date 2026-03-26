package com.jf.mcp.discogs.tools;

import com.jf.mcp.discogs.api.DiscogsCollectionApi;
import com.jf.mcp.discogs.config.DiscogsApiConfig;
import com.jf.mcp.discogs.model.*;
import org.springframework.ai.mcp.annotation.McpTool;
import org.springframework.ai.mcp.annotation.McpToolParam;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class DiscogsCollectionTools {

    private final DiscogsCollectionApi api;
    private final DiscogsApiConfig config;

    public DiscogsCollectionTools(DiscogsCollectionApi api, DiscogsApiConfig config) {
        this.api = api;
        this.config = config;
    }

    @McpTool(name = "discogs_collection_folders", description = "Retrieve a list of folders in a user’s collection. If the collection has been made private by its owner, authentication as the collection owner is required. If you are not authenticated as the collection owner, only folder ID 0 (the “All” folder) will be visible")
    public CollectionFolders getCollectionFolders(
            @McpToolParam(description = "The username of the collection you are trying to retrieve. If not provided, defaults to the authenticated user. Example rodneyfool", required = false) String username) {
        return api.getCollectionFolders(StringUtils.hasText(username) ? username : config.getUsername());
    }

    @McpTool(name = "discogs_collection_folder", description = "Retrieve metadata about a folder in a user’s collection.")
    public Folder getCollectionFolder(
            @McpToolParam(description = "The username of the collection you are trying to request. If not provided, defaults to the authenticated user. Example rodneyfool", required = false) String username,
            @McpToolParam(description = "The ID of the folder to request. Example: 3") Integer folderId) {
        return api.getCollectionFolder(StringUtils.hasText(username) ? username : config.getUsername(), folderId);
    }

    @McpTool(name = "discogs_collection_folder_create", description = "Create a new folder in a user’s collection.")
    public Folder createCollectionFolder(
            @McpToolParam(description = "The username of the collection you are trying to retrieve. If not provided, defaults to the authenticated user. Example rodneyfool", required = false) String username,
            @McpToolParam(description = "The name of the newly-created folder. Example: My favorites") String name) {
        return api.createCollectionFolder(StringUtils.hasText(username) ? username : config.getUsername(), name);
    }

    @McpTool(name = "discogs_collection_folder_delete", description = "Delete a folder in a user’s collection. A folder must be empty before it can be deleted. Folders 0 and 1 cannot be deleted.")
    public Message deleteCollectionFolder(
            @McpToolParam(description = "The username of the collection you are trying to modify. If not provided, defaults to the authenticated user. Example rodneyfool", required = false) String username,
            @McpToolParam(description = "The ID of the folder to request. Example: 3") Integer folderId) {
        var response = api.deleteCollectionFolder(StringUtils.hasText(username) ? username : config.getUsername(), folderId);
        return response.getStatusCode().equals(HttpStatus.NO_CONTENT) ?
            new Message("Folder %s deleted successfully.".formatted(folderId)) :
            new Message("Unexpected answer from discogs with status %s.".formatted(response.getStatusCode().value()));
    }

    @McpTool(name = "discogs_collection_items_by_release", description = "View the user’s collection folders which contain a specified release. This will also show information about each release instance. The release_id must be non-zero.")
    public CollectionItems getCollectionItemsByRelease(
            @McpToolParam(description = "The username of the collection you are trying to view. If not provided, defaults to the authenticated user. Example rodneyfool", required = false) String username,
            @McpToolParam(description = "The ID of the release to request. Example: 7781525") Integer releaseId) {
        return api.getCollectionItemsByRelease(StringUtils.hasText(username) ? username : config.getUsername(), releaseId);
    }

    @McpTool(name = "discogs_collection_items_by_folder", description = "Returns the list of item in a folder in a user’s collection. Accepts Pagination parameters. Basic information about each release is provided, suitable for display in a list. For detailed information, make another API call to fetch the corresponding release.")
    public CollectionItems getCollectionItemsByFolder(
            @McpToolParam(description = "The username of the collection you are trying to view. If not provided, defaults to the authenticated user. Example rodneyfool", required = false) String username,
            @McpToolParam(description = "The criteria to filter the collection items by.") CollectionItemsCriteria criteria,
            @McpToolParam(description = "Pagination criteria for the collection items.") PaginationCriteria pagination) {
        return api.getCollectionItemsByFolder(StringUtils.hasText(username) ? username : config.getUsername(), criteria, pagination);
    }

    @McpTool(name = "discogs_collection_add_to_folder", description = "Add a release to a folder in a user’s collection. The folder_id must be non-zero. You can use 1 for “Uncategorized”")
    public Instance addToCollectionFolder(
            @McpToolParam(description = "The username of the collection you are trying to modify. If not provided, defaults to the authenticated user. Example rodneyfool", required = false) String username,
            @McpToolParam(description = "The ID of the folder to request. Example: 3") Integer folderId,
            @McpToolParam(description = "The ID of the release to request. Example: 7781525") Integer releaseId) {
        return api.addToCollectionFolder(StringUtils.hasText(username) ? username : config.getUsername(), releaseId, folderId);
    }

    @McpTool(name = "discogs_collection_release_change_rating", description = "Change the rating of a release in a user’s collection.")
    public Message changeRatingOfRelease(
            @McpToolParam(description = "The username of the collection you are trying to modify. If not provided, defaults to the authenticated user. Example rodneyfool", required = false) String username,
            @McpToolParam(description = "The criteria for the release rating change.") ChangeRatingOfReleaseCriteria criteria) {
        var response = api.changeRatingOfRelease(StringUtils.hasText(username) ? username : config.getUsername(), criteria);
        return response.getStatusCode().equals(HttpStatus.NO_CONTENT) ?
                new Message("Rating changed to %d on release with id %d.".formatted(criteria.rating(), criteria.releaseId())) :
                new Message("Unexpected answer from discogs with status %s.".formatted(response.getStatusCode().value()));
    }

    @McpTool(name = "discogs_delete_release_instance_from_folder", description = "Remove an instance of a release from a user’s collection folder.")
    public Message deleteReleaseInstanceFromFolder(
            @McpToolParam(description = "The username of the collection. If not provided, defaults to the authenticated user. Example rodneyfool", required = false) String username,
            @McpToolParam(description = "The criteria for the release instance deletion.") DeleteInstanceFromFolderCriteria criteria) {
        var response = api.deleteInstanceFronFolder(StringUtils.hasText(username) ? username : config.getUsername(), criteria);
        return response.getStatusCode().equals(HttpStatus.NO_CONTENT) ?
                new Message("Instance deleted from folder with id %d.".formatted(criteria.folderId())) :
                new Message("Unexpected answer from discogs with status %s.".formatted(response.getStatusCode().value()));
    }

    @McpTool(name = "discogs_remove_release_instance_from_folder", description = "Remove an instance of a release from a user’s collection folder and move it to the “Uncategorized” folder.")
    public Message removeReleaseInstanceFromFolder(
            @McpToolParam(description = "The username of the collection. If not provided, defaults to the authenticated user. Example rodneyfool", required = false) String username,
            @McpToolParam(description = "The criteria for the release instance removal.") DeleteInstanceFromFolderCriteria criteria) {
        var response = api.removeInstanceFronFolder(StringUtils.hasText(username) ? username : config.getUsername(), criteria);
        return response.getStatusCode().equals(HttpStatus.NO_CONTENT) ?
                new Message("Instance removed from folder with id %d.".formatted(criteria.folderId())) :
                new Message("Unexpected answer from discogs with status %s.".formatted(response.getStatusCode().value()));
    }

    @McpTool(name = "discogs_list_custom_collection_fields", description = "Retrieve a list of user-defined collection notes fields. These fields are available on every release in the collection.")
    public CollectionFields listCustomCollectionFields(
            @McpToolParam(description = "The username of the collection. If not provided, defaults to the authenticated user. Example rodneyfool", required = false) String username) {
        return api.getCustomFields(StringUtils.hasText(username) ? username : config.getUsername());
    }

    @McpTool(name = "discogs_edit_custom_collection_field_instance", description = "Edit a user-defined collection notes field of a particular release instance.")
    public Message editCustomCollectionFieldInstance(
            @McpToolParam(description = "The username of the collection. If not provided, defaults to the authenticated user. Example rodneyfool", required = false) String username,
            @McpToolParam(description = "The criteria for the release instance field edition.") EditFieldInstanceCriteria criteria) {
        var response = api.editCustomFieldInstance(StringUtils.hasText(username) ? username : config.getUsername(), criteria);
        return response.getStatusCode().equals(HttpStatus.NO_CONTENT) ?
                new Message("Field instance has been edited.") :
                new Message("Unexpected answer from discogs with status %s.".formatted(response.getStatusCode().value()));
    }

    @McpTool(name = "discogs_collection_value", description = "Returns the minimum, median, and maximum value of a user’s collection.")
    public CollectionValue getCollectionValue(
            @McpToolParam(description = "The username of the collection. If not provided, defaults to the authenticated user. Example rodneyfool", required = false) String username) {
        return api.getCollectionValue(StringUtils.hasText(username) ? username : config.getUsername());
    }
}
