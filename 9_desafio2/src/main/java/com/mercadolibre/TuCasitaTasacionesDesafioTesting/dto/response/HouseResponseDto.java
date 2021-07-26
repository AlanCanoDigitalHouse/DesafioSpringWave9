package com.mercadolibre.TuCasitaTasacionesDesafioTesting.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import java.util.ArrayList;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class HouseResponseDto {

    private String prop_name;
    private Double houseArea;
    private Double prop_price;
    private String district_name;
    private ArrayList<RoomResponseDto> rooms;

}
