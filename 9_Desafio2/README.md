# Calculadora de Metros Cuadrados (desafio 2)
Detalles de mi implementacion del proyecto:

- La base de datos de Distritos debe ser realizada por el usuario. Por defecto, el proyecto no posee
    ningun nombre de distrito cargado. Para subirlo, el usuario debera acceder al siguiente endpoint:
  

        http://localhost:8080/loadDistricts

   y el formato de entrada de distritos sera de la forma:
   

    {
    "districts" : 
        [
        "distrito1",
        "distrito2",
        ...
        "distritoX"
        ]
    }


- El proyecto implementa las clases y metodos del proyecto previamente compartido en 
  el resuelto del Modulo 6 dentro del Playground. Es por este motivo que el proyecto comparte
  un tipo de respuesta similar al proyecto en el cual se encuentra basado. Con esto me refiero a que,
  si bien se cumplen todos los requisitos que plantea el enunciado, las respuestas de cada ejercicio 
  se muestran en un mismo JSON de respuesta. 
  
  
- Es decir, no hay un endpoint para cada ejercicio, sino que existe uno solo en el cual se desarrollan
todas las respuestas esperadas (al igual que en el proyecto base subido a Playground).
  

- Para realizar la consulta que devuelve todos los datos esperados, el usuario debera acceder mediante
  el siguiente endpoint:


    http://localhost:8080/loadDistricts


   y el formato de entrada sera de la forma:


    {
      "name":"NombreCasa",
      "district": {
        "name": "NombreDistrito",
        "price": 1234
        },
      "enviroments": [
        {
          "name": "Hab1",
          "width": 12,
          "length": 23
        },{
          "name": "Hab2",
          "width": 23,
          "length": 12
        },
        ...
      ]
    }


- Todos los valores deben cumplir con las condiciones pautadas en el siguiente documento:
  https://drive.google.com/file/d/1Vl7nqxJvrIVwbuipuX8sFnEZSJiuaMJu/view
  

- Finalmente, la respuesta sera de la siguiente forma:


    {
        "name":"NombreCasa",
        "district": {
            "name": "NombreDistrito",
            "price": 1234
        },
        "enviroments": [
            {
              "name": "Hab1",
              "width": 12,
              "length": 23,
              "squareFeet": 276
            },{
              "name": "Hab2",
              "width": 2,
              "length": 2,
              "squareFeet": 276
            },
            ...
        ]
        "squareFeet": 280,
        "price": 345520,
        "biggest": {
            "name": "Hab1",
            "width": 12.0,
            "length": 23.0,
            "squareFeet": 276.0
        }
    }


- Como puede verse, en cada Enviroment aparece un nuevo campo llamado squareFeet que indica los 
  "pies cuadrados" de la habitacion individualmente. Por otro lado, fuera de los enviroments, 
  aparecen los campos squareFeet y price que indican el tamaño y precio total de la casa.
  Finalmente, el campo biggest nos muestra los datos del ambiente mas grande de la casa enviada.
 
 
- Por ultimo, debo aclarar que un motivo de eleccion personal, elegi almacenar la base de datos
de distritos en memoria, y no en un archivo a modo de repositorio.