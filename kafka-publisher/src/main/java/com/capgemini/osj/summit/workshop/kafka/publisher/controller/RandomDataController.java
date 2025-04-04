package com.capgemini.osj.summit.workshop.kafka.publisher.controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.osj.summit.workshop.kafka.publisher.service.StarwarsService;

@RestController
@RequestMapping("/random")
public class RandomDataController {

    private StarwarsService starwarsService;

    public RandomDataController(StarwarsService starwarsService) {
        this.starwarsService = starwarsService;
    }

    @GetMapping("/starwars/people")
    public String getStarwarsCharacter(@RequestParam(required = false) boolean forward) throws Exception {
        if (forward) {
            return starwarsService.resolveRandomCharacterAndForward();
        }
        return starwarsService.resolveRandomCharacter();
    }

}
