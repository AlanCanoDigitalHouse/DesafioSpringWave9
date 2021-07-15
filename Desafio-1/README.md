# Bienvenid@ a mi propuesta de solución del primer desafío 
# Estructura del proyecto
La siguiente imagen da a conocer como se encuentra estructurado mi proyecto : 
![Estructura](https://photos.app.goo.gl/AKN65tvXo2NfTC1P7)

Decidi utilizar dos controladores **ProductController** y **UserController** para poder dividir y clasificar ciertos endPoints que iniciaban con user o con products.

Cree dos repositorios **BuyerRepository** y **SellerRepository** que manejan la carga desde un json, guardan, devuelven y eliminan información de un Seller o un Buyer.

El servicio **SocialMeliService** se encarga de realizar la mayoria de lógica de negocio para dar respuesta a las peticiones de alguno de los controladores.

Se deben **crear** post  y postPromo ya que no existen en los datos cargados, así como generar las relaciones entre Buyer y Seller.

A continuación se mostraran una serie de pasos para obtener un **correcto** funcionamiento de mi programa:


# 0. Carga de archivos
Primero se debe correr el siguiente endPoint, el cual realiza la primera y unica carga de los archivos de **buyer.json** y **seller.json**

    http://localhost:8080/users/initdata

## Posibles endPoints

En los pasos siguientes se dará un endPoint de ejemplo relacionado a la solución de cada historia de usuario, así como su posible respuesta.

## 1.  US 0001

    http://localhost:8080/users/2999/follow/2365
    http://localhost:8080/users/18/follow/2365
    http://localhost:8080/users/18/follow/4512
    http://localhost:8080/users/8943/follow/4512

**Expected response**

    Status code 200 OK
    Status code 200 OK
    Status code 200 OK
    Status code 200 OK

## 2.  US 0002

    http://localhost:8080/users/2365/followers/count

**Expected response**
```json
{
    "userId": 2365,
    "userName": "Boris Seller",
    "followers_count": 2
}
```

## 3.  US 0003

    http://localhost:8080/users/2365/followers/list

**Expected response**
```json
{
    "userId": 2365,
    "userName": "Boris Seller",
    "followers": [
        {
            "userId": 2999,
            "userName": "Antu Buyer"
        },
        {
            "userId": 18,
            "userName": "Charlie Buyer"
        }
    ]
}
```
## 4.  US 0004

    http://localhost:8080/users/18/followed/list

**Expected response**
```json
{
    "userId": 18,
    "userName": "Charlie Buyer",
    "followed": [
        {
            "userId": 2365,
            "userName": "Boris Seller"
        },
        {
            "userId": 4512,
            "userName": "Lilli Seller"
        }
    ]
}
```

## 5.  US 0005

    http://localhost:8080/products/newPost

 ```json
 {
    "userId" :4512,
    "date" : "02-07-2021",
    "detail": 
    {
        "productName" : "Gafas natacion",
        "type": "Sport",
        "brand" : "Speedo",
        "color" : "black",
        "notes" : "The best"
    },
    "category" : 234,
    "price" : 56.7
}
  ```
```json
{
    "userId" :4512,
    "date" : "02-06-2021",
    "detail": 
    {
        "productName" : "Aletas",
        "type": "Sport",
        "brand" : "Speedo",
        "color" : "Dark Blue",
        "notes" : "The best"
    },
    "category" : 345,
    "price" : 89.5
}
```
```json
{
    "userId" :4512,
    "date" : "29-06-2021",
    "detail": 
    {
        "productName" : "Toy cat",
        "type": "Pets",
        "brand" : "Fancat",
        "color" : "Yellow",
        "notes" : "For your cat"
    },
    "category" : 567,
    "price" : 34.1
}
```
```json
{
    "userId" :2365,
    "date" : "10-07-2021",
    "detail": 
    {
        "productName" : "Historia de una gaviota y el gato que le enseño a volar",
        "type": "Book",
        "brand" : "Planeta",
        "color" : "Black",
        "notes" : "Good reading"
    },
    "category" : 876,
    "price" : 58.0
}
```
**Expected response por cada newPost**

    Status code 200 OK
## 6.  US 0006

    http://localhost:8080/products/followed/18/list
**Expected response**
```json
{
    "userId": 18,
    "posts": [
        {
            "userId": 2365,
            "date": "10-07-2021",
            "detail": {
                "productName": "Historia de una gaviota y el gato que le enseño a volar",
                "type": "Book",
                "brand": "Planeta",
                "color": "Black",
                "notes": "Good reading"
            },
            "category": 876,
            "price": 58.0
        },
        {
            "userId": 4512,
            "date": "02-07-2021",
            "detail": {
                "productName": "Gafas natacion",
                "type": "Sport",
                "brand": "Speedo",
                "color": "black",
                "notes": "The best"
            },
            "category": 234,
            "price": 56.7
        },
        {
            "userId": 4512,
            "date": "29-06-2021",
            "detail": {
                "productName": "Toy cat",
                "type": "Pets",
                "brand": "Fancat",
                "color": "Yellow",
                "notes": "For your cat"
            },
            "category": 567,
            "price": 34.1
        }
    ]
}
```
## 7.  US 0007

    http://localhost:8080/users/8943/unfollow/4512

**Expected response**
        The buyer stopped following the seller


## 8.  US 0008

 `http://localhost:8080/users/2365/followers/list?order=name_asc`
**Expected response**
```json
{
    "userId": 2365,
    "userName": "Boris Seller",
    "followers": [
        {
            "userId": 18,
            "userName": "Charlie Buyer"
        },
        {
            "userId": 2999,
            "userName": "Antu Buyer"
        }
    ]
}
```
 ## 
 
    http://localhost:8080/users/2365/followers/list?order=name_desc
    
**Expected response**
```json
{
    "userId": 2365,
    "userName": "Boris Seller",
    "followers": [
        {
            "userId": 2999,
            "userName": "Antu Buyer"
        },
        {
            "userId": 18,
            "userName": "Charlie Buyer"
        }
    ]
}
```
##

    http://localhost:8080/users/18/followed/list?order=name_asc
**Expected response**
```json
{
    "userId": 18,
    "userName": "Charlie Buyer",
    "followed": [
        {
            "userId": 4512,
            "userName": "Lilli Seller"
        },
        {
            "userId": 2365,
            "userName": "Boris Seller"
        }
    ]
}
```
##

    http://localhost:8080/users/18/followed/list?order=name_desc
**Expected response**
```json
{
    "userId": 18,
    "userName": "Charlie Buyer",
    "followed": [
        {
            "userId": 2365,
            "userName": "Boris Seller"
        },
        {
            "userId": 4512,
            "userName": "Lilli Seller"
        }
    ]
}
```

## 9.  US 0009



    http://localhost:8080/products/followed/18/list?order=date_asc

**Expected response**
```json
{
    "userId": 18,
    "posts": [
        {
            "userId": 4512,
            "date": "29-06-2021",
            "detail": {
                "productName": "Toy cat",
                "type": "Pets",
                "brand": "Fancat",
                "color": "Yellow",
                "notes": "For your cat"
            },
            "category": 567,
            "price": 34.1
        },
        {
            "userId": 4512,
            "date": "02-07-2021",
            "detail": {
                "productName": "Gafas natacion",
                "type": "Sport",
                "brand": "Speedo",
                "color": "black",
                "notes": "The best"
            },
            "category": 234,
            "price": 56.7
        },
        {
            "userId": 2365,
            "date": "10-07-2021",
            "detail": {
                "productName": "Historia de una gaviota y el gato que le enseño a volar",
                "type": "Book",
                "brand": "Planeta",
                "color": "Black",
                "notes": "Good reading"
            },
            "category": 876,
            "price": 58.0
        }
    ]
}
```
##

    http://localhost:8080/products/followed/18/list?order=date_desc

**Expected response**
```json
{
    "userId": 18,
    "posts": [
        {
            "userId": 2365,
            "date": "10-07-2021",
            "detail": {
                "productName": "Historia de una gaviota y el gato que le enseño a volar",
                "type": "Book",
                "brand": "Planeta",
                "color": "Black",
                "notes": "Good reading"
            },
            "category": 876,
            "price": 58.0
        },
        {
            "userId": 4512,
            "date": "02-07-2021",
            "detail": {
                "productName": "Gafas natacion",
                "type": "Sport",
                "brand": "Speedo",
                "color": "black",
                "notes": "The best"
            },
            "category": 234,
            "price": 56.7
        },
        {
            "userId": 4512,
            "date": "29-06-2021",
            "detail": {
                "productName": "Toy cat",
                "type": "Pets",
                "brand": "Fancat",
                "color": "Yellow",
                "notes": "For your cat"
            },
            "category": 567,
            "price": 34.1
        }
    ]
}
```


## 10.  US 0010

    http://localhost:8080/products/newpromopost
    
```json
{
    "userId" :4512,
    "date" : "09-07-2021",
    "detail": 
    {
        "productName" : "Narraciones extraordinarias",
        "type": "Book",
        "brand" : "Clasicos de la literatura universal",
        "color" : "black",
        "notes" : "Read"
    },
    "category" : 678,
    "price" : 34.5,
    "hasPromo":true,
    "discount": 0.25
}
```
```json
{
    "userId" :2365,
    "date" : "11-07-2021",
    "detail": 
    {
        "productName" : "Stickers",
        "type": "Stationery",
        "brand" : "Stick",
        "color" : "more than one",
        "notes" : "beautiful Stickers"
    },
    "category" : 890,
    "price" : 12.4,
    "hasPromo":true,
    "discount": 0.15
}
```
```json
{
    "userId" :4512,
    "date" : "01-07-2021",
    "detail": 
    {
        "productName" : "Funko Freddie M",
        "type": "Figures",
        "brand" : "Funko",
        "color" : "more than one",
        "notes" : "genial"
    },
    "category" : 45,
    "price" : 78.0,
    "hasPromo":true,
    "discount": 0.30
}
```
**Expected response por cada newPromoPost**

    Status code 200 OK
## 11.  US 0011

    http://localhost:8080/products/4512/countPromo

**Expected response**

 ```json
 {
    "userId": 4512,
    "userName": "Lilli Seller",
    "promoproducts_count": 2
}
 ```
## 12.  US 0012

    http://localhost:8080/products/4512/list

**Expected response**

 ```json
   {
    "userId": 4512,
    "userName": "Lilli Seller",
    "post_promo": [
        {
            "userId": 4512,
            "date": "01-07-2021",
            "detail": {
                "productName": "Funko Freddie M",
                "type": "Figures",
                "brand": "Funko",
                "color": "more than one",
                "notes": "genial"
            },
            "category": 45,
            "price": 78.0,
            "hasPromo": true,
            "discount": 0.3
        },
        {
            "userId": 4512,
            "date": "09-07-2021",
            "detail": {
                "productName": "Narraciones extraordinarias",
                "type": "Book",
                "brand": "Clasicos de la literatura universal",
                "color": "black",
                "notes": "Read"
            },
            "category": 678,
            "price": 34.5,
            "hasPromo": true,
            "discount": 0.25
        }
    ]
}
```






> **Creator:**  Karen Juliana Celis Buitrago



