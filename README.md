# Spring Kafka Workshop

## Introduction
This workshop is intended to cover various topics related to Spring's integration with Kafka. You will gain hands on experience by converting this conventional microservices apps using http communication to a more async and message based solution. You will prove how to make the change with little user impact thanks to the Spring framework and using Springboot.

## Prerequisites
Before attempting, please ensure the following is available in your environment:
- A container tool like Docker, Rancher or Podman. Any will do, as long as a container can be ran.
- Java 21 (this is LTS and for the purpose of this workshop, no need to have any new Java features)

> This project provides the option of using Dev Containers, specifically in VS Code. 
> For more information, please follow [this link](https://code.visualstudio.com/docs/devcontainers/containers)

## Setup
> The assumption here is that this repository has already been cloned and exists in your local environment. 

- You can start by opening this project inside of your preferred IDE. This assignment has been set up using VS Code with Dev Containers.
    - most IDE's will handle the importing of the project and due to the simplicity of it, this is pretty much all that is required.
- If you opt for using the Vs Code and Dev Containers approach, then do the following:
    - Ensure that you have installed the required extensions for VS Code.
    - Open this project in a new workspace
    - Run the command: `> Dev Containers: Reopen in container`
    - After sometime, the container will have been created, ran and installed required extensions and tools.

## Topics Covered
- **Kafka Basics**: Introduction to Kafka and its core concepts.
- **Spring Integration**: How Spring integrates with Kafka.
- **Hands-On Exercises**: Practical examples and exercises.

## Project Structure
The structure of this project is fairly simple. 

- **root directory**: this directory contains common files and directories that relate to the 2 springboot applications in the project
- **kafka-consumer**: this is a springboot app which will represent a microservice that will become a kafka consumer
- **kafka-publisher**: this is a springboot app which will represent a microservice that will become a kafka publisher

## Running the Application
There are multiple ways to start the 2 springboot apps and will most likely depend on your local setup. Here it will only explain a select few common cases.

- **Intellij**: Inside of this IDE, you can navigate to the 2 main classes for each app and choose to run them. It is advised to use the debug option.
- **VS Code**: This IDE gives 2 options.
    - Inside of this IDE, you can go to the Debug view and choose to run both apps from the dropdown in the view. This is the preferred approach.
    - Inside of this IDE, you can navigate to the 2 main classes for each app and choose to run them. It is advised to use the debug option
- **Maven**: Via your CMD, you can navigate to the root of each app (where the pom.xml lives) and execute `./../mvnw spring-boot:run` for each app. Or you can navigate to the root directory in your CMD and execute `./runAll.sh`

## Troubleshooting
**Dev container did not start**
- Check that your container environment is started
- View the terminal output to try and find obvious causes

**runAll.sh permission denied**:
- Execute `chmod +x runAll.sh`

## Resources
Here are some useful links to help you solve this workshop.

- [Spring for Apache Kafka (project overview)](https://spring.io/projects/spring-kafka#overview)
- [Spring Kafka Reference Doc](https://docs.spring.io/spring-kafka/reference/)
- [Spring Kafka API Doc](https://docs.spring.io/spring-kafka/docs/3.3.4/api/)
- [Spring Boot: Apache Kafka Support](https://docs.spring.io/spring-boot/reference/messaging/kafka.html)
- [Spring Kafka Tutorial - Baeldung](https://www.baeldung.com/spring-kafka)