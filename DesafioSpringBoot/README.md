# DesafioSpringWave9
Este documento ayudara a entender cómo esta distribuido el proyecto con todos sus archivos y cómo funcionan sus endpoints.

## Generación de datos
Para este proyecto se decidió generar datos con ayuda de unos json para trabajar los datos en memoria una vez inicializado el proyecto.

### Usuarios

Cuando uno inicializa la aplicación se generaran 100 usuarios 70 de tipo cliente y 30 de tipo vendedor, con esas respectivas id, es decir: 
el usuario con id del 1 al 70 serán clientes, los usuarios con id 71 - 100 serán vendedores.

### Post

Los vendedores cada uno de ellos tendrán 1 o más post, esto fue generado de un total de 100 post, para estos vendedores.

## Estructura del proyecto
~~~tree
├── DesafiospringApplication.java
├── controllers
│   ├── ProductController.java
│   └── UserController.java
├── dtos
│   ├── request
│   │   ├── PostPromoRequestDto.java
│   │   ├── PostRequestDto.java
│   │   └── ProductRequestDto.java
│   └── response
│       ├── ClientResponseDto.java
│       ├── CountPromoDto.java
│       ├── FollowedPostDto.java
│       ├── FollowersCountDto.java
│       ├── PostResponseDto.java
│       ├── ProductResponseDto.java
│       ├── SellerResponseDto.java
│       └── UserResponseDto.java
├── exceptions
│   ├── ApiExceptionHandler.java
│   ├── ErrorListMessage.java
│   ├── ErrorMessage.java
│   ├── LogicValidationException.java
│   ├── TypeNotFoundException.java
│   └── UserNotFoundException.java
├── repositories
│   ├── CRUD.java
│   ├── ProductRepository.java
│   └── UserRepository.java
├── services
│   ├── ProductServices.java
│   ├── Sorter.java
│   └── UserServices.java
└── utils
    ├── Factory.java
    └── importsmodels
        ├── Post.java
        ├── Product.java
        └── User.java
~~~
## Requerimientos

- US 0001 
~~~http
POST http://localhost:8080/users/{1-70}/follow/{71-100}
~~~
- US 0002 
~~~http
GET http://localhost:8080/users/{71-100}/followers/count
~~~
- US 0003
~~~http
GET http://localhost:8080/users/{71-100}/followers/list
~~~
- US 0004
~~~http
GET http://localhost:8080/users/{1-70}/followed/list
~~~
- US 0005
~~~http
GET http://localhost:8080/products/newpost
~~~
Body:
~~~JSON
{
    "userId": 80,//71-100
    "date": "20-04-2021",
    "detail": {
        "productName": "Silla Gamer",
        "type": "Gamer",
        "brand": "Racer",
        "color": "Red & Black",
        "notes": "Special Edition"
    },
    "category": 100,
    "price": 1500.50
}
~~~
- US 0006
~~~http
GET http://localhost:8080/products/followed/{1-70}/list
~~~
- US 0007
~~~http
POST http://localhost:8080/users/{1-70}/unfollow/{71-100}
~~~
- US 0008
~~~http
GET http://localhost:8080/users/{71-100}/followers/list?order=name_asc
~~~
~~~http
GET http://localhost:8080/users/{71-100}/followers/list?order=name_desc
~~~
~~~http
GET http://localhost:8080/users/{1-70}/followed/list?order=name_asc
~~~
~~~http
GET http://localhost:8080/users/{1-70}/followed/list?order=name_desc
~~~

- US 0009
~~~http
GET http://localhost:8080/products/followed/{1-70}/list?order=date_desc
~~~
~~~http
GET http://localhost:8080/products/followed/{1-70}/list?order=date_asc
~~~

- US 0010
~~~http
POST http://localhost:8080/products/newpromopost
~~~
Body:
~~~json
{
    "userId": 80,//userId 71 - 100
    "date": "29-04-2021",
    "detail": {
        "product_id": 1,
        "productName": "Silla Gamer",
        "type": "Gamer",
        "brand": "Racer",
        "color": "Red & Black",
        "notes": "Special Edition"
    },
    "category": 100,
    "price": 1500.50,
    "hasPromo": true,
    "discount": 0.25
}
~~~
- US 0011
~~~http
POST http://localhost:8080/products/{71-100}/countPromo
~~~
- US 0012
~~~http
POST http://localhost:8080/products/{71-100}/list
~~~
