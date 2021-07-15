
# Datos - Mercado Libre  
    Lucas Agustin Diaz 
    Argentina, Catamarca
    site Mendoza
    lucasagustin.diaz@mercadolibre.com

# Inicio de la API
Al levantar la API, se carga automáticamente un JSON de usuarios, donde los mismos son los necesarios para efectuar todos los flujos de cada endpoint.


# Usuarios
Existe un archivo json en la ruta: src/main/resources/static/users.json
El mismo sirve para alimentar la API de usuarios

Se genera una Lista de UsuarioModel.
## UsuarioModel:
     userId : Integer
     userName: String

##Se plantearon 2 modelos que extienden de UsuarioModel:
    
### BuyerModel 
     userId : Integer
     userName: String
### SellerModel
    userId : Integer
    userName: String


# Publicaciones
Las publicaciones tienen un producto anidado, el mismo se genera y controla con una Lista de productos.

Para que un usuario genere una publicación, se genera un Map< Integer, List < PublicModel > >, Una key es un userId, un Value es una lista de publicaciones;

Cuando se crea una publicación, se valida que no exista un producto con las mismas características, en el caso de existir, el mismo queda anidado a la publicación a crear, en caso de no existir, se crea un nuevo producto.

## PublicModel
     id_post: Integer 
     Date date - pattern = "dd-MM-yyyy"
     ProductModel detail;
     Integer category;
     Double price;

## PublicPromoModel
    id_post : Integer
    date : Date - pattern = "dd-MM-yyyy"
    detail : ProductModel
    category : Integer
    price : Double
    hasPromo : Boolean
    discount : Double

## ProductModel
    product_id : Integer
    productName : String
    type : String
    brand : String
    color : String
    notes : String

# Validación Personalizada 
Se diseñó una anotación personalizada definido como @ValidDate, donde el mismo valida que la fecha no sea mayor que la fecha actual. 

Él mismo recibe un parámetro: message donde si no se ingresa, va un valor por defecto: "the date entered cannot be greater than today", la misma notación convoca la clase MyDateValidation.class para efectuar la validación.

# Consideraciones a tener en cuenta
    - El endpoint: localhost:8080/products/followed/{userId}/list, tiene un ordenamiento descendente por defecto. el mismo puede ser modificado con el parametro order:['date_asc','date_desc']
    - Se uso UserId / userId, UserID como parametros en los endpoints, debido a que en la documetacion de requerimientos estaban asi tipeados. sito algunos ejemplos:
        1) /users/{userId}/unfollow/{userIdToUnfollow}
        2) /users/{UserID}/followers/list?order=name_desc
        3) /users/{UserID}/followed/list?order=name_asc
    - localhost:8080/users/{userId}/unfollow/{userIdToUnfollow} al dejar de seguir un usuario, siempre devolvera un 200 salvo que no exista el usuario.
    - Se definio una validacion personalizada para validar las fechas "@ValidDate"
    - IMPORTANTE!! En el BONUS - Solo se muestran las promos que tienen el campo en TRUE, si son FALSE no se muestran! abria que desarrollar.