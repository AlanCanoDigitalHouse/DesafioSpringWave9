package com.mercadolibre.socialmeli.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.mercadolibre.socialmeli.dto.request.ProductRequest;
import com.mercadolibre.socialmeli.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class UserIdPostResponse {

    private Integer userId;

    private List<PostResponse> posts;
}
