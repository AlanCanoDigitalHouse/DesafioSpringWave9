# Desafio testing

¡Hola!, él desafió maneja los datos de los barrios en un JSON. 

Los que se cargan por defecto son:
- Barney
- Galicia
- Cano

Si se quiere agregar un barrio nuevo, modificar el archivo `defaultDistricts.json` (en todo el root del proyecto) y reiniciar, el precio del barrio en la DB no se tiene en cuenta, se agrega por ser fiel al modelo.

¡De resto el desafio se encarga de cargar estos datos en la base de datos que es `districts.json` asi que tan solo es ejecutar, ver los test e intentar romperlo!

Nota: para facilitar el mapeo de DTO -> Modelo y Modelo -> DTO, se optó por utilizar la librería DozerMapper, tiene alrededor de 3 años de vida, 1000 estrellas en git por lo que la considero estable y digna de usar :)

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

### 0002: Obtener valor de una propiedad

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

### 0003: Obtener el entorno más grande una propiedad

Endpoint example: POST [localhost:8080/calculator/biggerRoom]()

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

### 0004: Obtener metros cuadrados de cada entorno de una propiedad

Endpoint example: POST [localhost:8080/calculator/metersPerRoom]()

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

