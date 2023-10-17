package com.example.demo_spring_kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicInteger;

@Service
public class KafkaConsumer {
    private AtomicInteger counter = new AtomicInteger(0);
    private long startTime = 0;

    private int totalMessages = 0;

    public void setTotalMessages(int totalMessages) {
        this.totalMessages = totalMessages;
    }

    @KafkaListener(topics = "int.notification-system.notification", groupId = "int.notification-consumer")
    public void consume(String message) {
        // Capture start time for the first message
        if (startTime == 0) {
            startTime = System.currentTimeMillis();
        }

        var name = Thread.currentThread().getName();
        System.out.println("Current thread: " + name);
        System.out.println("Consumed message: " + message);

        // Simulate processing time
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        // Increment the counter
        int currentCount = counter.incrementAndGet();
        // If this is the last message, capture the end time and calculate the total time
        if (currentCount == totalMessages) {
            long endTime = System.currentTimeMillis();
            System.out.println("Total time for processing: " + (endTime - startTime) + " ms");

            // Reset the counter
            counter.set(0);
            startTime = 0;
        }
    }
}
