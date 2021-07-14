# DesafioSpringWave9
DesafioSpringWave9
Bootcamp Backend Java Desafío Spring boot

Content
Architecture
Technologies and tools
Endpoints
Author
License

Architecture
A application is proposed that uses lightweight protocols such as HTTP or REST API .
A service-oriented code architecture is proposed with routes, controllers and validators .
Technologies
[Java 11] - Programming language
[Spring-boot] - Server
[Maven] - Dependency management
[IDEA IntelliJ] - IDE
[GitHub] - Repository
user.json, UserToUser.json, podst.json - Temp Database

Endpoints
US 000	Method	URL	VARIABLES	DESCRIPTION
1	GET	/users/{userId}/follow/{userIdToFollow}"	{userId}(int) identification user. {userIdToFollow}(int) identification user to follow	follow a other user

2	GET	/users/{userID}/followers/count	{userID}(int) indentification user	Get Followers Users Count

3	GET	/users/{userID}/followers/list	{userID}(int) indentification user	Get Followers User List

4	GET	/users/{UserID}/followed/list	{userID}(int) indentification user	User Following List

5	POST	/products/newpost	  {"userId":"100","date": "13-07-2021","detail": {"productName": "Mause Gamer","type": "electronica","brand": "racer","color": "black","notes": "Special Edition"},"category":"120","price": 15000.50 }Create New Post

6	GET	/products/followed/{userId}/list	{userId}(int) identification user	Get Resent(14 days a go) Products User List

7	GET	/users/{userId}/unfollow/{userIdToUnfollow}	{userId}(int) identification user. {userIdToFollow}(int) identification user to unfollow	Unfollow

8	GET	/users/{UserID}/followers/list?order= & /users/{UserID}/followed/list?order=	{UserID}(int) identification user, {order}(String) sorted method	Sort User Followers & Followed 

9	GET	/products/followed/{userId}/list?order=	{userId}(int) identification user, {order}(String) sorted method	Sort User Products by Date (6,12)

10	POST /products/newpromopost	  {"userId":350,"date": "11-07-2021","detail": {"product_id": 1,"productName": "tilla Gamer","type": "Gamer","brand": "racer","color": "black","notes": "Special Edition" },"category":"120", "price": 15000.50,"hasPromo": true,"discount":0.1}	Create New Promo Post

11	GET	/products/{userId}/countPromo/	{userId}(int) identification user	promo post count by user

12	GET	/products/{userId}/list	{userId}(int) identification user promo post list by user

Author
Fiordelmondo Lorena Daniela

License
This project is property of Mercado Libre.
