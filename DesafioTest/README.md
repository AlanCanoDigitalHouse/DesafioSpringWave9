# Getting Started

### Reference Documentation

#### Desafío Test

* Si bien podría haberse resuelto con un solo endpoint (tal cual figura en la resolución provista en el playground), el sistema cuenta con cuatro separados para la diferente información que se solicita por un tema de testeo individual de cada uno de ellos.
    * http://localhost:8080/calculate/squareFeet
    * http://localhost:8080/calculate/price
    * http://localhost:8080/calculate/biggestRoom
    * http://localhost:8080/calculate/roomSquareFeet
* Todos devuelven un único DTO, pero se ignoran las propiedades no utilizadas en cada uno de los endpoints.
* Los tests en general tienen dos variantes (una casa con una sola habitación y con varias) y se tomó del ejemplo que había en la solución del playground.
* Además se testea el ingreso de algunos parámetros incorrectos (no están validadas por test todas las opciones que se contemplan en los VALID)
