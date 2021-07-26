# Challenge Spring Boot 2: Test “TuCasita Tasaciones”

In this challenge, the unit tests and integration are implemented for 
a rest api created in spring boot that, through data from a house, 
gives you information on total square meters, larger room, 
total cost of ownership and square meters per room.

## Running the application locally

There are several ways to run a Spring Boot application on your local machine. One way is to execute the `main` method in the `CalculadoraMetrosCuadradosApplication.java` class from your IDE.

Alternatively you can use the [Spring Boot Maven plugin](https://docs.spring.io/spring-boot/docs/current/reference/html/build-tool-plugins-maven-plugin.html) like so:

```shell
mvn spring-boot:run
```

## Api Information

This api contains an endpoint to perform the information calculation.
```shell
METHOD: POST
ENDPOINT: /calculate
PYLOAD: 
{
    "prop_name" : "Casa de las flores",
    "district_name": "Santiago",
    "district_price":3000.0,
    "enviroments": [
        {
            "enviroment_name": "Cuarto amarillo",
            "enviroment_width": 4,
            "enviroment_length": 7
        },
        {
            "enviroment_name": "Cuarto",
            "enviroment_width": 2,
            "enviroment_length": 4
        }
    ]
}
```

```shell
RESPONSE:
{
    "prop_name": "Casa de las flores",
    "district_name": "Santiago",
    "district_price": 3000.0,
    "enviroments": [
        {
            "enviroment_name": "Cuarto amarillo",
            "enviroment_width": 4,
            "enviroment_length": 7,
            "squareFeet": 28
        },
        {
            "enviroment_name": "Cuarto",
            "enviroment_width": 2,
            "enviroment_length": 4,
            "squareFeet": 8
        }
    ],
    "squareFeet": 36,
    "price": 108000.0,
    "biggest": {
        "enviroment_name": "Cuarto amarillo",
        "enviroment_width": 4,
        "enviroment_length": 7,
        "squareFeet": 28
    }
}
```
## Test Information

The tests are developed in the path: `src/test/java/com/mercadolibre/calculadorametroscuadrados`

Includes unit tests and integration tests in their respective folders

To run command test:
```shell
mvn test
```

## Information to consider

This api use static information in repository for simulate a database 
that object of activity is make Test and not implement crud repository.

```shell
this.districtPrice = new HashMap<>();
this.districtPrice.put("Santiago", 3000.0);
this.districtPrice.put("Las Condes", 4000.0);
this.districtPrice.put("Providencia", 3500.0);
```





