# Kafka Consumer Tasks
In this section, you will find step by step information to complete this workshop regarding the Kafka Producer portion.

## 1. Add the necessary dependencies

```xml
		<dependency>
			<groupId>org.springframework.kafka</groupId>
			<artifactId>spring-kafka</artifactId>
		</dependency>
```

## 2. To keep things simple, we will make use of the provide AutoConfiguration, so add the following properties under `spring:`

```yaml
  kafka:
    bootstrap-servers: http://host.docker.internal:9092
    topic:
      name: starwars
      partition-count: 1
      replication-factor: 1
    consumer:
      group-id: spring-kafka-workshop-consumer
      key-deserializer:
        org.apache.kafka.common.serialization.StringDeserializer
      value-deserializer:
        com.capgemini.osj.summit.workshop.kafka.consumer.deserializer.StarwarsPeopleDeserializer
      client-id: spring-kafka-workshop-consumer      
```

> Take note, in the above example, we are using `host.docker.internal` as the server host as this example was prepared within a `devcontainer`. If you are not using a `devcontainer`, the config here should not have to change. Please make sure in `docker-compose.yaml` to make sure the `localhost` setting is uncommented: `KAFKA_ADVERTISED_LISTENERS: PLAINTEXT://broker:29092,PLAINTEXT_HOST://localhost:9092`

## 3. Create a new package under `com.capgemini.osj.summit.workshop.kafka.consumer.controller` called `listener` (or anything suitable)

## 4. Create a new Java class called: `StarwarsPeopleListener`

## 5. Inside of your `StarwarsPeopleListener` you will configure the component to be a Kafka Listener: 

```java
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
```
