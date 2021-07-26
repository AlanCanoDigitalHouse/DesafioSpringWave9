TU CASITA TRANSACCIONES

---Descripcion del proyecto:---
El proyecto consiste de un unico endpoint que devuelve los datos requeridos, los cuales son:
*Calculo de metros cuadrados de cada habitacion
*Calculo total de metros cuadrados de la casa
*Calculo del precio de la casa
*Habitacion con mayor cantidad de metros cuadrados.

---Endpoint---
La api cuenta con un unico endpoint que cuenta con las siguientes caracteristicas:

**Metodo : POST
**URI: /calculate
**Parametros de entrada: Payload con la siguiente estructura
{"prop_name" : "String",
  "district" : {
    "district_name" : "String",
    "district_price" : double
  },
  "environments" : [ {
    "environment_name" : "String",
    "environment_width" : double,
    "environment_length" : double,
  }]}
 Donde environments es una lista de habitaciones de la casa

**Parametros de salida :
 {"prop_name":"string",
  "district":{
    "district_name":"string"
    "district_price": double
  },
  "environments": [ {
    "environment_name":"String",
    "environment_width": double,
    "environment_length": double,
    "environment_squareMeters": double,
  } ],
  "squareMeters": double,
  "price":149700.0,
  "biggest": {
     "environment_name":"string",
     "environment_width":double,
     "environment_length":double,
     "environment_squareMeters":double
  }


