# SocialMeLi - The MeLi social network

Mercado Libre continues to grow and for next year it intends to start implementing a series of tools that will allow buyers and sellers to have a totally innovative experience, where the bond that unites them is much closer.
The launch date is getting closer, so it is necessary to present a Beta version of what will be known as Beta version of what will be known as "SocialMeli", where buyers will be able to follow their favorite sellers and find out to follow their favorite sellers and find out about all the news they publish. post.

### Introduction and technologies
This app will allow to store and manipulate the relationship
(tracking) of the users. You will also be able to create and filter
posts and prodcuts in various ways.
This is a Java server application made with Springboot using JDK11.

### Configuration
The Application should be ready to run as soon as you launch it.

You'll want to make sure the whole project compiles,
and you may need to resolve some Maven dependencies.


### Usage
The full scope of functionality, endpoints, and input/output formats can be found at [this link](https://drive.google.com/file/d/1iPdb8VVgxi4SZtWNqwHo_lo-quODgi3i/view).
The application (for now) will not be developed further, as it was simply a challenge to get some practice using SpringBoot Framework.

Translated with www.DeepL.com/Translator (free version)

## Important Notes

Note that when registering a new publication must take into account the date format (dd-MM-yyyy). 
```diff
- This remains to be corrected because when implementing the JSONFORMAT solution does not work.
```
<span style="color: red">  </span>

```json
{
    "userId":0,
    "id_post":18,
    "date":"2021-07-15",
    "detail":{
        "product_id":1,
        "productName":"Silla Gamer",
        "type":"Gamer",
        "brand":"Razer",
        "color":"Red & Black",
        "notes":"Especial Edition"
    },
    "category":100,
    "price": 100.6

}
```
