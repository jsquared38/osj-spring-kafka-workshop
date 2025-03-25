#!/bin/bash

# Navigate to kafka-consumer directory and run the application
echo "Starting kafka-consumer..."
cd kafka-consumer || exit
./../mvnw spring-boot:run &
consumer_pid=$!
cd ..

# Navigate to kafka-publisher directory and run the application
echo "Starting kafka-publisher..."
cd kafka-publisher || exit
./../mvnw spring-boot:run &
publisher_pid=$!
cd ..

# Wait for both processes to finish
wait $consumer_pid $publisher_pid

echo "Both applications have stopped."