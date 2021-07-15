# SocialMeli
## Table of Contents
1. [General Info](#general-info)
2. [Data](#data)
3. [Apis](#apis)
## General Info
***
Spring boot challenge for Mercado Libre's ITBootcamp
## Data
***
In the json file ```users.jsons``` are the preloaded users.\

Example of user: \
{ 
    "userId": 1,
    "userName": "Name",
    "following": []
}

Example of seller: \
{
"userId": 1,
"userName": "Name",
"followers": []
}

It's possible to get existing users with a GET ```/users/list```.

The ```posts.json``` file is empty, and the posts created with the apis ```products/newpost``` and ```products/newpromopost``` are being saved.

## Apis
***
1. ```/users/{userId}/follow/{userIdToFollow}```   
   Follow action 
   
   
2. ```/users/{userId}/followers/count```
   
   Get how many users are following the seller **userId**


3. ```/users/{userId}/followers/list```

   List of users are following the seller **userId**


4. ```/users/{userId}/followed/list```

   List of users followed by user **userId**


5. ```/products/newpost```

   Create new post


6. ```/products/followed/{userId}/list```

   Last two weeks posts


7. ```/users/{userId}/unfollow/{userIdToUnfollow}```

   Un follow action


8. ```/users/{userId}/followers/list?order=name_asc```
   
   ```/users/{userId}/followers/list?order=name_desc```
   
   ```/users/{userId}/followed/list?order=name_asc```
   
   ```/users/{userId}/followed/list?order=name_desc```

   


9. ```/products/followed/{userId}/list?order=date_asc```

   ```/products/followed/{userId}/list?order=date_desc```


10. ```/products/newpromopost```
    
    Create new post


11. ```/products/{userId}/countPromo```

    Number of promo posts


12. ```/products/{userId}/lis```

    List of promo posts