# Desafío 2 Testing

La API TuCasita permite a sus clientes calcular las diferentes metricas de su propiedad: total metros cuadrados, costo, listado de habitaciones, etc.

## Instalación

1. Clonar el repositorio

```sh
   git clone git@github.com:AlanCanoDigitalHouse/DesafioSpringWave9.git
```

2. Cambiar la rama.

```sh
   git branch Vesga_Edwin
```

3. Ubicarse en la carpeta del proyecto de testing: 9_desafio2/tucasita

4. Ejecutar el proyecto

```sh
   mvn spring-boot:run
```

## API - TuCasita

- US-0001: Calcular el total de metros cuadrados de una propiedad.

```sh
   [POST]: /house/totalSquareMeters
```

- US-0002: Indicar el valor de una propiedad a partir de sus ambientes y medidas. Tener en cuenta que los precios por metro cuadrado están determinados según el barrio.

```sh
   [POST]: /house/price
```

- US-0003: Determinar cuál es el ambiente más grande.

```sh
   [POST]: /house/biggestRoom
```

- US-0004: Determinar la cantidad de metros cuadrados que tiene cada ambiente de una propiedad.

```sh
   [POST]: /house/roomSizes
```

### Payload

El payload para todos los métodos definidos en la API es el siguiente:

```sh
    {
		"prop_name": "string",
		"prop_district": {
			"district_name": "string",
			"district_price": double
		},
		"prop_environments": [
				{
				"environment_name": "string",
				"environment_width": double,
				"environment_length": double
				},
				...
		]
    }
```

## Tests

Antes de ejecutar los test, se debe agregar los siguientes paquetes dentro de las excepciones del coverage:

```sh
   com.mercadolibre.tucasita.domain.*
   com.mercadolibre.tucasita.dto.*
   com.mercadolibre.tucasita.exception.*
   com.mercadolibre.tucasita.utils.*
   com.mercadolibre.tucasita.util.*
```