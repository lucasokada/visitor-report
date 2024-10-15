package com.example.visitor_report.dto;

import com.example.visitor_report.entity.EventType;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

public record EventDto<T>(
        EventType eventType,
        T content,
        LocalDateTime occurredAt
) implements Serializable {
    public EventDto(EventType eventType, T content) {
        this(eventType, content, LocalDateTime.now());
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (!(object instanceof EventDto<?> eventDto)) return false;
        return Objects.equals(content, eventDto.content) && eventType == eventDto.eventType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(eventType, content);
    }
}
