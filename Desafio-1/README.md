## Table of contents
* [General info](#general-info)
* [Technologies](#technologies)
* [Setup](#setup)
* [Endpoints](#endpoints)

## General info
This project is the first challenge of the meli bootcamp 2021. The knowledge of JAVA and SpringBoot seen to date were integrated.
	
## Technologies
Project is created with:
- [Java 11](https://www.oracle.com/ar/java/technologies/javase-jdk11-downloads.html) - Development environment
- [Spring-boot](https://spring.io/projects/spring-boot) - Framework[
- [Maven](https://maven.apache.org/) - Dependency management
- [IDEA IntelliJ](https://www.jetbrains.com/es-es/idea/) - IDE
- [GitHub](https://github.com/) - Repository

	
## Setup
This API has an endpoint to pre-load the BDD, which in this case will be saved in memory.

- First, call the endpoint:

```sh
localhost:8080/init-bdd
```

which will return 
```sh
{
    "timestamp": "2021-07-15T12:48:52.631+00:00",
    "status": 200,
    "error": "OK",
    "message": "No message available",
    "path": "/init-bdd"
}
```

- This will load 5 users ("Comprador 1", "Comprador 2", "Comprador 3", "Vendedor 1", "Vendedor 2")
- After this, the endpoints of the documentation can be called quietly.


## Endpoints

| US 0001 | [plugins/dropbox/README.md][PlDb] |
You can see the technical requirement in the following link: https://drive.google.com/file/d/1iPdb8VVgxi4SZtWNqwHo_lo-quODgi3i/view

| US | Type | Endpoint |
| ------ | ------ | ------ |
| US 0001 | POST | /users/{userId}/follow/{userIdToFollow} |
| US 0002 | GET | /users/{userId}/followers/count/ |
| US 0003 | GET | /users/{UserID}/followers/list |
| US 0004 | GET | /users/{UserID}/followed/list |
| US 0005 | POST | /products/newpost |
| US 0006 | GET | /products/followed/{userId}/list |
| US 0007 | POST | /users/{userId}/unfollow/{userIdToUnfollow} |
| US 0008 | GET | /users/{UserID}/followers/list?order=name_asc (or name_desc) AND /users/{UserID}/followed/list?order=name_asc (or name_desc) |
| US 0009 | GET | /products/followed/{userId}/list?order=date_asc / date_asc  |
| US 00010 | POST | /products/newpromopost |
| US 00011 | GET | /products/{userId}/countPromo/ |
| US 00013 | GET | /products/{userId}/list/ |
