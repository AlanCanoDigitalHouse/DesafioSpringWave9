#  Desafio 2 - API Calculate-Metros-Cuadrados

    ● US-0001: Calcular el total de metros cuadrados de una propiedad
    ● US-0002: Indicar el valor de una propiedad a partir de sus ambientes y medidas. Tener en cuenta que los precios por metro cuadrado están determinados según el barrio.
    ● US-0003: Determinar cuál es el ambiente más grande.
    ● US-0004: Determinar la cantidad de metros cuadrados que tiene cada ambiente de una propiedad.

# API: DOCUMENTACIÓN

## Endpoint
    
    {{LOCALHOST}}/calculate

## Request
    {
        "prop_name": String,
        "district": {
            "district_name": String,
            "district_price": Double
            },
        "environments": [
            {
                "environment_name": String, 
                "environment_width": Double,
                "environment_length": Double
            },
            ...
        ]
    }
    

## Payload de prueba

    {
        "prop_name":"Propiedad X",
        "district": {
            "district_name":"Belgrano",
            "district_price":444.0
            },
        "environments": [
            {
                "environment_name": "Hab1", 
                "environment_width": 15.5,
                "environment_length": 8.0
            },{
                "environment_name": "Hab2", 
                "environment_width": 11.0,
                "environment_length": 7.0
            },{
                "environment_name": "Hab3", 
                "environment_width": 10.0,
                "environment_length": 6.0
            }
        ]
    }

## Response
        {
        "prop_name": String,
        "district": {
            "district_name": String,
            "district_price": Double
        },
        "environments": [
                {
                    "environment_name": String,
                    "environment_width": Double,
                    "environment_length": Double,
                    "squareFeet": Double
                }
                ...
        ],
        "squareFeet": Double,
        "price": Double,
        "biggest": {
            "environment_name": String,
            "environment_width": Double,
            "environment_length": Double,
            "squareFeet": Double
            }
        }   
        

## Response de ejemplo

        {
        "prop_name": "Propiedad X",
        "district": {
            "district_name": "Belgrano",
            "district_price": 444.0
        },
        "environments": [
                {
                    "environment_name": "Hab1",
                    "environment_width": 15.5,
                    "environment_length": 8.0,
                    "squareFeet": 124.0
                },
                {
                    "environment_name": "Hab2",
                    "environment_width": 11.0,
                    "environment_length": 7.0,
                    "squareFeet": 77.0
                },
                {
                    "environment_name": "Hab3",
                    "environment_width": 10.0,
                    "environment_length": 6.0,
                    "squareFeet": 60.0
                }
        ],
        "squareFeet": 261.0,
        "price": 115884.0,
        "biggest": {
            "environment_name": "Hab1",
            "environment_width": 15.5,
            "environment_length": 8.0,
            "squareFeet": 124.0
            }
        }   

## Historias de Usuario

1. US001: Calcular el total de metros cuadrados de una propiedad
   

        {
            ...
            "squareFeet": 261.0
            ...
        }
   
2. US002: Indicar el valor de una propiedad a partir de sus ambientes y medidas. Tener en cuenta que los precios por metro cuadrado están determinados según el barrio.
  
 
        {
        ...
        "price": 115884.0
        ...
        }
        

3. US003: Determinar cuál es el ambiente más grande.


        {
        ...
        "biggest": {
            "environment_name": "Hab1",
            "environment_width": 15.5,
            "environment_length": 8.0,
            "squareFeet": 124.0
        }
        }

4. US004: Determinar la cantidad de metros cuadrados que tiene cada ambiente de una propiedad.


        {
        ...
        "environments": 
        [
            {
           "environment_name": "Hab1",
           "environment_width": 15.5,
           "environment_length": 8.0,
           "squareFeet": 124.0
           },
           {
           "environment_name": "Hab2",
           "environment_width": 11.0,
           "environment_length": 7.0,
           "squareFeet": 77.0
           },
           {
           "environment_name": "Hab3",
           "environment_width": 10.0,
           "environment_length": 6.0,
           "squareFeet": 60.0
            }
       ],
        ...
        }


## Tener en cuenta

        - las clases CalculateService ,HouseDTO, HouseResponseDTO y ApiExceptionControllerAdvide por alguna razón
        presenta lineas sin covered, las cuales estan vacias, entonces se determino como bug.
        - al levantar la API se genera una List<EnvironmentModel>, la cual consume un environments.json para completarse.
        - la validacion del nombre del barrio se hace contra la lista descripta en el punto anterior.