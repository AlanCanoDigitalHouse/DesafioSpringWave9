# DesafioSpringWave9

Bootcamp Backend Java Desafío Spring



## Content

- [Architecture used](#Architecture)
- [Technologies and tools](#Technologies)
- [Installation](#Installation)
- [Build&Run](#Build&Run) 
- [API](#API)
- [UsersForTestAPI](#UsersForTestAPI)
- [Proposal](#Proposal)
- [Contributors](#Contributors)
- [License](#License)

----

# Architecture

- A microservice is proposed that uses lightweight protocols such as HTTP or REST API.
- A service-oriented code architecture is proposed with routes, controllers, validators, entities and repository pattern (Clean code).
- This REST service uses a caching strategy for balanced performance of external APIs
----

# Technologies

* [Java 11] - Programming language
* [Spring-boot] - Server
* [Spring-core] - Framework
* [Git] - Code versioning
* [Maven] - Dependency management
* [IDEA IntelliJ] - IDE
* [HashMap] - Temp Database
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
| 001 | GET | /users/{userId}/follow/{userIdToFollow}" | {userId}(int) identification user. {userIdToFollow}(int) identification user to follow | follow a other user |
| 002 | GET | /users/{userID}/followers/count | {userID}(int) indentification user | Get Followers Users Count |
| 003 | GET |/users/{userID}/followers/list | {userID}(int) indentification user | Get Followers User List |
| 004 | GET | /users/{UserID}/followed/list | {userID}(int) indentification user | User Following List |
| 005 | POST | /products/newpost | {"userId":1001,"userName":"Usuario1","posts":[{"userId":1001,"id_post":18,"date":"11-07-2021","detail":{"product_id":1,"productName":"Silla Gamer","type":"Gamer","brand":"Racer","color":"Red & Black","notes":"Special Edition"},"category":100,"price":1500.5,"hasPromo":true,"discount":0.25}]} | Create New Post |
| 006 | GET | /products/followed/{userId}/list| {userId}(int) identification user | Get Resent(14 days a go) Products User List |
| 007 | GET | /users/{userId}/unfollow/{userIdToUnfollow} | {userId}(int) identification user. {userIdToFollow}(int) identification user to unfollow | Unfollow |
| 008 | GET | /users/{UserID}/followers/list?order= & /users/{UserID}/followed/list?order= | {UserID}(int) identification user, {order}(String) sorted method | Sort User Followers & Followed |
| 009 | GET | /products/followed/{userId}/list?order= | {userId}(int) identification user, {order}(String) sorted method | Sort User Products by Date |
| 010 | POST | /products/newpromopost | {"userId":1001,"userName":"Usuario1","posts":[{"userId":1001,"id_post":18,"date":"11-07-2021","detail":{"product_id":1,"productName":"Silla Gamer","type":"Gamer","brand":"Racer","color":"Red & Black","notes":"Special Edition"},"category":100,"price":1500.5,"hasPromo":true,"discount":0.25,"hasPromo":true,"discount":0.25 } | Create New Promo Post |
| 011 | GET | /products/{userId}/countPromo/ | {userId}(int) identification user | promo post count by user |
| 012 | GET | /products/{userId}/list | {userId}(int) identification user | promo post list by user |

----- 

# Users For Test API 

| User Id | User Name | Followers(Id) | Following(Id) | 
| ------- | --------- | --------- | --------- |
| 1001 | aaabbb | Empty | 1002, 1003, 1004 |
| 1002 | zzzzz | 1001, 1003, 1004, 1005 | Empty |
| 1003 | xxxxx | Empty | 1002 |  
| 1004 | ccccc | Empty | 1002 |
| 1005 | ppppp | Empty | 1002 |

-----
# Proposal

Mi propuesta es el crowdfounding a traves de SocialMeli, los usuarios podran crear campañas para su ideas de productos, Meli podria apadrinar los proyectos mas viables, otros usuarios podran aportar voluntariamente dinero a estos proyectos a fin de obtener descuentos en la producción de dichos proyectos. 

# API Proposal

| US 000 | Method | URL | VARIABLES | DESCRIPTION | 
| ------ | ------ | --- | ------ | --------- |
| 013 | Post | /products/{user_id}/newcampaing | userId: tipo int identification del creador de la campaña, FoundingPost (Igual a un Post Normal, mas isFoundign(boolean), expectativeMoney(Double) cuanto espera recaudar la campaña, currentMoney(Double) cuanto dinero lleva la campaña) | Crear una nueva mapaña | 
| 013 | Post | /products/{userId}/campaing | userId(Int) Identificacion del dueño de la campaña, Donation(DonationRequest(donor(int) donador, donation(Double) cantidad donada, postId(Int) identificacion del post)) | Hacer una donación |
| 013 | GET | /products/{userId}/campaings/list | userId(Int) identificación de dueño de campalas | Traer campañas de un usuario |
-----
# Contributors
- [Manuel Alejandro García](https://github.com/ManuGarciaMeli)

-----

# License
This project is property of Mercado Libre.
