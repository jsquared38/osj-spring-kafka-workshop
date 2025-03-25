package com.capgemini.osj.summit.workshop.kafka.consumer.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.capgemini.osj.summit.workshop.kafka.consumer.model.StarwarsPersonModel;
import com.capgemini.osj.summit.workshop.kafka.consumer.service.StarwarsPeopleService;

@Controller
@RequestMapping("/starwars/people")
public class StarwarsPeopleController {

    private StarwarsPeopleService starwarsPeopleService;

    public StarwarsPeopleController(StarwarsPeopleService starwarsPeopleService) {
        this.starwarsPeopleService = starwarsPeopleService;
    }

    @PostMapping
    public ResponseEntity<StarwarsPersonModel> savePerson(@RequestBody StarwarsPersonModel starwarsPersonModel) {
        starwarsPeopleService.savePerson(starwarsPersonModel.name(), starwarsPersonModel.height(),
                starwarsPersonModel.mass(), starwarsPersonModel.birthYear());

        return ResponseEntity.ok(starwarsPersonModel);
    }

}
