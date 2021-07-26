# Second BootCamp Challenge - Testing

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

Corra los siguientes comandos en la raíz de la carpeta del proyecto:

```bash
 $ mvn package
 $ java -jar target/9_desafio2 0.0.1-SNAPSHOT.jar
```

## ¿Cómo funciona?


## ¿Cómo funciona?


## Decisiones tomadas
