package com.mercadolibre.tucasita.utils;

import com.mercadolibre.tucasita.dto.DistrictDTO;
import com.mercadolibre.tucasita.dto.EnvironmentDTO;
import com.mercadolibre.tucasita.dto.PropertyDTO;
import com.mercadolibre.tucasita.dto.response.EnvironmentInfoResponseDTO;

import java.util.ArrayList;
import java.util.List;

/**
 * This class provides basic stunt object for testing purposes. It also provides methods to automatically create
 * a Property with a list of environments. They can be called by the testing classes.
 */
public class TestGeneratorUtils {
    public static String house = "Casa";
    public static String apartment = "Departamento";

    public static DistrictDTO palermo = new DistrictDTO("Palermo", 80D);
    public static DistrictDTO recoleta = new DistrictDTO("Recoleta", 100D);
    public static DistrictDTO caballito = new DistrictDTO("Caballito", 50D);

    public static EnvironmentDTO kitchen = new EnvironmentDTO("Cocina", 3D, 5D);
    public static EnvironmentDTO livingroom = new EnvironmentDTO("Living", 4D, 5D);
    public static EnvironmentDTO bedroom = new EnvironmentDTO("Dormitorio", 4D, 4D);
    public static EnvironmentDTO bathroom = new EnvironmentDTO("Baño", 3D, 3D);

    public static EnvironmentInfoResponseDTO kitchenResponse =
            new EnvironmentInfoResponseDTO(kitchen.getEnvironment_name(),
                    kitchen.getEnvironment_length() * kitchen.getEnvironment_width());
    public static EnvironmentInfoResponseDTO bedroomResponse =
            new EnvironmentInfoResponseDTO(bedroom.getEnvironment_name(),
                    bedroom.getEnvironment_length() * bedroom.getEnvironment_width());
    public static EnvironmentInfoResponseDTO livingRoomResponse =
            new EnvironmentInfoResponseDTO(livingroom.getEnvironment_name(),
                    livingroom.getEnvironment_length() * livingroom.getEnvironment_width());
    public static EnvironmentInfoResponseDTO bathroomResponse =
            new EnvironmentInfoResponseDTO(bathroom.getEnvironment_name(),
                    bathroom.getEnvironment_length() * bathroom.getEnvironment_width());

    /**
     * @return a {@link PropertyDTO} with one environment
     */
    public static PropertyDTO createPropertyWith1Environment() {
        List<EnvironmentDTO> environmentList = new ArrayList<>();

        environmentList.add(bedroom);

        return new PropertyDTO(apartment, recoleta, environmentList);
    }

    /**
     * @return a {@link PropertyDTO} with fout environments
     */
    public static PropertyDTO createPropertyWith4Environments() {
        List<EnvironmentDTO> environmentList = new ArrayList<>();

        environmentList.add(kitchen);
        environmentList.add(livingroom);
        environmentList.add(bedroom);
        environmentList.add(bathroom);

        return new PropertyDTO(house, palermo, environmentList);
    }

    /**
     * @return The amount of square feet of all the environments provided by this class.
     */
    public static Double getSumOfAllEnvsSquareFeet() {
        return bathroom.getEnvironment_length() * bathroom.getEnvironment_width() +
                bedroom.getEnvironment_length() * bedroom.getEnvironment_width() +
                livingroom.getEnvironment_length() * livingroom.getEnvironment_width() +
                kitchen.getEnvironment_length() * kitchen.getEnvironment_width();
    }

}
