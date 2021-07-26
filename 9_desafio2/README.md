# Desafio testing

## setup
El proyecto incluye un json que se cargará al momento de correr el proyecto, este json contiene un pequeño arreglo con nombres de distritos válidos.

# Test unitarios

Existen test unitarios para el controller, repository y service, para llenar algunos objetos en los test se utiliza una clase llamada TestUtilGenerator con métodos estáticos que devuelve los objetos y listas necesarias para utilizar en los test.

# Test integración

Existe una clase encargada de los test de integración, este prueba el endpoint ```"/properties/calulate"``` que retorna un json con todos los cálculos requeridos por propiedad y sus ambientes.

# Endpoint

```
  /properties/calculate:
    post:
      summary: "POST properties/calculate"
      operationId: "calculate"
      responses:
        "200":
          description: "OK"
```