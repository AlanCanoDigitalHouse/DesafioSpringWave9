package com.mercadolibre.socialmeli.dto.response;

import com.mercadolibre.socialmeli.dto.request.PostDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostResponseListDTO {
    private Integer userId;
    private List<PostDTO> posts;
}
