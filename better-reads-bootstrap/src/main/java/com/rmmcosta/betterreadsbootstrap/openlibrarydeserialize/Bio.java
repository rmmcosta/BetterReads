package com.rmmcosta.betterreadsbootstrap.openlibrarydeserialize;

import com.fasterxml.jackson.annotation.JsonProperty;

public record Bio(
        @JsonProperty("type") String type,
        @JsonProperty("value") String value
) {
}
