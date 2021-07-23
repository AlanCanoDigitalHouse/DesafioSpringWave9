# TuCasitaTasaciones - Challenge 2 - SpringBoot

## Installation
### Step 1
#### Clone the project
### Step 2
#### Wait all the dependencies from maven are downloaded
### Step 3
#### Run the TuCasitaTasacionesApplication

## Function
### This is a such basic API rest developed to put in practise test skills

### EndPoint /calculate : CalculateRestController : Post
#### request body:
#### {
#### "prop_name": "Property Name",
#### "district":
####    {
####    "district_name":"District Name",
####    "district_price" : 1000.0
####    },
#### "environments":
####    [
####        {
####        "environment_name": "Environment Name",
####        "environment_width": 20,
####        "environment_length": 30
####        }
####    ]
#### }

### To be considered:
#### prop_name : every name must begin with Mayus
#### district_name : must exist in the price.json file (data static base)
#### district_price : must be consistent with the district_name and must be <= 4000
#### environment_width :  <= 25
#### environment_length : <= 33
#### * null and blank parameters are not allowed

## Test Cases
### You can find the static data used for testing in:
#### -> resources/static/price.json
#### -> src/test/java/com/tucasitatasaciones/utils/TestGenerator.java : Hardcoded test cases
### You can run the test cases in:
#### -> src/test/java/com/tucasitatasaciones : unit and integration
