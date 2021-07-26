# SocialMeliTesting
Aplicacion práctica para el seguimiento de usuarios y sus respectivas publicaciones.

## Table of Contents
* [Informacion importante](#Informacion-importante)
* [Informacion general](#Informacion-general)
* [Tecnologias](#Tecnologias)
* [Ejecucion](#Ejecucion)

## Informacion importante
- Uso del programa: <br>
  	El programa tiene un único endpoint, que tras enviarle el payload, se recibe un response con toda la informacion pedida.
- Uso del repositorio: <br>
  	En el mismo se almacenan las distintas locaciones, al correr el programa y llamar al endpoint, se verifica que la locacion de la casa, además de existir en el repositorio, coincida en precio con el cargado previamente.
- Renombramiento de variables: <br>
  	Los nombres de las variables fueron cambiadas de snake_case a camelCase
	Enviroment fue cambiado por Room.
- Tests unitarios: <br>
	Servicio y Handler: En estos se testeó individualmente las distintas funcionalidades necesarias para el correcto procesamiento del endpoint. </br>
  	Repoitory: Se testeó el correcto levantamiento de datos, el acceso a los mismos, y los errores esperados al intentar acceder a valores inexistentes. <br>
  	Exceptions: Se testeó el recibir el tipo de error esperado en los diferentes casos.

## Informacion general
A través del endpoint de tipo get, calculate, se envía un payload conteniendo el nombre, locacion, y habitaciones de una casa. A través del mismo se recibe un Dto de tipo HouseResponse, el cual contiene toda la informacíon esperada: </br>
	Los metros cuadrados de una propiedad. </br>	
	El valor de la propiedad. </br>
	El ambiente más grande. </br>
	El tamaño de sus habitaciones. </br>



## Tecnologias
- Java 11
- Springboot

## Ejecucion
Abrir el proyecto y correr la clase CalculadoraMetrosCuadradosApplication.

