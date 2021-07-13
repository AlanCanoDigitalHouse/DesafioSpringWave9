package com.mercadolibre.socialmeli.dto.respons;

import com.mercadolibre.socialmeli.dto.Publication;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PublicationsResponse {
    private int userId;
    private List<Publication> posts;
}
