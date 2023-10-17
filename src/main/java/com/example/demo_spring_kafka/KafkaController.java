package com.example.demo_spring_kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class KafkaController {
    @Autowired
    KafkaProducer kafkaProducer;

    @Autowired
    KafkaConsumer kafkaConsumer;

    @PostMapping("/send")
    public String sendMessage(@RequestBody Map<String, String> payload) {
        String topic = payload.get("topic");
        String message = payload.get("message");
        int total = Integer.parseInt(payload.get("total"));
        kafkaConsumer.setTotalMessages(total);
        for (int i = 1; i <= total; i++) {
            kafkaProducer.sendMessage(topic, message + "___" + i);
        }
        return "Message sent to topic " + topic;
    }
}
