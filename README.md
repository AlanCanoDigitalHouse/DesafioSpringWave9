# Social Meli  
***
## Tabla de contenidos  

* [Info](#general-info)
* [Setup](#setup)
* [Documentación](#docs)

## Info 
Este proyecto se crea como requisito del desafío práctico de Spring del Módulo 8 de la Wave 9.
Dispone una API para brindar herramientas que permiten a compradores y vendedores 
tener una expercienca innovadora, en donde el lazo que los une es mucho más cercano.

***

## Setup 
Para correr este proyecto es necesario tener el IDE de IntlliJ IDEA instalado, basta con 
clonar este repositorio, abrir el proyecto en el IDE y correr el programa.  
Al iniciar el programa se cargan datos de 2 archivos `.json` para realizar las primeras pruebas 
del programa, se cuenta con 3 usuarios con ids `{4001, 4002, 4003}` 3 vendedores 
con ids `{1001, 1002, 1003}` y las siguientes relaciones:  
* El usuario con `id = 4001` sigue a los vendedores con `id = {1001, 1002}`
* El usuario con `id = 4002` sigue al vendedor con `id = 1001`
* El usuario con `id = 4003` sigue al vendedor con `id = 1001`

***

## Documentación
La especificación de los endpoints viene dada en la siguiente [Documentación](https://drive.google.com/uc?export=download&id=1iPdb8VVgxi4SZtWNqwHo_lo-quODgi3i).  
La arquitectura del proyecto esta dada en 2 controladores y 2 servicios, uno para cada controlador,
teniendo estos la responsabilidad de administrar los productos y los usuarios. En caso de que se desee
realizar una modificación a una funcionalidad existente, solo será necesario modificar el código de las
clases que implementan los servicios. Y en caso de querer agregar una nueva funcionalidad,
deberá agregarse la ruta del endpoint al controlador correspondiente, crear la firma del método en
la interfaz de servicio y luego implementarla en la clase.

