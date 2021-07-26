package com.apismeli.socialmeli.dtos.request;

import lombok.Data;

import java.util.List;

@Data
public class SellerDTO extends UserDTO {

    List<UserDTO> followers;
}
