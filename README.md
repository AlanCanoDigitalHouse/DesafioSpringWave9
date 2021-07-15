### Table of contents
* [General info](#general-info)
* [Technologies](#technologies)
* [Endpoints and functionalities](#endpoints)

## General info
This project was developed for the DesafioSpring Wave 9
	
## Technologies
Project is created with:
* Spring Boot
	
## Endpoints and functionalities
US OOO1 Following a seller
http://localhost:8080/users/1235/follow/1569

US 0002 Get the ammount of users that follow a seller
http://localhost:8080/users/1569/followers/count

US 0003 - US 0008 Get the list of users that follow a seller 
(Request param optional for ordering by name in ascending or descending order(?order=name_asc), (?order=name_desc))
http://localhost:8080/users/1569/followers/list?order=name_asc

US 0004 - US 0008 Get the list of sellers that a user is following
(Request param optional for ordering by name in ascending or descending order(?order=name_asc), (?order=name_desc))
http://localhost:8080/users/1200/followed/list?order=name_asc

US 0005 Create a new Post
http://localhost:8080/products/newpost

payload: 
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

US OOO6 - US 0009 Get the list of posts done in the last two weeks by a seller that a user follows 
(Request param optional for ordering by date in ascending or descending order(?order=date_asc), (?order=date_desc))
http://localhost:8080/products/followed/1200/list?order=date_desc

US 0007 Unfollow a seller
http://localhost:8080/users/1200/unfollow/1569


