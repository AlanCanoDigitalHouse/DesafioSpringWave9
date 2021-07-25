package com.mercadolibre.desafio_testing.dto.request;

import com.mercadolibre.desafio_testing.exception.PropertyNotValidException;
import com.mercadolibre.desafio_testing.util.ConstantsUtils;
import lombok.*;

@Getter
@Setter
public class RoomDTO implements Validatable {
    private String environment_name;
    private double environment_width;
    private double environment_length;

    public void validate() throws PropertyNotValidException {
        this.validateEnvironmentName();
        this.validateEvironmentLength();
        this.validateEvironmentWidth();
    }

    public void validateEnvironmentName()
            throws PropertyNotValidException {
        if (this.getEnvironment_name() == null
                || this.getEnvironment_name().length() == 0) {
            throw new PropertyNotValidException(
                    ConstantsUtils.INVALID_ENVIRONMENT_NAME);
        }

        if (!Character.isUpperCase(this.getEnvironment_name().charAt(0))) {
            throw new PropertyNotValidException(
                    ConstantsUtils.ENVIRONMENT_NAME_NOT_CAPITALIZED);
        }

        if (this.getEnvironment_name().length() > 30) {
            throw new PropertyNotValidException(
                    ConstantsUtils.ENVIRONMENT_NAME_TOO_LONG);
        }
    }

    public void validateEvironmentWidth()
            throws PropertyNotValidException {
        if (this.getEnvironment_width() == 0) {
            throw new PropertyNotValidException(
                    ConstantsUtils.INVALID_ENVIRONMENT_WIDTH);
        }

        if (this.getEnvironment_width() > 25) {
            throw new PropertyNotValidException(
                    ConstantsUtils.ENVIRONMENT_WIDTH_TOO_BIG);
        }
    }

    public void validateEvironmentLength()
            throws PropertyNotValidException {
        if (this.getEnvironment_length() == 0) {
            throw new PropertyNotValidException(
                    ConstantsUtils.INVALID_ENVIRONMENT_LENGTH);
        }

        if (this.getEnvironment_length() > 33) {
            throw new PropertyNotValidException(
                    ConstantsUtils.ENVIRONMENT_LENGTH_TOO_BIG);
        }
    }
}
