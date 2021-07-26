# Desafio 2 Wave 9 - Testing

En este desafío se realizaron los test al proyecto de "TuCasita Tasaciones" tanto unitarios, como de integración, en este documento vamos a pasar por cada uno de los requerimientos solicitados, sus respectivos payloads para ser ejecutados y los test que se realizaron para entender como esta distribuido el proyecto.

## Requerimientos

### Base de datos
Antes de comenzar a ahondar en los requerimientos cabe mencionar que existen 4 distritos dentro de la base de datos, que la aplicación espera validar cuando se hacen las peticiones, estos distritos son:
~~~json
[
  {
    "district_name": "Norte",
    "district_price": 400
  },
  {
    "district_name": "Sur",
    "district_price": 300
  },
  {
    "district_name": "Este",
    "district_price": 300
  },
  {
    "district_name": "Oeste",
    "district_price": 100
  }
]
~~~
### US 0001
Calcular el total de metros cuadrados de una propiedad
- Endpoint
~~~HTTP
POST http://localhost:8080/house/getSquareMeters
~~~
- Request
~~~json
{
    "prop_name": "La Casita",
    "district_name": "Sur",
    "district_price": 300.0,
    "environments": [
        {
            "environment_name": "Baño",
            "environment_width": 2.0,
            "environment_length": 4.0
        },
        {
            "environment_name": "Habitacion Matrimonial",
            "environment_width": 10.0,
            "environment_length": 6.0
        },
        {
            "environment_name": "Habitacion Visitas",
            "environment_width": 3.0,
            "environment_length": 4.0
        }
    ]
}
~~~
- Response
~~~json
{
    "name": "La Casita",
    "squareMeters": 80.0
}
~~~
### US 0002
Indicar el valor de una propiedad a partir de sus ambientes y medidas.
Tener en cuenta que los precios por metro cuadrado están determinados según el barrio.
- Endpoint
~~~HTTP
POST http://localhost:8080/house/getPrice
~~~
- Request
~~~json
{
    "prop_name": "La Casita",
    "district_name": "Sur",
    "district_price": 300.0,
    "environments": [
        {
            "environment_name": "Baño",
            "environment_width": 2.0,
            "environment_length": 4.0
        },
        {
            "environment_name": "Habitacion Matrimonial",
            "environment_width": 10.0,
            "environment_length": 6.0
        },
        {
            "environment_name": "Habitacion Visitas",
            "environment_width": 3.0,
            "environment_length": 4.0
        }
    ]
}
~~~

- Response
~~~json
{
    "name": "La Casita",
    "price": 24000.0,
    "district_name": "Sur",
    "district_price": 300.0,
    "environment": [
        {
            "name": "Baño",
            "squareMeters": 8.0,
            "price": 2400.0
        },
        {
            "name": "Habitacion Matrimonial",
            "squareMeters": 60.0,
            "price": 18000.0
        },
        {
            "name": "Habitacion Visitas",
            "squareMeters": 12.0,
            "price": 3600.0
        }
    ]
}
~~~
### US 0003
Determinar cuál es el ambiente más grande.
- Endpoint
~~~HTTP
POST http://localhost:8080/house/getBiggerEnv
~~~
- Request
~~~json
{
    "prop_name": "La Casita",
    "district_name": "Sur",
    "district_price": 300.0,
    "environments": [
        {
            "environment_name": "Baño",
            "environment_width": 2.0,
            "environment_length": 4.0
        },
        {
            "environment_name": "Habitacion Matrimonial",
            "environment_width": 10.0,
            "environment_length": 6.0
        },
        {
            "environment_name": "Habitacion Visitas",
            "environment_width": 3.0,
            "environment_length": 4.0
        }
    ]
}
~~~

- Response
~~~json
{
    "environment_name": "Habitacion Matrimonial",
    "environment_width": 10.0,
    "environment_length": 6.0
}
~~~
### US 0004
Determinar la cantidad de metros cuadrados que tiene cada ambiente de
una propiedad.
- Endpoint
~~~HTTP
POST http://localhost:8080/house/getSquareMetersEnv
~~~
- Request
~~~json
{
    "prop_name": "La Casita",
    "district_name": "Sur",
    "district_price": 300.0,
    "environments": [
        {
            "environment_name": "Baño",
            "environment_width": 2.0,
            "environment_length": 4.0
        },
        {
            "environment_name": "Habitacion Matrimonial",
            "environment_width": 10.0,
            "environment_length": 6.0
        },
        {
            "environment_name": "Habitacion Visitas",
            "environment_width": 3.0,
            "environment_length": 4.0
        }
    ]
}
~~~
- Response
~~~json
[
    {
        "name": "Baño",
        "squareMeters": 8.0,
        "price": 2400.0
    },
    {
        "name": "Habitacion Matrimonial",
        "squareMeters": 60.0,
        "price": 18000.0
    },
    {
        "name": "Habitacion Visitas",
        "squareMeters": 12.0,
        "price": 3600.0
    }
]
~~~

## Distribucion del proyecto
~~~bash
├── main
│   ├── java
│   │   └── com
│   │       └── example
│   │           └── desafio2
│   │               ├── Desafio2Application.java
│   │               ├── controllers
│   │               │   └── HouseController.java
│   │               ├── dtos
│   │               │   ├── DistrictDTO.java
│   │               │   ├── EnvDTO.java
│   │               │   ├── EnvResponseDto.java
│   │               │   ├── HouseDTO.java
│   │               │   ├── HouseDetailResponseDto.java
│   │               │   └── HouseResponseDTO.java
│   │               ├── exceptions
│   │               │   ├── ApiExceptionHandler.java
│   │               │   ├── BadRequestException.java
│   │               │   ├── ErrorDTO.java
│   │               │   └── ErrorListMessage.java
│   │               ├── repositories
│   │               │   ├── DistrictRepository.java
│   │               │   └── IDistrict.java
│   │               ├── services
│   │               │   └── HouseService.java
│   │               └── utils
│   │                   └── DistrictGenerator.java
│   └── resources
│       ├── application.properties
│       ├── static
│       │   └── district.json
│       └── templates
└── test
    ├── java
    │   └── com
    │       └── example
    │           └── desafio2
    │               ├── Desafio2ApplicationTests.java
    │               ├── controllers
    │               │   └── HouseControllerTest.java
    │               ├── exceptions
    │               │   └── ApiExceptionHandlerTest.java
    │               ├── integration
    │               │   ├── ApiIntegrationTest.java
    │               │   └── HouseDTOValidationTest.java
    │               ├── repositories
    │               │   └── DistrictRepositoryTest.java
    │               ├── services
    │               │   └── HouseServiceTest.java
    │               └── utils
    │                   ├── DistrictGeneratorTest.java
    │                   └── TestGenerator.java
    └── resources
        └── static
            └── district.json
~~~

## Testing

En la sección de test, del proyecto, se encuentran 2 tipos de test, unit testing - integration testing, todos los test de integración se encuentran dentro de la carpeta integration, donde un archivo es el encargado de probar cada uno de los endpoints (ApiIntegrationTest.java) y el otro de probar las validaciones de los DTO (HouseDtoValidationTest.java).