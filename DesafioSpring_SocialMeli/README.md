# DesafioSpringWave9

**15-07-2021**

Se sube una primera versión de la aplicación SocialMeli para el desafío Spring Wave 9.

La misma cuenta con los siguientes requerimientos funcionales andando:

--------------------------------------------------------------------------------

**REQUERIMIENTOS OBLIGATORIOS INCLUIDOS EN LA VERSIÓN:**

- US 0001
- US 0002
- US 0003
- US 0004
- US 0005
- US 0006
- US 0007
- US 0008
- US 0009



**REQUERIMIENTOS BONUS INCLUIDOS EN LA VERSIÓN:**

- US 0010
- US 0011



--------------------------------------------------------------------------------

**CONFIGURACIÓN INICIAL:**

Para la ejecución de las pruebas se encuentra un archivo json (database.json) con datos de usuarios pre cargados y con algunas relaciones follow-followed ya definidas, para ver estos usuarios y las relaciones se agrega el endpoint: http://localhost:8080/users/health.

Usuarios cargados:

"userId": 1001,
"userName": "PPerez",

"userId": 1002,
"userName": "LRodriguez"

"userId": 1003,
"userName": "HFernandez"

"userId": 1004,
"userName": "AMartinez"



No cuenta con productos cargados, por lo que se debe invocar el endpoint del US 0005 para dar de alta alguno.
Debajo se envia un JSON de ejmplo para dar de alta el producto:

{
    	"userId": 1235,
      "date" : "29-04-2021",
    	"detail" :
        	{    "productName" : "Silla Gamer",
          	   "type" : "Gamer",,
               "brand" : "Racer"
               "color" : "Red & Black",
               "notes" : "Special Edition"
        	},
     	"category" : 100,
     	"price" : 1500.50,
}

--------------------------------------------------------------------------------

**MEJORAS PENDIENTES A REALIZAR:**

- Agregar requerimiento bonus: US 0012
- Agregar validación de fecha valida al cargar un nuevo post.
- Refactorización para que los controladores usen cada uno su servicio ya que PostController usa el servicio de Post y de User. 
- Mejoras en algún nombre de método. 
