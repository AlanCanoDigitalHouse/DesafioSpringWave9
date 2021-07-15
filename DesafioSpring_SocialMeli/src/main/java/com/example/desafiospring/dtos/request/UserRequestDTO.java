package com.example.desafiospring.dtos.request;

import com.example.desafiospring.dtos.FollowDTO;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@Validated
public class UserRequestDTO {

    @Min(message = "El valor de userId debe ser mayor a 0", value = 0)
    private int userId;
    @NotNull(message = "El userName no puede ser nulo.")
    @NotBlank(message = "El userName no puede estar vacio.")
    private String userName;
    private ArrayList<FollowDTO> usersFollowed;
    private ArrayList<FollowDTO> followers;

}
