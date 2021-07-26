# Desafio Spring - Social Meli
Armado y funcional hasta el ejercicio 12, se utilizo una sola base de datos, en este caso userSeller.json y posts.json. Estos se guardan en memoria.

## US 0001: Realizar la acción de “Follow” (seguir) a un determinado vendedor
URI: ``` http://localhost:8080/users/7/follow/2 ```
- Params:
  - userId – id del cliente que va a seguir al vendedor
  - userIdToFollow – id del vendedor que va a seguir el cliente
- Returns:
  - ResponseEntity 
- Throws:
  - UserSellerNotFoundExceptions – Lo arroja si el vendedor no existe
  - UserAlreadyFollowingSellerException – Lo arroja si el cliente ya esta siguiendo al vendedor


## US 0002: Obtiene el resultado de la cantidad de usuarios que siguen a un determinado vendedor
URI: ``` http://localhost:8080/users/2/followers/count ```
- Params:
  - userId – es el id del vendedor
- Returns:
  - ResponseEntity 
- Throws:
  - UserSellerNotFoundExceptions – Lo arroja si el vendedor no existe


## US 0003: Obtiene un listado de todos los usuarios clientes que siguen a un determinado vendedor / US 0008: Ordenamiento alfabético ascendente y descendente
URI: ``` http://localhost:8080/users/2/followers/list ```
- Params:
  - userId – es el id del cliente
  - order – es el tipo de ordenamiento que va a tener el listado, segun el nombre del cliente
- Returns:
  - ResponseEntity 
- Throws:
  - UserSellerNotFoundExceptions – Lo arroja si el vendedor no existe
  - OrderNotValidException – Lo arroja si la forma de ordenar no es valida (se acepta: name_asc y name_desc), name_asc es por defecto

## US 0004: Obtiene un listado de todos los vendedores a los cuales sigue un determinado usuario cliente
URI: ``` http://localhost:8080/users/7/followed/list ```
- Params:
  - userId – es el id de un usuario cliente
- Returns:
  - ResponseEntity 
- Throws:
  - UserClientDoesNotExistsException – Lo arroja si un usuario cliente no existe

## US 0005: Da de alta una nueva publicación
URI: ``` http://localhost:8080/products/newpost ```
- Params:
  - post – es una publicacion con los atributos: userId, date, detail, product_id, productName, type, brand, color, notes, category, price
- Returns:
  - ResponseEntity 
- Throws:
  - UserSellerNotFoundExceptions – Lo arroja si el vendedor no existe
  - PostNotValidDateException – Lo arroja si la fecha que se le pasa es posterior a la fecha del dia de hoy
- Request body:
```
{
    "userId": 2,
    "date": "01-07-2021",
    "detail": {
      "product_id": 6,
      "productName": "juju",
      "type": "Gamer",
      "brand": "Racer",
      "color": "Red & Black",
      "notes": "Special Edition"
    },
    "category": "6",
    "price": 1500.3
  }
```
  
## US 0006: Obtiene un listado de las publicaciones realizadas por los vendedores que un usuario cliente sigue en las últimas dos semanas / US 0009: Ordenamiento por fecha ascendente y descendente
URI: ``` http://localhost:8080/products/followed/2/list?order=date_desc ```
- Params:
  - userId – es el id de un vendedor
  - order – es el tipo de ordenamiento que va a tener el listado, la fecha del post
- Returns:
  - ResponseEntity 
- Throws:
  - UserSellerNotFoundExceptions – Lo arroja si el vendedor no existe
  - OrderNotValidException – Lo arroja si la forma de ordenar no es valida (se acepta: date_asc y date_asc), date_Asc es por defecto

## US 0007: Puede realizar la acción de “Unfollow” (dejar de seguir) a un determinado vendedor.
URI: ``` http://localhost:8080/users/7/unfollow/2 ```
- Params:
  - userId – es el id del cliente
  - userIdToUnfollow – es el id del vendedor
- Returns:
  - ResponseEntity 
- Throws:
  - UserSellerNotFoundExceptions – Lo arroja si el vendedor no existe
  - UserClientNotFollowingSellerException – Lo arroja si el cliente no se encuentra siguiendo al vendedor

## US 0010: Lleva a cabo la publicación de un nuevo producto en promoción
URI: ``` http://localhost:8080/products/newpromopost ```
- Params:
  - post – post es una publicacion con los atributos: userId, date, detail, product_id, productName, type, brand, color, notes, category, price, hasPromo, discount
- Returns:
  - ResponseEntity 
- Throws:
  - PostNotValidDateException – Lo arroja si la fecha que se le pasa es posterior a la fecha del dia de hoy
- Request body:
```
{
    "userId": 2,
    "date": "02-07-2021",
    "detail": {
      "product_id": 6,
      "productName": "sroducto1",
      "type": "Gamer",
      "brand": "Racer",
      "color": "Red & Black",
      "notes": "Special Edition"
    },
    "category": "6",
    "price": 1500.3,
    "hasPromo": true,
    "discount": 0.6
  }
```

## US 0011: Obtiene la cantidad de productos en promoción de un determinado vendedor
URI: ``` http://localhost:8080/products/2/countPromo ```
- Params:
  - userId – es el id de un vendedor
- Returns:
  - ResponseEntity 
- Throws:
  - UserSellerNotFoundExceptions – Lo arroja si el vendedor no existe

## US 0012: Obtiene un listado de todos los productos en promoción de un determinado vendedor, este puede ser ordenado por nameProduct_asc o nameProduct_desc
URI: ``` http://localhost:8080/products/2/list?order=nameProduct_asc ```
- Params:
  - userId – es el id de un vendedor
  - order – es el tipo de ordenamiento que va a tener el listado, por nombre de producto
- Returns:
  - ResponseEntity 
- Throws:
  - UserSellerNotFoundExceptions – Lo arroja si el vendedor no existe
  - OrderNotValidException – Lo arroja si la forma de ordenar no es valida (se acepta: nameProduct_asc y nameProduct_desc), nameProduct_asc es por defecto
