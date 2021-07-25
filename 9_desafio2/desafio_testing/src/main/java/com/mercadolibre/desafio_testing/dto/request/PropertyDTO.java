package com.mercadolibre.desafio_testing.dto.request;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mercadolibre.desafio_testing.util.ConstantsUtils;
import lombok.*;
import org.springframework.util.ResourceUtils;
import com.mercadolibre.desafio_testing.exception.PropertyNotValidException;

import java.io.IOException;
import java.util.*;

@Getter
@Setter
public class PropertyDTO implements Validatable {
    private String prop_name;
    private String district_name;
    private List<RoomDTO> environments;

    public static PropertyDTO getPropertyDTO(String path) {
        PropertyDTO propertyDTO = null;

        try {
            propertyDTO = new ObjectMapper().readValue(
                    ResourceUtils.getFile(path),
                    new TypeReference<>() {
                    });
        } catch (IOException e) {
            e.printStackTrace();
        }

        return propertyDTO;
    }

    public void validateName(String name)
            throws PropertyNotValidException {
        if (name == null || name.length() == 0) {
            throw new PropertyNotValidException(
                    ConstantsUtils.INVALID_PROPERTY_NAME);
        }

        if (!Character.isUpperCase(name.charAt(0))) {
            throw new PropertyNotValidException(
                    ConstantsUtils.INVALID_PROPERTY_CAPITALIZATION);
        }

        if (name.length() > 30) {
            throw new PropertyNotValidException(
                    ConstantsUtils.PROPERTY_NAME_TOO_LONG);
        }

        if (name.contains(" ")) {
            throw new PropertyNotValidException(
                    ConstantsUtils.PROPERTY_NAME_WITH_SPACES);
        }
    }


    public void validateEnvironments(List<RoomDTO> environments)
            throws PropertyNotValidException {
        for (RoomDTO room: environments) {
            room.validate();
        }
    }

    public void validate() throws PropertyNotValidException {
        this.validateName(this.getProp_name());
        this.validateEnvironments(this.getEnvironments());
    }
}
