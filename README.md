# SocialMeli - Challenge 1 - SpringBoot

## Installation
### Step 1
1 - Clone the repository 
### Step 2
2 - This project use as build atomization tool, wait all the dependencies are downloaded
### Step 3
3 - Look at the Application is configured with JAVA 11
### Step 4
4 - Run the "SocialmeliApplication" application

## How to use the API

#### Package - controllers
#### UserController
Manage all the routes that begins with "/users". These are the principal actions for user entities requests.
#### PostController
Manage all the routes that begins with "/products". These are the principal actions for post entities requests.
### Package - DTO
Here you can find the Data Transfer Object used for particular requests and responses
### Package - entities
Here you can find the main entities from the API
### Package - global constants
Reference : used for CONSTANTS as Paths directories, orders filters and more / Message: used for generals messages as errors o informative staff.
### Package - repositories
#### UserRepository
Manage the data related with the User Entity. 
#### PostRepository
Manage the data related with the Post Entity.
### Package - services
#### UserService
Take action depend on the request from the client, this response to the UserController
#### PostService
Take action depend on the request from the client, this response to the PostController
### Package - utils
#### SortUtil
Utilities and tools, SortUtil: sort a collection (ASC or DESC).
#### DateValidator
DateValidator: manage dates, validating if is correct in the calendar.
#### IDGenerator 
Using an AtomicInteger can generate IDs for the different entities
### Package - JSONDB - Data Base
#### post.json
Storage the post objects entity
#### post_id_generator.json
Storage a "seed" for generate the IDs for Post entity
#### product_id_generator.json
Storage a "seed" for generate the IDs for Product entity
#### user.json
Storage the user objects entity
#### user_id_generator.json
Storage a "seed" for generate the IDs for User entity

### Help
#### For particular questions please contact: agustin.gonzales@mercadolibre.com