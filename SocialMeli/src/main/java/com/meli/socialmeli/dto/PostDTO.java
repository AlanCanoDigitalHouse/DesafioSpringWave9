package com.meli.socialmeli.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.meli.socialmeli.utils.Constant;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostDTO {
    private Integer userId;
    private Integer id_post;
    @JsonFormat(pattern = Constant.FORMATO_FECHA, timezone = Constant.ZONA_HORARIA)
    private Date date;
    private Producto detail;
    private Integer category;
    private double price;
}
