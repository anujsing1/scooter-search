## Guide to install docker on windows machine
https://docs.docker.com/docker-for-windows/

## What you'll need
- JDK 1.8 or later
- Maven 3 or later
- Docker 

## Stack
- Spring Boot
- Java
- mongodb 
- Docker

## Steps to run application
- docker build command to build the docker image:- 
     docker build -t anuj_prog --no-cache . 
- docker run command to run the application 
    docker run  -p 8080:8080 -it anuj_prog ./home/run.sh
- Open browser and hit http://localhost:8080
- To open the swagger API documentation hit http://localhost:8080/swagger-ui.html on browser
