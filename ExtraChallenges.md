# Extra Challenges
If you are reading this, it means that implementing the consumer and producer tasks were simple and you are looking for more. Well because you are so great, here are some extra challenges for you to complete. But beware, this is not going to be as easy. No instructions on how to implement the challenges will be provided, rather just the expectations will be outlined.

## Kafka Producer Retries
As you may have noticed, the `KafkaTemplate.send()` method returns a `CompletableFuture`. In the current standing of the implementation, we do not really wait for a response of that future, so if any failures are to happen, we will most likely not retry the publishing of this message. 

### Steps to Complete
1. Research how `CompletableFuture` works in Java and how to handle its completion stages.
2. Implement a mechanism to retry sending messages when the `CompletableFuture` fails.
3. Ensure that retries happen asynchronously and do not block the HTTP response to the client.
4. Test the implementation by simulating failures and verifying that retries are attempted.

It is your task to figure out how we can leverage `CompleteableFuture` to retry publishing messages, even though our http response to the client is already sent. 

## Kafka Consumer Retries
We all know that sometimes we can consume a message from some topic, but then a weird IO issue has occurred meaning that our processing of the message has failed. In the current implementation, when something like this happens, we will not reprocess the message. 

It is your task to investigate why does the message get "lost" or why can we not process the exact same one again. Once you have figured this out, you are further tasked to implement a solution to this problem.

### Steps to Complete
1. Investigate the default behavior of Kafka consumers when message processing fails.
2. Research Kafka's acknowledgment modes and how they affect message reprocessing.
3. Implement a retry mechanism for failed message processing, ensuring idempotency.
4. Test the solution by simulating failures and verifying that messages are reprocessed correctly.

## Get Metrics using Micrometer
Micrometer is a powerful library for collecting application metrics. Your task is to integrate Micrometer into the application to collect and expose Kafka-related metrics. 

### Steps to Complete:
1. Research how to add Micrometer to a Spring Boot application.
2. Configure Micrometer to collect metrics such as:
    - Number of messages produced.
    - Number of messages consumed.
    - Any errors encountered during production or consumption.
3. Expose these metrics through an endpoint (e.g., `/actuator/metrics`).
4. Test the metrics collection by producing and consuming messages, and verify that the metrics are updated accordingly.

Bonus: Integrate a monitoring tool like Prometheus or Grafana to visualize these metrics.
