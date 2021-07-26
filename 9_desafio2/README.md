# DesafioTesting

El objetivo de este desafío es aplicar los contenidos abordados hasta el momento durante el
BOOTCAMP MeLi (Git, Java, Spring y Testing), haciendo principal hincapié en las validaciones
y tipos de testing que pueden ser utilizados a partir de un enunciado propuesto, una
especificación de requisitos y documentación anexada.

# Descripción

Una empresa constructora necesita desarrollar un API que reciba los datos de una Propiedad Inmobiliaria con su *nombre*, barrio y sus *habitaciones*. Cada una de las habitaciones, al mismo tiempo, contienen un *nombre*, un *ancho* y un *largo*.

La empresa necesita que la API sea capaz de:
- Retornar un objeto que indique la cantidad total de metros cuadrados de la propiedad.
- Indicar el valor de la propiedad en base al valor del metro cuadrado del barrio.
- Retornar los datos de la habitación más grande.
- Calcular y retornar la cantidad de metros cuadrados por habitación.


# Instalación

Simplemente clone el repositorio y vaya a mi branch **hernandez_emilio** y entre al directorio **9_desafio2**

Abra el proyecto en Intellij de *Run* en la clase main *DesafioDosWave9Application.java*
# Uso

| METHOD | URI |
| ----------- | ----------- | 
| POST | /metroscuadrados |

`paylod body`

```json
{
  "prop_name": "Casa",
  "district_name": "Palermo",
  "district_price": 1000.0,
  "environments": [
    {
      "environment_name": "Sala",
      "environment_width": 2.0,
      "environment_length": 2.0
    },
    {
      "environment_name": "Comedor",
      "environment_width": 3.0,
      "environment_length": 2.0
    },
    {
      "environment_name": "Recamara",
      "environment_width": 2.0,
      "environment_length": 4.0
    }
  ]
}
```
Los barrios validos son:
| BARRIO | PRECIO |
| ------ | ------ | 
| Palermo | 1000.0 |
| Belgrano | 1100.0 |
| Recoleta | 900.0 |
| Puerto Madero | 2000.0 |
| Agronomia | 1530.0 |
| Balvanera | 2850.0 |

# Autores
Emilio Hernandez Segovia
Software Developer
Estoy en github como [embucketsmeli][github]

[github]: <https://github.com/embucketsmeli>