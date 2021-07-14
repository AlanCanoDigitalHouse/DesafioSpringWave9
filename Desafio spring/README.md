# Desafio spring

## Iniciando

La aplicación realiza el guardado de datos en JSON en los archivos (se crean automaticamente)

- post.json
- user.json

Al iniciar la aplicación spring se cargaran datos default de dataSet*.json, 
asi no existe preocupacion por andar reseteando manualmente los datos.

En caso de que se quiera persistir la informacion entre reinicios, 
modificar la propiedad `resetDataAtStart` a **false** dentro del archivo `application.properties`

#### ¡Asi que tan solo es correr y empezar a romper!

## Requerimientos

### 0001: Un usuario puede seguir a otro

Endpoint example: POST [localhost:8080/users/1/follow/4]()

Payload: Ninguno

Excepciones esperadas:
 - El usuario no existe
 - Usuario se intenta seguir a si mismo
 - Usuario ya seguido
 - Mala entrada (letras en vez de numeros)

### 0002: Un usuario puede saber cuantas personas lo siguen

Endpoint example: GET [localhost:8080/users/4/followers/count/]()

Payload: Ninguno

Excepciones esperadas:
- El usuario no existe
- Mala entrada (letras en vez de numeros)

### 0003: Obtener la lista de las persoans que me siguen

Endpoint example: GET [localhost:8080/users/1/followers/list]()

Payload: Ninguno

Excepciones esperadas:
- El usuario no existe
- Mala entrada (letras en vez de numeros)


### 0004: Obtener la lista de las personas a las que sigo

Endpoint example: GET [localhost:8080/users/1/followed/list]()

Payload: Ninguno

Excepciones esperadas:
- El usuario no existe
- Mala entrada (letras en vez de numeros)

### 0005: Crear un post

Endpoint example: GET [localhost:8080/products/newpost]()

Payload:
````json
{
"userId": 1,
"date": "29-04-2021",
"detail": {
    "productName": "Silla Gamer",
    "type": "Gamer",
    "brand": "Racer",
    "color": "Red & Black",
    "notes": "Special Edition"
},
"category": 100,
"price": 15000.5
}
````

Excepciones esperadas:
- Error de parseo (si en la fecha se ingresa un letra)
- Error lógico, si la fecha tiene dias o meses inesperados
- La fecha esta en el futuro
- El usuario no existe
- Error general en el body, un error en el JSON o bien se pasa un texto que se espera sea número
- Falta algún atributo esperado
- Numeros muy grandes, se permiten maximo 8 reales y 2 decimales (ojo, 12.000000 será válido por ser ceros, pero un 12.55555 saltara error), aplica solo para precio, a la categoria se le quitan los decimales si es necesario

### 0006: Obtener la lista de posts (solo si tienen menos de una semana) de los vendedores que un usuario sigue

Endpoint example: GET [localhost:8080/followed/7/list]()

Payload: Ninguna

Excepciones esperadas:
- Id no existe
- Entrada no esperada (letras)

### 0007: Un usuario puede realizar la accion de unfollow

Endpoint example: GET [localhost:8080/users/7/unfollow/1]()

Payload: Ninguna

Excepciones esperadas:
- Id no existe
- No se esta siguiendo a ese usuario
- Error de  tipo (letra en vez de número)

