# Spring Challenge Wave 9

Objetivo

El objetivo de este desafío es aplicar los contenidos dados hasta el momento durante el
BOOTCAMP MeLi (Git, Java y Spring), con la finalidad de poder implementar una API REST a
partir de un enunciado propuesto, una especificación de requisitos y documentación
anexada.

## Pautas para la actividad

- Desarrollar una API para un escenario determinado. En el punto A de la siguiente sección se encuentra una descripción detallada del escenario y cada uno de los requerimientos solicitados.

- Bonus. En el caso de que se completen todos los requerimientos establecidos en el punto A y aún se disponga de tiempo, se podrá realizar esta actividad que presenta un mayor nivel de complejidad.



## A. Escenario

Mercado Libre sigue creciendo y para el año que viene tiene como objetivo empezar a
implementar una serie de herramientas que permitan a los compradores y vendedores tener
una experiencia totalmente innovadora, en donde el lazo que los una sea mucho más
cercano.

La fecha de lanzamiento se aproxima, por lo cual es necesaria la presentación de una versión
Beta de lo que va a ser conocido como “SocialMeli”, en donde los compradores van a poder
seguir a sus vendedores favoritos y enterarse de todas las novedades que los mismos
posteen.

Para ello, se plantea el requerimiento de la creación de una API Rest que permita:

1. Poder realizar la acción de “Follow” (seguir) a un determinado vendedor
2. Obtener el resultado de la cantidad de usuarios que siguen a un determinado vendedor
3. Obtener un listado de todos los usuarios que siguen a un determinado vendedor (¿Quién me sigue?)
4. Obtener un listado de todos los vendedores a los cuales sigue un determinado usuario (¿A quién sigo?)
5. Dar de alta una nueva publicación.
6. Obtener un listado de las publicaciones realizadas por los vendedores que un usuario sigue en las últimas dos semanas (para esto tener en cuenta ordenamiento por fecha, publicaciones más recientes primero).
7. Poder realizar la acción de “Unfollow” (dejar de seguir) a un determinado vendedor. Por otra parte, dado que se pretende una buena experiencia de usuario con respecto a la forma de presentación de los resultados de cada consulta, se necesita que los mismos puedan ser ordenados mediante cualquiera de los siguientes criterios:
8. Alfabético Ascendente y Descendente
9. Fecha Ascendente y Descendente
## Instalación

Clonar el repositorio en el espacio local

```bash
  Dar RUN en DesafioSpringW9Application
```

## Referencias de API para testear en Postman

### US 0001: Poder realizar la acción de “Follow” (seguir) a un determinado vendedor

```http
  POST /users/{userId}/follow/{userIdToFollow}
```

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `userId` | `int` | Número que identifica al usuario actual |
| `userIdToFollow` | `int` | Número que identifica al usuario a seguir |

---
### US 0002: Obtener el resultado de la cantidad de usuarios que siguen a un determinado vendedor

```http
  GET /users/{userId}/followers/count/
```

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `userId` | `int` | Número que identifica a cada usuario |
| `userName` | `String` | Nombre de usuario asociado a la userId |

---
### US 0003: Obtener un listado de todos los usuarios que siguen a un determinado vendedor (¿Quién me sigue?)

```http
  GET /users/{UserID}/followers/list
```

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `userId` | `int` | Número que identifica a cada usuario |
| `userName` | `String` | Nombre de usuario asociado a la userId |

---
### US 0004: Obtener un listado de todos los vendedores a los cuales sigue un determinado usuario (¿A quién sigo?)

```http
  GET /users/{UserID}/followed/list
```

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `userId` | `int` | Número que identifica a cada usuario |
| `userName` | `String` | Nombre de usuario asociado a la userId |

---
### US 0005: Dar de alta una nueva publicación

```http
  POST /products/newpost
```

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `userId` | `int` | Número que identifica a cada usuario |
| `id_post` | `int` | Número identificatorio de cada una de las publicaciones |
| `date` | `Date` | Fecha de la publicación en formato dd-mm-yyyy |
| `product_id` | `int` | Número identificatorio de cada uno de los productos asociados a una publicación |
| `productName` | `String` | Cadena de caracteres que representa el nombre de un producto |
| `type` | `String` | Cadena de caracteres que representa el tipo de un producto |
| `brand` | `String` | Cadena de caracteres que representa la marca de un producto |
| `color` | `String` | Cadena de caracteres que representa el color de un producto |
| `notes` | `String` | Cadena de caracteres para colocar notas u observaciones de un producto |
| `category` | `int` | Identificador que sirve para conocer la categoría a la que pertenece un producto. Por ejemplo: 100: Sillas, 58: Teclados|
| `price` | `int` | Precio del producto |

---
### US 0006: Obtener un listado de las publicaciones realizadas por los vendedores que un usuario sigue en las últimas dos semanas (para esto tener en cuenta ordenamiento por fecha, publicaciones más recientes primero).

```http
  GET /products/followed/{userId}/list
```

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `userId` | `int` | Número que identifica a cada usuario |

---
### US 0007: Poder realizar la acción de “Unfollow” (dejar de seguir) a un determinado vendedor.

```http
  POST /users/{userId}/unfollow/{userIdToUnfollow}
```

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `userId` | `int` | Número que identifica al usuario actual |
| `userIdToUnfollow` | `int` | Número que identifica al usuario a dejar de seguir |

---
### US 0008: Ordenamiento alfabético ascendente y descendente.

```http
  GET /users/{UserID}/followers/list?order=name_asc
      /users/{UserID}/followers/list?order=name_desc
      /users/{UserID}/followed/list?order=name_asc
      /users/{UserID}/followed/list?order=name_desc
```

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `name_asc` | `String` | Alfabético ascendente.|
| `name_desc` | `String` | Alfabético descendente. |

---
### US 0009: Ordenamiento por fecha ascendente y descendente.

```http
  GET /products/followed/{userId}/list?order=date_asc
      /products/followed/{userId}/list?order=date_desc
```

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `date_asc` | `String` | Fecha ascendente (de más antigua a más nueva)|
| `date_desc` | `String` | Fecha descendente (de más nueva a más antigua)|





## Links para coleccion de Postman

Coleccion para test de apis

[![Run in Postman](https://run.pstmn.io/button.svg)](https://app.getpostman.com/run-collection/7cf7272bf40c1e50c688?action=collection%2Fimport)

https://www.getpostman.com/collections/7cf7272bf40c1e50c688