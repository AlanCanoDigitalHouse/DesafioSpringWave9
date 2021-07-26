# Desafio Testing
Desafío donde se generan test unitarios y de integración.

# Contenido

- Introducción
- Requerimientos
- Test Unitarios
- Test Integración (Bonus)
- DataBase
- Test POSTMAN 


# Introducción

El objetivo de este desafío es aplicar los contenidos abordados hasta el momento durante el BOOTCAMP MeLi (Git, Java, Spring y Testing), haciendo principal hincapié en las validaciones y tipos de testing que pueden ser utilizados a partir de un enunciado propuesto, una especificación de requisitos y documentación anexada.

# Requerimientos

US-0001: Calcular el total de metros cuadrados de una propiedad

US-0002: Indicar el valor de una propiedad a partir de sus ambientes y medidas. Tener en cuenta que los precios por metro cuadrado están determinados según el barrio.

US-0003: Determinar cuál es el ambiente más grande.

US-0004: Determinar la cantidad de metros cuadrados que tiene cada ambiente de una propiedad.

# Test Unitarios

- Verificar que el total de metros cuadrados totales calculados por propiedad sea el correcto.	
        - Devuelve el cálculo correcto del total de metros cuadrados de una propiedad. 

- Verificar que el barrio de entrada exista en el repositorio de barrios.	
        - "Se cumple: Permite continuar con normalidad.
        No se cumple: Notifica la no coincidencia mediante una excepción."

- Verificar que efectivamente se devuelva el ambiente con mayor tamaño.	
        - Devuelve el ambiente con mayor tamaño. No existe ninguno que lo supere.

- Verificar que efectivamente el total de metros cuadrados por ambiente sea el correcto.	
        - Devuelve el cálculo correcto del total de metros cuadrados de un ambiente. 

# Test Unitarios Descripción

- Package controller: PropertyControllerTest - (Test) Valida el controller y el ResponseDto.
- Package repository: DistrictRespositoryTest - (Test) Valida si nos regresa o si no nos regresa datos de la base de datos.
- Package service: PropertyServiceTest - (Test) Valida los puntos de la cosigna y excpeciones.
                        DistrictServiceTest - (Test) Valida si existe o no en la base de datos, y excepciones.


- Dentro del Package util
Clase de apoyo para generar datos de prueba.

# Test Integración (Bonus).

- Dentro del Package integrations: 
Pruebas de integracón, validando el body de entrada.

# DataBase

La base de datos se generó a partir de un JSON.
Se generó un archivo en `src/main/resources/static/prices.json`

Datos que se encuentran en el archivo JSON que se usa como Base de Datos.
        [
                {
                "districtName": "Palermo",
                "districtPrice": 1000
                }, 
                {
                "districtName": "Belgrano",
                "districtPrice": 1100
                }, 
                {
                "districtName": "Recoleta",
                "districtPrice": 900
                }, 
                {
                "districtName": "Puerto Madero",
                "districtPrice": 2000
                }, 
                {
                "districtName": "Plants vs Zombies",
                "districtPrice": 123.45
                }
        ]


# Test POSTMAN

- Endpoint

METHOD POST: /house/calculateMeters


- Request 

//Validar HappyPath
{
"propName" : "House 1",
"district":
{
"districtName":"Palermo",
"districtPrice": 1000.0
},
"environments": [
        {
        "environmentName": "Bedroom",
        "environmentWidth": 10,
        "environmentLength": 10
        },
        {
        "environmentName": "Kitchen",
        "environmentWidth": 25,
        "environmentLength": 10
        }
    ]
}

- Response 

{
    "totalArea": 350.0,
    "propertyPrice": 350000.0,
    "biggerEnvironment": "Kitchen",
    "environments": [
        {
            "environmentName": "Bedroom",
            "environmentWidth": 10.0,
            "environmentLength": 10.0,
            "squareMeters": 100.0
        },
        {
            "environmentName": "Kitchen",
            "environmentWidth": 25.0,
            "environmentLength": 10.0,
            "squareMeters": 250.0
        }
    ]
}


