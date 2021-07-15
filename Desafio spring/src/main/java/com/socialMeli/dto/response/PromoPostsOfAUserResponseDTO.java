package com.socialMeli.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class PromoPostsOfAUserResponseDTO {
    private int userId;
    private String userName;
    private List<PromoPostInfoResponseDTO> posts;
}
