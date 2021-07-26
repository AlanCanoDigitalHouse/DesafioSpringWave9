# DesafioSpringWave9
## TuCasita

En este repositorio se encuentra el proyecto del desafio, contiene un respositorio mediante archivo en Json. El archivos Json es distritos.json. Este se encuentran en la ruta resources/static. 

Para el coverage se debe ignorar las clases ResponseErrorDTO, DataBaseException y CasaException ya que el IDE solicita cubrir lineas de casos externos al coverage del codigo 

A continuacion se presentan los metodos que que se desarrollaron para cumplir con el objetivo de cada Historia de Usuario.


|URI| Sign|
| ------------- | ------------- |
| /casa/calcular |POST|

### US 0001: 
**Descripcion:** Calcular el total de metros cuadrados de una propiedad.


|Metodo | Descripción |
| ------------- | ------------- |
| **CasaService.obtenerTamanoCasa()**  | Metodo para la llamada del servicio que se encarga de realizar la funcion de "Follow" hacia un determinado vendedor|


### US 0002: 
**Descripcion:** Indicar el valor de una propiedad a partir de sus ambientes y medidas. Tener en cuenta que los precios por metro cuadrado están determinados según el barrio.

|Metodo | Descripción |
| ------------- | ------------- |
| **CasaService.calcularMetroPropiedad()**  | Metodo para hacer el calcular el tamaño de la casa|
| **CasaRepository.obtenerDistrito(String districtName)**  | Metodo para obtener la informacion de el distrito|


### US 0003: 
**Descripcion:** Determinar cuál es el ambiente más grande.

|Metodo | Descripción |
| ------------- | ------------- |
| **CasaService.obtenerHabitacionMasGrande()**  | Metodo para obtener el ambiente mas grande|

### US 0004
**Descripcion:** Determinar la cantidad de metros cuadrados que tiene cada ambiente de una propiedad.

|Metodo | Descripción |
| ------------- | ------------- |
| **CasaService.calcularAreaHabitaciones()**  | Metodo para determinar la cantidad de metros cuadrados de cada ambiente|
