# Desafio Spring
## _Autor: Jhonnatan Felipe Sanchez Cortes_


[![Build Status](https://travis-ci.org/joemccann/dillinger.svg?branch=master)](https://travis-ci.org/joemccann/dillinger)

En el presente documento se presenta la información basica del desafio y el detalle del servicio adicional hecho.


## Contenido

- Presentación general del proyecto
- Documentación de servicio adicional
- Aclaracion de la base de datos y data inicial

## Presentación

El proyecto presente en el repositorio AlanCanoDigitalHouse/DesafioSpringWave9 presenta en el branch Sanchez_Jhonnatan la carpeta del desafio-spring.

En dicha carpeta se encuentra el proyecto de IntelliJ en donde se realizo la solución al desafio planteado, asi como los archivos .json que hacen de base de datos.

Por ultimo, se aclara que todos los metodos presentes en las diferentes interfaces estan comentados para mayor compresión de sus funcionalidades.

Para correr el proyecto solo basta con clonar el branch anteriormente descrito, importar todas las dependencias descritas en el POM y correr el proyecto en IntelliJ. Y para probarlo se puede usar la Collection facilitada por los instructores para probar las diferentes US.

## Servicio adicional

Se agrega un servicio adicional para poder consultar la lista de usuarios presentes en el sistema, esto para poder que ids se tienen disponibles para usar los distintos servicios. Este servicio consulta la lista del .json de usuarios precargados.

**METHOD**
GET: users/all
**RESPONSE:**
```sh
[
    {
        "userId": 1,
        "userName": "Superman",
        "isSeller": true
    },
    {
        "userId": 2,
        "userName": "Batman",
        "isSeller": true
    },
    {
        "userId": 3,
        "userName": "Spider-man",
        "isSeller": false
    },
    {
        "userId": 4,
        "userName": "Wonder woman",
        "isSeller": false
    },
    {
        "userId": 5,
        "userName": "Poseidon",
        "isSeller": false
    }
]
```


## Aclaración

Debido a que una de las limitaciones del desafio es aplicar solo lo enseñado hasta el momento en el bootcamp, se hizo necesario hacer la persistencia de la información tratada en la App mediante archivos .json para las 4 entidades creadas para la solución.

El unico Json con data inicial es el de user.json, los demas se iran poblando con las acciones del usuario al consumir los diferentes servicios

Los 4 json son:
- user.json: Este json simula la tabla/entidad de usuarios en donde se tiene información como el id, tipo y nombre
- follower.json: Este json simula la tabla/entidad que almacena las relaciones del usuario seguidor y el vendedor seguido. Sus unicos atributos son userId y userIdFollowed
- post.json: Este json simula la tabla/entidad en donde se almacenara las publicacion, sin enterar que producto tiene asociada
- product.json: Por ultimo, este json simula la entidad/tabla donde se almacenan todos los productos de la aplicación, aparte de tener los campos descritos en los servicios del desafio, tiene un atributo en donde se le relaciona el id de la publicacion al cual pertenece
