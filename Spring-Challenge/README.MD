Spring Challenge - IT Bootcamp
-

Con este proyecto es posible:
-

POST /users/{userId}/follow/{userIdToFollow}
Ralizar la acción de “Follow” (seguir) a un determinado vendedor

GET /users/{userId}/followers/count/
Obtener el resultado de la cantidad de usuarios que siguen a un determinado vendedor

GET /users/{UserID}/followers/list
Obtener un listado de todos los usuarios que siguen a un determinado vendedor (¿Quién me sigue?)

GET /users/{UserID}/followed/list
Obtener un listado de todos los vendedores a los cuales sigue un determinado usuario (¿A quién sigo?)

POST /products/newpost
Dar de alta una nueva publicación

GET /products/followed/{userId}/list
Obtener un listado de las publicaciones realizadas por los vendedores que un usuario sigue en las últimas dos semanas (para esto tener en cuenta ordenamiento por fecha, publicaciones más recientes primero).

POST /users/{userId}/unfollow/{userIdToUnfollow}
Poder realizar la acción de “Unfollow” (dejar de seguir) a un determinado vendedor.

GET Ejemplos:
/users/{UserID}/followers/list?order=name_asc
name_asc Alfabético ascendente.
name_desc Alfabético descendente.
Ordenamiento alfabético ascendente y descendente

GET Ejemplos:
/products/followed/{userId}/list?order=date_asc
/products/followed/{userId}/list?order=date_desc
date_asc Fecha ascendente (de más antigua a más nueva)
date_desc Fecha descendente (de más nueva a más antigua)
Ordenamiento por fecha ascendente y descendente (US-006)
