package com.project.manager.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Setter
@Getter
public class CurrencyResponse {
    String base;
    JsonNode rates;
    @JsonProperty("start_at")
    String startAt;
    @JsonProperty("end_at")
    String endAt;
}
