package com.example.demo;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

@RestController
public class MessageProducer {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @PostMapping("/send")
    public ResponseEntity<String> sendMessage(@RequestBody String message) {
        try {
            rabbitTemplate.convertAndSend(RabbitConfig.QUEUE_NAME, message);
            return ResponseEntity.ok("Message sent: " + message);
        } catch (Exception e) {
            e.printStackTrace(); // Log the error
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .body("Failed to send message: " + e.getMessage());
        }
    }
}
