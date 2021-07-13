package com.socialMeli.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class WhoFollowUserResponseDTO {
    private int userId;
    private String userName;
    private List<BasicUserResponseDTO> followers;
}
