# Desafio Testing

## Objetivo

El objetivo de este desafío es aplicar los contenidos abordados hasta el momento durante el BOOTCAMP MeLi (Git, Java,
Spring y Testing), haciendo principal hincapié en las validaciones y tipos de testing que pueden ser utilizados a partir
de un enunciado propuesto, una especificación de requisitos y documentación anexada.

### Pautas para la actividad 📋

El desafío que se propone a continuación consta de 2 partes:

A. Implementar validaciones y diferentes tests a un escenario determinado: A partir de un escenario conocido, se deberán
establecer los distintos procesos de validación de datos y procesos de test unitarios necesarios.

B. Bonus: En el caso de que se complete todo lo solicitado en el punto A y aún se disponga de tiempo, se podrá realizar
esta actividad que presenta un mayor nivel de complejidad.

### Escenario 🔧

La empresa “TuCasita Tasaciones” necesita poder calcular los metros cuadrados y el costo de distintas propiedades con
las que cuenta en su cartera de clientes. Para ello, solicita por cada propiedad: un nombre, un barrio y la cantidad de
ambientes que posee; al mismo tiempo, cada ambiente contiene un nombre, un ancho y un largo. A partir de estos datos, se
solicita el desarrollo de una API que sea capaz de:

● US-0001: Calcular el total de metros cuadrados de una propiedad

● US-0002: Indicar el valor de una propiedad a partir de sus ambientes y medidas. Tener en cuenta que los precios por
metro cuadrado están determinados según el barrio.

● US-0003: Determinar cuál es el ambiente más grande.

● US-0004: Determinar la cantidad de metros cuadrados que tiene cada ambiente de una propiedad.

“TuCasita Tasaciones” tiene unos estándares de calidad muy altos con respecto a los productos de software que utiliza,
dado que las transacciones que realiza diariamente son por cifras muy altas de dinero. Es por esto que un consultor
informático que trabaja con ellos estableció una serie de validaciones que considera que sean necesarias tener en cuenta
a la hora de incorporar datos como así también diferentes test unitarios que aseguren los correctos cálculos. Como
documentación de respaldo, adjunta el siguiente documento técnico funcional:
https://drive.google.com/file/d/1Vl7nqxJvrIVwbuipuX8sFnEZSJiuaMJu/view?usp=sharing

B. Bonus Siguiendo con el principio de que “TuCasita Tasaciones” posee unos estándares de calidad altos, el consultor
informático sugirió la posibilidad de contar con diferentes tests de integración además de los tests unitarios. El
consultor conoce los tiempos acotados con los que se cuenta para realizar el desarrollo solicitado, por lo que sugiere
llevar a cabo esta implementación solo en caso de que alcancen los tiempos y se pueda cumplir con la fecha de entrega
estimada.

## Requerimientos técnicos funcionales ⚙️

```http
  POST http://localhost:8080/estate/assessment
```

### Datos de entrada:

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `prop_name` | `String` | Nombre de la propiedad |
| `district_name` | `String` | Barrio |
| `district_price` | `double` | Precio por metro cuadrado|
| `environment_name` | `String` | Nombre del Ambiente |
| `environment_width` | `double` | Ancho del ambiente |
| `environment_length` | `double` | largo del ambiente |

### Tests Unitarios: 🔩

| Situacion de Entrada |  Comportamiento Esperado |
| :-------- |  :------------------------- |
| Verificar que el total de metros cuadrados totales calculados por propiedad sea el correcto. | Devuelve el cálculo correcto del total de metros cuadrados de una propiedad. | 
| Verificar que el barrio de entrada exista en el repositorio de barrios. | Se cumple: Permite continuar con normalidad. // No se cumple: Notifica la no coincidencia mediante una excepción. | 
| Verificar que efectivamente se devuelva el ambiente con mayor tamaño. | Devuelve el ambiente con mayor tamaño. No existe ninguno que lo supere. | 
| Verificar que efectivamente el total de metros cuadrados por ambiente sea el correcto. | Devuelve el cálculo correcto del total de metros cuadrados de un ambiente. | 

## Requerimientos técnicos Extra ⚙️

US-extra: Agrega barrio por nombre y precio

```http
  POST http://localhost:8080/estate/new/district
```

### Datos de entrada:

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `name` | `String` | Nombre del barrio |
| `price` | `Integer` | Precio del barrio |

## Links para coleccion de Postman

Coleccion para test de apis

[![Run in Postman](https://run.pstmn.io/button.svg)](https://app.getpostman.com/run-collection/731a4a9352e2a202ed73?action=collection%2Fimport)

https://www.getpostman.com/collections/731a4a9352e2a202ed73

## Raw Payload ticket 01, 02, 03, 04

{
"prop_name": "Casa",
"district_name": "Sur",
"district_price": 200,
"environments": [
{
"environment_name": "Habitacion",
"environment_width": 2,
"environment_length": 2 }, {
"environment_name": "Living",
"environment_width": 1.5,
"environment_length": 4 }, {
"environment_name": "Pieza",
"environment_width": 1,
"environment_length": 1.5 }
]
}

## Raw Payload Extra

{
"name": "Bajo",
"price": 700
}
