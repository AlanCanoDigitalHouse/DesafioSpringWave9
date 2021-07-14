# Social Meli Documentation

### Reference Documentation

El desarrollo de esta api se basa en la siguiente especificacion de requerimientos:

* [PG_Esp. de Req. técnicos funcionales Desafío Spring.docx](https://drive.google.com/file/d/1iPdb8VVgxi4SZtWNqwHo_lo-quODgi3i/view)

### Guia inicial

Especificación de uso de la aplicacion Social Meli:

* Se encuentran precargados un 4 usuarios (Sin Post, ni Followers, ni Followed), para conocer los mismo acceder a
  Get http://localhost:8080/users
* Se pueden dar de altas usuarios, mediante el siguiiente metodo POST http://localhost:8080/users/{userName}
* Para el funcionamiento del **BONUS**, se opto por no interferir en el funcionamiento original del proyecto. Es por esto
que los PostPromo heredan de los post originales y se almacenan en listas separadas.

## Documentación funcionamiento 

###Tabla de contenidos:
* [US - 0001](#US-0001:)
* [US - 0002](#US-0002:)
* [US - 0003](#US-0003:)
* [US - 0004](#US-0004:)
* [US - 0005](#US-0005:)
* [US - 0006](#US-0006:)
* [US - 0007](#US-0007:)
* [US - 0008](#US-0008:)
* [US - 0009](#US-0009:)
* [BONUS](#BONUS)
  * [US - 0010](#US-0010:)
  * [US - 0011](#US-0011:)
  * [US - 0012](#US-0012:)



### US 0001:
El endpoind **POST US 0001** se encuentra en la clase UserController.<br>
Se esperan los siguientes parametros {userID} y {userIdToFollow} ambos Integer. Donde al userId va agregar a su lista de seguidos al userIdToFollow y al userIdToFollow
  se va a agregar a su lista de seguidores el userID.<br>
  Resultados esperados:
  * Los dos usuarios existen y no se siguen:
    <pre>Status code: 200<br>
    Response Body:
    {<br> "status": 200,<br> "message": "Follow SuccessFull"<br>}</pre>
  * Los dos usuarios existen y ya se siguen:
    <pre>Status code: 200<br>
    Response Body:
    {<br> "status": 200,<br> "message": "Already following"<br>}</pre>
  * Los dos usuarios son el mismo:
    <pre>Status code: 400<br>
    Response Body:
    {<br> "status": 400,<br> "msg": "Error, no puede seguir al mismo usuario",<br> "details": null<br>}</pre>
  * Alguno de los usuarios no existe:
    <pre>Status code: 400<br>
    Response Body:
    {<br> "status": 400,<br> "msg": "Usuario con userId: 5 no existe ",<br> "details": null<br>}</pre>
  * Se ingresa mal un path Variable (Ej.: /users/5/follow/S):
    <pre>Status code: 400<br>
    Response Body:
    {<br> "status": 400,<br> "msg": "Error de input",<br> "details": {<br>  "userIdToFollow : S": "Tipo Requerido: Integer"<br> }<br>}</pre>
  <br>

### US 0002:
El endpoind **GET US 0002** se encuentra en la clase UserController.<br>
  Se espera el siguiente parametro {userId} que puede ser del tipo Integer (buscara por ID) o del tipo String (buscara por userName).
  Retornara los datos del user (UserName, UserId) y la cantidad de seguidores que posee.
  <br>Resultados esperados:
  * El usuario existe y se busca por userId (Ej.: /users/4/followers/count/):
    <pre>Status code: 200<br>
    Response Body:
    {<br> "userId": 4,<br> "userName": "Victoria",<br> "followers_count": 1<br>}</pre>
  * El usuario existe y se busca por userId (Ej.: /users/Victoria/followers/count/):
    <pre>Status code: 200<br>
    Response Body:
    {<br> "userId": 4,<br> "userName": "Victoria",<br> "followers_count": 1<br>}</pre>
  * El usuario no existe y se busca por userId (Ej.: /users/5/followers/count/):
    <pre>Status code: 400<br>
    Response Body:
    {<br> "status": 400,<br> "msg": "Usuario con userId: 5 no existe ",<br> "details": null<br>}</pre>
  * El usuario no existe y se busca por userName (Ej.: /users/Miguel/followers/count/):
    <pre>Status code: 400<br>
    Response Body:
    {<br> "status": 400,<br> "msg": "Usuario con userName: Miguel no existe ",<br> "details": null<br>}</pre>
  <br>

### US 0003:
El endpoind **GET US 0003** se encuentra en la clase UserController.<br>
  Se espera el siguiente parametro {UserID} que puede ser del tipo Integer (buscara por ID) o del tipo String (buscara por userName).
  Retornara los datos del user (UserName, UserId) y el detalle de cada uno de los seguidores(UserName, userId)
  <br>Resultados esperados:
  * El usuario existe y se busca por userId (Ej.: /users/4/followers/list):
    <pre>Status code: 200<br>
    Response Body:
    {<br> "userId": 4,<br> "userName": "Victoria",<br> "followers": [
        {
            "userId": 1,
            "userName": "Marcos"
        }
      ]
    }</pre>
  * El usuario existe y se busca por userId (Ej.: /users/Victoria/followers/list):
       <pre>Status code: 200<br>
    Response Body:
    {<br> "userId": 4,<br> "userName": "Victoria",<br> "followers": [
        {
            "userId": 1,
            "userName": "Marcos"
        }
      ]
    }</pre>
  * El usuario no existe y se busca por userId (Ej.: /users/5/followers/list):
    <pre>Status code: 400<br>
    Response Body:
    {<br> "status": 400,<br> "msg": "Usuario con userId: 5 no existe ",<br> "details": null<br>}</pre>
  * El usuario no existe y se busca por userName (Ej.: /users/Miguel/followers/list):
    <pre>Status code: 400
    {<br> "status": 400,<br> "msg": "Usuario con userName: Miguel no existe ",<br> "details": null<br>}</pre>
  <br>

### US 0004:
El endpoind **GET US 0004** se encuentra en la clase UserController.<br>
  Se espera el siguiente parametro {UserID} que puede ser del tipo Integer (buscara por ID) o del tipo String (buscara por userName).
  Retornara los datos del user (UserName, UserId) y el detalle de cada uno de sus seguidos(UserName, userId)
  <br>Resultados esperados:
  * El usuario existe y se busca por userId (Ej.: /users/1/followed/list):
    <pre>Status code: 200<br>
    Response Body:
    {<br> "userId": 1,<br> "userName": "Marcos",<br> "followers": [
        {
            "userId": 4,
            "userName": "Victoria"
        }
      ]
    }</pre>
  * El usuario existe y se busca por userId (Ej.: /users/Marcos/followed/list):
       <pre>Status code: 200<br>
    Response Body:
    {<br> "userId": 1,<br> "userName": "Marcos",<br> "followers": [
        {
            "userId": 4,
            "userName": "Victoria"
        }
      ]
    }</pre>
  * El usuario no existe y se busca por userId (Ej.: /users/5/followed/list):
    <pre>Status code: 400<br>
    Response Body:
    {<br> "status": 400,<br> "msg": "Usuario con userId: 5 no existe ",<br> "details": null<br>}</pre>
  * El usuario no existe y se busca por userName (Ej.: /users/Miguel/followed/list):
    <pre>Status code: 400<br>
    Response Body:
    {<br> "status": 400,<br> "msg": "Usuario con userName: Miguel no existe ",<br> "details": null<br>}</pre>
  <br>

### US 0005:
El endpoind **POST US 0005** se encuentra en la clase ProductController.<br>
  Se espera el siguiente **PAYLOAD**: <br>
  <pre>
  {
    "userId": 4,
    "date" : "10-07-2021",
    "detail" :
        	{ 
               "productName" : "Silla Gamer",
               "type" : "Gamer",
               "brand" : "Racer",
               "color" : "Red & Black",
               "notes" : "Special Edition"
        	},
     	"category" : 100,
     	"price" : 1500.50
  }</pre>
  Resultados esperados:
  * Los datos son correctos y el user existe:
    <pre>Status code: 200<br>
    Response Body:
    {<br> "status": 200,<br> "message": "Post created"<br>}</pre>
  * El usuario no existe:
    <pre>Status code: 400<br>
    Response Body:
    {<br> "status": 400,<br> "msg": "Usuario con userId: 5 no existe ",<br> "details": null<br>}</pre>
  * Se ingresa mal algun valor del PAYLOAD:
    <pre>Status code: 400<br>
    Response Body:
    {<br> "status": 400,<br> "msg": "Bad Request:",<br> "details": {
        "date": "La fecha debe tener el siguiente formato dd-mm-yyy",
        "detail.brand": "La marca del producto no puede ser vacia",
        "detail.productName": "El nombre del producto debe contener entre 2 y 2000 caracteres",
        "price": "EL precio debe ser superior a 1,50$",
        "category": "La categoria debe ser mayor que 0"
      }
    }</pre>
  <br>

### US 0006:
El endpoind **GET US 0006** se encuentra en la clase ProductController.<br>
  Se esperan los siguientes parametros {userID} del tipo Integer.<br>
  Este endpoind retorna el conjunto de publicaciones de los vendedores que un usuario sigue. (Ordenadas de las más reciente a la más antigua).
  <br>Resultados esperados:
  * El usuario existe y sigue a un vendedor con publicaciones (Ej.: /products/followed/1/list):
    <pre>Status code: 200<br>
    Response Body:
    [
      {
        "userId": 4,
        "posts": [
            {
                "idPost": 1,
                "date": "10-07-2021",
                "detail": {
                    "product_id": 1,
                    "productName": "Silla Gamer",
                    "type": "Gamer",
                    "brand": "Racer",
                    "color": "Red & Black",
                    "notes": "Special Edition"
                },
                "category": "100",
                "price": 1500.5
            }
        ]
      }
    ]</pre>
  * El usuarios existe pero no sigue vendedores o los vendedores no realizaron publicaciones en las ultimas dos semanas (Ej.: /products/followed/4/list) :
    <pre>Status code: 200<br>
    Response Body:
    []
    </pre>
  * El usuario no existe:
    <pre>Status code: 400<br>
    Response Body:
    {<br> "status": 400,<br> "msg": "Usuario con userId: 5 no existe ",<br> "details": null<br>}</pre>
  * Se ingresa mal un path Variable (Ej.: /products/followed/S/list):
    <pre>Status code: 400<br>
    Response Body:
    {<br> "status": 400,<br> "msg": "Error de input",<br> "details": {<br>    "userId : S": "Tipo Requerido: Integer"<br> } <br>}</pre>
  <br>

### US 0007:
El endpoind **POST US 0007** se encuentra en la clase UserController.<br>
  Se esperan los siguientes parametros {userID} y {userIdToFollow} ambos Integer. Donde al userId va eliminar de su lista de seguidos al userIdToFollow y al userIdToUnfollow
  se va a eliminar de su lista de seguidores el userID.<br>
  Resultados esperados:
  * Los dos usuarios existen y se siguen:
    <pre>Status code: 200<br>
    Response Body:
    {<br> "status": 200,<br> "message": "UnFollow SuccessFull"<br>}</pre>
  * Los dos usuarios existen y no se siguen:
    <pre>Status code: 200<br>
    Response Body:
    {<br> "status": 200,<br> "message": "Not following"<br>}</pre>
  * Los dos usuarios son el mismo:
    <pre>Status code: 400<br>
    Response Body:
    {<br> "status": 400,<br> "msg": "Error, no puede dejar de seguir al si mismo",<br> "details": null<br>}</pre>
  * Alguno de los usuarios no existe:
    <pre>Status code: 400<br>
    Response Body:
    {<br> "status": 400,<br> "msg": "Usuario con userId: 5 no existe ",<br> "details": null<br>}</pre>
  * Se ingresa mal un path Variable (Ej.: /users/5/unfollow/S):
    <pre>Status code: 400<br>
    Response Body:
    {<br> "status": 400,<br> "msg": "Error de input",<br> "details": {<br>  "userIdToUnfollow : S": "Tipo Requerido: Integer"<br> }<br>}</pre>
  <br>

### US 0008:
El metodo se encuentra en User controller y su compoortamiento es identico a [US 0003](#US-0003:) y [US 0004](#US-0004:), permitiendo enviar por requestParam el orden de los seguidores y seguidos.<br>
A los get anteriores ([US 0003](#US-0003:) y [US 0004](#US-0004:)), se le agrega el requestParam order, aceptando como parametros name_asc y name_desc
  Resultados esperados:
  * Se ingresa un usuario existente y order = name_asc:
    <pre>Status code: 200<br>
    Response Body:
    {
    "userId": 1,
    "userName": "Marcos",
    "followed": [
        {
            "userId": 2,
            "userName": "Martin"
        },
        {
            "userId": 4,
            "userName": "Victoria"
        }
      ]
    }</pre>
  * Se ingresa un usuario existente y order = name_desc:
    <pre>Status code: 200<br>
    Response Body:
    {
    "userId": 1,
    "userName": "Marcos",
    "followed": [
        {
            "userId": 4,
            "userName": "Victoria"
        },
        {
            "userId": 2,
            "userName": "Martin"
        }
      ]
    }</pre>
  * El usuario existe y order = otro valor no esperado:
    <pre>Status code: 400<br>
    Response Body:
    {
      "status": 400,
      "msg": "Parametro invalido para el ordenamiento. Valores validos: name_asc o name_desc",
      "details": null
    }</pre>
  * El usuario no existe:
    <pre>Status code: 400<br>
    Response Body:
    {<br> "status": 400,<br> "msg": "Usuario con userId: 5 no existe ",<br> "details": null<br>}</pre>
  * Se ingresa mal un path Variable :
    <pre>Status code: 400<br>
    Response Body:
    {<br> "status": 400,<br> "msg": "Error de input",<br> "details": {<br>  "userIdToUnfollow : S": "Tipo Requerido: Integer"<br> }<br>}</pre>
  <br>

### US 0009:
El metodo se encuentra en ProductController y su comportamiento es identico a US 0006, permitiendo enviar por requestParam el orden de las publicaciones.<br>
  Al get anterior (US 0006), se le agrega el requestParam order, aceptando como parametros date_asc y date_desc
  Resultados esperados:
  * Se ingresa un usuario existente y order = date_asc:
    <pre>Status code: 200<br>
    Response Body:
    [
    {
        "userId": 4,
        "posts": [
            {
                "idPost": 3,
                "date": "30-06-2021",
                "detail": {
                    "product_id": 3,
                    "productName": "Silla Gamer",
                    "type": "Gamer",
                    "brand": "Racer",
                    "color": "Red & Black",
                    "notes": "Special Edition"
                },
                "category": "100",
                "price": 1500.5
            }
        ]
    },
    {
        "userId": 4,
        "posts": [
            {
                "idPost": 1,
                "date": "10-07-2021",
                "detail": {
                    "product_id": 1,
                    "productName": "Silla Gamer",
                    "type": "Gamer",
                    "brand": "Racer",
                    "color": "Red & Black",
                    "notes": "Special Edition"
                },
                "category": "100",
                "price": 1500.5
            }
        ]
      }
    ]</pre>
  * Se ingresa un usuario existente y order = date_desc:
    <pre>Status code: 200<br>
    Response Body:
    [
    {
        "userId": 4,
        "posts": [
            {
                "idPost": 1,
                "date": "10-07-2021",
                "detail": {
                    "product_id": 1,
                    "productName": "Silla Gamer",
                    "type": "Gamer",
                    "brand": "Racer",
                    "color": "Red & Black",
                    "notes": "Special Edition"
                },
                "category": "100",
                "price": 1500.5
            }
        ]
    },
    {
        "userId": 4,
        "posts": [
            {
                "idPost": 3,
                "date": "30-06-2021",
                "detail": {
                    "product_id": 3,
                    "productName": "Silla Gamer",
                    "type": "Gamer",
                    "brand": "Racer",
                    "color": "Red & Black",
                    "notes": "Special Edition"
                },
                "category": "100",
                "price": 1500.5
            }
        ]
      }
    ]</pre>
  * El usuario existe y order = otro valor no esperado:
    <pre>Status code: 400<br>
    Response Body:
    {
      "status": 400,
      "msg": "Parametro invalido para el ordenamiento. Valores validos: date_asc o date_desc",
      "details": null
    }</pre>
  * El usuario no existe:
    <pre>Status code: 400<br>
    Response Body:
    {<br> "status": 400,<br> "msg": "Usuario con userId: 5 no existe ",<br> "details": null<br>}</pre>
  * Se ingresa mal un path Variable :
    <pre>Status code: 400<br>
    Response Body:
    {<br> "status": 400,<br> "msg": "Error de input",<br> "details": {<br>  "userId : s": "Tipo Requerido: Integer"<br> }<br>}</pre>
  <br>

###BONUS

### US 0010:
El endpoind **POST US 0010** se encuentra en la clase ProductController.<br>
Se espera el siguiente **PAYLOAD**: <br>
  <pre>
  {
    "userId": 4,
    "date" : "10-07-2021",
    "detail" :
        	{ 
               "productName" : "Silla Gamer",
               "type" : "Gamer",
               "brand" : "Racer",
               "color" : "Red & Black",
               "notes" : "Special Edition"
        	},
    "category" : 100,
    "price" : 1500.50,
    "hasPromo" : true,
    "discount" : 20.50
  }</pre>
Resultados esperados:
* Los datos son correctos y el user existe:
  <pre>Status code: 200<br>
  Response Body:
  {<br> "status": 200,<br> "message": "Post created"<br>}</pre>
* El usuario no existe:
  <pre>Status code: 400<br>
  Response Body:
  {<br> "status": 400,<br> "msg": "Usuario con userId: 5 no existe ",<br> "details": null<br>}</pre>
* Se ingresa mal algun valor del PAYLOAD:
  <pre>Status code: 400<br>
  Response Body:
  {<br> "status": 400,<br> "msg": "Bad Request:",<br> "details": {
      "date": "La fecha debe tener el siguiente formato dd-mm-yyy",
      "detail.brand": "La marca del producto no puede ser vacia",
      "detail.productName": "El nombre del producto debe contener entre 2 y 2000 caracteres",
      "price": "EL precio debe ser superior a 1,50$",
      "category": "La categoria debe ser mayor que 0"
    }
  }</pre>
  <br>

### US 0011:
El endpoind **GET US 0011** se encuentra en la clase ProductController.<br>
Devuelve la cantidad de Post en promo de un determinado user
Resultados esperados:
* Usuario existe y tiene productos en promo:
  <pre>Status code: 200<br>
  Response Body:
  {
    "userId": 4,
    "userName": "Victoria",
    "promoproducts_count": 2
  }</pre>
* Usuario existe y NO tiene productos en promo:
  <pre>Status code: 200<br>
  Response Body:
  {
    "userId": 1,
    "userName": "Marcos",
    "promoproducts_count": 0
  }</pre>
* El usuario no existe:
  <pre>Status code: 400<br>
  Response Body:
  {<br> "status": 400,<br> "msg": "Usuario con userId: 5 no existe ",<br> "details": null<br>}</pre>
* Se ingresa mal un path Variable (Ej.: products/ssssss/countPromo/):
  <pre>Status code: 400<br>
  Response Body:
  {
    "status": 400,
    "msg": "Error de input",
    "details": {
        "userId : ssss": "Tipo Requerido: Integer"
    }
  }
</pre>
<br>

### US 0012:
El endpoind **GET US 0012** se encuentra en la clase ProductController.<br>
Devuelve el conjutno de post de un vendedor en promo.
Resultados esperados:
* Usuario existe y tiene productos en promo:
  <pre>Status code: 200<br>
  Response Body:
  [
    {
        "userId": 4,
        "posts": [
            {
                "idPost": 4,
                "date": "10-07-2021",
                "detail": {
                    "product_id": 4,
                    "productName": "Silla Gamer",
                    "type": "Gamer",
                    "brand": "Racer",
                    "color": "Red & Black",
                    "notes": "Special Edition"
                },
                "category": "100",
                "price": 1500.5,
                "hasPromo": true,
                "discount": 20.5
            }
        ]
    },
    {
        "userId": 4,
        "posts": [
            {
                "idPost": 5,
                "date": "10-07-2021",
                "detail": {
                    "product_id": 5,
                    "productName": "Silla Gamer",
                    "type": "Gamer",
                    "brand": "Racer",
                    "color": "Red & Black",
                    "notes": "Special Edition"
                },
                "category": "100",
                "price": 1500.5,
                "hasPromo": true,
                "discount": 20.5
            }
        ]
    }
  ]</pre>
* Usuario existe y NO tiene productos en promo:
  <pre>Status code: 200<br>
  Response Body:
  []</pre>
* El usuario no existe:
  <pre>Status code: 400<br>
  Response Body:
  {<br> "status": 400,<br> "msg": "Usuario con userId: 5 no existe ",<br> "details": null<br>}</pre>
* Se ingresa mal un path Variable (Ej.: products/ssssss/countPromo/):
  <pre>Status code: 400<br>
  Response Body:
  {
    "status": 400,
    "msg": "Error de input",
    "details": {
        "userId : ssss": "Tipo Requerido: Integer"
    }
  }
</pre>
<br>
