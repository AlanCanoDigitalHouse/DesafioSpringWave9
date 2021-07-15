package com.meli.itbootcamp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ListPostSellerDTO {

    private Integer userId;
    private List<PostDTO> posts;



}
