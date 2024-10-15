package com.example.visitor_report.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "event")
public class EventEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "event_type")
    @Enumerated(EnumType.STRING)
    private EventType eventType;

    @Column(name = "occurred_at")
    private LocalDateTime occurredAt;

    @Column(name = "visitor_name")
    private String visitorName;

    @Column(name = "visitor_document_number")
    private String visitorDocumentNumber;

    @Column(name = "visitor_birth_date")
    private LocalDate bornAt;

    @Column(name = "visitor_type")
    @Enumerated(EnumType.STRING)
    private VisitorType visitorType;

    @Column(name = "company_name")
    private String companyName;

    @Column(name = "company_document_number")
    private String documentNumber;
}
