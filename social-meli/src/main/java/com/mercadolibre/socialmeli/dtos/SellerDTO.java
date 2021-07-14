package com.mercadolibre.socialmeli.dtos;

import lombok.*;

import java.util.LinkedList;
import java.util.List;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class SellerDTO extends UserDTO {

    private List<UserDTO> followers = new LinkedList<>();

}