package com.mercadolibre.socialmeli.dto.respons;

import com.mercadolibre.socialmeli.dto.Publication;
import com.mercadolibre.socialmeli.dto.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PromProductsResponse extends User {
    private List<Publication> posts;

}
