package com.mercadolibre.socialmeli.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.mercadolibre.socialmeli.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.validation.annotation.Validated;
import javax.validation.constraints.NotNull;
@Validated
@Data
@Builder
@AllArgsConstructor
public class FollowRequest {

    @NotNull(message = "userId cannot be null")
    private Integer userId;
    @NotNull(message = "userId cannot be null")
    private Integer userIdToFollow;
}
