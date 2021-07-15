package challenge1springboot.socialmeli.DTO.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SaleQuantityResponseDTO {

    private int userId;
    private String userName;
    private int promoproducts_count;
}
