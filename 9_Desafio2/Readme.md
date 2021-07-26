# Desafío Testing - Bootcamp MeLi

_Desarrollo de las consignas designadas para el segundo trabajo de integración de conceptos en el IT Bootcamp 2021 - Wave 9._

## Comenzando 🚀

_Para poder usar los endpoints del proyecto, primero vamos a necesitar cargar algunos datos de prueba en nuestro repositorio interno. Para esto vamos a usar el endpoint:_



```
localhost:8085/challenge/start
```
Notese que en este ejemplo usamos el puerto 8085. Obtendremos como salida la confirmación de los barrios cargados.
```
Barrios cargados: Centro, Chacarita, Valle Chico, Villa Reyes y Terrazas del Valle.
```



## Activando los endpoints 🤖

_Todos los endpoints del proyecto se encuentran dentro del package **controllers**. Se adopto la estrategia de generar un endpoint por historia de usuario en función del enunciado._

### US-0001: Calcular el total de metros cuadrados de una propiedad

```
/challenge/calculate-area
```
Payload de prueba

```
{
    "prop_name" : "Casita",
    "district_name" : "Centro",
    "rooms" : [
        {
            "environment_name" : "Sala",
            "environment_width" : 15,
            "environment_length" : 20
        },
        {
            "environment_name" : "Cocina",
            "environment_width" : 8,
            "environment_length" : 10
        }
    ]
}
```
Salida 📝
```
{
    "prop_name": "Casita",
    "area": 380
}
```

### US-0002: Indicar el valor de una propiedad a partir de sus ambientes y medidas.

```
/challenge/house-price
```
Payload de prueba

```
{
    "prop_name" : "Casita",
    "district_name" : "centro",
    "district_price" : 1800.0,
    "rooms" : [
        {
            "environment_name" : "Sala",
            "environment_width" : 15,
            "environment_length" : 20
        },
        {
            "environment_name" : "Cocina",
            "environment_width" : 8,
            "environment_length" : 10
        }
    ]
}
```
Salida 📝
```
{
    "prop_name": "Casita",
    "price": 494000.0
}
```


### US-0003: Determinar cuál es el ambiente más grande.

```
/challenge/biggest-room
```
Payload de prueba

```
{
    "prop_name" : "Casita",
    "district_name" : "Centro",
    "rooms" : [
        {
            "environment_name" : "Sala",
            "environment_width" : 15,
            "environment_length" : 20
        },
        {
            "environment_name" : "Cocina",
            "environment_width" : 8,
            "environment_length" : 10
        },
        {
            "environment_name" : "Garage",
            "environment_width" : 18,
            "environment_length" : 30
        }
    ]
}
```
Salida 📝
```
{
    "environment_name": "Garage",
    "environment_area": 540.0
}
```

### US-0004: Determinar la cantidad de metros cuadrados que tiene cada ambiente de una propiedad.

```
/challenge/count-size
```
Payload de prueba

```
{
    "prop_name" : "Casita",
    "district_name" : "Centro",
    "rooms" : [
        {
            "environment_name" : "Sala",
            "environment_width" : 15,
            "environment_length" : 20
        },
        {
            "environment_name" : "Cocina",
            "environment_width" : 8,
            "environment_length" : 10
        },
        {
            "environment_name" : "Garage",
            "environment_width" : 18,
            "environment_length" : 30
        }
    ]
}
```
Salida 📝
```
{
    "prop_name": "Casita",
    "environment_sizes": [
        {
            "environment_name": "Sala",
            "size": 300.0
        },
        {
            "environment_name": "Cocina",
            "size": 80.0
        },
        {
            "environment_name": "Garage",
            "size": 540.0
        }
    ]
}
```

## Validaciones, excepciones y cobertura
Para este proyecto se siguen las validaciones propuestas por el documento de **Requerimientos técnicos funcionales** sobre cada uno de los DTO que ingresan datos al sistema.
Se contemplan dos excepciones personalizadas _DisctrictNotFoundException_ y _NothingToCalculateException_, ambas disparadas en tiempo de ejecución (RuntimeException). En cuanto a la cobertura del proyecto, se priorizo el _package service_, 
el _package repositories_ y _CalculateRestController_. Se excluye a modelos, excepciones y DTOS sin lógica interna.

## Made with ♥️ by️

* **Julián Acosta Argañaraz** - *Ingeniero Informático* 