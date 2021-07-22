package challenge1springboot.socialmeli.DTO.response;

import challenge1springboot.socialmeli.DTO.SaleDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SaleListResponseDTO {
    private int userId;
    private String userName;
    private List<SaleDTO> posts;
}
