# Project Name
"TuCasita Tasaciones" - Test Desafio 2 - Wave 9

## General Information

The company "TuCasita Tasaciones" needs to be able to calculate the square meters and the cost of different properties that it has in its client portfolio.
To do this, request for each property: a name, a neighborhood and the number of rooms it has; at the same time, each room contains a name, a width and a length.
From this data, the development of an API that is capable of:
US-0001: Calculate the total square meters of a property
US-0002: Indicate the value of a property based on its environments and measurements. Bear in mind that the prices per square meter are determined according to the neighborhood.
US-0003: Determine which is the largest room.
US-0004: Determine the amount of square meters that each room of a property has.

## Technologies Used

- Tech 1 - Java
- Tech 2 - SpringBoot
- Tech 3 - Maven

## Setup
    <dependencies>
            <>spring-boot-starter-validation</>
            <>spring-boot-starter-web</>
            <>spring-boot-devtools</>
            <>lombok</>
            <>spring-boot-starter-test</>
            <>jackson-databind</>
            <>jackson-datatype-jsr310</>
    </dependencies>

## Usage

The project count with a unic endpoint

POST: localhost:8080/calculate

Valid Payload:
```
{
    "prop_name": "CasaValida",
    "district": {
        "district_name": "Recoleta",
        "district_price": 900.0
    },
    "environments": [
        {
            "environment_name": "Oficina",
            "environment_width": 3.0,
            "environment_length": 2.0
        },
        {
            "environment_name": "Sala",
            "environment_width": 9.0,
            "environment_length": 8.0
        },
        {
            "environment_name": "Habitacion",
            "environment_width": 4.0,
            "environment_length": 4.0
        }
    ]
}
```
The project has several unit and integration tests to satisfy the client's requirements

## Project Status

Project is: _complete_

## Acknowledgements

Many thanks to my teachers and classmates

## Contact

matias.harispe@mercadolibre.com BootCamp BE Meli