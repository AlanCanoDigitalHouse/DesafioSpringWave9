# Desafio Spring

API REST **SocialMeli**

## Clone Repository

Execute from console

```bash
git clone git@github.com:AlanCanoDigitalHouse/DesafioSpringWave9.git
git checkout Barreto_Lucas
```

## Usage

```
###
US 0001 POST /users/{userId}/follow/{userIdToFollow} Follows an user

Params:
userId – user Id that will perform the follow action
userIdToFollow – user Id (seller) that will be followed by the user with the previous specified id
Response: 200 OK / 400 Bad Response
Throws:
SellerException – exception in cases of: user already follows the seller, user doesn't exist, seller doesnt exist
UserException -  exception in cases of: user already follows the seller, user doesn't exist, seller doesnt exist


###
GET http://localhost:8080/users/{{userId}}/followers/count 

US 0002 GET users/{userId}/followers/count Returns a JSON response with the seller data and the number of followers If the seller hasn't any followers, it will return the object with value 0 for the followers_count field

Params:
sellerId – The seller id of what we want to retrieve the data
Returns:
ResponseEntity  Seller data in response
Throws: 
SellerException – exception in case that seller doesn't exists


###
GET http://localhost:8080/users/{{userId}}/followers/list

US 0003 GET users/{userId}/followers/list Returns a list with the followers of a seller for the specified id If the seller doesn't have any follower, the field "followers" will be an empty array in the response

Params:
sellerId – Seller id of what we want to retrieve his followers

order – Order parameter, it can be name_asc or name_desc, for default it will return the followers ordered by id
Returns: 
ResponseEntity 
Throws:
SellerException – exception in case that seller doesn't exist


###
GET http://localhost:8080/users/{{userId}}/followed/list
US 0004 GET users/{userId}/followed/list Returns a list with the sellers that an specific user follows

Params:
userId – Id of the user
order – Order of results, it can be name_asc, name_desc or id for default
Returns:
ResponseEntity  User data with the sellers that follows
Throws:
UserException – Exception in case that user not exists


###
POST http://localhost:8080/products/newpost

US 0005 POST /products/newpost Creates a new post

Params:
postRequestDTO – New post data in the body
Response: 200 OK / 400 Bad Response
Throws:
SellerException – Seller exception in case that the seller not exists

###
GET http://localhost:8080/products/followed/{{userId}}/list 

US 0006 GET /products/followed/{userId}/list Las two weeks post from a user followed sellers If theres'no results then return an empty posts array

Params:
userId – user id to retrieve the followed posts
order – order of results, date_asc or date_desc (default if null)
Returns:
ResponseEntity 
Throws:
UserException – Exception in case user doesn't exist


###
POST http://localhost:8080/users/{{userId}}/unfollow/{{userIdToUnfollow}}

US 0007 POST users/{userId}/unfollow/{userIdToUnfollow} User which userId is in path variable userId unfollows the seller for the seller id specified in userIdToUnfollow path variable

Params:
userId – userId that will perform the unfollow action
userIdToUnfollow – seller that will be unfollowed
Response: 200 OK / 400 Bad Response
Throws:
SellerException – Exception in case that userId specified doesn't follow the seller


###
POST http://localhost:8080/products/newpromopost

US 0010 /products/newpromopost Creates a new promo post

Params:
postRequestDTO – data in the request body
Response: 200 OK / 400 Bad Response
Throws:
PostException – post exception if the promo post fields are null
SellerException – seller exception in case the seller id in field doesn't belong to any seller

###
GET http://localhost:8080/products/{{userId}}/countPromo

US 0011 /products/{userId}/countPromo Returns the number of promo posts belonging to an user

Params:
userId – id of the user which promo posts to retrieve
Returns:
ResponseEntity<>(responseDTO, HttpStatus.OK)
Throws:
SellerException – Exception in case of Seller doesn't exist

###
GET http://localhost:8080/products/{{userId}}/list

US 0012 /products/{userId}/list Return a list with the promo posts of a seller

Params:
userId – id of the seller
Returns:
ResponseEntity 
Throws:
SellerException – in case that seller doesn't exists

```

## Data Files with examples

*src/main/resources/static/posts.json*

```JSON
{
  "$id": "posts",
  "$schema": "src/main/resources/static/posts.json",
  "description": "Posts",
  "examples": [
    [
      {
        "userId": 1,
        "id_post": 1,
        "date": "09-07-2021",
        "productId": 1,
        "category": 100,
        "price": 1500.0
      },
      {
        "userId": 2,
        "id_post": 2,
        "date": "10-07-2021",
        "productId": 2,
        "category": 100,
        "price": 1500.0,
        "hasPromo": true,
        "discount": 0.5
      }
    ]
  ],
  "type": "array",
  "items": {
    "$id": "#/items",
    "anyOf": [
      {
        "$id": "#/items/anyOf/0",
        "type": "object",
        "description": "A post made by a seller",
        "examples": [
          {
            "userId": 1,
            "id_post": 1,
            "date": "09-07-2021",
            "productId": 1,
            "category": 100,
            "price": 1500.0
          }
        ],
        "required": [
          "userId",
          "id_post",
          "date",
          "productId",
          "category",
          "price"
        ],
        "properties": {
          "userId": {
            "$id": "#/items/anyOf/0/properties/userId",
            "type": "number",
            "description": "User id (id of the seller) that created the post",
            "examples": [
              1
            ]
          },
          "id_post": {
            "$id": "#/items/anyOf/0/properties/id_post",
            "type": "number",
            "description": "Unique identification for a post",
            "examples": [
              1
            ]
          },
          "date": {
            "$id": "#/items/anyOf/0/properties/date",
            "type": "string",
            "description": "String belonging to the date when the post was published",
            "examples": [
              "09-07-2021"
            ]
          },
          "productId": {
            "$id": "#/items/anyOf/0/properties/productId",
            "type": "number",
            "description": "An explanation about the purpose of this instance.",
            "examples": [
              1
            ]
          },
          "category": {
            "$id": "#/items/anyOf/0/properties/category",
            "type": "number",
            "description": "Post category, example: 100 for technology, 6 for home",
            "examples": [
              100
            ]
          },
          "price": {
            "$id": "#/items/anyOf/0/properties/price",
            "type": "number",
            "description": "Decimal number for the price of the post product",
            "examples": [
              1500.0
            ]
          }
        },
        "additionalProperties": true
      },
      {
        "$id": "#/items/anyOf/1",
        "type": "object",
        "description": "Promo post example, same properties as the normal post but adding the hasPromo and discount fields",
        "examples": [
          {
            "userId": 2,
            "id_post": 2,
            "date": "10-07-2021",
            "productId": 2,
            "category": 100,
            "price": 1500.0,
            "hasPromo": true,
            "discount": 0.5
          }
        ],
        "required": [
          "userId",
          "id_post",
          "date",
          "productId",
          "category",
          "price",
          "hasPromo",
          "discount"
        ],
        "properties": {
          "hasPromo": {
            "$id": "#/items/anyOf/1/properties/hasPromo",
            "type": "boolean",
            "description": "Flag that indicates if the post is a promo post",
            "examples": [
              true
            ]
          },
          "discount": {
            "$id": "#/items/anyOf/1/properties/discount",
            "type": "number",
            "description": "Proportional of the promo discount",
            "examples": [
              0.5
            ]
          }
        },
        "additionalProperties": true
      }
    ]
  }
}
```

*src/main/resources/static/products.json*

```JSON
{
  "$id": "Products",
  "$schema": "src/main/resources/static/products.json",
  "description": "products schema",
  "examples": [
    [
      {
        "product_id": 1,
        "productName": "Silla Gamer",
        "type": "Gamer",
        "brand": "Racer",
        "color": "Red & Black",
        "notes": "Special Edition"
      },
      {
        "product_id": 2,
        "productName": "Silla Oficina",
        "type": "Office",
        "brand": "Red Dragon",
        "color": "Black & Blue",
        "notes": "Standard Edition"
      }
    ]
  ],
  "type": "array",
  "items": {
    "$id": "#/items",
    "anyOf": [
      {
        "$id": "#/items/anyOf/0",
        "type": "object",
        "description": "A product",
        "examples": [
          {
            "product_id": 1,
            "productName": "Silla Gamer",
            "type": "Gamer",
            "brand": "Racer",
            "color": "Red & Black",
            "notes": "Special Edition"
          }
        ],
        "required": [
          "product_id",
          "productName",
          "type",
          "brand",
          "color",
          "notes"
        ],
        "properties": {
          "product_id": {
            "$id": "#/items/anyOf/0/properties/product_id",
            "type": "number",
            "description": "Unique identifier (int secuential) por a product",
            "examples": [
              1
            ]
          },
          "productName": {
            "$id": "#/items/anyOf/0/properties/productName",
            "type": "string",
            "description": "String belongint to the product name",
            "examples": [
              "Silla Gamer"
            ]
          },
          "type": {
            "$id": "#/items/anyOf/0/properties/type",
            "type": "string",
            "description": "String corresponding to product type",
            "examples": [
              "Gamer"
            ]
          },
          "brand": {
            "$id": "#/items/anyOf/0/properties/brand",
            "type": "string",
            "description": "String with the name of the product brand",
            "examples": [
              "Racer"
            ]
          },
          "color": {
            "$id": "#/items/anyOf/0/properties/color",
            "type": "string",
            "description": "String for the product color",
            "examples": [
              "Red & Black"
            ]
          },
          "notes": {
            "$id": "#/items/anyOf/0/properties/notes",
            "type": "string",
            "description": "String with additional information for the product",
            "examples": [
              "Special Edition"
            ]
          }
        },
        "additionalProperties": true
      }
    ]
  }
}
```

*src/main/resources/static/sellers.json*

```JSON
{
  "$id": "Sellers",
  "$schema": "src/main/resources/static/sellers.json",
  "description": "Sellers schema",
  "examples": [
    [
      {
        "userId": 1,
        "userName": "La casa del hardware",
        "followersIds": [
          3
        ]
      },
      {
        "userId": 2,
        "userName": "El imperio del colchón",
        "followersIds": [
          1
        ]
      }
    ]
  ],
  "type": "array",
  "items": {
    "$id": "#/items",
    "anyOf": [
      {
        "$id": "#/items/anyOf/0",
        "type": "object",
        "description": "Seller example",
        "examples": [
          {
            "userId": 1,
            "userName": "La casa del hardware",
            "followersIds": [
              3
            ]
          }
        ],
        "required": [
          "userId",
          "userName",
          "followersIds"
        ],
        "properties": {
          "userId": {
            "$id": "#/items/anyOf/0/properties/userId",
            "type": "number",
            "description": "Unique identifier for a seller",
            "examples": [
              1
            ]
          },
          "userName": {
            "$id": "#/items/anyOf/0/properties/userName",
            "type": "string",
            "description": "String for the seller user name",
            "examples": [
              "La casa del hardware"
            ]
          },
          "followersIds": {
            "$id": "#/items/anyOf/0/properties/followersIds",
            "type": "array",
            "description": "Int array corresponding to the followers ids of the seller",
            "examples": [
              [
                3
              ]
            ],
            "items": {
              "$id": "#/items/anyOf/0/properties/followersIds/items",
              "anyOf": [
                {
                  "$id": "#/items/anyOf/0/properties/followersIds/items/anyOf/0",
                  "type": "number",
                  "description": "A follower ID",
                  "examples": [
                    3
                  ]
                }
              ]
            }
          }
        },
        "additionalProperties": true
      }
    ]
  }
}
```

*src/main/resources/static/users.json*

```JSON
{
  "$schema": "Users",
  "$id": "src/main/resources/static/users.json",
  "type": "array",
  "description": "Users schema",
  "examples": [
    [
      {
        "userId": 1,
        "userName": "Carlos"
      },
      {
        "userId": 2,
        "userName": "Horacio"
      }
    ]
  ],
  "items": {
    "$id": "#/items",
    "anyOf": [
      {
        "$id": "#/items/anyOf/0",
        "type": "object",
        "description": "User example",
        "examples": [
          {
            "userId": 1,
            "userName": "Carlos"
          }
        ],
        "required": [
          "userId",
          "userName"
        ],
        "properties": {
          "userId": {
            "$id": "#/items/anyOf/0/properties/userId",
            "type": "number",
            "description": "Id belonging to an user",
            "examples": [
              1
            ]
          },
          "userName": {
            "$id": "#/items/anyOf/0/properties/userName",
            "type": "string",
            "description": "String that represents the user name",
            "examples": [
              "Carlos"
            ]
          }
        },
        "additionalProperties": true
      }
    ]
  }
}
```

Please make sure to update tests as appropriate.
