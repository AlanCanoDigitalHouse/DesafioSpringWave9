TuCasita Tasaciones
=======================================

Testing Challenge for MercadoLibre Backend Bootcamp

## About

- Java application made with Springboot using JDK11.
- The project comes with static json files for data persistence that contains some
districts to get prices from.
- Unit tests made with JUnit 5.
- Integration tests made with MockMvc.
- Mocks made using Mockito.

## Setup

- Make sure to have JDK11 installed
- This project has some dependencies managed using Maven so make sure everything is OK before compiling
- Data persistence is made with static JSON files that are part of the project. Make sure they all are part of your
  compiled version

## Interface

The project was made
following [technical requirements](https://drive.google.com/file/d/1Vl7nqxJvrIVwbuipuX8sFnEZSJiuaMJu/view)
so everything should work as stated there.

There are only a few valid districts available:

```
[
  {
    "district_name": "Palermo",
    "district_price": 100.0
  },
  {
    "district_name": "Avellaneda",
    "district_price": 70.0
  },
  {
    "district_name": "Belgrano",
    "district_price": 90.0
  }
]
```

API Endpoints available:

All endpoints expect the same payload in the request body:

```JSON
{
    "prop_name": "Casa",
    "prop_district": {
        "district_name": "Palermo",
        "district_price": 100.0
    },
    "prop_environments": [
        {
            "environment_name": "Cocina",
            "environment_width": 10.0,
            "environment_length": 5.0
        },
        {
            "environment_name": "Baño",
            "environment_width": 5.0,
            "environment_length": 5.0
        }
    ]
}
```

### Calculate property total area:
```
POST localhost:8080/property/totalArea
```

Response example:
```JSON
{
  "prop_name": "Casa",
  "prop_total_area": 75.0
}
```

### Valuate property:
```
POST localhost:8080/property/valuation
```

Response example:
```JSON
{
  "prop_name": "Casa",
  "prop_value": 7500.0
}
```

### Determine property largest environment:
```
POST localhost:8080/property/environment/largest
```

Response example:
```JSON
{
  "prop_name": "Casa",
  "prop_largest_environment": {
    "environment_name": "Cocina",
    "environment_area": 50.0
  }
}
```

### Calculate property's environment's areas:
```
POST localhost:8080/property/environment/area
```

Response example:
```JSON
{
  "prop_name": "Casa",
  "property_environments": [
    {
      "environment_name": "Cocina",
      "environment_area": 50.0
    },
    {
      "environment_name": "Baño",
      "environment_area": 25.0
    }
  ]
}
```