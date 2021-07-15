SocialMeli - Social focused marketplace
=======================================

Spring Challenge for MercadoLibre Backend Bootcamp

## About

- Java application made with Springboot using JDK11
- Using this app you will be able to establish and search for follow relationship between users, create and look for
  product posts
- The project comes with static json files for data persistence that contains about 14
fake users to use. At the moment there is no way to create more users.

## Setup

- Make sure to have JDK11 installed
- This project has some dependencies managed using Maven so make sure everything is OK before compiling
- Data persistence is made with static JSON files that are part of the project. Make sure they all are part of your
  compiled version

## Interface

The project was made
following [technical requirements](https://drive.google.com/file/d/1iPdb8VVgxi4SZtWNqwHo_lo-quODgi3i/view)
so everything should work as stated there.

There is also an extra method to get all users:

```
GET localhost:8080/users
```
Response example:
```JSON
[
  {
    "userId": 0,
    "userName": "JohnDoe"
  },
  {
    "userId": 1,
    "userName": "Goku123"
  }
]
```
