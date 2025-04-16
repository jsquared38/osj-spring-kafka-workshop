package com.capgemini.osj.summit.workshop.kafka.publisher.mapper;

import com.capgemini.osj.summit.workshop.kafka.publisher.model.StarwarsPersonModel;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class SwapiMapper {

    public static String mapToStarwarsPerson(String json) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode root = objectMapper.readTree(json);
        JsonNode properties = root.path("result").path("properties");
        StarwarsPersonModel person = new StarwarsPersonModel(
                properties.path("name").asText(),
                properties.path("height").asText(),
                properties.path("mass").asText(),
                properties.path("weight").asText());
        return objectMapper.writeValueAsString(person);
    }
}
