# TuCasita Tasaciones Documentation

### Reference Documentation

El desarrollo de esta api se basa en la siguiente especificacion de requerimientos:

* [Especif. Téc. Desafío Testing.docx](https://drive.google.com/file/d/1Vl7nqxJvrIVwbuipuX8sFnEZSJiuaMJu/view)

### Guia inicial

Especificación de uso de la aplicacion Social Meli:

* Se encuentran precargados un 4 districtos validos (Palermo,Belgrano,Recoleta,Puerto Madero).
* El coverage obtenido es del 93% de las lineas.

## Documentación funcionamiento

### Tabla de contenidos:
* US-0001 - US-0002 - US-0003 - US-0004 



### US-0001 - US-0002 - US-0003 - US-0004:
El endpoind **/calculate** se encuentra en la clase CalculateRestController.<br>
Se esperan el siguientes RequestBody {HouseDTO}.
<br>Se adjunta PayLoad de ejemplo:
<pre>
{
    "prop_name": "House",
    "district_name": "Palermo",
    "district_price": 200,
    "environment" : [
        {
            "name": "Comedor",
            "width": 10,
            "length": 5
        },
        {
            "name": "Baño",
            "width": 8,
            "length": 5
        }
    ]
}
</pre>
