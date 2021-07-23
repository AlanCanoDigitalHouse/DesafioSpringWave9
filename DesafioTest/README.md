# Desafío Test

_El siguiente proyecto es una API donde los usuarios pueden calcular los metros cuadrados y el costo de distintas propiedades._

### Pre-requisitos

* [Maven](https://maven.apache.org/) - Dependency Management
* [Java 11](https://www.oracle.com/co/java/technologies/javase-jdk11-downloads.html) -  Development Environment
* [Git](https://git-scm.com/) - Version Control System
* [Spring](https://spring.io/) - Framework for creating web applications in Java
### Instalación

1. Clonar el repo
   ```sh
   git clone git@github.com:AlanCanoDigitalHouse/DesafioSpringWave9.git
   ```

2. Cambiar a la branch Abelenda_Marcos
   ```sh
   git branch Abelenda_Marcos
   ```
3. Moverse a la carpeta sobre la carpeta en la que se encuentra el proyecto


4. Maven packages
   ```sh
   mvn package
   ```
5. Correr java jar
   ```sh
   java -jar ./target/calculadora-metros-cuadrados-0.0.1-SNAPSHOT.jar
   ```

## Configuración ⚙️
En la carpeta /src/main/resources se encuentra el archivo districts.json el cual tiene todos los barrios pre cargado del proyecto.
## Ejemplos y Uso 📖

1. Calcular los metros cuadrados, el costo, ambiente más grande y metro cuadrado por ambiente de una propiedad: \
   POST /calculate
   ```
    curl --location --request POST 'http://localhost:8080/calculate' \
    --header 'Content-Type: application/json' \
    --data-raw '{
    "prop_name": "Prop",
    "district_name": "Palermo",
    "district_price": 1000,
    "environments": [
    {
    "environment_name": "Living",
    "environment_width": 6,
    "environment_length": 10
    },
    {
    "environment_name": "Cocina",
    "environment_width": 3,
    "environment_length": 4
    },
    {
    "environment_name": "Habitación",
    "environment_width": 4,
    "environment_length": 4
    }
    ]
    }'
   ```