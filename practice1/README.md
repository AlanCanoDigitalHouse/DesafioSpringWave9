# Spring challenge

## About de project

"SocialMeli" is a REST API that provides tools for users (buyers and sellers) to be up to date in terms of new seller's
posts and promos. With the api a buyer can follow a seller, get information about followers and followed users. Also the
products posted by a seller. A seller can create new posts and promos offering discounts.

Post can be ordered by its publication date, and users can be ordered by their name.

### Notes

There is information about users and posts loaded in memory when initialized the project.

### API endpoints::

- GET request:
  - /users/{userId}/followers/count/
  - /users/{UserID}/followers/list
  - /users/{UserID}/followed/list
  - /products/followed/{userId}/list
  - /products/{userId}/countPromo/
  - /products/{userId}/list/

- POST request:
  - /users/{userId}/follow/{userIdToFollow}
  - /users/{userId}/unfollow/{userIdToUnfollow}
  - /products/newpost
  - /products/newpromopost

## Getting starting

### Requirements

* [Maven](https://maven.apache.org/)
* [Java 11](https://www.oracle.com/co/java/technologies/javase-jdk11-downloads.html)
* [Git](https://git-scm.com/)

### Run

`````
mvn package
mvn spring-boot:run
`````

### Usage

Make request from a http agent.

Sample request:

- Get request. Find all users.
  ```
  http://localhost:8080/users
  ```
  Request result should be:
  ```
  [
      {
          "userID": 0,
          "userName": "user1"
      },
      {
          "userID": 1,
          "userName": "vendor1"
      },
      {
          "userID": 2,
          "userName": "Luis"
      },
      {
          "userID": 3,
          "userName": "Simon"
      },
      {
          "userID": 4,
          "userName": "Carla"
      }
  ]
  ```

## Author

Luis Eduardo Moreno Acevedo
