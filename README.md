# DesafioSpringWave9

Detalles de mi implementacion del proyecto:

- El registro de usuarios se ejecuta mediante la siguiente url:
    
    "http://localhost:8080//create/{userId}/{userName}"
    
    donde userId y userName son el numero de id y el nombre del usuario respectivamente.
    
- Un usuario puede seguirse a si mismo
- Agregar dos usuarios con el mismo id hace que el segundo reemplace al primero
- El ingreso de fechas en los JSON de publicaciones debe ingresarse con el siguiente formato: "dd-MM-yyyy". Caso contrario, provoca error.
- Dado que se implementaron los metodos del enunciado Bonus, el ejercicio US 0006 ahora devuelve un JSON con 2 valores extra: "hasPromo" y "discount", ya que considero que el boolean hasPromo careceria de sentido en los JSON de promos. Es por esto que las publicaciones que no sean de tipo "promo" contendran de forma fija los siguientes valores:
  - hasPromo=false
  - discount=0
  
- Debido a problemas en el proyecyo, el formato de fecha en los JSON de publicaciones no es el mismo. que se indica en el enunciado

Todos los otros metodos y endpoints se ejecutan de la misma forma que lo indica el siguiente link
https://drive.google.com/file/d/1iPdb8VVgxi4SZtWNqwHo_lo-quODgi3i/view
