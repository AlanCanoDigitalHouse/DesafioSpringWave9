## Desafio de Testing
###TuCasita - Tasaciones

This is an API-REST that allows you to calculate square meters and price from  initial information about a house.

### Requirements
Calculate:
* Total square meters
* Price
* Biggest environment
* Square meters for each environment.
~~~http
POST http://localhost:8080/calculate
~~~
You will need this payload:

~~~JSON
{
"prop_name": "Casita",
"district_name" :"palermo",
"district_price":3900,
"environments":[
{
"environment_name":"Estar",
"environment_length":3,
"environment_width":7
},
{
"environment_name":"Cocina",
"environment_length":3,
"environment_width":8
},
{
"environment_name":"Dormitorio 1",
"environment_length":3,
"environment_width":9
},
{
"environment_name":"Dormitorio 2",
"environment_length":3,
"environment_width":10
}
]
}
~~~
#### Important: 
As part of the payload is contrasted against the "database" (district.json), to see the expected result you should use any of the following pair of distrct_name/district_price
* La plata / 200
* Campana /800
* Mar del Plata / 1000
* Palermo / 3900
