# README

Desafio Wave 9 - Miriam Gutiérrez

## CONTENTS OF THIS FILE
   
* Introducción
* Requirimientos
* Recomendaciones

## INTRODUCTION

Para el Bootcamp Java de Meli, nos fue requerido desarrollar una API encargada de implementar una nueva red social en la cual los usuarios (los cuales pueden ser vendedores también), pueden seguir a otros usuarios, además de realizar publicaciones.


## REQUIRIMIENTOS

Para poder realizar las pruebas es necesario contar con un usuario valido. La lista de usuarios se puede encontra en el archivo users.json ubicado en la ruta:

    DesafioSpringWave9/desafioSpring/src/main/resources/static

De igual forma, se mencionan en la siguiente lista:

Usuario 1: {"userId":101,"name":"Miriam Gutierrez","followers":[],"following":[]}
Usuario 2: {"userId":102,"name":"Fernando Garcia","followers":[],"following":[]}
Usuario 3: {"userId":103,"name":"Andrea Diaz","followers":[],"following":[]}
Usuario 3: {"userId":104,"name":"David Murillo","followers":[],"following":[]}
Usuario 4: {"userId":105,"name":"Yessica Hernandez","followers":[],"following":[]}

En caso de requerir agregar más usuarios, copiar y pegar lo siguiente en el archivo users.json, después del último registro. Verificar que haya una como antes y que el nuevo registro se encuentre dentro de los corchetes ([]):

    {"userId":<userId>,"name":"<userName>","followers":[],"following":[]}
    

## RECOMMENDED

Se recomienda ampliamente no borrar los archivos de la siguiente ruta:

    DesafioSpringWave9/desafioSpring/src/main/resources/static

Dentro de estos archivos se encuentra nuestra base de datos local, por lo cual el borrarlos nos deja sininformación para las validaciones.


