#Unit and Integration Tests - (Challenge "TuCasita - Tasaciones")

### Endpoint: `POST - /valuation`
- Parameter:\
  PropertyDTO, a property to be evaluated
- Return:\
  ValuationDTO, returns the valuation and the area of the property, the biggest enviroment (name and area), and the 
  details of 
  every 
  environment.
- The endpoint accomplishes the following User Stories:\
      -Total area of the property (US-0001)\
      -Valuation of the property (US-0002)\
      -Biggest environment (US-0003)\
      -Area of every environment (US-0004)
  
- Throws: An exception if the district has not been found in the repository (DistrictException), or a Validation 
  Exception, when a value can't be validated(type, or constraint)
### Request Example (Payload):
```
{
    "prop_name": "Propiedad_1",
    "district": {
        "district_name": "Belgrano",
        "district_price": 1234.0
    },
    "environments": [
        {
            "environment_name": "Bath",
            "environment_width": 20.0,
            "environment_length": 15.0
        },
        {
            "environment_name": "Kitchen",
            "environment_width": 1.0,
            "environment_length": 4.0
        },
        {
            "environment_name": "Master Room",
            "environment_width": 17.0,
            "environment_length": 27.0
        }
    ]
}
```

### Response Example:
```
{
    "property_name": "Propiedad_1",
    "property_area": 763.0,
    "property_valuation": 941542.0,
    "biggest_environment": {
        "environment_name": "Master Room",
        "environment_area": 459.0
    },
    "environmentDetails": [
        {
            "environment_name": "Bath",
            "environment_area": 300.0
        },
        {
            "environment_name": "Kitchen",
            "environment_area": 4.0
        },
        {
            "environment_name": "Master Room",
            "environment_area": 459.0
        }
    ]
}
```

##Tests:
###Unit Test

- ####Repository:
    - districtNotExits(): district no contained in the repository.
    - districtExists(): the district is contained.

- ####Service:
    - biggestEnvironmentTest(): the biggest environment in the property
    - calculatedAreaTest(): area of the property
    - districtInvalidTest(): return a DistrictException, the district isn't contained in the repository (throw 
      exception) 
    - districtTest(): return true, the district it's founded

- ####Controller:
    - areaPropertyTest(): the area of the property
    - areaEnvironmentTest(): a list of all the environment details
    - biggestEnvironmentTest(): the biggest environment on the property
    - valuationPropertyTest(): valuation of the property

- ###Integration Test:
    - propertyValueTest(): valuation of the property (ValuationDTO)
    - invalidDistrictTest(): throw DistrictException, district not founded
    - DTOArgumentTest(): throw a Validation Exception, a value of the payload can't be validate 
    - DTOTypeTest(): throw a Validation Exception, type mismatch on the paylod