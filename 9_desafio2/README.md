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
El payload necesario para probar el unico endpoint de la API (`GET /calculate/house`) a través de PostMan es el siguiente:
```json
{
    "prop_name":"oficina de MeLi",
    "district":{
        "district_name":"Polo Dot",
        "district_price":100
    },
    "environments":[{
        "environment_name":"Sector de FP",
        "environment_width":25,
        "environment_length":33
    },
    {
        "environment_name":"Sector de SI",
        "environment_width":20,
        "environment_length":30
    }]
}
```

## Decisiones tomadas
#### ¿Por qué se decidio no testear la función `loadDistricts()` perteneciente a `NeighborhoodRepository`?
Considerando que los casos en los cuales puede ocurrir un error son casos muy bordes (por ejemplo, lo que lanza un IO exception es que no se tenga permisos de escritura dentro del archivo) se decidio tomar la limitación de no testearlo, sin embargo, dicho caso se encuentra catcheado por el `try/catch`.

#### ¿Por qué no se tienen modelos de casas y habitaciones?
Como no se tiene una base de datos, y solamente se cuenta con el respositorio de barrios, no se lo tiene. Sin embargo, en caso de escalar el modelo y/o implementar una base de datos, será necesario contar con ellos.
