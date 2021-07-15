# First BootCamp Challenge

## Tabla de contenido
- [iOS setup](#ios-setup)
    - [Requerimientos](#requerimientos)
        - [Set up](#set-up)
    - [¿Cómo correrlo localmente?](#cómo-correrlo-localmente)
- [¿Cómo funciona?](#cómo-funciona)
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

Previo al levantarse el server para poder realizarle las request al servidor, se levantara un archivo `.JSON`, que se encuentra en `src/java/resources/json`. Ya se encuentra pre-cargado usuarios que se encuentran en el archivo `users.json`, pero puede haber en el mismo lugar otro llamado `relations.json` (actualmente se encuentra vacio, pero debe respetar la estructura dada por el model `Relation`).

Todos los endpoints se encuentran detallados y explicados en el [siguiente documento](https://drive.google.com/file/d/1iPdb8VVgxi4SZtWNqwHo_lo-quODgi3i/view).

## Decisiones tomadas
### Respecto al ordenamiento de nombres, ¿distingue mayusculas y minusculas?
Si, se decidio que sea asi dado que se busca distinguir aquellos usuarios que tiene nombres con mayusculas de los que no.

### ¿Por qué se tiene una clase abstracta de usuario?
Dado que en el futuro si se espera expandir el scope, y se agregan otros tipos de usuarios no se espera que se tenga lo que tenga un vendedor.

### ¿Por qué se hizo el endpoint de `GET /users/`?
Dado que se todavía la solución no se lo integro con una base de datos, para testear se decidió agregarle dicho endpoint para consultar los ya pre-cargados en memoria.

