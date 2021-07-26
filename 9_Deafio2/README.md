# README
## Desafío 2 - Calculadora metros cuadrados
### Test unitarios


La api cuenta únicamente con un endpoint POST (http://localhost:8080/home/calculate)
que ya devuelve todos los datos solicitados en los requerimientos funcionales:

Cuenta con un archivo prices.json el cual sirve como repositorio para verificar si los datos del distrito (district_price y district_name) existan. En caso de que los datos de distrito ingresados en el request, no exista, el sistema despegará un error. 

- __US-0001:__ Calcular el total de metros cuadrados de una propiedad
- __US-0002:__ Indicar el valor de una propiedad a partir de sus ambientes y medidas. Tener en     cuenta que los precios por metro cuadrado están determinados según el barrio.
- __US-0003:__ Determinar cuál es el ambiente más grande.
- __US-0004:__ Determinar la cantidad de metros cuadrados que tiene cada ambiente de

En la siguientes paginas se encuentra un ejemplo del body necesario para realizar para una invocación exitosa y un ejemplo del response body que debería obtener.

Se puede usar ese mismo request cambiando algunos valores para probar las validaciones implementas. 


__En la siguientes paginas se encuentra un ejemplo del body necesario para realizar para una invocación exitosa y un ejemplo del response body que debería obtener.__

__Se puede usar ese mismo request cambiando algunos valores para probar las validaciones implementas.__


__Metodo:__ POST
__Endpoint:__ http://localhost:8080/home/calculate

__Request body:__

{
    "prop_name" : "Test",
    "district": {
    "district_name" : "Belgrano",
    "district_price" : "1100D"
    },
    "rooms": [
        {
            "environment_name": "Cuarto",
            "environment_length": 5,
            "environment_width": 4
        },
        {
            "environment_name": "Cocina",
            "environment_length": 4,
            "environment_width": 2
        },
             {
            "environment_name": "Comedor",
            "environment_length": 6,
            "environment_width": 5
        }
    ]
}













__Response body:__

{
    "totalArea": 58.0,
    "housePrice": 63800.0,
    "biggestRoom": {
        "environment_name": "Comedor",
        "environment_length": 6.0,
        "environment_width": 5.0,
        "squareMeters": 30.0
    },
    "rooms": [
        {
            "environment_name": "Cuarto",
            "environment_length": 5.0,
            "environment_width": 4.0,
            "squareMeters": 20.0
        },
        {
            "environment_name": "Cocina",
            "environment_length": 4.0,
            "environment_width": 2.0,
            "squareMeters": 8.0
        },
        {
            "environment_name": "Comedor",
            "environment_length": 6.0,
            "environment_width": 5.0,
            "squareMeters": 30.0
        }
    ]
}