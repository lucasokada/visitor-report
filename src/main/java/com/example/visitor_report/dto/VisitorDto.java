package com.example.visitor_report.dto;

import com.example.visitor_report.entity.VisitorType;

import java.io.Serializable;
import java.time.LocalDate;

public record VisitorDto(
        String name,
        String documentNumber,
        LocalDate bornAt,
        VisitorType visitorType
) implements Serializable {
}
