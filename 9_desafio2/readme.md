# Desafio Testing: "TuCasita Tasaciones" 🏡
## _Introducción_ 🚀
La empresa “TuCasita Tasaciones” necesita poder calcular los metros cuadrados y el costo de distintas propiedades con las que cuenta en su cartera de clientes.
## _Funcionalidades_ 📌
- Calcular el total de metros cuadrados de una propiedad
- Indicar el valor de una propiedad a partir de sus ambientes y medidas.
- Determinar cuál es el ambiente más grande.
- Determinar la cantidad de metros cuadrados que tiene cada ambiente de una propiedad.

## _Ejemplo de Request_ 📍
| METHOD | SIGN                             |
| ------ | -------------------------------- |
| POST | /calculate                         |
| PAYLOAD | { "prop_name": "House","district": {"district_name": "Recoleta","district_price": 900},"environments": [ { "environment_name": "Cocina", "environment_width": 24,"environment_length": 33}, { "environment_name": "Comedor", "environment_width": 20, "environment_length": 32 } ] }| 
| RESPONSE | { "squareFeet": 1432.0,"price": 1288800.0, "biggest": {"environment_name": "Cocina","environment_width": 24.0,"environment_length": 33.0,"squareFeet": 792.0},"enviroments": [{"environment_name": "Cocina","enviroment_squareMeters": 792.0 }, { "environment_name": "Comedor", "enviroment_squareMeters": 640.0 } ] }

## _Tests_ 🔧
Se realizaron tests unitarios y tests de integración. Podra encontrar cada uno dentro de la carpeta "units" o "integrations" respectivamente. 

## _Reporte de los tests_ 📝
Dentro de este directorio se encuentra una carpeta con el nombre "testReport", ahi podrá visualizar el reporte de coverage de los test de una forma mas amigable.

