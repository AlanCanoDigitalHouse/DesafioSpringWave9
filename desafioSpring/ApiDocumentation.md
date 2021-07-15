# API DOCUMENTATION

## Requerimientos técnicos funcionales

### US 0001: 
Poder realizar la acción de “Follow” (seguir) a un determinado vendedor

#### Method : POST

#### SIGN: users/{userId}/follow/{userIdToFollow}

#### Required
    userId : Integer : Número que identifica al usuario actual
    userIdToFollow: Integer : Número que identifica al usuario a seguir

#### Response
    * [Status code 200](OK)
    {
        statusCode: Integer
        menssage: String
    }

    * [Status code 400](BAD REQUEST)
    {
        statusCode: Integer
        menssage: String
    }

### US 0002:
Obtener el resultado de la cantidad de usuarios que siguen a un determinado vendedor

#### Method : GET

#### SIGN: /users/{userId}/followers/count/

#### Required
    userId : Integer : Número que identifica al usuario actual

#### Response
    * [Status code 200](OK)
    {
        userId : Integer,
        userName : String,
        followers_count : Integer
    }   

    * [Status code 400](BAD REQUEST)
    {
        statusCode: Integer
        menssage: String
    }

### US 0003:
Obtener un listado de todos los usuarios que siguen a un determinado vendedor (¿Quién me sigue?)

#### Method : GET

#### SIGN: /users/{UserID}/followers/list

#### Required
    UserID : Integer : Número que identifica al usuario actual

#### Response
    * [Status code 200](OK)
    {
	userId :  Integer
    userName : String 
	followers  :  [
   				 {
   				 userId : Integer
   				 userName : String
   				 },
                ...
   			   ]
    }  

    * [Status code 400](BAD REQUEST)
    {
        statusCode: Integer
        menssage: String
    }


### US 0003:
Obtener un listado de todos los vendedores a los cuales sigue un determinado usuario (¿A quién sigo?)

#### Method : GET

#### SIGN: /users/{UserID}/followed/list

#### Required
    userId : Integer : Número que identifica al usuario actual

#### Response
    * [Status code 200](OK)
    {
	userId :  Integer
    userName : String 
	followed  :  [
   				 {
   				 userId : Integer
   				 userName : String
   				 },
                ...
   			   ]
    }  

    * [Status code 400](BAD REQUEST)
    {
        statusCode: Integer
        menssage: String
    }

### US 0004:
Obtener un listado de todos los vendedores a los cuales sigue un determinado usuario (¿A quién sigo?)

#### Method : GET

#### SIGN: /users/{UserID}/followed/list

#### Required
    userId : Integer : Número que identifica al usuario actual

#### Response
    * [Status code 200](OK)
    {
	userId :  Integer
    userName : String 
	followed  :  [
   				 {
   				 userId : Integer
   				 userName : String
   				 },
                ...
   			   ]
    }  

    * [Status code 400](BAD REQUEST)
    {
        statusCode: Integer
        menssage: String
    }

### US 0005:
Dar de alta una nueva publicación.

#### Method : POST

#### SIGN: /products/newpost

#### Request body
        {
            userId: Integer
            date : Date
            detail :
                {
                productName : String
                type : String
                brand : String
                color : String
                notes : String
                },
            category : Integer
            price : Double
        }

#### Response
    * [Status code 200](OK)
    {
        statusCode: Integer
        menssage: String
    }

    * [Status code 400](BAD REQUEST)
    {
        statusCode: Integer
        menssage: String
    }


### US 0006:
Obtener un listado de las publicaciones realizadas por los vendedores que un usuario sigue en las últimas dos semanas (para esto tener en cuenta ordenamiento por fecha, publicaciones más recientes primero).

#### Method : GET

#### SIGN: /products/followed/{userId}/list

#### Required
    userId : Integer : Número que identifica a cada usuario.

#### Response
    * [Status code 200](OK)
    {
	userId :  Integer
	posts  :  [
   				{
                id_post: Integer
                date : Date
                detail :
                    { 
                    product_id : Integer
                    productName : String
                    type : String
                    brand : String
                    color : String
                    notes : String
                    },
                category : Integer
                price : Double
                },
                ...
   			   ]
    }  

    * [Status code 400](BAD REQUEST)
    {
        statusCode: Integer
        menssage: String
    }




### US 0007:
Poder realizar la acción de “Unfollow” (dejar de seguir) a un determinado vendedor.

#### Method : POST

#### SIGN: /users/{userId}/unfollow/{userIdToUnfollow}

#### Required
    userId : Integer : Número que identifica a cada usuario.
    userIdToUnfollow : Número que identifica al usuario a dejar de seguir.

#### Response
    * [Status code 200](OK)
    {
        statusCode: Integer
        menssage: String
    }

    * [Status code 400](BAD REQUEST)
    {
        statusCode: Integer
        menssage: String
    }

### US 0008:
Ordenamiento por fecha ascendente y descendente.

#### Method : GET

#### SIGN: /users/{UserID}/followed/list?order=name_asc
#### SIGN: /users/{UserID}/followed/list?order=name_desc

#### Requirement
    name_asc Alfabético ascendente.
    name_desc Alfabético descendente.

#### Response
    * [Status code 200](OK)
    {
	userId :  Integer
    userName : String 
	followed  :  [
   				 {
   				 userId : Integer
   				 userName : String
   				 },
                ...
   			   ]
    }  

    * [Status code 400](BAD REQUEST)
    {
        statusCode: Integer
        menssage: String
    }


#### Method : GET

#### SIGN: /users/{UserID}/followers/list?order=name_asc
#### SIGN: /users/{UserID}/followers/list?order=name_desc

#### Requirement
    name_asc Alfabético ascendente.
    name_desc Alfabético descendente.

#### Response
    * [Status code 200](OK)
    {
	userId :  Integer
    userName : String 
	followers  :  [
   				 {
   				 userId : Integer
   				 userName : String
   				 },
                ...
   			   ]
    }  

    * [Status code 400](BAD REQUEST)
    {
        statusCode: Integer
        menssage: String
    }


### US 0009:
Ordenamiento alfabético ascendente y descendente.

#### Method : GET

#### SIGN: /products/followed/{userId}/list?order=date_asc
#### SIGN: /products/followed/{userId}/list?order=date_desc

#### Requirement
    date_asc Fecha ascendente (de más antigua a más nueva).
    date_desc Fecha descendente (de más antigua a más nueva).

#### Response
    * [Status code 200](OK)
    {
	userId :  Integer
	posts  :  [
   				{
                id_post: Integer
                date : Date
                detail :
                    { 
                    product_id : Integer
                    productName : String
                    type : String
                    brand : String
                    color : String
                    notes : String
                    },
                category : Integer
                price : Double
                },
                ...
   			   ]
    }  

    * [Status code 400](BAD REQUEST)
    {
        statusCode: Integer
        menssage: String
    }


## BONUS

### US 0010:
Llevar a cabo la publicación de un nuevo producto en promoción.

#### Method : POST

#### SIGN: /products/newpromopost

#### Required
    userId : Integer : Número que identifica al usuario actual

### Request body
        {
            userId: Integer
            date : Date
            detail :
                {
                productName : String
                type : String
                brand : String
                color : String
                notes : String
                },
            category : Integer
            price : Double
            hasPromo : boolean,
            discount : Double
        }

#### Response
    * [Status code 200](OK)
    {
        statusCode: Integer
        menssage: String
    }

    * [Status code 400](BAD REQUEST)
    {
        statusCode: Integer
        menssage: String
    }

### US 0011:
Obtener la cantidad de productos en promoción de un determinado vendedor.US
#### Method : GET

#### SIGN: /products/{userId}/countPromo/

#### Required
    userId : Integer : Número que identifica a cada usuario.

#### Response
    * [Status code 200](OK)
    {  
        userId : Integer,
        userName : String,
        promoproducts_count : Integer
    }

    * [Status code 400](BAD REQUEST)
    {
        statusCode: Integer
        menssage: String
    }



### US 0012:
Obtener un listado de todos los productos en promoción de un determinado vendedor.

#### Method : GET

#### SIGN: /products/followed/{userId}/list

#### Required
    userId : Integer : Número que identifica a cada usuario.

#### Response
    * [Status code 200](OK)
    {
	userId :  Integer
	posts  :  [
   				{
                id_post: Integer
                date : Date
                detail :
                    { 
                    product_id : Integer
                    productName : String
                    type : String
                    brand : String
                    color : String
                    notes : String
                    },
                category : Integer
                price : Double
                hasPromo : boolean,
                discount : Double
                },
                ...
   			   ]
    }  

    * [Status code 400](BAD REQUEST)
    {
        statusCode: Integer
        menssage: String
    }


