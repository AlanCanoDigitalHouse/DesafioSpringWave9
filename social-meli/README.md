# Desafio Spring

## Infomacion General
* Proyecto: SocialMeli
* Autor: Regis Emiliano

## Setup
- Dentro de resources/static hay dos archivos ".json".
- Uno contiene 3 vendedores (ids: 1,2,3) y el otro 3 compradores (ids: 100,101,102)
- Los archivos cargan al instanciarse UserRepositoryImpl (metodo anotado con @PostConstruct)

## Exceptions
* SellerNotFoundException
  - `when seller is not found by the given ID`
* BuyerNotFoundException
  - `when buyer us bit found by the given ID` 
* SellerAlreadyFollowedException
  - `when trying to follow a seller who is already followed`
* SellerNotFollowedException
  - `when trying to unfollow a seller who is not followed`
* ValidationException    
  - `when params validation fails`
* HttpMessageNotReadableException
  - `when json payload is not well formed`

## Features

### US 0001: follow seller
* POST `/users/{userId}/follow/{userIdToFollow}`
* Path params
    * `userId [integer]` buyer id
    * `userIdToFollow [integer]` seller id
* Responses
    * `200` success
    * `400` fail

### US 0002: followers count
* GET `/users/{userId}/followers/count/`
* Path params
    * `userId [integer]` seller id
* Responses
  * `200` success      
    ```
    { 
      "userId": [integer],
      "userName: [string],
      "followers_count": [long] 
    }
    ```
  * `400` fail
    
### US 0003: followers list (buyers list)
* GET `/users/{userID}/followers/list`
* Path Params
    * `userId [integer]` seller id
* Request Params
    * `order [string] (optional) (default = "name_asc")` sorting method ("name_asc", "name_desc")
* Response
    * `200` success
    ```
    { 
      "userId": [integer],
      "userName: [string],
      "followers": [array of followers] 
    }
    ```
    * `400` fail

### US 0004: followed list (sellers list)
* GET `/users/{userID}/followed/list`
* Path Params
    * `userId [integer]` buyer id
* Request Params
    * `order [string] (optional) (default = "name_asc")"` sorting method ("name_asc", "name_desc")
* Response
    * `200` success
    ```
    { 
      "userId": [integer],
      "userName: [string],
      "followed": [array of sellers followed] 
    }
    ```
    * `400` fail

### US 0005: new post
* POST `/products/newpost`
* Body params (payload)
    ```
    {
      "userId": [integer],
      "date": [string] (dd-MM-yyyy),
      "category": [integer],
      "price": [double],
      "detail": {
          "productName": [string],
          "type": [string],
          "brand": [string],
          "color": [string],
          "notes": [string] (optional)
      }
    }
    ```
* Response
    * `200` success
    * `400` fail
    
### US 0006: followed sellers posts (last two weeks)
* GET `/products/followed/{userId}/list`
* Path Params
    * `userId [integer]` buyer id
* Request Params
    * `order [string] (optional) (default = "date_desc")` sorting method ("date_asc", "date_desc")
* Response
    * `200` success
    ```
    {
      "userId": [integer],
      "posts: [array of posts]
    }
    ```
    * `400` fail

### US 0007: unfollow seller
* POST `/users/{userId}/unfollow/{userIdToUnfollow}`
* Path params
    * `userId [integer]` buyer id
    * `userIdToUnfollow [integer]` seller id
* Responses
    * `200` success
    * `400` fail
    
### US 0010: new promo post
* POST `/products/newpromopost`
* Body params (payload)
    ```
    {
      "userId": [integer],
      "date": [string] (dd-MM-yyyy),
      "category": [integer],
      "price": [double],
      "hasPromo": [boolean] (TRUE),
      "discount": [double],
      "detail": {
          "productName": [string],
          "type": [string],
          "brand": [string],
          "color": [string],
          "notes": [string] (optional)
      }
    }
    ```
* Response
    * `200` success
    * `400` fail
    
### US 0011: seller promo-products count
* GET `/products/{userId}/countPromo/`
* Path params
    * `userId [integer]` seller id
* Response
    * `200` success
    ```
    {
      "userId": [integer],
      "userName": [string],
      "promoproducts_count": [integer]
    }
    ```
    * `400` fail

### US 0012: seller promo-products list
* GET `/products/{userId}/list/`
* Path params
  * `userId [integer]` seller id
* Request Params
  * `order [string] (optional) (default = "product_name_asc"")` sorting method ("product_name_asc", "product_name_desc")  
* Response
    * `200` success
    ```
    {
      "userId": [integer],
      "userName": [string],
      "posts": [array of posts]
    }
    ```
    * `400` fail
    
