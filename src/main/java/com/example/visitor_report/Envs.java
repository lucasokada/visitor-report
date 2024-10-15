package com.example.visitor_report;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Envs {
    public static final String QUEUE_NAME = "EVENTS_QUEUE";
    public static final String EXCHANGE_NAME = "amq.direct";
}
