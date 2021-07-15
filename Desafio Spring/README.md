<h1>SPRING BOOT CHALLENGE</h1>

La applicacion es el backend de una pequeña red social en la cual un usuario 
puede seguir a otro y ver sus publicaciones que son productos a la venta. 
Cualquier usuario puede postear productos, seguir o ser seguido.

Los datos precargados son:

<ul>
    <li>5 usuarios.</li>
    <li>3 publicaciones.</li>
</ul>

Las relaciones entre usuarios y publicaciones se basan en tablas relacionadas
en forma de objetos JSON. Estas relaciones son:

<b>USUARIO-PUBLICACION:</b> Los usuarios tienen relacion uno a muchos con las publicaciones. Es decir,
un usuario puede tener muchas publicaciones y cada publicacion pertenece a solo
un usuario.

<b>SEGUIDOR-SEGUIDO:</b> Los usuarios tienen una relacion muchos a muchos con otros usuarios. Es decir,
un usuario puede seguir a muchos usuarios y ser seguido por muchos usuarios al 
mismo tiempo. Para esto es necesario crear la tabla intermedia de relaciones 
que se encuentra en follow.json.

Los datos estan organizados asi:

<ul>
    <li>El usuario 1 cuenta con 3 publicaciones.</li>
    <li>Los usuarios 2, 3, 4 y 5 siguen al usuario 1.</li>
    <li>El usuario 1 no sigue a nadie.</li>
    <li>De las tres publicaciones solo dos entran a la ventana de dos ultimas semanas.
    </li>
</ul>

<h2>ENDPOINTS</h2>

Una vez descargado el repositorio y el proyecto compilado, iniciar la aplicacion
para el levantamieto del servidor en el puerto 8080 para tener acceso a
los siguientes endpoints: 

<ol>
    <li>
        <h4>/users/{userId}/follow/{userIdToFollow}</h4>
        El usuario con id "userId" seguira al usuario con id "userIdToFollow"
    </li>
    <li>
        <h4>/users/{userId}/followers/count/</h4>
        Retorna el numero de seguidores que tiene el usuario con id "userId"
    </li>
    <li>
        <h4>/users/{userId}/followers/list</h4>
        Retorna la lista de seguidores del usuario con id "userId".
    </li>
    <li>
        <h4>/users/{userId}/followed/list</h4>
        Retorna una lista de los usuarios a los cuales el usuario con id "userId" sigue.
    </li>
    <li>
        <h4>/products/newpost</h4>
        Crea una nueva publicacion. El payload tiene el siguiente contenido en formato JSON.
        <code>
            {
            "userId": 1235,
            "date" : "29-04-2021",
            “detail" :
                {
                    "productName" : "Silla Gamer",
                    "type" : "Gamer",,
                    "brand" : "Racer"
                    "color" : "Red & Black",
                    "notes" : "Special Edition"
                },
            "category" : 100,
            "price" : 1500.50,
            }
        </code>
    </li>
    <li>
        <h4>/products/followed/{userId}/list</h4>
        Retorna las publicaciones de las ultimas dos semanas de todos los usuarios 
        a los que sigue el usuario con id "userId"
    </li>
    <li>
        <h4>/users/{userId}/unfollow/{userIdToUnfollow}</h4>
        El usuario con id "userId" dejara de seguir al usuario con id "userIdToUnfollo".
    </li>
    <li>
        <h4>/users/{userId}/followers/list?order=name_asc</h4>
        <h4>/users/{userId}/followers/list?order=name_desc</h4>
        <h4>/users/{userId}/followed/list?order=name_asc</h4>
        <h4>/users/{userId}/followed/list?order=name_desc</h4>
        Igual que endopoints 3 y 4 pero con la posiblidad de ordenar los resultados por nombre
        de usuario ascendentemente A-Z o descendentemente Z-A.
    </li>
    <li>
        <h4>/products/followed/{userId}/list?order=date_asc</h4>
        <h4>products/followed/{userId}/list?order=date_desc</h4>
        Igual que 6 pero con la posiblidad de ordenar los resultados por fecha.
    </li>
</ol>





