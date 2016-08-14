[![Build Status](https://travis-ci.org/JasonGermaine/CurrencyFair.svg?branch=master)](https://travis-ci.org/JasonGermaine/CurrencyFair)
# CurrencyFair 

##### Requirements
 - Java 8
 - Maven

##### Installation
Clone the application (or Download zip)
```sh
$ git clone https://github.com/JasonGermaine/CurrencyFair.git CurrencyFair
$ cd CurrencyFair
```
Building and Run the application
```sh
$ mvn clean package
$ java -jar webapp\target\trading-webapp-1.0.0-SNAPSHOT.jar
```

##### Important Note
The application currently works within the time zone of UTC.

##### Usage
Basic Authentication:: *username*: **admin**, *password*: **password**

Endpoints
* Web UI - localhost:8080/  
* Swagger UI - localhost:8080/swagger (for ease of sending POST requests)
* FxUpdate API - GET localhost:8080/api/fx
* Trade API - POST localhost:8080/api/trade
* Trade API - GET localhost:8080/api/stats


### Notes
For speed of development + time constraints, this application was developed with a single service architecture. With more time and investment, this could be broken down into a micro-services architecture that would communicate through seperately deployed message brokers as well as RESTful communication with the use of circuit breakers. The services may include some of the following:
- API Gateway for allowing public facing access to an API.
- Authentication service for ensuring the authorization of users
- The Fx service to maintain current prices and send out onto stream
- An in-memory cluster that each cluster would process incoming trades for a set of userId's
- Statistics service that would process UI requests for statistics.

Also, one current integration test exists to ensure the application context can be loaded and the application can serve requests. BDD tests could be incorporated in to allowed for comprehensible test scenarios.