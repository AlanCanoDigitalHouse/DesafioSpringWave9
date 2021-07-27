# TU CASITA APP :computer:

:house: Esta aplicación permite obtener información de una propiedad (casa, departamento,etc) 
a partir de las medidas de sus ambientes y el barrio donde se encuentra.

:memo: Developed in IntelliJ IDEA with Java 11, SpringBoot, JUnit & Mockito.  

---
### **ENDPOINTS**

* Get property info: **POST** /propertyInfo
    * `propertyDTO`: _PropertyDTO_ con información de la propiedad.

  
    Permite obtener información de la propiedad, los metros cuadrados por ambiente, los metros 
    cuadrados totales y el precio total. También informá cuál es el ambiente de mayor tamaño. 
    Esto se calcula a partir del precio del barrio donde se encuentra y las medidas de sus 
    ambientes.
    ```
    Ej: /propertyInfo
    {
    "prop_name": "Chalet",
    "district": 
        {
            "district_name":"Palermo"
        },
    "environments":[
        {
            "environment_name":"Cocina",
            "environment_width": 6.0,
            "environment_length": 5.0
        },
        {
            "environment_name":"Dormitorio",
            "environment_width": 4.5,
            "environment_length": 4.0
        }
      ]
    }
    ```
  
---
### **TESTS**

```
Los test de esta aplicación fueron implementados usando JUnit y Mockito.
```
* Controller tests: cubren la funcionalidad del endpoint, y las validaciones de datos de entrada así como sus excepciones.
* Dto tests: evalúan los métodos comunes de los dtos, que puedan ser construidos y modificados.
* Handler tests: tests para el Property handler que evaluan los métodos de negocio de la aplicación.
* Repository tests: evalúan la función de la capa repositorio.
* Service tests: tests para la capa de servicio, que obtiene los datos para devolver al controller.
  
#### **TEST GENERATOR UTILS**
```
El paquete _utils_ de la suite de tests proporciona metodos y objetos predeterminados 
que pueden utilizarse para facilitar los tests.
```
* Instancias de objetos _static_ : Son objetos predeterminados para ser usados en los tests.
* createPropertyWith1Environment(): Este método instancia y devuelve una propiedad con un ambiente, una localidad y un nombre.
* createPropertyWith4Environments(): Este método instancia y devuelve una propiedad con cuatro ambientes, una localidad y un nombre.
* getSumOfAllEnvsSquareFeet(): Devuelve el total de metros cuadrados que suman todos los ambientes de la clase.  
  
---
### **COVERAGE**
```
A continuación encontraremos la captura del coverage de la aplicación al último commit.
```

<html>

<h3>Overall Coverage Summary </h3>
<table class="coverageStats">
  <tr>
    <th class="name">Package</th>
<th class="coverageStat 
">
  Class, %
</th>
<th class="coverageStat 
">
  Method, %
</th>
<th class="coverageStat 
">
  Line, %
</th>
  </tr>
  <tr>
    <td class="name">all classes</td>
<td class="coverageStat">
  <span class="percent">
    100%
  </span>
  <span class="absValue">
    (15/ 15)
  </span>
</td>
<td class="coverageStat">
  <span class="percent">
    96.4%
  </span>
  <span class="absValue">
    (53/ 55)
  </span>
</td>
<td class="coverageStat">
  <span class="percent">
    93.5%
  </span>
  <span class="absValue">
    (100/ 107)
  </span>
</td>
  </tr>
</table>

<h3>Coverage Breakdown</h3>

<table class="coverageStats">
  <tr>
    <th class="name  sortedAsc
">
Package    </th>
<th class="coverageStat 
">
  Class, %
</th>
<th class="coverageStat 
">
  Method, %
</th>
<th class="coverageStat 
">
  Line, %
</th>
  </tr>
  <tr>
    <td class="name"><a href="https://github.com/AlanCanoDigitalHouse/DesafioSpringWave9/tree/Bianchini_Juan/desafio-2-testing/calculadora-metros-cuadrados/src/main/java/com/mercadolibre/tucasita/controller">com.mercadolibre.tucasita.controller</a></td>
<td class="coverageStat">
  <span class="percent">
    100%
  </span>
  <span class="absValue">
    (1/ 1)
  </span>
</td>
<td class="coverageStat">
  <span class="percent">
    100%
  </span>
  <span class="absValue">
    (2/ 2)
  </span>
</td>
<td class="coverageStat">
  <span class="percent">
    100%
  </span>
  <span class="absValue">
    (4/ 4)
  </span>
</td>
  </tr>
  <tr>
    <td class="name"><a href="https://github.com/AlanCanoDigitalHouse/DesafioSpringWave9/tree/Bianchini_Juan/desafio-2-testing/calculadora-metros-cuadrados/src/main/java/com/mercadolibre/tucasita/dto">com.mercadolibre.tucasita.dto</a></td>
<td class="coverageStat">
  <span class="percent">
    100%
  </span>
  <span class="absValue">
    (3/ 3)
  </span>
</td>
<td class="coverageStat">
  <span class="percent">
    100%
  </span>
  <span class="absValue">
    (15/ 15)
  </span>
</td>
<td class="coverageStat">
  <span class="percent">
    100%
  </span>
  <span class="absValue">
    (15/ 15)
  </span>
</td>
  </tr>
  <tr>
    <td class="name"><a href="https://github.com/AlanCanoDigitalHouse/DesafioSpringWave9/tree/Bianchini_Juan/desafio-2-testing/calculadora-metros-cuadrados/src/main/java/com/mercadolibre/tucasita/dto/response">com.mercadolibre.tucasita.dto.response</a></td>
<td class="coverageStat">
  <span class="percent">
    100%
  </span>
  <span class="absValue">
    (2/ 2)
  </span>
</td>
<td class="coverageStat">
  <span class="percent">
    90.9%
  </span>
  <span class="absValue">
    (10/ 11)
  </span>
</td>
<td class="coverageStat">
  <span class="percent">
    90.9%
  </span>
  <span class="absValue">
    (10/ 11)
  </span>
</td>
  </tr>
  <tr>
    <td class="name"><a href="https://github.com/AlanCanoDigitalHouse/DesafioSpringWave9/tree/Bianchini_Juan/desafio-2-testing/calculadora-metros-cuadrados/src/main/java/com/mercadolibre/tucasita/exception">com.mercadolibre.tucasita.exception</a></td>
<td class="coverageStat">
  <span class="percent">
    100%
  </span>
  <span class="absValue">
    (2/ 2)
  </span>
</td>
<td class="coverageStat">
  <span class="percent">
    100%
  </span>
  <span class="absValue">
    (3/ 3)
  </span>
</td>
<td class="coverageStat">
  <span class="percent">
    100%
  </span>
  <span class="absValue">
    (5/ 5)
  </span>
</td>
  </tr>
  <tr>
    <td class="name"><a href="https://github.com/AlanCanoDigitalHouse/DesafioSpringWave9/tree/Bianchini_Juan/desafio-2-testing/calculadora-metros-cuadrados/src/main/java/com/mercadolibre/tucasita/exception/handler">com.mercadolibre.tucasita.exception.handler</a></td>
<td class="coverageStat">
  <span class="percent">
    100%
  </span>
  <span class="absValue">
    (2/ 2)
  </span>
</td>
<td class="coverageStat">
  <span class="percent">
    100%
  </span>
  <span class="absValue">
    (9/ 9)
  </span>
</td>
<td class="coverageStat">
  <span class="percent">
    100%
  </span>
  <span class="absValue">
    (23/ 23)
  </span>
</td>
  </tr>
  <tr>
    <td class="name"><a href="https://github.com/AlanCanoDigitalHouse/DesafioSpringWave9/tree/Bianchini_Juan/desafio-2-testing/calculadora-metros-cuadrados/src/main/java/com/mercadolibre/tucasita/handler">com.mercadolibre.tucasita.handler</a></td>
<td class="coverageStat">
  <span class="percent">
    100%
  </span>
  <span class="absValue">
    (1/ 1)
  </span>
</td>
<td class="coverageStat">
  <span class="percent">
    100%
  </span>
  <span class="absValue">
    (5/ 5)
  </span>
</td>
<td class="coverageStat">
  <span class="percent">
    100%
  </span>
  <span class="absValue">
    (8/ 8)
  </span>
</td>
  </tr>
  <tr>
    <td class="name"><a href="https://github.com/AlanCanoDigitalHouse/DesafioSpringWave9/tree/Bianchini_Juan/desafio-2-testing/calculadora-metros-cuadrados/src/main/java/com/mercadolibre/tucasita/repositories">com.mercadolibre.tucasita.repositories</a></td>
<td class="coverageStat">
  <span class="percent">
    100%
  </span>
  <span class="absValue">
    (2/ 2)
  </span>
</td>
<td class="coverageStat">
  <span class="percent">
    100%
  </span>
  <span class="absValue">
    (5/ 5)
  </span>
</td>
<td class="coverageStat">
  <span class="percent">
    84%
  </span>
  <span class="absValue">
    (21/ 25)
  </span>
</td>
  </tr>
  <tr>
    <td class="name"><a href="https://github.com/AlanCanoDigitalHouse/DesafioSpringWave9/tree/Bianchini_Juan/desafio-2-testing/calculadora-metros-cuadrados/src/main/java/com/mercadolibre/tucasita/service">com.mercadolibre.tucasita.service</a></td>
<td class="coverageStat">
  <span class="percent">
    100%
  </span>
  <span class="absValue">
    (1/ 1)
  </span>
</td>
<td class="coverageStat">
  <span class="percent">
    100%
  </span>
  <span class="absValue">
    (3/ 3)
  </span>
</td>
<td class="coverageStat">
  <span class="percent">
    100%
  </span>
  <span class="absValue">
    (13/ 13)
  </span>
</td>
  </tr>
</table>
</div>

<div class="footer">
    
    
</div>
</body>
</html>




---
:office: **Application created during Mercadolibre&copy; BootCamp 2021**  
:bust_in_silhouette: **Author** Juan Bianchini :email: `juan.bianchini@mercadolibre.com`
