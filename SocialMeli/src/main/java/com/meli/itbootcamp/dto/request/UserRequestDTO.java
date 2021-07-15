package com.meli.itbootcamp.dto.request;

import lombok.*;

@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRequestDTO {
    private Integer userId;
    private String userName;

}
