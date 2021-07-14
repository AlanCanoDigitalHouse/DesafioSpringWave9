## Table of contents
* [General info](#general-info)
* [Technologies](#technologies)
* [Setup](#setup)

## General info
This project is the first challenge of the meli bootcamp 2021. The knowledge of JAVA and SpringBoot seen to date were integrated.
	
## Technologies
Project is created with:
* Java version: 11.0.11
	
## Setup
This API has an endpoint to pre-load the BDD, which in this case will be saved in memory.

- First, call the endpoint "localhost:8080/init-bdd" which will return a status 200.
- This will load 5 users with the following data:
                                                - userId: 1, userName:"Comprador 1"
                                                - userId: 2, userName:"Comprador 2"
                                                - userId: 3, userName:"Comprador 3"
                                                - userId: 4, userName:"Vendedor 1"
                                                - userId: 5, userName:"Vendedor 2"

- After this, the endpoints of the documentation can be called quietly.


