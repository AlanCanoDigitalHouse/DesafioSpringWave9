# How To

El proyecto utiliza como base de datos un HasMap en memoria por lo que al correr el projecto contiene datos pre cargados, en este caso 7 usuarios, sus followers, posts y followed son datos vacios:

    user0) userId: 0 userName: "lllll"
    user1) userId: 1 userName: "fffff"
    user2) userId: 2 userName: "xxxxx"
    user3) userId: 3 userName: "bbbbb"
    user4) userId: 4 userName: "rrrrr"
    user5) userId: 5 userName: "ppppp"
    user6) userId: 6 userName: "zzzzz"
    user7) userId: 7 userName: "aaaaa"


## Casos de uso 
Se recomienda utilizar los casos de ejemplo para ir cargando datos de followers, post y followed.

### US 0001

POST: /users/{userId}/follow/{userIdToFollow}

Ejemplo:
    
    /users/0/follow/1
    /users/0/follow/2
    /users/2/follow/1
    /users/3/follow/1

### US 0002
GET: /users/{userId}/followers/count/

Ejemplo:

    /users/1/followers/count/

### US 0003
GET: /users/{UserID}/followers/list

Ejemplo:

    /users/1/followers/list

### US 0004
GET: /users/{UserID}/followed/list

Ejemplo:

    /users/0/followed/list

### US 0005
POST: /products/newpost

PAYLOAD:
    
    {
        "userId": 1,
        "date" : "15-07-2021", 
        "detail" :
            { 
                "productName" : "Publicacion Reciente: PC",
                "type" : "PC", 
                "brand" : "Racer",
                "color" : "Red & Black",
                "notes" : "Special Edition"
            },
        "category" : 100, 
        "price" : 1500.50
    }

---

    {
        "userId": 1,
        "date" : "10-07-2021", 
        "detail" :
            { 
                "productName" : "Publicacion Reciente: Mouse",
                "type" : "Mouse", 
                "brand" : "Racer",
                "color" : "Red & Black",
                "notes" : "Special Edition"
            },
        "category" : 100, 
        "price" : 1500.50
    }

---

    {
        "userId": 1,
        "date" : "06-06-2021", 
        "detail" :
            { 
                "productName" : "Publicacion Vieja: Monitor",
                "type" : "Monitor", 
                "brand" : "Racer",
                "color" : "Red & Black",
                "notes" : "Special Edition"
            },
        "category" : 100, 
        "price" : 1500.50
    }

### US 0006
GET: /products/followed/{userId}/list

Ejemplo:
    
    /products/followed/0/list

### US 0007
POST: /users/{userId}/unfollow/{userIdToUnfollow}

Ejemplo:

    /users/3/unfollow/1

### US 0008
GET: 

    /users/1/followers/list?order=name_asc 
    /users/1/followers/list?order=name_desc 
    /users/1/followed/list?order=name_asc 
    /users/1/followed/list?order=name_desc

### US 0009
GET:

    /products/followed/0/list?order=date_asc 
    /products/followed/0/list?order=date_desc
