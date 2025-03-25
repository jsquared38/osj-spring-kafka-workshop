package com.capgemini.osj.summit.workshop.kafka.consumer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.osj.summit.workshop.kafka.consumer.entity.StarwarsPerson;
import com.capgemini.osj.summit.workshop.kafka.consumer.repository.StarwarsPersonRepository;

@Service
public class StarwarsPeopleService {

    private StarwarsPersonRepository starwarsPersonRepository;

    @Autowired
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

        starwarsPersonRepository.save(starwarsPerson);
    }

}
