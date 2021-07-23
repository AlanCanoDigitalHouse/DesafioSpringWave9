# Spring test challenge

## Clone Repository

Execute from console

```bash
git clone git@github.com:AlanCanoDigitalHouse/DesafioSpringWave9.git
git checkout Barreto_Lucas
```

## What to exclude from testing

Nothing to exlude, include all files

## Test utils generator

Util class that provides some data manipulation methods to make tests easier to write Some function of this file are:

- getDistrictsSet(): Generates a list of valid districts
- fillDistrictsFile(): Fills the districts.json file with valid districts
- corruptDistrictsFile(): Writes a random json string into the districts.json file in order to force the exception
  throwing in district repository method mapObject

## Usage

### POST http://localhost:8080/calculate

Calculate the square metters, biggest environment and price of the property sent as parameter

- Params: propertyDTO
- Returns: PropertyResponseDTO
- Throws: Exception

### Payload structure

```JSON
{
  "type": "object",
  "properties": {
    "prop_name": {
      "type": "string",
      "description": "Property name, must start with an uppercase character",
      "pattern": "^\\p{Lu}.*$"
    },
    "district": {
      "type": "object",
      "properties": {
        "district_name": {
          "type": "string",
          "description": "District name"
        },
        "district_price": {
          "type": "number",
          "description": "District price ($) can't be greater than 4000",
          "minimum": 0.0,
          "maximum": 4000.0
        }
      },
      "description": "",
      "required": [
        "district_name",
        "district_price"
      ]
    },
    "environments": {
      "type": "array",
      "items": {
        "type": "object",
        "properties": {
          "environment_name": {
            "type": "string",
            "description": "Environment name, must start with uppercase",
            "pattern": "^\\p{Lu}.*$"
          },
          "environment_width": {
            "type": "number",
            "description": "Environment width, can't be larger than 25.0",
            "minimum": 0.0,
            "maximum": 25.0,
            "exclusiveMinimum": true
          },
          "environment_length": {
            "type": "number",
            "description": "Environment length, can't be larger than 33.0",
            "minimum": 0.0,
            "maximum": 33.0,
            "exclusiveMinimum": true
          }
        },
        "required": [
          "environment_name",
          "environment_width",
          "environment_length"
        ]
      }
    }
  },
  "required": [
    "prop_name"
  ]
}
```

### Payload example

```JSON
{
  "prop_name": "The Dark Tower",
  "district": {
    "district_name": "Belgrano",
    "district_price": "1100"
  },
  "environments": [
    {
      "environment_name": "Bathroom",
      "environment_width": "5",
      "environment_length": "10"
    },
    {
      "environment_name": "Living Room",
      "environment_width": "7",
      "environment_length": "5.3"
    },
    {
      "environment_name": "Kitchen",
      "environment_width": "3.1",
      "environment_length": "7"
    },
    {
      "environment_name": "Bedroom",
      "environment_width": "4",
      "environment_length": "4"
    }
  ]
}
```

### Response structure

```JSON
{
  "type": "object",
  "properties": {
    "prop_name": {
      "type": "string",
      "description": "Property name"
    },
    "district": {
      "type": "object",
      "properties": {
        "district_name": {
          "type": "string",
          "description": "District name"
        },
        "district_price": {
          "type": "number",
          "description": "District price"
        }
      }
    },
    "environments": {
      "type": "array",
      "items": {
        "type": "object",
        "properties": {
          "environment_name": {
            "type": "string",
            "description": "Environment name"
          },
          "environment_width": {
            "type": "number",
            "description": "Environment width"
          },
          "environment_length": {
            "type": "number",
            "description": "Environment length"
          }
        }
      },
      "description": "Environments"
    },
    "squareMetter": {
      "type": "number",
      "description": "Square metters of the property"
    },
    "price": {
      "type": "number",
      "description": "Property price"
    },
    "biggest": {
      "type": "object",
      "properties": {
        "environment_name": {
          "type": "string",
          "description": "Property biggest room"
        },
        "environment_width": {
          "type": "number",
          "description": "Biggest room width"
        },
        "environment_length": {
          "type": "number",
          "description": "Biggest room length"
        }
      }
    }
  }
}
```

### Response example

```JSON
{
  "prop_name": "The Dark Tower",
  "district": {
    "district_name": "Belgrano",
    "district_price": 1100.0
  },
  "environments": [
    {
      "environment_name": "Bathroom",
      "environment_width": 5.0,
      "environment_length": 10.0,
      "squareMetter": 50.0
    },
    {
      "environment_name": "Living Room",
      "environment_width": 7.0,
      "environment_length": 5.3,
      "squareMetter": 37.1
    },
    {
      "environment_name": "Kitchen",
      "environment_width": 3.1,
      "environment_length": 7.0,
      "squareMetter": 21.7
    },
    {
      "environment_name": "Bedroom",
      "environment_width": 4.0,
      "environment_length": 4.0,
      "squareMetter": 16.0
    }
  ],
  "squareMetter": 124.8,
  "price": 137280.0,
  "biggest": {
    "environment_name": "Bathroom",
    "environment_width": 5.0,
    "environment_length": 10.0,
    "squareMetter": 50.0
  }
}
```

Pumba!!