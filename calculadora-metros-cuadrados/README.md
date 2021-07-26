# Desafío Testing - Calculadora metros cuadrados

## Before running tests
For a more representational coverage number, please exclude the following class from the coverage analyzer:
- CalculadoraMetrosCuadradosApplication

## Endpoints
The only endpoint is `/calculate`, a `GET` which performs all 4 calculations asked for
in the user stories:
- Total house squared metres
- Total house price
- Determine biggest room in house
- Determine each room's area individually

## DTO

- `HouseRequestDTO`: body payload for GET request at endpoint `/calculate`.
- `HouseResponseDTO`: contains all 4 user stories calculations

## Tests

### Unit

Controllers:

- CalculateRestControllerTests

Repositories:

- LocationRepositoryTests

Services:

- CalculateServiceTests

DTOs:

- HouseRequestDTOUnitTests

### Integration

CalculadoraMetrosCuadradosIntegrationTests

- Calcular el área de casa con 1 habitación
- Calcular el área de casa con 2 habitaciones
- Calcular el área de casa con 3 habitaciones
- Bad request por buscar un distrito que no existe
- Bad request por pasar precio de distrito igual a cero
- Bad request por pasar nombre de distrito nulo
- Bad request por pasar nombre de propiedad vacío
- Bad request por pasar nombre de distrito de más de 45 caracteres y precio mayor a 4000
- Bad request por pasar nombre de propiedad y de distrito nulos
- Bad request por pasar nombre de distrito vacío y precio igual a cero
- Bad request por pasar nombre de propiedad vacío y precio de distrito negativo
- Bad request por pasar nombre de propiedad de más de 35 caracteres
- Bad request por pasar una casa sin habitaciones (lista vacía)
- Bad request por pasar una casa con habitaciones nulas (lista nula)
- Bad request por pasar precio de distrito nulo
- Bad request por pasar habitación con ancho negativo
- Bad request por pasar 2 habitaciones con nombre vacío

## Coverage

In *com.mercadolibre.calculadorametroscuadrados*

- 100% classes
- 95% lines covered
