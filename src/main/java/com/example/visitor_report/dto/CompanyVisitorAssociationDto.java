package com.example.visitor_report.dto;

import java.io.Serializable;

public record CompanyVisitorAssociationDto(
        CompanyDto companyDto,
        VisitorDto visitorDto
) implements Serializable {
}
