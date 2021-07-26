# calculadora de metros cuadrados

> Es una API REST que nos permite obtener diferentes datos de una propiedad

## Table of Contents

* [Informacion General](#informacion-general)
* [Tecnologias Usadas](#tecnologias-usadas)
* [Caracteristicas](#caracteristicas)
* [Configuracion](#configuracion)
* [Margen De Mejora](#margen-de-mejora)
* [Contacto](#contacto)

<!-- * [License](#license) -->

## Informacion General

Permite al usuario acceder rapidamente al area total, precio y al area por ambiente de una propiedad

## Tecnologias Usadas

- Java 11
- Spring boot
- Mockito

## Caracteristicas

- Cargar una propiedad al sistema (POST) (localHost:8080/inicializarPropiedad)

- Obtener el ambiente mas grande de la propiedad (GET) (localHost:8080/calcularAmbienteMasGrande)
- Calcular el total de metros cuadrados en la propiedad (GET) (localHost:8080/calcularMetrosDePropiedad)
- Calcular el precio de la propiedad (GET) (localHost:8080/calcularValorDePropiedad)
- Listar todos los ambientes con sus respectivas areas (GET) (localHost:8080/listarAreaDeAmbientes)

## Configuracion

Al iniciar el programa lo primero que se debe hacer es cargar una propiedad al sistema, para esto
con una peticion tipo POST, enviamos el Json de una propiedad a la siguiente direccion
- localHost:8080/inicializarPropiedad

Aqui un ejemplo del Json

{
"prop_name":"Propiedad de tu mama",
"district":{"district_name":"Palermo",
"district_price":"1000"},
"environments":[{"environment_name":"Cocina", "environment_width":12,"environment_length":31},
{"environment_name":"Salon", "environment_width":12,"environment_length":25},
{"environment_name":"Cuarto", "environment_width":15,"environment_length":10.5}]

}


## Margen de Mejora
Por hacer:

- Implementar pruebas Integradas

## Contacto

Creado por miguel.mestra@mercadolibre.com.co 


<!-- Optional -->
<!-- ## License -->
<!-- This project is open source and available under the [... License](). -->

<!-- You don't have to include all sections - just the one's relevant to your project -->
