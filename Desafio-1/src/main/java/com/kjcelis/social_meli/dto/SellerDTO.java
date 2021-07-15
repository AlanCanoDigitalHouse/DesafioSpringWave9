package com.kjcelis.social_meli.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SellerDTO extends UserDTO {
    private ArrayList<PostDTO> posts;
    private Integer followers_count;
    private ArrayList<BuyerDTO> followers;
    private Integer promoproducts_count;
    private ArrayList<PostDTO> post_promo;


    public SellerDTO(Integer userId, String userName) {
        super(userId, userName);
    }


    public SellerDTO(Integer userId, String userName, ArrayList<PostDTO> posts, Integer followers_count, ArrayList<BuyerDTO> followers, Integer promoproducts_count, ArrayList<PostDTO> post_promo) {
        super(userId, userName);
        this.posts = posts;
        this.followers_count = followers_count;
        this.followers = followers;
        this.promoproducts_count = promoproducts_count;
        this.post_promo = post_promo;
    }

    public SellerDTO(Integer userId, String userName, Integer followers_count) {
        super(userId, userName);
        this.followers_count = followers_count;
    }

    public SellerDTO(Integer userId, String userName, ArrayList<BuyerDTO> followers) {
        super(userId, userName);
        this.followers = followers;
    }
}
