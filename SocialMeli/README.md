## SETUP

Cuando el proyecto se inicia carga datos de usuarios desde el archivo dataUser.json, 
se cargan 10 usuarios con ids desde el 1 al 10

## REQUERIMIENTOS

- US 0001: Poder realizar la acción de “Follow” (seguir) a un determinado vendedor
  - ```POST /users/{userId}/follow/{userIdToFollow}```
- US 0002: Obtener el resultado de la cantidad de usuarios que siguen a un
  determinado vendedor
  - ```GET /users/{userId}/followers/count/```
  
- US 0003: Obtener un listado de todos los usuarios que siguen a un determinado
vendedor (¿Quién me sigue?)
  - ```GET /users/{UserID}/followers/list```
  
- US 0004: Obtener un listado de todos los vendedores a los cuales sigue un
  determinado usuario (¿A quién sigo?)
  - ```GET /users/{UserID}/followed/list```
  
- US 0005: Dar de alta una nueva publicación
- ```POST /products/newpost```

- US 0006: Obtener un listado de las publicaciones realizadas por los vendedores que
  un usuario sigue en las últimas dos semanas (para esto tener en cuenta
  ordenamiento por fecha, publicaciones más recientes primero).
  - ```GET /products/followed/{userId}/list```
  
- US 0007: Poder realizar la acción de “Unfollow” (dejar de seguir) a un determinado
  vendedor.
  - ```POST /users/{userId}/unfollow/{userIdToUnfollow}```
  
- US 0008: Ordenamiento alfabético ascendente y descendente
- ```GET /users/{UserID}/followers/list?order=name_asc|name_desc```

- US 0009: Ordenamiento por fecha ascendente y descendente
- ```GET /products/followed/{userId}/list?order=date_asc|date_desc```

- US 0010: Llevar a cabo la publicación de un nuevo producto en promoción
- ```POST /products/newpromopost```

- US 0011: Obtener la cantidad de productos en promoción de un determinado
  vendedor
  - ```GET /products/{userId}/countPromo/```
  
- US 0012: Obtener un listado de todos los productos en promoción de un
  determinado vendedor
  - ```GET /products/{userId}/list/```
  

