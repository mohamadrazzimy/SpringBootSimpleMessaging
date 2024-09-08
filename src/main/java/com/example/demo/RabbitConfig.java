package com.example.demo;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {
    public static final String QUEUE_NAME = "demoQueue";

    @Bean
    public Queue demoQueue() {
        return new Queue(QUEUE_NAME, false);
    }
}
