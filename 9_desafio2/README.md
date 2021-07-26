# Desafio Testing
Desafío donde se generan test unitarios y de integración.

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

//Se tienen validaciones del Nombre y Precio del District

# Unit Test

-Package controller: PropertyControllerTest - (Test) Valida el controller y el ResponseDto.
-Package repository: DistrictRespositoryTest - (Test) Valida si nos regresa o si no nos regresa datos de la base de datos.
-Package service: PropertyServiceTest - (Test) Valida los puntos de la cosigna y excpeciones.
                        DistrictServiceTest - (Test) Valida si existe o no en la base de datos, y excepciones.

Dentro del Package integrations
Pruebas de integracón, validando el body de entrada.

Dentro del Package util
Clase de apoyo para generar datos de prueba.
