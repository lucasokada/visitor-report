package com.example.visitor_report.service;

import com.example.visitor_report.Envs;
import com.example.visitor_report.dto.CompanyDto;
import com.example.visitor_report.dto.CompanyVisitorAssociationDto;
import com.example.visitor_report.dto.EventDto;
import com.example.visitor_report.dto.VisitorDto;
import com.example.visitor_report.entity.EventEntity;
import com.example.visitor_report.repository.EventRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;

@Service
@RequiredArgsConstructor
public class RabbitMQEventConsumer {
    private final ObjectMapper mapper;
    private final EventRepository repository;

    @RabbitListener(queues = Envs.QUEUE_NAME)
    private void consume(EventDto<Object> event) {
        System.out.println(event);
        System.out.println("------------------");
        EventEntity entity = getEvent(event);
        repository.save(entity);
    }

    private EventEntity getEvent(EventDto<?> event) {
        EventEntity.EventEntityBuilder eventBuilder = EventEntity.builder()
                .eventType(event.eventType())
                .occurredAt(event.occurredAt());

        var content = (LinkedHashMap) event.content();
        if(content.containsKey("companyDto") && content.containsKey("visitorDto")) {
            CompanyVisitorAssociationDto association = mapper.convertValue(content, CompanyVisitorAssociationDto.class);
            buildCompanyVisitantAssociationContent(eventBuilder, association);
        } else if(content.containsKey("visitorType")) {
            VisitorDto visitor = mapper.convertValue(content, VisitorDto.class);
            buildVisitorContent(eventBuilder, visitor);
        } else if(content.containsKey("companyName")) {
            CompanyDto company = mapper.convertValue(content, CompanyDto.class);
            buildCompanyContent(eventBuilder, company);
        } else {
            throw new IllegalArgumentException("Unknown content type: " + content.getClass());
        }

        return eventBuilder.build();
    }

    private void buildVisitorContent(EventEntity.EventEntityBuilder eventBuilder, VisitorDto content) {
        eventBuilder
                .visitorName(content.name())
                .visitorDocumentNumber(content.documentNumber())
                .bornAt(content.bornAt())
                .visitorType(content.visitorType());
    }

    private void buildCompanyContent(EventEntity.EventEntityBuilder eventBuilder, CompanyDto content) {
        eventBuilder
                .companyName(content.companyName())
                .documentNumber(content.documentNumber());
    }

    private void buildCompanyVisitantAssociationContent(EventEntity.EventEntityBuilder eventBuilder, CompanyVisitorAssociationDto companyVisitorAssociationDto) {
        buildVisitorContent(eventBuilder, companyVisitorAssociationDto.visitorDto());
        buildCompanyContent(eventBuilder, companyVisitorAssociationDto.companyDto());
    }
}
