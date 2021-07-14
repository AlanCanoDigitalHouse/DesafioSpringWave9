package com.example.socialmeli.dtos.request;


import lombok.*;
import org.springframework.validation.annotation.Validated;




@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Validated
public class PostPromoRequestDTO extends PostRequestDTO{

    private Boolean hasPromo;
    private Double discount;


}
