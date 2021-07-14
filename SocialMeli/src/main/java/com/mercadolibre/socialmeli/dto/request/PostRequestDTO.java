package com.mercadolibre.socialmeli.dto.request;

import com.mercadolibre.socialmeli.entity.Post;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostRequestDTO extends Post {
    String date;
}
