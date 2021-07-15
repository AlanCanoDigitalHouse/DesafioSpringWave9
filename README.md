# DesafioSpringWave9

El objetivo de este desafío es aplicar los contenidos dados hasta el momento durante el BOOTCAMP MeLi (Git, Java, Spring), con la finalidad de poder implementar una API REST

# Descripción

Mercado Libre sigue creciendo y para el año que viene  tiene como objetivo empezar a implementar una serie de herramientas que permitan a los compradores y vendedores tener una experiencia totalmente innovadora, en donde el lazo que los una sea mucho más cercano. 
La fecha de lanzamiento se aproxima, por lo cual es necesaria la presentación de una versión Beta de lo que va a ser conocido como **SocialMeli**, en donde los compradores van a poder seguir a sus vendedores favoritos y enterarse de todas las novedades que los mismos posteen.

Esta aplicación permite a los vendedores crear nuevas publicaciones y a los usuarios seguir a sus vendedores favoritos. Además de ver los seguidores de los vendedores y sus posts.

# Instalación

Simplemente clone el repositorio y vaya a mi branch **hernandez_emilio**
El proyecto tiene la siguiente estructura

```
├── DesafioSpring
│   ├── DesafioSpringWave9.iml
│   ├── HELP.md
│   ├── mvnw
│   ├── mvnw.cmd
│   ├── pom.xml
│   ├── src
│   └── target
└── README.md
```

Abra el proyecto en Intellij de *Run* en la clase main *DesafioSpringWave9Application.java*

# Uso

Los datos se cargan en tiempo de ejecución. Los id's de los usuarios disponibles son del 1 al 4.

Las solicitudes disponibles son:

1. Poder realizar la acción de “Follow” (seguir) a un determinado vendedor
1. Obtener el resultado de la cantidad de usuarios que siguen a un determinado vendedor
1. Obtener un listado de todos los usuarios que siguen a un determinado vendedor (¿Quién me sigue?)
1. Obtener un listado de todos los vendedores a los cuales sigue un determinado usuario (¿A quién sigo?)
1. Dar de alta una nueva publicación.
1. Obtener un listado de las publicaciones realizadas por los vendedores que un usuario sigue en las últimas dos semanas
1. Poder realizar la acción de “Unfollow” (dejar de seguir) a un determinado vendedor.
1. Llevar a cabo la publicación de un nuevo producto en promoción.
1. Obtener la cantidad de productos en promoción de un determinado vendedor
1. Obtener un listado de todos los productos en promoción de un determinado vendedor

| Caso de uso | METHOD | URI |
| ----------- | ----------- | ----------- | 
| 1 | POST | /users/1235/follow/1569 |
| 2 | GET | /users/1569/followers/count |
| 3 | GET | /users/1569/followers/list |
| 4 | GET | /users/4698/followed/list |
| 5 | POST | /products/newpost |
| 6 | GET | /products/followed/4698/list |
| 7 | POST | /users/1569/unfollow/1235 |
| 8 | GET | /users/1235/followers/list?order=name_asc |
| 8 | GET | /users/1235/followers/list?order=name_desc |
| 8 | GET | /users/1235/followed/list?order=name_asc |
| 8 | GET | /users/1235/followed/list?order=name_desc |
| 9 | GET | /products/followed/1235/list?order=date_asc |
| 9 | GET | /products/followed/1235/list?order=date_desc |
| 10 | POST | /products/newpromopost |
| 11 | GET | /products/1569/countPromo |
| 12 | GET | /products/1235/list |

# Autores
Emilio Hernandez Segovia
