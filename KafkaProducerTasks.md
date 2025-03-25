# Kafka Producer Tasks
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
    producer:
      key-serializer: org.apache.kafka.common.serialization.StringSerializer
      value-serializer: org.apache.kafka.common.serialization.StringSerializer
      client-id: spring-kafka-workshop-producer
```

> Take note, in the above example, we are using `host.docker.internal` as the server host as this example was prepared within a `devcontainer`. If you are not using a `devcontainer`, the config here should not have to change. Please make sure in `docker-compose.yaml` to make sure the `localhost` setting is uncommented: `KAFKA_ADVERTISED_LISTENERS: PLAINTEXT://broker:29092,PLAINTEXT_HOST://localhost:9092`

## 3. Navigate to `com.capgemini.osj.summit.workshop.kafka.publisher.service.StarwarsService.java` to make the following changes:
- Add new dependencies to the service

```java
    private KafkaTemplate<String, String> kafkaTemplate;
    private String kafkaTopic;
```

- Amend the constructor to inject the dependency instance as well as the property value for the kafka topic

```java
    public StarwarsService(RestTemplate restTemplate, @Value("${api.swapi.base-url}") String swapiBaseUrl, KafkaTemplate<String, String> kafkaTemplate,
            @Value("${spring.kafka.topic.name}") String kafkaTopic) {
        this.restTemplate = restTemplate;
        this.swapiBaseUrl = swapiBaseUrl;
        this.kafkaTemplate = kafkaTemplate;
        this.kafkaTopic = kafkaTopic;
    }
```

## Replace the HTTP call to the 2nd microservice to send the message to Kafka instead. 

Function to edit inside `com.capgemini.osj.summit.workshop.kafka.publisher.service.StarwarsService.java`

```java
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
```
