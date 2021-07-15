# DesafioSpringWave9
Repositorio donde guardaremos el código de nuestros proyectos Spring de Ignacio Cordo

## Tabla de contenido
- [Requerimientos](#Requerimientos)
- [¿Cómo levantar el proyecto localmente?](#cómo-levantar-el-proyecto-localmente)
- [¿Cómo funciona?](#cómo-funciona)
- [Decisiones tomadas](#decisiones-tomadas)

## Requerimientos
- Java 11
- Maven 3.6.2
- Clonarse el repositorio y pararse en el branch "Cordo_Ignacio"
- Coleccion de Postman [BootcampSignDesafioSpring.postman_collection.json](BootcampSignDesafioSpring.postman_collection.json) (Opcional)

## ¿Cómo levantar el proyecto localmente?
1.  Abrir la carpeta "DesafioSpring" como proyecto con IntelliJ
2.  Correr el proyecto

## ¿Cómo funciona?
La API expone los servicios definidos en los requerimientos del ejercicio: [PG_Esp. de Req. técnicos funcionales Desafío Spring.docx](PG_Esp._de_Req.técnicos_funcionales_Desafío_Spring.docx)

A su vez, se expuso los servicios GET "/information/users" y GET "/information/sellers" para poder obtener los usuarios y vendedores cargados en la base de datos.

Todos estos servicios mencionados se encuentran en la colección de Postman.

## Decisiones tomadas
- El proyecto se desarrolló simulando una base de datos en memoria.
- Se tienen 2 archivos .json los cuales se utilizan para cargar en memoria una lista de User y otra de Seller.  
- Al levantar el proyecto no hay ninguna relación en la base de datos en memoria, es decir, no hay ningun comprador que siga a un vendedor.
- A fines prácticos como el comprador no es diferente al usuario, ya que ambos solo poseen los atributos "userId" y "userName", no se creó una clase Buyer que herede de User.

