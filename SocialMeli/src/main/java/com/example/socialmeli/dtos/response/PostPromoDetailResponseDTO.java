package com.example.socialmeli.dtos.response;


import com.example.socialmeli.domains.PostPromo;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PostPromoDetailResponseDTO extends PostDetailResponseDTO{

    private Boolean hasPromo;
    private Double discount;

    public PostPromoDetailResponseDTO(PostPromo post) {
        super(post);
        this.hasPromo = post.getHasPromo();
        this.discount = post.getDiscount();
    }
}
