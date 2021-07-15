# SocialMeli
Aplicacion práctica para el seguimiento de usuarios y sus respectivas publicaciones.

## Table of Contents
* [Informacion general](#Informacion-general)
* [Componentes](#Componentes)
* [Tecnologias](#Tecnologias)
* [Ejecucion](#Ejecucion)


## Informacion general
La aplicacion permite simular el proceso de seguir usuarios, lo que implica:
- Que un usuario pueda seguir a otro
- Que un usuario pueda dejar de seguir a otro
- Que un usuario pueda consultar su cantidad de seguidores, y la informacion de cada uno de ellos.
- Que un usuario pueda consultar su cantidad de seguidos

Asimismo, esta informacion es almacenada de forma dinámica en la memoria, por lo que los datos solo duran el tiempo de vida de la aplicación, una vez cerrada ésta, se pierde la informacion guardada.
Como contramedida, se genera de manera automática al levantar la aplicacion 3 usuarios, con sus respectivos Ids, y nombres, el resto de la información, seguir usuarios, postear productos, debe ser cargada manualmente.
Se habla unicamente de usuarios porque se considera innecesaria la distincion entre vendedores y compradores.

## Componentes
- Controladores:
	Controlador para apis de productos: postear un producto, consultar posteos de usuarios seguidos.
	Controlador para apis de usuarios: seguis/dejar de seguir usuarios, consultar seguidores/seguidos.
- Servicios:
	Servicio de usuarios: un metodo por cada endpoint de su respectivo controlador, además de un repositorio donde almaceno los usuarios.
	Servicio de productos: un método por cada endpoint de su respectivo controlador, además de un repositorio donde almaceno los productos.
- Modelos: 
	Producto: Almacena su product_id, productName, tipo, mara,color y ,opcionalmente, notas.
	Usuario: Almacena su id, nombre, lista de seguidores, seguidos, y de posts.
- Dtos:
	RequestDto: usado unicamente cuando el usuario postea un producto.
		RequestPostDto: Informacion del posteo, el usuario que lo postea, la fecha de posteo, la categoría, el precio y el objeto mismo que está siendo publicado.
		RequestProductDto: Información del producto posteado, puede ser un producto nuevo o uno cargado previamente, en caso del primero, se lo carga en el momento. Guarda el nombre del producto, su tipo, marca, y color. Básicamente lo mismo que el ProductModel pero sin el id del producto, ya que el usuario al publicarlo no lo tiene.
	ResponseDto: Todos los tipos de respuesta para los distintos metodos de get.
- Handlers: Encargado de realizar funciones estáticas.
	ServiceHandler: inicializa el repositorio de usuarios precargando algunos.
	UserHandler: obtiene la informacion dentro de un usuario y la transformar al formato de Dto de respuesta necesario.
	ProductHandler: Convierte el ProductDto a ProductModel. La diferencia entre ambos radica en que uno tiene id de producto y otro no.





## Tecnologias
- Java 11
- Springboot

## Ejecucion
Abrir el proyecto y correr la clase SocialMeliApplication

