package com.capgemini.osj.summit.workshop.kafka.consumer.listener;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.capgemini.osj.summit.workshop.kafka.consumer.model.StarwarsPersonModel;
import com.capgemini.osj.summit.workshop.kafka.consumer.service.StarwarsPeopleService;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class StarwarsPeopleListener {

    private StarwarsPeopleService starwarsPeopleService;

    public StarwarsPeopleListener(StarwarsPeopleService starwarsPeopleService) {
        this.starwarsPeopleService = starwarsPeopleService;
    }

    @KafkaListener(topics = "${spring.kafka.topic.name}", groupId = "${spring.kafka.consumer.group-id}")
    public void listen(StarwarsPersonModel starwarsPersonModel) {
        log.info("Received message: {}", starwarsPersonModel);
        starwarsPeopleService.savePerson(starwarsPersonModel.name(), starwarsPersonModel.height(), starwarsPersonModel.mass(), starwarsPersonModel.birthYear());
    }

}
