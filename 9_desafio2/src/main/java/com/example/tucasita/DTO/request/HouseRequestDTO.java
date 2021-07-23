package com.example.tucasita.DTO.request;

import com.example.tucasita.DTO.request.constants.JsonProperties;
import com.example.tucasita.DTO.request.constants.ValidationMessages;
import com.example.tucasita.DTO.request.constants.ValidationValues;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.List;

@Data
@Validated
public class HouseRequestDTO {

    @NotBlank(message = ValidationMessages.PROP_NAME_BLANK_MESSAGE)
    @Pattern(message = ValidationMessages.PROP_NAME_CAPITALIZATION_MESSAGE, regexp = ValidationValues.FIRST_LETTER_UPPER_CASE_REGEX)
    @Size(message = ValidationMessages.PROP_NAME_MAX_LENGTH_MESSAGE, max = ValidationValues.MAX_HOUSE_NAME_LENGTH)
    @JsonProperty(JsonProperties.HOUSE_NAME_JSON_PROP)
    private String name;

    @NotBlank(message = ValidationMessages.DISTRICT_NAME_BLANK_MESSAGE)
    @Size(message = ValidationMessages.DISTRICT_NAME_MAX_LENGTH_MESSAGE, max = ValidationValues.MAX_DISTRICT_NAME_LENGTH)
    @JsonProperty(JsonProperties.HOUSE_DISTRICT_NAME_JSON_PROP)
    private String districtName;

    @NotNull(message = ValidationMessages.DISTRICT_PRICE_NULL_MESSAGE)
    @DecimalMin(message = ValidationMessages.DISTRICT_PRICE_MIN_VALUE_MESSAGE, value = ValidationValues.MIN_DISTRICT_PRICE)
    @DecimalMax(message = ValidationMessages.DISTRICT_PRICE_MAX_VALUE_MESSAGE, value = ValidationValues.MAX_DISTRICT_PRICE)
    @JsonProperty(JsonProperties.HOUSE_DISTRICT_PRICE_JSON_PROP)
    private Double districtPrice;


    @Valid
    @NotEmpty(message = ValidationMessages.ROOM_LIST_NOT_EMPTY)
    @JsonProperty(JsonProperties.HOUSE_ROOM_LIST_JSON_PROP)
    private List<RoomRequestDTO> rooms;

}
