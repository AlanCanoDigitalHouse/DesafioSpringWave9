# Desafio Testing

## Funcionamiento
User Stories:
 
✔	US-0001: Calcular el total de metros cuadrados de una propiedad

✔   US-0002: Indicar el valor de una propiedad a partir de sus ambientes y medidas. Tener en cuenta que los precios por metro cuadrado están determinados según el barrio.

✔	US-0003: Determinar cuál es el ambiente más grande.

✔	US-0004: Determinar la cantidad de metros cuadrados que tiene cada ambiente de una propiedad.


**La API resuelve todos los user stories con el uso de una sola llamada al siguiente endpoint:**

    http://127.0.0.1:8080/calculate

Espera como entrada un body request como se muestra a continuacion:

    {
        "prop_name": "Casa Test",
        "district_name": "Palermo",
        "district_price": 1000,
        "rooms": [
            {
                "enviroment_name": "Cocina",
                "enviroment_width": 5.0,
                "enviroment_length": 5.0,
                "squareFeet": 25.0
            },
            {
                "enviroment_name": "Dormitorio",
                "enviroment_width": 6.0,
                "enviroment_length": 3.0,
                "squareFeet": 18.0
            },
            {
                "enviroment_name": "Garage",
                "enviroment_width": 7.0,
                "enviroment_length": 4.0,
                "squareFeet": 28.0
            }
        ]
    }

 La informacion retornada es la siguiente:

    {
        "prop_name": "Casa Test",
        "district_name": "Palermo",
        "district_price": 1000.0,
        "rooms": [
            {
                "enviroment_name": "Cocina",
                "enviroment_width": 5.0,
                "enviroment_length": 5.0,
                "squareFeet": 25.0
            },
            {
                "enviroment_name": "Dormitorio",
                "enviroment_width": 6.0,
                "enviroment_length": 3.0,
                "squareFeet": 18.0
            },
            {
                "enviroment_name": "Garage",
                "enviroment_width": 7.0,
                "enviroment_length": 4.0,
                "squareFeet": 28.0
            }
        ],
        "squareFeet": 71.0,
        "price": 71000.0,
        "biggest": {
            "enviroment_name": "Garage",
            "enviroment_width": 7.0,
            "enviroment_length": 4.0,
            "squareFeet": 28.0
        }
    }   

# Resultados de Testing

Luego de ralizar los testing, segun la metrica sobre el coverage brindada por el IDE IntelliJ los resultados fueron los siguientes:

### A nivel proyecto

    * Class: 100%
    * Method: 97%
    * Line: 94%

### Por package

**Controller**

    * Class: 100%
    * Method: 100%
    * Line: 100%

**dto**

    * Class: 100%
    * Method: 100%
    * Line: 100%

**exceptions**

    * Class: 100%
    * Method: 100%
    * Line: 100%

**repositories**

    * Class: 100%
    * Method: 100%
    * Line: 84%

**services**

    * Class: 100%
    * Method: 100%
    * Line: 100%