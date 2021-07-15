package com.mercadolibre.socialmeli.dtos.users;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class VendorFollowQtyRespDTO {

    private Integer userId;
    private String userName;
    private Integer followers_count;

}
