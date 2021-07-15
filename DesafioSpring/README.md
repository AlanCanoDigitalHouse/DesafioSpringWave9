# Desafío Spring

_SocialMeli, es una API en donde los compradores van a poder seguir a sus vendedores favoritos y enterarse de todas las novedades que los mismos posteen._

## Comenzando 🚀

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
   java -jar ./target/socialmeli-0.0.1-SNAPSHOT.jar
   ```

## Configuración ⚙️
En la carpeta /src/main/resources/static se encuentra el archivo database.json el cual tiene todos los usuarios pre cargado del proyecto.
## Ejemplos y Uso 📖

1. Seguir un vendedor: \
   POST /users/{userId}/follow/{userIdToFollow}
   ```
   curl --location --request POST 'localhost:8080/users/1/follow/2' 
   --data-raw ''
   ```
2. Obtener la cantidad de seguidores: \
   GET /users/{userId}/followers/count/
   ```
    curl --location --request GET 'localhost:8080/users/2/followers/count/'
   ```
3. Obtener el listado de seguidores: \
   Recibe un parámetro de ordenamiento por nombre opcional. Puede ser name_asc o name_desc. \
   GET /users/{userId}/followers/list
   ```
    curl --location --request GET 'localhost:8080/users/2/followers/list'
    curl --location --request GET 'localhost:8080/users/2/followers/list'?order=name_asc
    curl --location --request GET 'localhost:8080/users/2/followers/list'?order=name_desc
   ```
4. Obtener el listado de seguidos: \
   Recibe un parámetro de ordenamiento por nombre opcional. Puede ser name_asc o name_desc. \
   GET /users/{userId}/followed/list
   ```
    curl --location --request GET 'localhost:8080/users/1/followed/list'
    curl --location --request GET 'localhost:8080/users/1/followed/list'?order=name_asc
    curl --location --request GET 'localhost:8080/users/1/followed/list'?order=name_desc
   ```
5. Dar de alta una nueva publicación: \
   POST /products/newpost
   ```
    curl --location --request POST 'http://localhost:8080/products/newpost' \
    --header 'Content-Type: application/json' \
    --data-raw '{
            "userId": 2,
            "date" : "04-07-2021",
            "detail" : {
            "productName" : "Silla Gamer",
            "type" : "Gamer",
            "brand" : "Racer",
            "color" : "Red & Black",
            "notes" : "Special Edition"
        },
        "category" : 100,
        "price" : 1500.50
    }'
   ```
6. Obtener el listado de las publicaciones realizadas por los vendedores que un usuario sigue en las últimas dos semanas: \
   Recibe un parámetro de ordenamiento por fecha opcional. Puede ser date_asc o date_desc. \
   GET /products/followed/{userId}/list
   ```
    curl --location --request GET 'localhost:8080/products/followed/1/list'
    curl --location --request GET 'localhost:8080/products/followed/1/list?order=date_asc'
    curl --location --request GET 'localhost:8080/products/followed/1/list?order=date_desc'
   ```   
7. Dejar de seguir un vendedor: \
   POST /users/{userId}/unfollow/{userIdToUnfollow}
   ```
    curl --location --request POST 'http://localhost:8080/users/1/unfollow/2'
   ``` 
8. Dar de alta una publicación de un nuevo producto en promoción: \
   POST /products/newpromopost
   ```
    curl --location --request POST 'http://localhost:8080/products/newpromopost' \
    --header 'Content-Type: application/json' \
    --data-raw '{
        "userId": 2,
        "date" : "06-07-2021",
        "detail" : {
            "productName" : "Silla Gamer",
            "type" : "Gamer",
            "brand" : "Racer",
            "color" : "Red & Black",
            "notes" : "Special Edition"
        },
        "category" : 100,
        "price" : 1500.50,
        "hasPromo": true,
        "discount": 0.1
    }'
   ``` 
9. Obtener la cantidad de productos en promoción de un determinado vendedor: \
   POST /products/newpromopost
   ```
    curl --location --request GET 'http://localhost:8080/products/2/countPromo/'
   ``` 
10. Obtener la lista de productos en promoción de un determinado vendedor: \
    POST /products/{userId}/list
   ```
    curl --location --request GET 'http://localhost:8080/products/2/list'
   ``` 
