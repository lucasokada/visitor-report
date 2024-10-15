package com.example.visitor_report.service;

import com.example.visitor_report.entity.EventEntity;
import com.example.visitor_report.repository.EventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EventService {
    private final EventRepository eventRepository;

    public Resource generateEventReport(LocalDate initialDate, LocalDate finalDate) throws IOException {

        List<EventEntity> events = eventRepository.findAllByOccurredAtBetween(initialDate, finalDate);
        File tempFile = File.createTempFile("event_report_", ".txt");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {
            events.sort(Comparator.comparing(EventEntity::getOccurredAt));
            for(EventEntity event : events) {
                writer.write(event.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return new FileSystemResource(tempFile);
    }
}
