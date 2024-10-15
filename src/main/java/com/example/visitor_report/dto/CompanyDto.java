package com.example.visitor_report.dto;

import java.io.Serializable;

public record CompanyDto(
        String companyName,
        String documentNumber
) implements Serializable {
}
