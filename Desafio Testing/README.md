<h1>Testing Challenge</h1>

La aplicación es el backend de un servicio de calculo de precio de propiedades. 
El usuario envia la informacion de localizacion y dimensiones de su propiedad, y el servicio
devuelve el calculo del precio principalmente, entre otras caracteristicas.

Los datos precargados son:

<ul>
    <li>Nombre de barrios.</li>
</ul>

El servicio verifica la existencia del barrio en la base de datos, por lo que es necesario
tenerlos de antemano, siendo estos:

<ol>
    <li>Alamos Norte Alamos Norte Alamos Norte Alamos</li>
    <li>San Marcos</li>
    <li>Villa Luz</li>
    <li>Alamos</li>
</ol>

<h2>ENDPOINTS</h2>

Una vez descargado el repositorio y el proyecto compilado, iniciar la aplicacion
para el levantamieto del servidor en el puerto 8080 para tener acceso a
los siguientes endpoints: 

<ol>
    <li>
        <h4>https://localhost:8080</h4>
        Calcula la info de una propiedad. El payload tiene el siguiente contenido en formato JSON.
        <code>
            {
                "prop_name": "Hogar Dulce Hogar Dulce Hogar",
                "district_name": "Villa Luz",
                "district_price": 2,
                "environments": [
                    {
                        "environment_name": "Sala",
                        "environment_width": 25,
                        "environment_length": 25
                    },{
                        "environment_name": "Baño",
                        "environment_width": 25,
                        "environment_length": 32
                    },{
                        "environment_name": "Cocina",
                        "environment_width": 25,
                        "environment_length": 33
                    }
                ]
            }
        </code>
    </li>
</ol>

El payload debe:

<ul>
    <li>Tener todos sus campos diligenciados.</li>
    <li>Tener al menos un ambiente.</li>
    <li>"prop_name y "environment_name" cada palabra debe empezar con mayuscula.</li>
    <li>"district_price" no debe superar los 4000 USD.</li>
    <li>"environment_width" no debe ser mayor a 25 m.</li>
    <li>"environment_length" no debe ser mayor a 33 m.</li>
    <li>"prop_name" no debe superar los 30 caracteres.</li>
    <li>"district_name" no debe superar los 45 caracteres.</li>
    <li>"environment_name" no debe superar los 30 caracteres.</li>
</ul>

El endpoint debe:

<ol>
    <li><b>US-0001: </b>Calcular el total de metros cuadrados de una propiedad como <b>"prop_area"</b></li>
    <li><b>US-0002: </b>Indicar el valor de la propiedad a partir de sus ambientes y medidas. Tener
        en cuenta que los precios por metro cuadrado estan determinados segun el barrio
        como <b>"prop_price".</b></li>
    <li><b>US-0003: </b>Determinar cual es el ambiente mas grande como <b>"biggest_environment"</b>.</li>
    <li><b>US-0004: </b>Determinar la cantidad de metros cuadrados que tiene cada ambiente de una propiedad.
        como <b>"environment_length"</b></li>
</ol>

Para verificar el funcionamiento de la aplicacion, se realizaron test unitarios y test de integracion. Los test 
unitarios se realizaron a las respectivas capas repositorio, servicio y controlador. Coverage superior al
80%.