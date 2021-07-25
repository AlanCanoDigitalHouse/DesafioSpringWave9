# Desafío Testing

### Endpoints
Considerando que se mantuvo por defecto el puerto 8080, y se levanta en localhost:

- para crear propiedades: post a /property
- para crear barrios: post a /district 
- para US-0001: get a /property/NOMBRE/sqrmts *
- para US-0002: get a /property/NOMBRE/price  *
- para US-0003: get a /property/NOMBRE/bigroom *
- para US-0004: get a /property/NOMBRE/room/sqrmts *

* donde NOMBRE es el nombre de la propiedad, con su correspondientes mayúsuculas y etc. 

### Consideraciones
- Entiendo que los ambientes se pueden especificar en una lista (con los nombres de parámetros pedidos), y que el número de ambientes de una casa no es un atributo (ya que no fue específicado como valor de entrada en el documento funcional).

- A partir de los datos de entrada, entiendo que el nombre del barrio y el precio van en un request body específico para la creación del barrio, mientras que todos los datos de entrada excepto el precio del barrio van en el request body de la creación de la propiedad.

- No es una reestricción que el nombre de la propiedad no tenga espacios, pero yo lo agregué porque ese atributo se usa para pegarle al endpoint, y si tiene espacios no puede conformar una URL. Por otro lado, asumo que el nombre de la propiedad debe ser único.

- También asumo que el nombre del ambiente debe ser único para que no haya ambigüedades al indicar cuál es el ambiente más chico.


### Tecnologías
- Java 11 (OpenJDK 64 Zulu11.48+21-CA)
- Spring 2.5.2 (ver dependencias en el POM.xml)


Repositorio original:
https://github.com/epdjeordjian/desafio_testing

