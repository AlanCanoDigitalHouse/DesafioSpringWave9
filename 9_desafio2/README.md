# Testing challenge

## About de project

"Tu Casita Tasaciones" is a REST API that provides the functionality to calculate:
property's square feet, each property's environment square feet, and property's price. All based on each environment
dimensions and district where is located.

### Notes

The project comes with in memory loaded information located in the class DistrictRepositoryImpl e.g

```json
{
  "district_name": "Test",
  "district_price": 220
}
```


### API endpoints::

- POST request:
    - /calculate -> Returns HouseResponseEntity with all information.
    - /calculate/property/squareFeet -> Returns property's total square feet
    - /calculate/property/price -> Returns property's price
    - /calculate/property/biggest -> Returns property's biggest environment
    - /calculate/environment -> Returns an environment list and their respective square feet

## Getting starting

### Requirements

* [Maven](https://maven.apache.org/)
* [Java 11](https://www.oracle.com/co/java/technologies/javase-jdk11-downloads.html)
* [Git](https://git-scm.com/)

### Run

`````
mvn package
mvn spring-boot:run
`````

### Usage

Make request from a http agent.

Sample request:

- Example request: post
  ```
  http://localhost:8080/calculate
  ```

Each payload request sould follow the next structure:

```json
{
  "prop_name": "prop_name",
  "district": {
    "district_name": "name",
    "district_price": 0
  },
  "environments": [
    {
      "environment_name": "name",
      "environment_width": 0,
      "environment_length": 0
    },
    {
      "environment_name": "name",
      "environment_width": 0,
      "environment_length": 0
    }
  ]
}
```

## Author

Luis Eduardo Moreno Acevedo
