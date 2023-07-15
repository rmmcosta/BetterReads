package com.rmmcosta.betterreadsbootstrap.openlibrarydeserialize;

import com.fasterxml.jackson.annotation.JsonProperty;

public record Book(
        String title,
        int[] covers,
        String key,
        WorkAuthorJson[] authors,
        Type type,
        String[] subjects,
        Description description,
        @JsonProperty("subject_places") String[] subjectPlaces,
        @JsonProperty("subject_people") String[] subjectPeople,
        @JsonProperty("latest_revision") int latestRevision,
        int revision, Created created,
        @JsonProperty("last_modified") LastModified lastModified) {}

