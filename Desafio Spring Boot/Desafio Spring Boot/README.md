Sobre Socia-Meli

Esta api tiene contiene su informacion almacena en memoria. 
Para inicializar, es necesario ir al siguiente endpoint con el objetivos de
cargar informacion inicial que consiste en algunos usuarios con id's del 0 
al 10 y algunos seguidores y/o seguidos.

http://localhost:8080/users/initialize


US 0001: Realizar la acción de “Follow” (seguir) a un determinado vendedor

http://localhost:8080/users/{userId}/follow/{userIdTofollow}

US 0002: Obtener el resultado de la cantidad de usuarios que siguen a un
determinado vendedor

http://localhost:8080/users/{userId}/followers/count

US 0003 Y US 0008: Obtener un listado de todos los usuarios que siguen a un determinado
vendedor 

http://localhost:8080/users/0/followers/list

http://localhost:8080/users/1/followers/list?order=name_asc

http://localhost:8080/users/1/followers/list?order=name_desc

US 0004 Y US 0008:obtener la lista de usuarios que son seguidos por otro 
usuario. Admite los mismos query params que el anterior.

http://localhost:8080/users/1/followed/list?order=name_asc

US 0005: Crear un nuevo post

http://localhost:8080/products/newpost

US 0006 Y US 0009: Obtener un listado de las publicaciones realizadas por los vendedores que
un usuario sigue en las últimas dos semanas (para esto tener en cuenta
ordenamiento por fecha, publicaciones más recientes primero).

http://localhost:8080/products/followed/0/list
http://localhost:8080/products/followed/0/list?order=date_asc

US 0007: Poder realizar la acción de “Unfollow” (dejar de seguir) a un determinado
vendedor.

http://localhost:8080/users/2/unfollow/2











