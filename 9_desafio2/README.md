# Bienvenid@ a mi propuesta de solución del segundo desafío 
# El proyecto

##  1. EndPoint

El primer y único endPoint que se debe enviar es el siguiente

    http://localhost:8080/calculate

**JSON de  ejemplo**
```json
{
    "prop_name": "Casa Bonita",
    "district_name" : "Bosa",
    "district_price" : 500,
    "environments":[
        {
        "evironment_name": "Habitacion P",
        "environment_width":20,
        "environment_length":27
        

    }, {
        "evironment_name":"Comedor",
        "environment_width":12,
        "environment_length":34

    }, {
        "evironment_name":"Patio de ropas",
        "environment_width":10,
        "environment_length":34

    }]

}
```
**Expected response**
```json
{
    "prop_name": "Casa Bonita",
    "district": {
        "name": "Bosa",
        "price": 500.0
    },
    "square_meter": 1823.0,
    "property_value": 911500.0,
    "larger_environment": "Patio de ropas",
    "meters_x_environment": {
        "Comedor": 408.0,
        "Patio de ropas": 875.0,
        "Habitacion P": 540.0
    }
}
```
##  2. Testing

Los paquetes y clases que se incluyeron en los datos de coverage son los que se encuentran en: 
 - controller
 - repository
 - services
>Cualquier otro se excluyo.

**Table Coverage**

100% classes, 89% lines

| Element    | Class %    | Method %   | Line %      |
|------------|------------|------------|-------------|
| controller | 100% (1/1) | 100% (2/2) | 100% (4/4)  |
| repository | 100% (2/2) | 100% (5/5) | 78% (15/19) |
| services   | 100% (1/1) | 100% (7/7) | 93% (31/33) |


**Designed by:**  Karen Juliana Celis Buitrago



