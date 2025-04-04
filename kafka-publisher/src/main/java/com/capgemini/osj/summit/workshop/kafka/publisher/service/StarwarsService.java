package com.capgemini.osj.summit.workshop.kafka.publisher.service;

import java.util.Random;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class StarwarsService {

    private RestTemplate restTemplate;

    private String swapiBaseUrl;
    private KafkaTemplate<String, String> kafkaTemplate;
    private String kafkaTopic;

    public StarwarsService(RestTemplate restTemplate, @Value("${api.swapi.base-url}") String swapiBaseUrl, KafkaTemplate<String, String> kafkaTemplate,
            @Value("${spring.kafka.topic.name}") String kafkaTopic) {
        this.restTemplate = restTemplate;
        this.swapiBaseUrl = swapiBaseUrl;
        this.kafkaTemplate = kafkaTemplate;
        this.kafkaTopic = kafkaTopic;
    }

    public String resolveRandomCharacter() {
        Random random = new Random();

        int randomPersonId = random.nextInt(83) + 1;
        return restTemplate.getForObject(swapiBaseUrl + "/people/" + randomPersonId, String.class);
    }

    public String resolveRandomCharacterAndForward() {
        String randomCharacter = resolveRandomCharacter();

        CompletableFuture<SendResult<String,String>> sentMessageFuture = kafkaTemplate.send(kafkaTopic, randomCharacter);

        sentMessageFuture.whenComplete((result, ex) -> {
            if (ex != null) {
                System.out.println("Error sending message: " + ex.getMessage());
            } else {
                System.out.println("Message sent successfully: " + result.getProducerRecord().value());
            }
        });

        return randomCharacter;
    }

}
