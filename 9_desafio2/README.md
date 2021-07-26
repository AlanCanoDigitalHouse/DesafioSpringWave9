# DesafioTestingWave9
Repositorio donde guardaremos el código de nuestro desafio de testing de Ignacio Cordo

## Tabla de contenido
- [Requerimientos](#Requerimientos)
- [¿Cómo levantar el proyecto localmente?](#cómo-levantar-el-proyecto-localmente)
- [¿Cómo correr los tests localmente?](#cómo-correr-los-tests-localmente)
- [¿Cómo funciona?](#cómo-funciona)
- [Decisiones tomadas](#decisiones-tomadas)

## Requerimientos
- Java 11
- Maven 3.6.2
- Clonarse el repositorio y pararse en el branch "Cordo_Ignacio" en la carpeta "9_desafio2"
- Coleccion de Postman [BootcampDesafioTesting.postman_collection.json](BootcampDesafioTesting.postman_collection.json) (Opcional)

## ¿Cómo levantar el proyecto localmente?
1.  Abrir la carpeta "9_desafio2" como proyecto con IntelliJ
2.  Correr el proyecto

## ¿Cómo correr los tests localmente?
1.  Abrir la carpeta "9_desafio2" como proyecto con IntelliJ
2.  Botón derecho en el proyecto -> More Run/Debug -> Run 'All Tests' with coverage 

## ¿Cómo funciona?
La API expone el servicio necesario para cumplir con requerimientos definidos del ejercicio: [Especif.Téc.DesafíoTesting.docx](Especif.Tec.DesafioTesting.docx)

A su vez, se expuso el servicio GET "/information/neighborhoods" los barrios cargados en la base de datos.

Todos estos servicios mencionados se encuentran en la colección de Postman.

## Decisiones tomadas
- El proyecto se desarrolló simulando una base de datos en memoria.
- Se tiene un archivo .json para cargar en memoria los barrios. Este archivo tambien se encuentra en "/src/test/resources/" para las pruebas de integración. 


