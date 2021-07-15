# SocialMeli - Desafío Spring

**_Esta es una aplicación de Java creada con Springboot usando JDK11._**

_Mercado Libre sigue creciendo y para el año que viene tiene como objetivo empezar a implementar una serie de
herramientas que permitan a los compradores y vendedores tener una experiencia totalmente innovadora, en donde el lazo
que los una sea mucho más cercano. La fecha de lanzamiento se aproxima, por lo cual es necesaria la presentación de una
versión Beta de lo que va a ser conocido como “SocialMeli”, en donde los compradores van a poder seguir a sus vendedores
favoritos y enterarse de todas las novedades que los mismos posteen. Para ello, se plantea el requerimiento de la
creación de una API Rest que permita:_

1. Poder realizar la acción de “Follow” (seguir) a un determinado vendedor.

2. Obtener el resultado de la cantidad de usuarios que siguen a un determinado vendedor.

3. Obtener un listado de todos los usuarios que siguen a un determinado vendedor (¿Quién me sigue?).

4. Obtener un listado de todos los vendedores a los cuales sigue un determinado usuario (¿A quién sigo?).

5. Dar de alta una nueva publicación.

6. Obtener un listado de las publicaciones realizadas por los vendedores que un usuario sigue en las últimas dos
   semanas (para esto tener en cuenta ordenamiento por fecha, publicaciones más recientes primero).

7. Poder realizar la acción de “Unfollow” (dejar de seguir) a un determinado vendedor.

_Por otra parte, dado que se pretende una buena experiencia de usuario con respecto a la forma de presentación de los
resultados de cada consulta, se necesita que los mismos puedan ser ordenados mediante cualquiera de los siguientes
criterios:_

8. Alfabético Ascendente y Descendente

9. Fecha Ascendente y Descendente

_SocialMeli además permitirá a los vendedores poder publicar nuevos productos con ofertas o descuentos especiales exclusivos para sus seguidores por un determinado período de tiempo. Para ello propone los siguientes requerimientos:_
10. Llevar a cabo la publicación de un nuevo producto en promoción.
11. Obtener la cantidad de productos en promoción de un determinado vendedor
12. Obtener un listado de todos los productos en promoción de un determinado vendedor


## Comenzando 🚀

_Para obtener una copia del proyecto en funcionamiento en tu máquina local para propósitos de desarrollo y pruebas
deberás:_

1. Ir a https://github.com/AlanCanoDigitalHouse/DesafioSpringWave9

2. Realizar un clone del proyecto con _**git clone**_

3. Moverte a la rama Torres_Aldana con _**git checkout**_

## Despliegue 📦

La aplicación esta lista para realizar las pruebas necesarias. Quizás deba descargar algunas dependencias de Maven.

El proyecto cuenta con dos json en la carpeta static que contienen los datos.

El json users.json cuenta con usuarios precargados para realizar las pruebas de los endpoints.

En el json posts.json se guardaron los nuevos posts creados a traves de la US0005, al iniciar la aplicación este json
sólo contiene [].

### Endpoints desarrollados 📋

Para listar los usuarios:

```
GET: http://localhost:8080/users/ 
```

Para listar los posts:

```
GET: http://localhost:8080/products/
```

Follow:

```
POST: http://localhost:8080/users/{userId}/follow/{userIdToFollow}
```

Cuenta los seguidores de un usuario:

```
GET: http://localhost:8080/users/{userId}/followers/count/
```

Lista los seguidores de un usuario:

```
GET: http://localhost:8080/users/{userId}/followers/list
```

Lista los usuarios seguidos de un usuario:

```
GET: http://localhost:8080/users/{userId}/followed/list
```

Crear una nueva publicación:

```
POST: http://localhost:8080/products/newpost
```

Lista las publicaciones realizadas por los vendedores que un usuario sigue en las últimas dos semanas:

```
GET: http://localhost:8080/products/followed/{userId}/list
```

Unfollow:

```
POST: http://localhost:8080/users/{userId}/follow/{userIdToUnfollow}
```

Ordenamiento de los listados de seguidores y seguidos por nombre

```
GET: http://localhost:8080/users/{UserID}/followers/list?order=name_asc
GET: http://localhost:8080/users/{UserID}/followers/list?order=name_desc
GET: http://localhost:8080/users/{UserID}/followed/list?order=name_asc
GET: http://localhost:8080/users/{UserID}/followed/list?order=name_desc
```

Ordenamiento del listado de publicaciones por fecha

```
GET: http://localhost:8080/products/followed/{userId}/list?order=date_asc
GET: http://localhost:8080/products/followed/{userId}/list?order=date_desc
```

Crear una nueva publicación con promoción:

_Aclaración: independientemente del valor colocado, el atributo hasPromo será true_

```
POST: http://localhost:8080/products/newpromopost
```

Cantidad de publicaciones en promoción de un vendedor:

```
GET: http://localhost:8080/products/{userId}/countPromo/
```

Lista de publicaciones en promoción de un vendedor:

```
GET: http://localhost:8080/products/{userId}/list/
```