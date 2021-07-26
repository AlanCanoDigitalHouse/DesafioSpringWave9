# <center> CHALLENGE TESTING 💻 👩‍💻 </center>

* Proyect: Tu Casita Tasaciones
* Author: Luciana Salinardi

### Save the data entered in the request

Its execution is necessary for the other endpoints can work
<p>
POST /TuCasitaTasaciones/saveData 
</p>

### US-0001: Calculate the total square metres of a property

<p>
GET /TuCasitaTasaciones/houseArea <br>
Response: <br>
200 success - OK
</p>

### US-0002: Calculate the total value of a property based on its environments and measurements.

<p>
GET /TuCasitaTasaciones/priceByLocation <br>
Response: <br>
200 success - OK 
</p>

### US-0003: Calculate which is the biggest room.

<p>
GET /TuCasitaTasaciones/biggestRoom <br>
Response: <br>
200 success - OK
</p>

### US-0004: Calculate the area of each room

GET /TuCasitaTasaciones/roomArea <br>
Response: <br>
200 success - OK <br>

### Specifications

Repository created with the districts names and their respectives values per square meter. Only the district name is
sent in the request.

### Payload example:

    {
        "prop_name": "Casa nueva",
        "district_name": "Palermo",
        "rooms": [
            {
                "environment_name": "Cocina",
                "length": 7,
                "width": 3
            },
            {
                "environment_name": "Living",
                "length": 3,
                "width": 3
            }
        ]
    }

### COVERAGE IMAGES

### Coverage without exclusions

[[Coverage screenshot tests](images/coverage%20without%20exclusions.png)]

### Coverage with exclusions

[[Coverage screenshot tests](images/coverage%20with%20exclusions.png)]

