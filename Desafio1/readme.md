### Desafio 1 Spring

-  Este proyecto contempla el desafío de Spring. Inicialmente se planeó terminar el bonus,
pero se implementaron annotations que consumieron bastante tiempo y no dio cabida a crear
   los puntos 10-12. 

## Estructura
- Controllers: acá va lo lógica que maneja requests y responses
- dto: Acá van las clases que se parsean, serializan y también responses.
- exceptions: acá están las clases que se devuelven por errores, se usó un advice y también 
una estructura de annotations y validators (constraint validations). 
  Dejo acá el link de dónde saqué la info:
  https://levelup.gitconnected.com/constraint-validation-in-spring-boot-microservices-b89805e9c540
  https://docs.jboss.org/hibernate/stable/validator/reference/en-US/html_single/#validator-gettingstarted

## Uso
  - Existen los usuarios con id 1 al 6, por ende cualquier usuario que no se encuentre
dentro de esos dará UserNotFoundException.
  - los usuarios 1,3 y 5 son "clientes". Los 2,4 y 6 son vendedores. A efectos prácticos
esto no quiere decir nada, todos pueden seguir a todos, y todos pueden subir productos.
  - En el caso de los endpoints que listan, se puede mandar el query string como no,
y está preparado para recibirlo nulo o sin nada. 