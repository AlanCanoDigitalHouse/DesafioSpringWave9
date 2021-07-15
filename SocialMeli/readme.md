# Desafio Spring: "SocialMeli" 

## Información General  🔎
Api que permite a compradores y vendedores forjar lazos mas cercanos.
Los compradores van a poder seguir a sus vendedores favoritos y enterarse de todas las novedades que los mismos posteen.


## Donde encontrar cada requerimiento solicitado  📋
1. Poder realizar la acción de seguir a un determinado vendedor: **UserController** 
   
2. Obtener el resultado de la cantidad de usuarios que siguen a un determinado vendedor: **SellerController**
3. Obtener un listado de todos los usuarios que siguen a un determinado vendedor: **SellerController**
4. Obtener un listado de todos los vendedores a los cuales sigue un determinado usuario: **UserController**
5. Dar de alta una nueva publicación: **UserController**
6. Obtener un listado de las publicaciones realizadas por los vendedores que un usuario sigue: **UserController**
7. Poder realizar la acción de “Unfollow” (dejar de seguir) a un determinado vendedor: **UserController**
8. Ordenamiento alfabético ascendente y descendente: **UserController**
9. Ordenamiento por fecha ascendente y descendente: **UserController**

## Tecnologías  🛠️
* Java 11
* Spring
* Maven

## Precondiciones para el funcionamiento ❗
Se debe ejecutar el siguiente endpoint para cargar los datos en memoria:

`localhost:8080/users/createuser`
## Ejemplos de requests 📚
### Seguir a un vendedor
`localhost:8080/users/13/follow/10`
### Contar la cantidad de seguidores de un vendedor
`localhost:8080/users/10/followers/count/`

### Dar de alta un nuevo "post" de un producto
`localhost:8080/products/newpost`

### Dejar de seguir a un vendedor
`localhost:8080/users/13/unfollow/10`
### Ordenar nombres de mis seguidores
* Ordena alfabeticamente de forma ascendente

`/users/10/followers/list?order=name_asc`

* Ordena alfabeticamente de forma ascendente

 `/users/10/followers/list?order=name_desc`

### Ordenar nombres de los vendedores que sigo
* Ordena alfabeticamente de forma ascendente

`/users/13/followed/list?order=name_asc`

* Ordena alfabeticamente de forma ascendente

`/users/{UserID}/followed/list?order=name_desc`
### Mostrar los productos de un vendedor ordenadas por fecha ascendente al que sigue un usuario
* Ordena de la fecha mas antigua a la mas nueva

`/products/followed/{userId}/list?order=date_asc`

* Ordena de la fecha mas nueva a la mas antigua

`/products/followed/{userId}/list?order=date_desc`



