package com.mercadolibre.tucasitatasaciones.unit.utils;

import com.mercadolibre.tucasitatasaciones.dtos.req.DistrictDTO;
import com.mercadolibre.tucasitatasaciones.dtos.req.EnvironmentDTO;
import com.mercadolibre.tucasitatasaciones.dtos.req.PropertyDTO;
import com.mercadolibre.tucasitatasaciones.exception.ErrorMessage;
import org.springframework.http.HttpStatus;

import java.util.*;

public class TestUtils {

    /**
     * Create a property for testing propose.
     * <p> - Total Dimension: <b>245 m2</b></p>
     * <p> - Total Price: <b>245.000 USD</b></p>
     * <p> - Biggest Room: <b>Main Bedroom</b></p>
     *
     * @return PropertyDTO
     */
    public static PropertyDTO createProperty() {
        return PropertyDTO.builder()
                .propName("Porsche Design Tower - 10th F")
                .district(new DistrictDTO("Sunny Isles Beach", 1000D))
                .environments(Arrays.asList(
                        EnvironmentDTO.builder()
                                .environmentName("Kitchen")
                                .environmentLength(6D)
                                .environmentWidth(8D)
                                .build(),
                        EnvironmentDTO.builder()
                                .environmentName("Main Bedroom")
                                .environmentLength(12D)
                                .environmentWidth(8D)
                                .build(),
                        EnvironmentDTO.builder()
                                .environmentName("Secondary Bedroom 1")
                                .environmentLength(5D)
                                .environmentWidth(6D)
                                .build(),
                        EnvironmentDTO.builder()
                                .environmentName("Secondary Bedroom 2")
                                .environmentLength(5D)
                                .environmentWidth(6D)
                                .build(),
                        EnvironmentDTO.builder()
                                .environmentName("Bathroom (Main Bedroom)")
                                .environmentLength(5D)
                                .environmentWidth(4D)
                                .build(),
                        EnvironmentDTO.builder()
                                .environmentName("Bathroom (Bedroom 1)")
                                .environmentLength(3.5D)
                                .environmentWidth(3D)
                                .build(),
                        EnvironmentDTO.builder()
                                .environmentName("Bathroom (Bedroom 2)")
                                .environmentLength(3.5D)
                                .environmentWidth(3D)
                                .build()
                )).build();
    }

    /**
     * Creates a EnvironmentDTO. This EnvironmentDTO is the biggest in the PropertyDTO
     * returned by TestUtils.createProperty() method.
     *
     * @return EnvironmentDTO
     */
    public static EnvironmentDTO createBiggestRoom() {
        return EnvironmentDTO.builder()
                .environmentName("Main Bedroom")
                .environmentLength(12D)
                .environmentWidth(8D)
                .build();
    }

    /**
     * Generates an ErrorMessage object for testing @NotBlank on propName attribute in PropertyDTO
     *
     * @return ErrorMessage
     */
    public static ErrorMessage createValidationErrorForNullPropName() {
        Integer status = HttpStatus.BAD_REQUEST.value();
        String error = "ValidationException";
        String message = "the request has validation errors";
        Map<String, String> details = new HashMap<>();
        details.put("propName", "El nombre de la propiedad no puede estar vacío.");
        return new ErrorMessage(status, error, message, details);
    }

    /**
     * Generates an ErrorMessage object for testing HttpMessageNotReadableException
     *
     * @return ErrorMessage
     */
    public static ErrorMessage createValidationErrorMalformedPayload() {
        Integer status = HttpStatus.BAD_REQUEST.value();
        String error = "HttpMessageNotReadableException";
        String message = "the json payload is not well formed";
        return new ErrorMessage(status, error, message, null);
    }

}
