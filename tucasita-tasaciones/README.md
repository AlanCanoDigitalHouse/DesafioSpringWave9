## Content
* [Description](#description)
* [Technologies](#technologies)
* [Database](#database)
* [Payload](#payload)
* [Stories](#stories)
* [Postman collection](#Postmancollection)
* [Endpoints](#endpoints)
* [Responses](#responses)


## Description
This project is the second integrative exercise of MercadoLibre's Bootcamp wave 9. Here the knowledge learned from JUnit and Integration testing with Mocks was applied

## Technologies
Project is created with:
- [Java 11](https://www.oracle.com/ar/java/technologies/javase-jdk11-downloads.html) - Development environment
- [Spring-boot](https://spring.io/projects/spring-boot) - Framework
- [Maven](https://maven.apache.org/) - Dependency management
- [IDEA IntelliJ](https://www.jetbrains.com/es-es/idea/) - IDE
- [GitHub](https://github.com/) - Repository
- [JUnit](https://junit.org/junit5/) - Unit testing
- [Mockito](https://site.mockito.org/) - Mocks


## Database
This API has an database "district.json":

"users.json" file:
```sh
[
  {
    "location": "Palermo",
    "price": 1000
  },
  {
    "location": "Belgrano",
    "price": 1100
  },
  {
    "location": "Recoleta",
    "price": 900
  },
  {
    "location": "Puerto Madero",
    "price": 2000
  }
]
```
## Payload
This API has the same payload for all request:
```sh
{
    "prop_name": "Casa en palermo",
    "district": {
        "district_name": "Palermo",
        "district_price": 1000.0
    },
    "environments": [
        {
            "environment_name": "Banio",
            "environment_width": "3.0",
            "environment_length": "3.0"
        },
        {
            "environment_name": "Living",
            "environment_width": "6.0",
            "environment_length": "10.0"
        },
        {
            "environment_name": "Cocina",
            "environment_width": "7.0",
            "environment_length": "6.0"
        }
    ]
}
```

## Stories
| Requirement | Explanation |
| ------ | ------ |
| US 0001 | Calculate the total square meters of a property |
| US 0002 | Indicate the value of a property based on its environments and measurements. Bear in mind that the prices per square meter are determined according to the neighborhood. |
| US 0003 | Determine which is the largest room. |
| US 0004 | Determine the amount of square meters that each room has a property. |

## Postman collection
This API have a already configured Postman collection file wich is inside the root folder. The name is "Modulo 11 - Desafio Testing POSTMAN Collection"

## Endpoints
| US | Type | Endpoint |
| ------ | ------ | ------ |
| US 0001 | POST | /house/square-meters |
| US 0002 | POST | /house/house-price |
| US 0003 | POST | /house/house-biggest-room |
| US 0004 | POST | /house/square-meters-rooms |

## Responses
- US 0001
```sh
{
    "prop_name": "Casa en palermo",
    "squareMeters": 111.0
}
```

- US 0002
```sh
{
    "prop_name": "Casa en palermo",
    "propertyPrice": 111000.0
}
```

- US 0003
```sh
{
    "prop_name": "Casa en palermo",
    "biggestRoom": {
        "environment_name": "Living",
        "environment_width": 6.0,
        "environment_length": 10.0,
        "squareMeters": 60.0
    }
}
```

- US 0004
```sh
{
    "prop_name": "Casa en palermo",
    "environments": [
        {
            "environment_name": "Banio",
            "environment_width": 3.0,
            "environment_length": 3.0,
            "squareMeters": 9.0
        },
        {
            "environment_name": "Living",
            "environment_width": 6.0,
            "environment_length": 10.0,
            "squareMeters": 60.0
        },
        {
            "environment_name": "Cocina",
            "environment_width": 7.0,
            "environment_length": 6.0,
            "squareMeters": 42.0
        }
    ]
}
```
