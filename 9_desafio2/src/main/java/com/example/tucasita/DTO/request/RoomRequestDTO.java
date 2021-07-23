package com.example.tucasita.DTO.request;

import com.example.tucasita.DTO.request.constants.JsonProperties;
import com.example.tucasita.DTO.request.constants.ValidationMessages;
import com.example.tucasita.DTO.request.constants.ValidationValues;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.*;

@Validated
@Data
public class RoomRequestDTO {

    @NotBlank(message = ValidationMessages.ROOM_NAME_BLANK_MESSAGE)
    @Pattern(message = ValidationMessages.ROOM_NAME_CAPITALIZATION_MESSAGE, regexp = ValidationValues.FIRST_LETTER_UPPER_CASE_REGEX)
    @Size(message = ValidationMessages.ROOM_NAME_MAX_LENGTH_MESSAGE, max = ValidationValues.MAX_ROOM_NAME_LENGTH)
    @JsonProperty(JsonProperties.ROOM_NAME)
    private String name;

    @NotNull(message = ValidationMessages.ROOM_WIDTH_NULL_MESSAGE)
    @DecimalMin(message = ValidationMessages.ROOM_WIDTH_MIN_VALUE_MESSAGE, value = ValidationValues.MIN_ROOM_WIDTH)
    @DecimalMax(message = ValidationMessages.ROOM_WIDTH_MAX_VALUE_MESSAGE, value = ValidationValues.MAX_ROOM_WIDTH)
    @JsonProperty(JsonProperties.ROOM_WIDTH)
    private Double width;

    @NotNull(message = ValidationMessages.ROOM_LENGTH_NULL_MESSAGE)
    @DecimalMin(message = ValidationMessages.ROOM_LENGTH_MIN_VALUE_MESSAGE, value = ValidationValues.MIN_ROOM_LENGTH)
    @DecimalMax(message = ValidationMessages.ROOM_LENGTH_MAX_VALUE_MESSAGE, value = ValidationValues.MAX_ROOM_LENGTH)
    @JsonProperty(JsonProperties.ROOM_LENGTH)
    private Double length;
}

