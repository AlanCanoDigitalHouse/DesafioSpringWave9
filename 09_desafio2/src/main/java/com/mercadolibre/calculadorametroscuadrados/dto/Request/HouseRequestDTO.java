package com.mercadolibre.calculadorametroscuadrados.dto.Request;

import com.mercadolibre.calculadorametroscuadrados.dto.HouseDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Validated
public class HouseRequestDTO extends HouseDTO {

    private List<@Valid EnvironmentRequestDTO> environments;

    public HouseRequestDTO(String name, DistrictDTO district, List<@Valid EnvironmentRequestDTO> environments) {
        this.setName(name);
        this.setDistrict(district);
        this.environments = environments;
    }
}
