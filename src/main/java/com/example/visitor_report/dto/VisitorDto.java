package com.example.visitor_report.dto;

import com.example.visitor_report.entity.VisitorType;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.time.LocalDate;

public record VisitorDto(
        @JsonProperty("name") String name,
        @JsonProperty("documentNumber") String documentNumber,
        @JsonProperty("bornAt") LocalDate bornAt,
        @JsonProperty("visitorType") VisitorType visitorType
) implements Serializable {
}
