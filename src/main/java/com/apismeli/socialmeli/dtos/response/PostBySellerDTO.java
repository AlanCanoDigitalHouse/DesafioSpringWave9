package com.apismeli.socialmeli.dtos.response;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.HashMap;

@Data
@NoArgsConstructor
public class PostBySellerDTO {
    private HashMap<Integer, ArrayList<PostResponseDTO>> sellerPosts = new HashMap<>();
}
