  
        Square Meter

   *Square Meter: Bootcamp 9 Backend Java challenge Testing Automatized*

App for the company “TuCasita Tasaciones”

      User Stories: 

US-0001 : Calculate the total square meters of a property.

US-0002 : property appraisal by district

US-0003 : the biggest environment.

US-0004 : Square meters of each environment.


***Content*** Architecture echnologies and tools Endpoints Author License

***Architecture***
*A application is proposed that uses lightweight protocols such as HTTP or REST API . 
A service-oriented code architecture is proposed with routes, controllers and validators .*


**Technologies**

[Java 11] - Programming language 
[Spring-boot] - Server 
[Maven] - Dependency management 


***Repository :*** district.json - Temp Database

**Endpoints**

1 GET /district/all : Get all the District at the base.

2 POST /district/report : Report of the calculation of : US-0001 : response.squareMeter US-0002 : response.priceAppraisal US-0003 : response.environmentBiggest US-0004 :
response.environmentsMeter[]

-
      request:
        { "name":"String",
          "district":{
            "district_name":"String",
            "district_price": Double 
               },
         "environment":[
           {
            "environment_name":"String",
            "environment_width":"Double",
            "environment_length":"Double 
           } 
         }

-

            response:
                 {
                    "squareMeter": "Double",
                    "priceAppraisal": "Double",
                    "environmentBiggest": "String",
                    "environmentsMeter": [
                     {
                       "name": "String",
                       "square_meter": "Double"
                     }
                     ]
                 }


*Author*
**Fiordelmondo Lorena Daniela**

*License* 

This project is property of Mercado Libre.