# Tu casita tasaciones - Desafío Testing

**_Esta es una aplicación de Java creada con Springboot usando JDK11._**

La empresa “TuCasita Tasaciones” necesita poder calcular los metros cuadrados y el costo de distintas propiedades con las que cuenta en su cartera de clientes.
Para ello, solicita por cada propiedad: un nombre, un barrio y la cantidad de ambientes que posee; al mismo tiempo, cada ambiente contiene un nombre, un ancho y un largo.
A partir de estos datos, se solicita el desarrollo de una API que sea capaz de:

US-0001: Calcular el total de metros cuadrados de una propiedad

US-0002: Indicar el valor de una propiedad a partir de sus ambientes y medidas. Tener en cuenta que los precios por metro cuadrado están determinados según el barrio.

US-0003: Determinar cuál es el ambiente más grande.

US-0004: Determinar la cantidad de metros cuadrados que tiene cada ambiente de una propiedad.

## Comenzando 🚀

_Para obtener una copia del proyecto en funcionamiento en tu máquina local para propósitos de desarrollo y pruebas
deberás:_

1. Ir a https://github.com/AlanCanoDigitalHouse/DesafioSpringWave9

2. Realizar un clone del proyecto con _**git clone**_

3. Moverte a la rama Torres_Aldana con _**git checkout**_

4. Ingresar a la carpeta 9_desafio2

## Despliegue 📦

La aplicación esta lista para realizar las pruebas necesarias. Quizás deba descargar algunas dependencias de Maven.

El proyecto cuenta con un json en la carpeta static que contiene los datos.

El json districts.json cuenta con barrios precargados para realizar las pruebas del endpoint.


### Endpoint desarrollado 📋

El proyecto cuenta con un sólo endpoint que retorna un JSON con todos los requerimientos:

```
POST: http://localhost:8080/calculate
```

Ejemplo de JSON para pasar por el body del endpoint:

```
{
    "prop_name" :"Casita",
    "district": {
        "district_name" : "Palermo",
        "district_price": 1000
    },
    "environments": [
        {
            "environment_name" : "Cocina",
            "environment_width": 25,
            "environment_length": 3
        },
        {
            "environment_name" : "Baño",
            "environment_width": 20,
            "environment_length": 10
        },
        {
            "environment_name" : "Comedor",
            "environment_width": 20,
            "environment_length": 5
        }
    ]
}
```

El JSON de respuesta de este caso deberá ser:

```
{
    "prop_name": "Casita",
    "district": {
        "district_name": "Palermo",
        "district_price": 1000.0
    },
    "squareFeet": 375.0,
    "price": 375000.0,
    "biggest": {
        "environment_name": "Baño",
        "environment_width": 20.0,
        "environment_length": 10.0,
        "squareFeet": 200.0
    },
    "squareFeetEnvironments": [
        {
            "environment_name": "Cocina",
            "squareFeet": 75.0
        },
        {
            "environment_name": "Baño",
            "squareFeet": 200.0
        },
        {
            "environment_name": "Comedor",
            "squareFeet": 100.0
        }
    ]
}
```

###Validaciones ✅

| Dato/parámetro | Validación |
| --- | --- |
| `prop_name` | Que el campo no esté vacío, que empiece con mayúscula y que tenga una longitud máxima de 30 caracteres. |
| `district_name` | Que el campo no esté vacío y que tenga una longitud máxima de 45 caracteres. |
| `district_price` | Que el campo no esté vacío y que el precio máximo por metro cuadrado sea 4000 U$S. |
| `environment_name` | Que el campo no esté vacío, que empiece con mayúscula y que tenga una longitud máxima de 30 caracteres. |
| `environment_width` | Que el campo no esté vacío y el ancho máximo permitido del ambiente sea de 25 mts. |
| `environment_length` | Que el campo no esté vacío y el largo máximo permitido del ambiente sea de 33 mts. |


###Tests Unitarios

| Situación de entrada | Comportamiento esperado |
| --- | --- |
| Verificar que el total de metros cuadrados totales calculados por propiedad sea el correcto. | Devuelve el cálculo correcto del total de metros cuadrados de una propiedad. |
| Verificar que el barrio de entrada exista en el repositorio de barrios. | **Se cumple:** Permite continuar con normalidad. |
| Verificar que el barrio de entrada exista en el repositorio de barrios. | **No se cumple:** Notifica la no coincidencia mediante una excepción. |
| Verificar que el precio del barrio de entrada sea el correcto | **No se cumple:** Notifica la no coincidencia mediante una excepción. |
| Verificar que efectivamente se devuelva el ambiente con mayor tamaño. | Devuelve el ambiente con mayor tamaño. No existe ninguno que lo supere. |
| Verificar que efectivamente el total de metros cuadrados por ambiente sea el correcto. | Devuelve el cálculo correcto del total de metros cuadrados de un ambiente. |

###Tests de integración

1. Test para validar el endpoint POST calculate.

2. Test para validar mensaje de excepción del barrio no encontrado.

3. Test para validar mensaje de excepción de precio del barrio incorrecto.

4. Test para validar mensaje de @Valid.

###Coverage esperado luego de correr los test de los tests

- Clases: 100%

- Métodos: 96%

- Líneas: 92%