## Content
* [Description](#description)
* [Technologies](#technologies)
* [Database](#database)
* [Stories](#stories)
* [Endpoints](#endpoints)

## Description
This project is the first integrative exercise of MercadoLibre's Bootcamp wave 9. Here the knowledge learned from Java and SpringBoot was applied

## Technologies
Project is created with:
- [Java 11](https://www.oracle.com/ar/java/technologies/javase-jdk11-downloads.html) - Development environment
- [Spring-boot](https://spring.io/projects/spring-boot) - Framework
- [Maven](https://maven.apache.org/) - Dependency management
- [IDEA IntelliJ](https://www.jetbrains.com/es-es/idea/) - IDE
- [GitHub](https://github.com/) - Repository


## Database
This API has an database with "users.json", "posts.json" "products.json"... 'tables', which they will be saved in memory automatically at the beggining of the Repository creation.

"users.json" file:
```sh
[
  {
    "userId": 1,
    "userName": "Alabama",
    "followers": [],
    "followed": []
  },
  {
    "userId": 2,
    "userName": "Arkansas",
    "followers": [],
    "followed": []
  },
  {
    "userId": 3,
    "userName": "Georgia",
    "followers": [],
    "followed": []
  },
  {
    "userId": 4,
    "userName": "Kansas",
    "followers": [],
    "followed": []
  },
  {
    "userId": 5,
    "userName": "Tennessee",
    "followers": [],
    "followed": []
  },
  {
    "userId": 6,
    "userName": "Nevada",
    "followers": [],
    "followed": []
  }
]
```

"posts.json" file:
```sh
[]
```

"products.json" file:
```sh
[]
```

## Stories
| Requirement | Explanation |
| ------ | ------ |
| US 0001 | To be able to carry out the action of “Follow” (follow) a certain seller |
| US 0002 | Obtain the result of the number of users who follow a certain seller |
| US 0003 | Get a list of all users who follow a certain seller (Who follows me?) |
| US 0004 | Get a list of all the sellers that a certain user follows (Who am I following?) |
| US 0005 | Register a new publication |
| US 0006 | Obtain a list of the publications made by sellers that a user follows in the last two weeks (for this, take into account ordering by date, most recent publications first) |
| US 0007 | To be able to carry out the action of “Unfollow” (stop following) a certain seller |
| - | On the other hand, since a good user experience is intended with respect to the form of presentation of the results of each query, it is necessary that they can be ordered by any of the following criteria: |
| US 0008 | Alphabetical Ascending and Descending |
| US 0009 | Ascending and Descending Date|
| BONUS | SocialMeli will also allow sellers to publish new products with exclusive special offers or discounts for their followers for a certain period of time. Take into account for these requirements the possibility of being able to sort them alphabetically by name of each product both in ascending and descending order. |
| US 0010 | Carry out the publication of a new promotional product. |
| US 0011 | Obtain the quantity of promotional products from a specific seller |
| US 0012 | Obtain a list of all the promotional products of a specific |

## Endpoints
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
| US 0010 | POST | /products/newpromopost |
| US 0011 | GET | /products/{userId}/countPromo/ |
| US 0013 | GET | /products/{userId}/list/ |