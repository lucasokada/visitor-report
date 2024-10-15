package com.example.visitor_report.service.messaging;

import com.example.visitor_report.Envs;
import com.example.visitor_report.dto.EventDto;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RabbitMQEventConsumer {
    @RabbitListener(queues = Envs.QUEUE_NAME)
    private void consume(EventDto<?> event) {
        System.out.println(event);
        System.out.println("------------------");
    }
}
