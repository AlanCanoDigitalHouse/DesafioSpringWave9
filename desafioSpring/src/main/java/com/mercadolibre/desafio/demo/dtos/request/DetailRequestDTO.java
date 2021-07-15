package com.mercadolibre.desafio.demo.dtos.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Validated
public class DetailRequestDTO {

//    @NotNull(message = "The parameter 'product_id' can not be null")
//    private Integer product_id;

    @NotNull(message = "The parameter 'productName' can not be null")
    private String productName;

    @NotNull(message = "The parameter 'type' can not be null")
    private String type;

    @NotNull(message = "The parameter 'brand' can not be null")
    private String brand;

    @NotNull(message = "The parameter 'color' can not be null")
    private String color;

    @NotNull(message = "The parameter 'notes' can not be null")
    private String notes;

}


/**
 *   {
 *     	"userId": 1235,
 *      "date" : "29-04-2021",
 *     	“detail" :
 *                        { “product_id” : 1,
 *                "productName" : "Silla Gamer",
 *           	   "type" : "Gamer",,
 *                "brand" : "Racer"
 *                "color" : "Red & Black",
 *                "notes" : "Special Edition"
 *      },
 *      "category" : 100,
 *      "price" : 1500.50,
 *    }
 */

