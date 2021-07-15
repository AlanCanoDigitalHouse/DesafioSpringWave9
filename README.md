# DesafioSpringWave9
## SocialMEli

En este repositorio se encuentra el proyecto del desafio, contiene unos respositorios realizados mediante archivos en Json. Los archivos Json son Postfile.json, ProductsFile.json, UsersFile.json. Estos se encuentran en la ruta resources/static

A continuacion se presentan los metodos que que se desarrollaron para cumplir con el objetivo de cada Historia de Usuario.


### US 0001: 
**Descripcion:** Poder realizar la acción de “Follow” (seguir) a un determinado vendedor

|URI| Sign|
| ------------- | ------------- |
| /users/{userId}/follow/{userIdToFollow} |POST|

|Metodo | Descripción |
| ------------- | ------------- |
| **UserController.Follow()**  | Metodo para la llamada del servicio que se encarga de realizar la funcion de "Follow" hacia un determinado vendedor|
| **UserService.FollowUser()** | Metodo delservicio que permite que un usuario pueda seguir a otro|
| **UserRepository.obtenerUsuario(userId)** | Metodo para realizar la busqueda de un usuario mediante su Id|
| **UserRepository.modificarFollowersUsuario()** | Metodo para actualizar los followers que tiene el usuario|
| **UserRepository.modificarFollowedUsuario** | Metodo para actualizar los followed que tiene el usuario|



### US 0002: 
**Descripcion:** Obtener el resultado de la cantidad de usuarios que siguen a un determinado vendedor

|URI| Sign|
| ------------- | ------------- |
| /users/{userId}/followers/count/ |GET|

|Metodo | Descripción |
| ------------- | ------------- |
| **UserController.countFollow()**  | Metodo para la llamada al servicio para contar cuantos seguidores tiene un usuario|
| **UserService.countFollow()** | Metodo para conocer el numero de seguidores|
| **UserRepository.obtenerUsuario(userId)** | Metodo para realizar la busqueda de un usuario mediante su Id|


### US 0003: 
**Descripcion:** Obtener un listado de todos los usuarios que siguen a un determinado
vendedor (¿Quién me sigue?)

|URI| Sign|
| ------------- | ------------- |
| /{UserID}/followers/list|GET|

|Metodo | Descripción |
| ------------- | ------------- |
| **UserController.obtainFollowList()**  | Metodo para la llamada al servicio para conocer quienes siguen a un usuario|
| **UserService.obtainFollowList()** | Metodo para conocer el numero de seguidores|
| **UserRepository.obtenerUsuario(userId)** | Metodo para realizar la busqueda de un usuario mediante su Id|


### US 0004: 
**Descripcion:** Obtener un listado de todos los vendedores a los cuales sigue un
determinado usuario (¿A quién sigo?)

|URI| Sign|
| ------------- | ------------- |
| /users/{UserID}/followed/list/|GET|

|Metodo | Descripción |
| ------------- | ------------- |
| **UserController.obtainFollowedList()**  | Metodo para la llamada para obtener la lista de a quienes siguen|
| **UserService.obtainFollowedList()** | Metodo para obtener la lista de a quienes siguen|
| **UserRepository.obtenerUsuario(userId)** | Metodo para realizar la busqueda de un usuario mediante su Id|


### US 0005: 
**Descripcion:** Dar de alta una nueva publicación

|URI| Sign|
| ------------- | ------------- |
| /products/newpost|POST|

|Metodo | Descripción |
| ------------- | ------------- |
| **ProductoController.newPost()**  | Metodo para la llamada al servicio para dar de alta una nueva publicacion|
| **UserService.newPost()** | Metodo para crear nueva publicacion|
| **ProductRespository.obtenerProducto(product_id)** | Metodo para realizar la busqueda de un producto mediante su Id de producto|
| **ProductRespository.obtenerPublicacion()** | Metodo para realizar la busqueda de un usuario mediante su Id|
| **ProductRespository.anadirProducto()** | Metodo para registrar un nuevo producto|
| **UserRespository.modificarPostUsuario()** | Metodo para añadir el identificador de la publicacion hecha por el usuario|


### US 0006: 
**Descripcion:** Obtener un listado de las publicaciones realizadas por los vendedores que un usuario sigue en las últimas dos semanas (para esto tener en cuenta ordenamiento por fecha, publicaciones más recientes primero).

|URI| Sign|
| ------------- | ------------- |
| /products/followed/{userId}/list|GET|

|Metodo | Descripción |
| ------------- | ------------- |
| **ProductoController.obtainPostList()**  | Metodo para la llamada al servicio para conocer las publicaciones realizadas por los que sigue el usuario|
| **UserService.obtainPostList()** | Metodo para obtener la lista de publicaciones de los que el usuario sigue|
| **ProductRespository.searchUsersRecentPosts()** | Metodo para realizar una busqueda de las publicaciones recientes|
| **Utils.postsSorter()** | Metodo para realizar el ordenamiento de las publicaciones|

### US 0007: 
**Descripcion:** Poder realizar la acción de “Unfollow” (dejar de seguir) a un determinado vendedor.

|URI| Sign|
| ------------- | ------------- |
| /users/{userId}/unfollow/{userIdToUnfollow}|GET|

|Metodo | Descripción |
| ------------- | ------------- |
| **UserController.obtainPostList()**  | Metodo para la llamada al servicio para dejar de seguir a alguien|
| **UserService.eliminarFollowerUsuario()** | Metodo para dejar de seguir a un usuario|
| **ProductRespository.eliminarFollowedUsuario()** | Metodo para eliminar al seguidor del usuario|
| **Utils.postsSorter()** | Metodo para realizar el ordenamiento de las publicaciones|
| **UserRepository.obtenerUsuario(userId)** | Metodo para realizar la busqueda de un usuario mediante su Id|



### US 0008: 
**Descripcion:** Ordenamiento alfabético ascendente y descendente.

|URI| Sign|
| ------------- | ------------- |
| /users/{userId}/unfollow/{userIdToUnfollow}|GET|

|Metodo | Descripción |
| ------------- | ------------- |
| **UserController.obtainPostList()**  | Metodo para la llamada al servicio para dejar de seguir a alguien|
| **UserService.eliminarFollowerUsuario()** | Metodo para dejar de seguir a un usuario|
| **ProductRespository.eliminarFollowedUsuario()** | Metodo para eliminar al seguidor del usuario|
| **Utils.postsSorter()** | Metodo para realizar el ordenamiento de las publicaciones|
| **UserRepository.obtenerUsuario(userId)** | Metodo para realizar la busqueda de un usuario mediante su Id|
