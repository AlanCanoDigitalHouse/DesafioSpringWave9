# Challenge Unit Test and Integration Tests - TuCasitaTasaciones
It has one endpoint which has "total metros cuadrados in the property", "total price of the property", "metros cuadrados of every enviroment" and the "biggest enviroment".

### Endpoint: `POST - /property/calculate`
- Tasks of the endpoint:
  - US-0001: Calculate the total metros cuadrados in the property.
  - US-0002: Calculate the amount of the property, the price of metro cuadrado is determinate where the property is located. 
  - US-0003: Returns the biggest environment. 
  - US-0004: Returns each metros cuadrados in every environment of the property..
- Params: property – is a PropertyDTO
- Returns: ResponseEntity "PropertyResponseDTO"
- Throws: DistrictException – throws and exception if the district is not in the data base.
### Request Body Example: 
```
{
	"prop_name": "Dinamo",
	"district": {
		"district_name": "Madero",
		"district_price": 2500
	},
	"enviroments": [
		{
		"enviroment_name": "Bathroom",
		"enviroment_width": 25,
		"enviroment_length": 33
		},
		{
		"enviroment_name": "Living Room",
		"enviroment_width": 10,
		"enviroment_length": 11
		}
	]
}
```

### Response Body Example: 
```
{
    "totalMts2": 935,
    "totalPrice": 2337500,
    "biggestEnviroment": {
        "enviroment_name": "Bathroom",
        "enviroment_width": 25,
        "enviroment_length": 33,
        "enviroment_mts2": 825
    },
    "enviroments": [
        {
            "enviroment_name": "Bathroom",
            "enviroment_width": 25,
            "enviroment_length": 33,
            "enviroment_mts2": 825
        },
        {
            "enviroment_name": "Living Room",
            "enviroment_width": 10,
            "enviroment_length": 11,
            "enviroment_mts2": 110
        }
    ]
}
```

## Unit Tests:

- ### Unit Test Repository:
  - DistrictExists(): The district exists in the data base (return True).
  - DistrictDoesntExists(): The district doesnt exist in the data base (return False).

- ### Unit Test Service:
  - districtNotFoundException(): Throws a DistrictException if the district is not in the database (returns throws DistrictException).
  - districtCalculate(): Returns a PropertyResponseDTO, the calculation is OK.
  - totalMts2IsOk(): The totalMts2 is OK, and returns a PropertyResponseDTO.
  - biggestEnviromentIsOk(): The biggest enviroment is OK, and returns a PropertyResponseDTO.
  - enviromentMts2IsOk(): mts2 per enviroment is OK, and returns a PropertyResponseDTO.

- ### Unit Test Controller:
  - calculateProperty(): Calculate the property, and returns a PropertyResponseDTO which is OK.
  - calculatePropertyDistrictException(): Returns DistrictException, the district is not in the database.

## Integration Test:

- ### calculatePropertyNameFirstLetterLowercase() `POST - /property/calculate`:
  - Returns a Validation Exception.
  - The first letter of name in prop_name is lower case and should be upper case.

- ### calculatePropertyDistrictException() `POST - /property/calculate`:
  - Returns a DistrictException.
  - The district is not in the data base.
  
- ### calculatePropertyIsOk() `POST - /property/calculate`:
  - Returns PropertyResponseDTO.
  - The calculation is OK.
  
- ### calculatePropertyEmptyEnviroments() `POST - /property/calculate`:
  - Returns ValidationError.
  - The property must have 1 enviroment.

- ### calculateProperty0PricePerMts2() `POST - /property/calculate`:
  - Returns ValidationError.
  - The price of mts2 must be uppon 0U$S.

- ### calculatePropertyLengthEnviromentUpon33mts() `POST - /property/calculate`:
  - Returns ValidationError.
  - The length must be less than 33mts.

- ### calculatePropertyWidthEnviromentUpon25mts() `POST - /property/calculate`:
  - Returns ValidationError.
  - The width must be less than 25mts.

