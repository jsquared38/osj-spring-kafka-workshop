package com.capgemini.osj.summit.workshop.kafka.consumer.service;

import org.springframework.stereotype.Service;

import com.capgemini.osj.summit.workshop.kafka.consumer.entity.StarwarsPerson;
import com.capgemini.osj.summit.workshop.kafka.consumer.repository.StarwarsPersonRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class StarwarsPeopleService {

    private StarwarsPersonRepository starwarsPersonRepository;

    public StarwarsPeopleService(StarwarsPersonRepository starwarsPersonRepository) {
        this.starwarsPersonRepository = starwarsPersonRepository;
    }

    /**
     * We only save people, we do not update them ;)
     */
    public void savePerson(String name, String height, String mass, String birthYear) {
        StarwarsPerson starwarsPerson = StarwarsPerson.builder()
                .name(name)
                .height(height)
                .mass(mass)
                .birthYear(birthYear)
                .build();

        log.info("Saving Starwars person: {}", starwarsPerson);
        starwarsPersonRepository.save(starwarsPerson);
    }

}
