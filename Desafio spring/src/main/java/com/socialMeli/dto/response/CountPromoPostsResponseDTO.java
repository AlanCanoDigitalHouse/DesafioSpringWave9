package com.socialMeli.dto.response;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CountPromoPostsResponseDTO {
    private String userId;
    private String userName;
    private int promoproducts_count;
}
