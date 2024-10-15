package com.example.visitor_report.repository;

import com.example.visitor_report.entity.EventEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<EventEntity, Long> {
    @Query("SELECT e FROM EventEntity e WHERE DATE(e.occurredAt) BETWEEN :startDate AND :endDate")
    List<EventEntity> findAllByOccurredAtBetween(LocalDate startDate, LocalDate endDate);
}
