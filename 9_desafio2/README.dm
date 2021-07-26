# Second Challenge - Units and Integrations Tests
## Table of contents
* [General info](#general-info)
* [Technologies](#technologies)
* [Endpoint Test](#endpoint-test)

## General info
This project is the second challenge of the meli bootcamp 2021. The knowledge of JAVA, SpringBoot, Unit Tests and Integrations Tests seen to date were integrated.

## Technologies
Project is created with:
- [Java 11](https://www.oracle.com/ar/java/technologies/javase-jdk11-downloads.html) - Development environment
- [Spring-boot](https://spring.io/projects/spring-boot) - Framework
- [Maven](https://maven.apache.org/) - Dependency management
- [IDEA IntelliJ](https://www.jetbrains.com/es-es/idea/) - IDE
- [GitHub](https://github.com/) - Repository
- [JaCoCo](https://www.eclemma.org/jacoco/) - Code Coverage


## Endpoint Test

- Call the endpoint:

```sh
localhost:8080/property/calculate
```

- With the example payload:

```sh
{
    "prop_name": "Casa de Juan",
    "district" : {
                "district_name" : "Belgrano",
                "district_price" : 1100.0
            },
    "environments": [{
                        "environment_name" : "Cocina",
                        "environment_width" : 3.0,
                        "environment_length" : 4.0,
                        "squareMeters" : 12.0
                    },
                    {
                        "environment_name" : "Baño",
                        "environment_width" : 2.0,
                        "environment_length" : 2.0,
                        "squareMeters" : 4.0
                    },
                    {
                        "environment_name" : "Dormitorio",
                        "environment_width" : 3.0,
                        "environment_length" : 3.0,
                        "squareMeters" : 9.0
                    }]
}
```

- And return this response:

```sh
{
    "totalArea": 25.0,
    "propertyPrice": 27500.0,
    "biggerEnvironment": "Cocina",
    "environments": [
        {
            "environment_name": "Cocina",
            "environment_width": 3.0,
            "environment_length": 4.0,
            "squareMeters": 12.0
        },
        {
            "environment_name": "Baño",
            "environment_width": 2.0,
            "environment_length": 2.0,
            "squareMeters": 4.0
        },
        {
            "environment_name": "Dormitorio",
            "environment_width": 3.0,
            "environment_length": 3.0,
            "squareMeters": 9.0
        }
    ]
}
```