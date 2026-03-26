package com.jf.mcp.discogs.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.OffsetDateTime;
import java.util.List;

public record CollectionItem(
    Integer id,
    @JsonProperty("instance_id") Integer instanceId,
    @JsonProperty("folder_id") Integer folderId,
    Integer rating,
    @JsonProperty("basic_information") CollectionItemBasicInformation basicInformation,
    @JsonProperty("date_added") OffsetDateTime dateAdded,
    List<CollectionItemNote> notes
) {}
