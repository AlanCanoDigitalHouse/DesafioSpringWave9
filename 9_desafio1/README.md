# First BootCamp Challenge

## Tabla de contenido
- [iOS setup](#ios-setup)
    - [Requerimientos](#requerimientos)
        - [Set up](#set-up)
    - [¿Cómo correrlo localmente?](#cómo-correrlo-localmente)
- [¿Cómo funciona?](#cómo-funciona)
    - [JSON para importar en PostMan](#json-para-importar-en-postman)
- [Decisiones tomadas](#decisiones-tomadas)

## iOS-Setup

### Requerimientos

- Java 11
- Maven 3.6.2

#### Set up

- Para instalar Java 11:
    1. Abrir la Terminal
    2. Escribir los siguientes comandos:
  ```bash
  $ brew tap adoptopenjdk/openjdk
  $ brew cask install adoptopenjdk11
  ```

- For installing Maven:
    1. Abrir la Terminal
    2. Escribir los siguientes comandos:
  ```bash
  $ brew install maven
  ```

Si no se tiene instalado Homebrew y Homebrew Cask, entonces podra [leer aqui como instalarlo en Mac OS](https://devqa.io/brew-install-java/).

### ¿Cómo correrlo localmente?

Corra los siguientes comandos en el raiz de la carpeta del proyecto:

```bash
 $ mvn package
 $ java -jar target/FirstSpringChallenge 0.0.1-SNAPSHOT.jar
```

## ¿Cómo funciona?

Previo al levantarse el server para poder realizarle las request deseadas, se levantará un archivo `.JSON`, que se encuentra en `src/java/resources/json`. Ya se encuentra pre-cargado vendedores que se encuentran en el archivo `sellers.json`, pero puede haber en el mismo lugar otro llamado `relations.json` (actualmente se encuentra vacío, pero debe respetar la estructura dada por el model `Relation`).

Todos los endpoints se encuentran detallados y explicados en el [siguiente documento](https://drive.google.com/file/d/1iPdb8VVgxi4SZtWNqwHo_lo-quODgi3i/view).

### JSON para importar en PostMan
Dentro de la carpeta se encuentra el archivo `Bootcamp - Sign Desafio Spring.postman_collection.json` que se podrá importar dentro de PostMan para obtener la colección utilizada para testear la solución con los usuarios ya creados.

## ¿Cómo funciona?


## Decisiones tomadas
### Respecto al ordenamiento de nombres, ¿distingue mayusculas y minusculas?
Si, se decidio que sea asi dado que se busca distinguir aquellos usuarios que tiene nombres con mayusculas de los que no.

### ¿Por qué se tiene una clase abstracta de usuario?
Dado que en el futuro si se espera expandir el scope, y se agregan otros tipos de usuarios no se espera que se tenga lo que tenga un vendedor.

### ¿Por qué se hizo el endpoint de `GET /users/`?
Dado que se todavía la solución no se lo integro con una base de datos, para testear se decidió agregarle dicho endpoint para consultar los ya pre-cargados en memoria.

### ¿Por qúe se tiene un enum para la categoría?
Considerando que en el futuro se pueden adicionar nuevos tipos de categorías, y no tienen un comportamiento propiamente dicho, se opto por dicha solución. En el momento en que estos tengan comportamiento, se podrá cambiar a una clase, u otra solución.
