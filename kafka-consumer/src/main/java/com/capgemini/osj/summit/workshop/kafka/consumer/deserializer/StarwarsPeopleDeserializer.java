package com.capgemini.osj.summit.workshop.kafka.consumer.deserializer;

import org.apache.kafka.common.serialization.Deserializer;

import com.capgemini.osj.summit.workshop.kafka.consumer.model.StarwarsPersonModel;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

public class StarwarsPeopleDeserializer implements Deserializer<StarwarsPersonModel> {

    ObjectMapper objectMapper = new ObjectMapper();

    public StarwarsPeopleDeserializer() {
        // Default constructor
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    @Override
    public StarwarsPersonModel deserialize(String topic, byte[] data) {
        String json = new String(data);

        try {
            return objectMapper.readValue(json, StarwarsPersonModel.class);
        } catch (Exception e) {
            throw new RuntimeException("Failed to deserialize JSON to StarwarsPersonModel", e);
        }
    }

    @Override
    public void close() {
        // No resources to close
    }

}
