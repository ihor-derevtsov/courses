package me.ihor.derevtsov.kafkaexample;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaListeners {
    @KafkaListener(topics = "amigoscode", groupId = "foo", containerFactory = "messageFactory")
    void listener(Message message) {
        System.out.println("Received: " + message);
    }

}
