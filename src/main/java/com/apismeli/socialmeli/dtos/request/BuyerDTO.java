package com.apismeli.socialmeli.dtos.request;

import lombok.Data;

import java.util.List;

@Data
public class BuyerDTO extends UserDTO {
    private List<UserDTO> followed;
}
