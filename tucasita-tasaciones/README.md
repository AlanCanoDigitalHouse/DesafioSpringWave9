# Desafio Testing

## Infomacion General
    * Proyecto: TuCasita Tasaciones

    * Autor: Regis Emiliano

## Info
    * El repo DistrictRepositoryImpl levanta un listado de districtos de un archivo.

    * El path del archivo es el siguiente: 'resources/static/districts.json'

## Exceptions
    * DistrictNotFoundException   
        when then given district name is not in the repository

    * ValidationException
        when params validation fails

    * HttpMessageNotReadableException
        when json payload is not well formed

## Payload (definition)
    All endpoints receive the same payload:
```
{
    "prop_name": [string] [mandatory] [max-length=30],
    "district": {
        "district_name": [string] [mandatory] [max-length=45],
        "district_price": [double] [mandatory] [min=1] [max=4000]
    },
    "environments": [
        { 
            "environment_name": [string] [mandatory] [max-length=30],  
            "environment_width": [double] [mandatory] [min=1] [max=25], 
            "environment_length": [double] [mandatory] [min=1] [max=33] 
        }, ...        
    ]
}
```

## Payload (example)
```
{
    "prop_name": "Dpto Ejemplo",
    "district": {
        "district_name": "Recoleta",
        "district_price": 2500
    },
    "environments": [
        { "environment_name": "Kitchen", "environment_width": 2.5, "environment_length": 3.5 },
        { "environment_name": "Living", "environment_width": 4, "environment_length": 6 },
        { "environment_name": "Bedroom", "environment_width": 4, "environment_length": 4.5 },
        { "environment_name": "Bathroom", "environment_width": 3, "environment_length": 2 }
    ]
}
```

## Features
### US-0001: Calcular el total de metros cuadrados de una propiedad.
* POST `/property/dimension`
* Payload
  [go to payload definition for more details](#payload-(definition))
* Responses
    * `200` success
    * `400` fail [go to exceptions for details](#exceptions)

### US-0002: Indicar el valor de una propiedad a partir de sus ambientes y medidas.
* POST `/property/price`
* Payload
  [go to payload definition for more details](#payload-(definition))
* Responses
    * `200` success
    * `400` fail [go to exceptions for details](#exceptions)

### US-0003: Determinar cuál es el ambiente más grande.
* POST `/property/rooms/biggest`
* Payload
  [go to payload definition for more details](#payload-(definition))
* Responses
    * `200` success
    * `400` fail [go to exceptions for details](#exceptions)

### US-0004: Determinar la cantidad de metros cuadrados que tiene cada ambiente de una propiedad.
* POST `/property/rooms/dimensions`
* Payload
  [go to payload definition for more details](#payload-(definition))
* Responses
    * `200` success
    * `400` fail [go to exceptions for details](#exceptions)