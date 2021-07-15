# Desafio Spring Web - Social Meli

Se realiza una carga de usuarios, los cuales son obtenido de 2 json sellers.json y nonSeller.json, los cuales son 
almacenados en memoria.
Los parametros `order` para los diferentes casos, puede ser igonrado, se deja un ordenamiento por defecto

## US 0001: Realizar la acción de Follow a un determinado vendedor
URI: ``` http://localhost:8080/users/{userId}/follow/{userIdToFollow} ```
- Params:\
`userId` del cliente que va a seguir al vendedor, `userIdToFollow` id del vendedor que seguira el 
  cliente


## US 0002: Cantidad de usuarios que siguen a un determinado vendedor
URI: ``` http://localhost:8080/users/{userId}/followers/count ```
- Params:\
  `userId` id del vendedor

##US 0003: Listado de todos los usuarios que siguen a un determinado vendedor 
####[US 0008: Ordenamiento alfabético ascendente y descendente]
URI: ``` http://localhost:8080/users/{userId}/followers/list ```
- Params:\
  `userId` el id del cliente, `order` ordenamiento que va a tener el listado, segun el nombre del cliente

## US 0004: Listado de todos los vendedores a los cuales sigue un determinado usuario
####[US 0008: Ordenamiento alfabético ascendente y descendente]
URI: ``` http://localhost:8080/users/{userId}/followed/list ```
- Params:\
  `userId` id de un usuario cliente

## US 0005: Dar de alta una post 
URI: ``` http://localhost:8080/products/newpost ```
- Params:\
  post – los atributos de una publicacion: userId, detail: productName, type, brand, color, notes, 
- Request body:
```
{
    "userId": 1235,
    "detail"
    { "productName" : "Silla Gamer",
     "type" : "Gamer",
     "brand" : "Racer",
      "color" : "Red & Black",
     "notes" : "Special Edition"
    },
     "category" : 100,
     "price" : 1500.50,
  }
```

## US 0006: Listado de las publicaciones realizadas por los vendedores seguidos por usuario, que se hayan generado en últimas dos semanas
####[US 0009: Ordenamiento por fecha ascendente y descendente]
URI: ``` http://localhost:8080/products/followed/{userId}/list?order=date_desc ```
- Params:
    - userId – es el id de un vendedor
    - order – es el tipo de ordenamiento que va a tener el listado, la fecha del post
- Returns:
    - ResponseEntity
- Throws:
    - UserSellerNotFoundExceptions – Lo arroja si el vendedor no existe
    - OrderNotValidException – Lo arroja si la forma de ordenar no es valida (se acepta: date_asc y date_asc), date_Asc es por defecto

## US 0007: realizar la acción de Unfollow a un determinado vendedor.
URI: ``` http://localhost:8080/users/{userId}/unfollow/{userIdToUnfollow} ```
- Params:\
  `userId` el id del cliente que quiere dejar de seguir, `userIdToUnfollow` id del vendedor a quien pretende dejar 
  de seguir

## US 0010: Publicar un post de un producto en promoción
URI: ``` http://localhost:8080/products/newpromopost ```
- Params:\
  `post` una publicacion con los atributos: userId, detail: productName, type, brand, color, 
  notes, category, price, hasPromo, discount
- Request body:
```
{
    "userId": 1235,
    "detail" :
    { "productName" : "Silla Gamer",
     "type" : "Gamer",
     "brand" : "Racer",
      "color" : "Red & Black",
     "notes" : "Special Edition"
    },
     "category" : 100,
     "price" : 1500.50,
    "hasPromo": true,
    "discount": 0.6
  }
```

## US 0011: Obtener la cantidad de productos en promoción de un determinado vendedor
URI: ``` http://localhost:8080/products/{userId}/countPromo ```
- Params:\
  `userId` el id de un vendedor

## US 0012: El listado de todos los productos en promoción de un determinado vendedor
URI: ``` http://localhost:8080/products/{userId}/list?order=nameProduct_asc ```
- Params:\
  `userId` el id de un vendedor, `order` ordenamiento que va a tener el listado, por nombre de producto