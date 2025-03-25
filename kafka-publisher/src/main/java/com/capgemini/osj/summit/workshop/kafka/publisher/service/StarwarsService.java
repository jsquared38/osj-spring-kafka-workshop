package com.capgemini.osj.summit.workshop.kafka.publisher.service;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class StarwarsService {

    private RestTemplate restTemplate;

    private String swapiBaseUrl;
    private String workshopConsumerBaseUrl;

    @Autowired
    public StarwarsService(RestTemplate restTemplate, @Value("${api.swapi.base-url}") String swapiBaseUrl,
            @Value("${api.workshop-consumer.base-url}") String workshopConsumerBaseUrl) {
        this.restTemplate = restTemplate;
        this.swapiBaseUrl = swapiBaseUrl;
        this.workshopConsumerBaseUrl = workshopConsumerBaseUrl;
    }

    public String resolveRandomCharacter() {
        Random random = new Random();

        int randomPersonId = random.nextInt(83) + 1;
        return restTemplate.getForObject(swapiBaseUrl + "/people/" + randomPersonId, String.class);
    }

    public String resolveRandomCharacterAndForward() {
        String randomCharacter = resolveRandomCharacter();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> request = new HttpEntity<>(randomCharacter, headers);
        
        return restTemplate.postForObject(workshopConsumerBaseUrl + "/people", request, String.class);
    }

}
