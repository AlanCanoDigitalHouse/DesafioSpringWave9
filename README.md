# Desafio Spring (Wave 9)
Repositorio donde guardaremos el código de nuestros proyectos Spring
## Tabla de contenido
* [US0001](#us0001)
* [US0002](#us0002)
* [US0003](#us0003)
* [US0004](#us0004)
* [US0005](#us0005)
* [US0006](#us0006)
* [US0007](#us0007)
* [US0008](#us0008)
* [US0009](#us0009)
* [US0010](#us0007)
* [US0011](#us0008)
* [US0012](#us0009)

## Ejecución del código
Para ejecutar la API es necesario descargar el repositorio y compilar. 
1. Es necesario agregar varios usuarios a la siguiente URL localhost:8080/users/create usando el metodo POST con la estructura:
```
{
    "userName": "test"
}
```
lo cual retornará un objeto de la siguiente estrucutra:
```
{
    "userId": 3,
    "userName": "test",
    "follower_count": 0,
    "followers": [],
    "followed": []
}
```
## US0001
- Para seguir un usuario localhost:8080/users/{userId}/follow/{userToFollow} con el metodo POST
## US0002
- Para ver el número de usuarios que siguen a determinado usuario, usando el metodo GET: localhost:8080/users/{userId}/followers/count/ 
## US0003
- Para ver una lista de usuarios que siguen a determinado usuario, usando el metodo GET: localhost:8080/users/{userId}/followers/list
## US0004
- Para ver una lista de usuarios que son seguidos por determinado usuario usando el metodo GET: localhost:8080/users/{userId}/followed/list
## US0005
- Para crear una publicación se usa la siguiente URL con el metodo POST localhost:8080/products/newpost y se agrega en el body un objeto con la siguiente estructura:
```
{
    "userId":2,
    "date": "13-07-2021",
    "detail":
        {
            "productName":"Teclado gamer",
            "type":"gamer",
            "brand":"redragon",
            "color":"black",
            "notes":"Special Edition"
        },
    "category":100,
    "price":1500.50
    
}
```
## US0006
- Para ver una lista de productos que han publicado los usuarios seguidos por determinado usuario, usando el metodo GET: localhost:8080/products/followed/{userId}/list
## US0007
- Para dejar de seguir un usuario: localhost:8080/users/{userId}/unfollow/{userIdToUnfollow} con el metodo POST
## US0008
- Para ver una lista de usuarios que siguen a determinado usuario, ordenados de manera ascendente o descendente: localhost:8080/users/{UserID}/followers/list?order=name_asc Tener en cuenta que el parametro order puede ser name_asc o name desc.
## US0009
- Para ver una lista de productos que han publicado los usuarios seguidos por determinado cliente y ordenarlos por fecha ascendente o descendente, usando el metodo GET: localhost:8080/products/followed/{userId}/list?order=date_desc
## US0010
- Para crear un producto que cuenta con promocion y descuento se usa la siguiente URL y el metodo POST: localhost:8080/products/newpromopost y en el body se ingresa un objeto con la siguiente estructura.
```
{
    "userId":0,
    "date": "14-07-2021",
    "detail":
        {
            "productName":"Cama",
            "type":"gamer",
            "brand":"redragon",
            "color":"black",
            "notes":"Special Edition"
        },
    "category":100,
    "price":1500.50,
    "hasPromo": true,
    "discount": 0.25
    
}
```
## US0011
- Para ver el número de productos en promoción que tiene un usuario con determinado id, se usa la siguiente URL con el metodo GET: localhost:8080/products/{userId}/countPromo/
## US0012
- Para ver una lista de productos en promoción que tiene un usuario con determinado id, se usa la siguiente URL con el metodo GET: localhost:8080/products/{userId}/list/
