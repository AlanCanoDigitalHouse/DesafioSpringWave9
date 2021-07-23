# Desafio testing

## Requerimientos

### 0001: Obtener metros cuadrados de una propiedad

Endpoint example: POST [localhost:8080/calculator/squareMeters]()

Payload:

````json
{
  "prop_name": "Galantis",
  "district": {
    "district_name": "Galicia",
    "district_price": 100
  },
  "environments": [
    {
      "environment_name": "Bathroom",
      "environment_width": 10,
      "environment_length": 10
    }
  ]
}
````

¿Que se testeo?
- Prueba unitaria del controlador, entradas, salidas, excepciones y códigos http
- Prueba unitaria del servicio, validación de que el cálculo estuviese correcto
- Prueba de integracion con validacion de entradas y excepciones

Excepciones esperadas:
- Atributo faltante
- Primera letra en mayúscula para prop_name y environment_name
- Exceder longitud maxima

### 0001: Obtener metros cuadrados de una propiedad

Endpoint example: POST [localhost:8080/calculator/value]()

Payload:

````json
{
  "prop_name": "Galantis",
  "district": {
    "district_name": "Galicia",
    "district_price": 100
  },
  "environments": [
    {
      "environment_name": "Bathroom",
      "environment_width": 10,
      "environment_length": 10
    }
  ]
}
````

¿Que se testeo?
- Prueba unitaria del controlador, salidas y códigos http
- Prueba unitaria del servicio, validación de que el cálculo estuviese correcto
- Prueba de integracion con validación de entradas y excepciones

Excepciones esperadas:
- Atributo faltante
- Primera letra en mayúscula para prop_name y environment_name
- Exceder longitud maxima

