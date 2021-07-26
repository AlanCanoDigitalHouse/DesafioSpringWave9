-----
# DesafioSpringWave9
## Desafio 2
Bootcamp Backend Java Desafío Spring Test

-----

## Content

- [Architecture used](#Architecture)
- [Technologies and tools](#Technologies)
- [Installation](#Installation)
- [Build&Run](#Build&Run) 
- [API](#API)
- [Contributors](#Contributors)
- [License](#License)

----

# Architecture

- A microservice is proposed that uses lightweight protocols such as HTTP or REST API.
- A service-oriented code architecture is proposed with routes, controllers, validators, entities and repository pattern (Clean code).
- This REST service uses a caching strategy for balanced performance of external APIs
- Pattern design Object using DAO 
----

# Technologies

* [Java 11] - Programming language
* [Spring-boot] - Server
* [Spring-core] - Framework
* [Git] - Code versioning
* [Maven] - Dependency management
* [IDEA IntelliJ] - IDE
* [Json] - Temp Database
* [GitHub] - Repository
* [Docker & Compose] - Container Framework

-------

# Installation


| Requires | URL |
| ------ | ------ |
| Java 11 | https://www.java.com/es/download/ |
| Git | https://git-scm.com/downloads |
| Docker | https://docs.docker.com/engine/install/ |
| Compose | https://docs.docker.com/compose/install/ |

------

# Build & Run

Run all containers
```
$ docker-compose up --build -d
```

Down containers
```
$ docker-compose down
```

-----

# API

| US 000 | Method | URL | VARIABLES | DESCRIPTION | 
| ------ | ------ | --- | ------ | --------- |
| 001 | POST | localhost:8080/calculate | (Ex) Body: {"prop_name":"Home DTO","district_name":"Belgrano","district_price":2000.0,"environments":[{"environment_name":"Habitacion Principal","environment_width":3.0,"environment_length":4.0,"squareFeet":12.0},{"environment_name":"Hall","environment_width":2.5,"environment_length":1.5,"squareFeet":3.75},{"environment_name":"Cocina","environment_width":2.0,"environment_length":3.0,"squareFeet":6.0}]}| Calculate House: Area, price, biggest environment, areas environments |

-----


# Contributors
- [Manuel Alejandro García](https://github.com/ManuGarciaMeli)

-----

# License
This project is property of Mercado Libre.
-----
