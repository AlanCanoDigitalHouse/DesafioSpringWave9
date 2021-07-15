# Desafío Spring Boot v1

_Desarrollo de las consignas designadas para el primer trabajo de integración de conceptos en el IT Bootcamp 2021 - Wave 9._

## Comenzando 🚀

_Para poder usar los endpoints del proyecto, primero vamos a necesitar cargar algunos datos de prueba en los repositorios internos. Para esto, basta con correr dos métodos asociados a dos endpoints._




### Cargar usuarios 🔧

_Desde un estadio local usamos la secuencia /start-user para cargar los compradores, vendedores y relaciones de prueba._

```
localhost:8080/users/start-users
```
### Cargar productos 🔧

_Desde un estadio local usamos la secuencia /start-products para cargar los productos de prueba que permitiran usar los distintos endpoints orientados a productos y publicaciones._

```
localhost:8080/products/start-products
```


## Activando los endpoints ⚙️

_Todos los endpoints del proyecto se encuentran dentro del package **controllers**. La separación entre controladores se hizo siguiendo un criterio de afinidad: todos los vinculados a usuarios por un lado y todos los vinculados a productos por otro._

### Poder realizar la acción de “Follow” (seguir) a un determinado vendedor

```
/users/{userId}/follow/{userIdFollow}
```
### Obtener el resultado de la cantidad de usuarios que siguen a un determinado vendedor

```
/users/{userId}/followers/count
```
### Obtener un listado de todos los usuarios que siguen a un determinado vendedor

```
/users/{userId}/followers/list
```
### Obtener un listado de todos los vendedores a los cuales sigue un determinado usuario

```
/users/{userId}/followed/list
```
### Dar de alta una nueva publicación

```
/products/newpost
```
### Obtener un listado de las publicaciones realizadas por los vendedores que un usuario sigue en las últimas dos semanas

```
/products/followed/{userId}/list
```
### Poder realizar la acción de “Unfollow” (dejar de seguir) a un determinado vendedor

```
/users/{userId}/unfollow/{userIdToUnfollow}
```

### Ordenamiento alfabético ascendente y descendente

```
/users/{UserID}/followers/list?order=name_asc
```
```
/users/{UserID}/followed/list?order=name_asc
```
_Para modificar el sentido del ordenamiento puedes cambiar el valor **name_asc** por **name_desc**._

### Ordenamiento por fecha ascendente y descendente

```
/products/followed/{userId}/list?order=date_asc
```
_Para modificar el sentido del ordenamiento puedes cambiar el valor **name_asc** por **name_desc**._



## Autor ✒️

* **Julián Acosta Argañaraz** - *Ingeniero Informático* 

