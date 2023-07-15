package com.rmmcosta.betterreadsbootstrap.openlibrarydeserialize;

import com.fasterxml.jackson.annotation.JsonProperty;

public record AuthorJson(
        Bio bio,
        String name,
        Link[] links,
        @JsonProperty("personal_name") String personalName,
        String wikipedia,
        @JsonProperty("death_date") String deathDate,
        @JsonProperty("alternate_names") String[] alternateNames,
        Created created,
        int[] photos,
        @JsonProperty("last_modified") LastModified lastModified,
        @JsonProperty("latest_revision") int latestRevision,
        String key,
        @JsonProperty("birth_date") String birthDate,
        int revision,
        Type type,
        @JsonProperty("remote_ids") RemoteIds remoteIds,
        @JsonProperty("source_records") String[] sourceRecords
) {
}

