## Guide to install docker on windows machine
https://docs.docker.com/docker-for-windows/

## What you'll need
- JDK 1.8 or later
- Maven 3 or later
- Docker to run mongodb on local machine 
or run the mongodb on local machine so that application can connect to it without any user credential

## Stack
- Spring Boot
- Java
- mongodb 

## Steps to run application
- To run mongodb on local machine run below command
    docker run -p 27017:27017 -d --name mongodbapp mongo:latest 
- To run application do the the project directory and run below given command
    mvn spring-boot:run
- Open browser and hit http://localhost:8080
- To open the swagger API documentation hit http://localhost:8080/swagger-ui.html on browser
