package com.example.visitor_report.config;

import com.example.visitor_report.entity.EventType;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

class EventTypeSerializer extends JsonSerializer<EventType> {
    @Override
    public void serialize(EventType value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        gen.writeString(value.name());
    }
}