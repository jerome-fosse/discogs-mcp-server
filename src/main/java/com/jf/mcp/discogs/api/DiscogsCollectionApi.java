package com.jf.mcp.discogs.api;

import com.jf.mcp.discogs.config.DiscogsApiConfig;
import com.jf.mcp.discogs.model.*;
import com.jf.mcp.discogs.tools.*;
import org.slf4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
public class DiscogsCollectionApi {
    private final DiscogsApiConfig config;
    private final RestClient client;

    private static final Logger LOGGER = org.slf4j.LoggerFactory.getLogger(DiscogsCollectionApi.class);

    public DiscogsCollectionApi(DiscogsApiConfig config, RestClient client) {
        this.config = config;
        this.client = client;
    }

    public CollectionFolders getCollectionFolders(String username) {
        LOGGER.info("Getting collection folders for username: {}", username);

        return client.get()
                .uri("/users/{username}/collection/folders", username)
                .retrieve()
                .body(CollectionFolders.class);
    }

    public Folder createCollectionFolder(String username, String name) {
        LOGGER.info("Creating a collection folder for username: {}, folder name: {}", username, name);

        return client.post()
                .uri("/users/{username}/collection/folders", username)
                .body("{\"name\": \"%s\"}".formatted(name))
                .retrieve()
                .body(Folder.class);
    }

    public Folder getCollectionFolder(String username, Integer folderId) {
        LOGGER.info("Retrieve a collection folder for username: {}, folderId: {}", username, folderId);

        return client.get()
                .uri("/users/{username}/collection/folders/{folder_id}",
                        username, folderId)
                .retrieve()
                .body(Folder.class);
    }

    public ResponseEntity<Void> deleteCollectionFolder(String username, Integer folderId) {
        LOGGER.info("Delete a collection folder for username: {}, folderId: {}", username, folderId);

        return client.delete()
                .uri("/users/{username}/collection/folders/{folder_id}",
                        username, folderId)
                .retrieve()
                .toBodilessEntity();
    }

    public CollectionItems getCollectionItemsByRelease(String username, Integer releaseId) {
        LOGGER.info("Getting collection items for username: {}, releaseId: {}", username, releaseId);

        return client.get()
                .uri("/users/{username}/collection/releases/{release_id}",
                        username, releaseId)
                .retrieve()
                .body(CollectionItems.class);
    }

    public CollectionItems getCollectionItemsByFolder(String username, CollectionItemsCriteria criteria, PaginationCriteria pagination) {
        LOGGER.info("Getting collection items for username: {}, criteria: {}, pagination: {}", username, criteria, pagination);

        return client.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/users/{username}/collection/folders/{folder_id}/releases")
                        .queryParamIfPresent("sort", criteria.getSortingField().map(CollectionItemsSortingFields::getField))
                        .queryParamIfPresent("sort_order", criteria.getSortingOrder().map(order -> order.name().toLowerCase()))
                        .queryParam("page", pagination.getPage().orElse(1))
                        .queryParam("per_page", pagination.getPageSize().orElse(config.getPageSize()))
                        .build(username, criteria.getFolderId()))
                .retrieve()
                .body(CollectionItems.class);
    }

    public Instance addToCollectionFolder(String username, Integer releaseId, Integer folderId) {
        LOGGER.info("Add a release to a collection folder for username: {}, releaseId: {}, folderId: {}", username, releaseId, folderId);

        return client.post()
                .uri("/users/{username}/collection/folders/{folder_id}/releases/{release_id}",
                        username, folderId, releaseId)
                .retrieve()
                .body(Instance.class);
    }

    public ResponseEntity<Void> changeRatingOfRelease(String username, ChangeRatingOfReleaseCriteria criteria) {
        LOGGER.info("Change rating of a release for username: {}, releaseId: {}, folderId: {}, instanceId: {}, rating: {}",
                username, criteria.releaseId(), criteria.folderId(), criteria.instanceId(), criteria.rating());

        return client.post()
                .uri("/users/{username}/collection/folders/{folder_id}/releases/{release_id}/instances/{instance_id}",
                        username, criteria.folderId(), criteria.releaseId(), criteria.instanceId())
                .body("{\"rating\": %d}".formatted(criteria.rating()))
                .retrieve()
                .toBodilessEntity();
    }

    public ResponseEntity<Void> deleteInstanceFronFolder(String username, DeleteInstanceFromFolderCriteria criteria) {
        LOGGER.info("Deleting a release instance from folder for username: {}, folderId: {}, releaseId: {}, instance: {}",
                username, criteria.folderId(), criteria.releaseId(), criteria.instanceId());

        return client.delete()
                .uri("/users/{username}/collection/folders/{folder_id}/releases/{release_id}/instances/{instance_id}",
                        username, criteria.folderId(), criteria.releaseId(), criteria.instanceId())
                .retrieve()
                .toBodilessEntity();
    }

    public ResponseEntity<Void> removeInstanceFronFolder(String username,DeleteInstanceFromFolderCriteria criteria) {
        LOGGER.info("Removing a release instance from folder for username: {}, folderId: {}, releaseId: {}, instance: {}",
                username, criteria.folderId(), criteria.releaseId(), criteria.instanceId());

        return client.post()
                .uri("/users/{username}/collection/folders/{folder_id}/releases/{release_id}/instances/{instance_id}",
                        username, criteria.folderId(), criteria.releaseId(), criteria.instanceId())
                .retrieve()
                .toBodilessEntity();
    }

    public CollectionFields getCustomFields(String username) {
        LOGGER.info("Getting custom fields for username: {}", username);

        return client.get()
                .uri("/users/{username}/collection/fields", username)
                .retrieve()
                .body(CollectionFields.class);
    }

    public ResponseEntity<Void> editCustomFieldInstance(String username, EditFieldInstanceCriteria criteria) {
        LOGGER.info("Editing custom field for username: {}, folderId: {}, releaseId: {}, instanceId: {}, field: {}, value: {}",
                username, criteria.folderId(), criteria.releaseId(), criteria.instanceId(), criteria.fieldId(), criteria.value());

        return client.post()
                .uri("/users/{username}/collection/folders/{folder_id}/releases/{release_id}/instances/{instance_id}/fields/{field_id}",
                        username, criteria.folderId(), criteria.releaseId(), criteria.instanceId(), criteria.fieldId())
                .body("{\"value\": \"%s\"}".formatted(criteria.value()))
                .retrieve()
                .toBodilessEntity();
    }

    public CollectionValue getCollectionValue(String username) {
        LOGGER.info("Getting collection value for username: {}", username);

        return client.get()
                .uri("/users/{username}/collection/value", username)
                .retrieve()
                .body(CollectionValue.class);
    }
}
