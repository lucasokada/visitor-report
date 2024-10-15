package com.example.visitor_report.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "event")
public class EventEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "event_type")
    EventType eventType;

    @Column(name = "occurred_at")
    LocalDateTime occurredAt;

    @Column(name = "visitor_name")
    String visitorName;

    @Column(name = "visitor_document_number")
    String visitorDocumentNumber;

    @Column(name = "visitor_birth_date")
    LocalDate bornAt;

    @Column(name = "visitor_type")
    VisitorType visitorType;

    @Column(name = "company_name")
    String companyName;

    @Column(name = "company_document_number")
    String documentNumber;
}
