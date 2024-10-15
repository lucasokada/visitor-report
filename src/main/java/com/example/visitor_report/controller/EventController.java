package com.example.visitor_report.controller;

import com.example.visitor_report.service.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalDateTime;

@RestController
@RequestMapping(path = "/v1/report")
@RequiredArgsConstructor
public class EventController {
    private final EventService eventService;

    @GetMapping("/from/{initialDate}/to/{finalDate}")
    public ResponseEntity<Resource> getEventReport(@PathVariable("initialDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate start,
                                                    @PathVariable("finalDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate end) {
        try {
            if(start.isBefore(end) || LocalDate.now().isBefore(end)) {
                Resource resource = eventService.generateEventReport(start, end);
                return ResponseEntity.ok()
                        .contentType(MediaType.APPLICATION_OCTET_STREAM)
                        .body(resource);
            } else {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
