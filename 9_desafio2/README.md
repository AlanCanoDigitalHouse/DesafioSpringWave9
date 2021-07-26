# Bootcamp MELI
## _Desafio 2 - Testing_

### Contenido
* Introducción
* Requerimientos
* Especificaciones Técnicas
* Ejecución
* Recomendaciones


### Introducción
El objetivo de este desafío es aplicar los contenidos abordados hasta el momento durante el BOOTCAMP MeLi (Git, Java, Spring y Testing), haciendo principal hincapié en las validaciones y tipos de testing que pueden ser utilizados a partir de un enunciado propuesto, una especificación de requisitos y documentación anexada.


### Requerimientos
La empresa “TuCasita Tasaciones” necesita poder calcular los metros cuadrados y el costo de distintas propiedades con las que cuenta en su cartera de clientes.

Para ello, solicita por cada propiedad: un nombre, un barrio y la cantidad de ambientes que posee; al mismo tiempo, cada ambiente contiene un nombre, un ancho y un largo.

A partir de estos datos, se solicita el desarrollo de una API que sea capaz de:

> **US-0001:** Calcular el total de metros cuadrados de una propiedad
> **US-0002:** Indicar el valor de una propiedad a partir de sus ambientes y medidas. Tener en cuenta que los precios por metro cuadrado están determinados según el barrio.
> **US-0003:** Determinar cuál es el ambiente más grande.
> **US-0004:** Determinar la cantidad de metros cuadrados que tiene cada ambiente de una propiedad.

### Especificaciones Técnicas
El sistema cuenta con cuatro end-points de tipo POST listados a continuación:
| CASO DE USO | URL |
| ------ | ------ |
| US-0001 | [localhost:8080/house/size][PlDb] |
| US-0002 | [localhost:8080/house/value][PlGh] |
| US-0003 | [localhost:8080/house/biggest-room][PlGd] |
| US-0004 | [localhost:8080/house/rooms-size][PlOd] |

Para practicidad, se recomienda importar una colección a Postman, la cual contiene los end-points con los payloads correspondientes. Mismos que pueden ser modificados de acuerdo a las pruebas que se requieran realizar. Esta colección se encuentra en la raíz del proyecto, y en el siguiente link

[Postman Collection Testing](https://www.getpostman.com/collections/48c01cd0bd2b9ce6fcb2)

### Ejecución
Para ejecutar las pruebas unitarias, se deben seguir los siguientes pasos:

1. Posicionarse en la siguiente ruta, dar clic derecho y seleccionar Modify run configuration, antes de ejecutar las pruebas con coverage:
    [DesafioSpringWave9/9_desafio2/RoomsPractice/src/test/java/com/example/demo/unit][PlDb]
2. En la nueva ventana que se abre, en la parte de Code Coverage, agregar **com-example.demo.***, y excluir **com-example.demo.dtos** & **com-example.demo.exceptions** packages
3. Una vez agregados, dar clic en aceptar, y posteriormente en OK.
4. Ya que se configuro el alcance del coverage, ejecutar las pruebas desde el package unit (Clic derecho > Run with Coverage)
    [DesafioSpringWave9/9_desafio2/RoomsPractice/src/test/java/com/example/demo/unit][PlDb]

   [dill]: <https://github.com/joemccann/dillinger>
   [git-repo-url]: <https://github.com/joemccann/dillinger.git>
   [john gruber]: <http://daringfireball.net>
   [df1]: <http://daringfireball.net/projects/markdown/>
   [markdown-it]: <https://github.com/markdown-it/markdown-it>
   [Ace Editor]: <http://ace.ajax.org>
   [node.js]: <http://nodejs.org>
   [Twitter Bootstrap]: <http://twitter.github.com/bootstrap/>
   [jQuery]: <http://jquery.com>
   [@tjholowaychuk]: <http://twitter.com/tjholowaychuk>
   [express]: <http://expressjs.com>
   [AngularJS]: <http://angularjs.org>
   [Gulp]: <http://gulpjs.com>

   [PlDb]: <https://github.com/joemccann/dillinger/tree/master/plugins/dropbox/README.md>
   [PlGh]: <https://github.com/joemccann/dillinger/tree/master/plugins/github/README.md>
   [PlGd]: <https://github.com/joemccann/dillinger/tree/master/plugins/googledrive/README.md>
   [PlOd]: <https://github.com/joemccann/dillinger/tree/master/plugins/onedrive/README.md>
   [PlMe]: <https://github.com/joemccann/dillinger/tree/master/plugins/medium/README.md>
   [PlGa]: <https://github.com/RahulHP/dillinger/blob/master/plugins/googleanalytics/README.md>
