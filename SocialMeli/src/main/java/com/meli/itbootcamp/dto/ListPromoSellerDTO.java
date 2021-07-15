package com.meli.itbootcamp.dto;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ListPromoSellerDTO {
    private  Integer userId;
    private  String userName;
    private List<PromoDTO> posts;
}
