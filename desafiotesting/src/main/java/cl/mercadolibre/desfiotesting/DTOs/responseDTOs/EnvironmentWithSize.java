package cl.mercadolibre.desfiotesting.DTOs.responseDTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EnvironmentWithSize {
    String name;
    Double size;
}
