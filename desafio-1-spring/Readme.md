# SOCIAL MELI APP :computer:

:penguin: This application allows you to create a little database of users who owns posts with products to sell. You can
create users and posts, and list them with different options.

:memo: Developed in IntelliJ IDEA with Java 11 & SpringBoot.

### **APPLICATION ENDPOINTS**

---

### _Sample_

* Create all: **POST** /init/createAll
  ``` 
    Creates a sample database.
    Eg: /init/createAll
    ```

---

### _User_

* Create user: **POST** /users/create
    * `userName`: _String_ with user name.

  ``` 
    Allows to create a user.
    Eg: /users/create
    {
        "userName":"John"
    }
    ```

* Follow user: **POST** /users/{{userId}}/follow/{{userIdToFollow}}
    * `userId`: _Integer_ with follower user id.
    * `userToFollowId`: _Integer_ with followed user id.

    ``` 
    Allows to follow a user.
    Eg: /users/1/follow/2
    ```

* Followers count: **GET** /users/{{userId}}/followers/count/
    * `userId`: _Integer_ user to count followers.

    ``` 
    Gets the amount of users who follows an user.
    Eg: /users/2/followers/count/
    ```


* Followers: **GET** /users/{UserID}/followers/list
    * `UserID`: _Integer_ user.
    * `order`: _String_ **RequestParam** specific order. Expected: name_asc, name_desc. Default is _none_.

    ```
    Gets the follower users list of a user ordered by user name
    Eg: /users/1/followers/list?order=name_asc
  ```

* Followed: **GET** /users/{UserID}/followed/list
    * `UserID`: _Integer_ user.
    * `order`: _String_ **RequestParam** specific order. Expected: name_asc, name_desc. Default is _none_.
    ```
    Gets the followed users list of a user ordered by user name.
    Eg: /users/1/followed/list?order=name_desc
   ```

* Unfollow: **POST** /{userId}/unfollow/{userIdToUnfollow}
    * `userId`: _Integer_ follower user.
    * `userIdToUnfollow`: _Integer_ followed user.
    ```
    Allows to unfollow another user.
    Eg: /users/1/unfollow/2
  ```

---

### _Product_

* Create post: **POST** /products/newpost
    * `postDTO`: _DTO_ containing post information.

  ``` 
    Allows to create a post.
  
    Eg: /products/newpost
    {
        "userId": 1235,
        "date" : "29-04-2021",
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
     }
    ```

* See followed posts: **GET** /products/followed/{userId}/list
    * `userId`: _Integer_ user id.
    * `order`: _String_ **RequestParam** specific order. Expected: date_asc, date_desc. Default is _desc_.

    ```
    Gets the followed users posts list of a user ordered by post date.
    Eg: /products/1/followed/list?order=name_desc
   ```

---
:office: **Application created during Mercadolibre&copy; BootCamp 2021**  
:bust_in_silhouette: **Author** Juan Bianchini :email: `juan.bianchini@mercadolibre.com`
