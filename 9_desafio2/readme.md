# Readme

0001: Calcular el total de metros cuadrados de una propiedad
POST localhost:8080/calculate/squareMeters

Payload:
```json
  "prop_name": "SoachaHouse",
  "district": {
    "district_name": "Soacha",
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
```

0002: Indicar el valor de la propiedad a partir de sus ambientes y medidas.
POST localhost:8080/calculate/value

Payload:
```json
  "prop_name": "SoachaHouse",
  "district": {
    "district_name": "Soacha",
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
```

0003: Determinar el entorno mas grande
POST localhost:8080/calculate/value

Payload:
```json
  "prop_name": "SoachaHouse",
  "district": {
    "district_name": "Soacha",
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
```

0004: Determinar los metros cuadrados que tiene cada habiente de cada propiedad
POST localhost:8080/calculate/metersPerRoom

Payload:
```json
  "prop_name": "SoachaHouse",
  "district": {
    "district_name": "Soacha",
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
```
