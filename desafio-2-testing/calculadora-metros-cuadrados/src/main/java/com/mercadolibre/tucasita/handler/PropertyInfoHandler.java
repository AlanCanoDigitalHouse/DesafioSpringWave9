package com.mercadolibre.tucasita.handler;

import com.mercadolibre.tucasita.dto.EnvironmentDTO;
import com.mercadolibre.tucasita.dto.response.EnvironmentInfoResponseDTO;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * This handler component performs basic business operations such as calculate the total square feet of a list of
 * environments, or build the response with the environment info.
 */
@Component
@NoArgsConstructor
public class PropertyInfoHandler {

    /** Returns a list of {@link EnvironmentInfoResponseDTO}
     * @param environments list of {@link EnvironmentDTO}
     * @return List o EnvironmentInfoResponseDTO
     */
    public List<EnvironmentInfoResponseDTO> getEnvironmentInfoList(List<EnvironmentDTO> environments) {
        List<EnvironmentInfoResponseDTO> environmentInfoList = new ArrayList<>();
        environments.forEach(env -> environmentInfoList.add(getEnvironmentInfo(env)));
        return environmentInfoList;
    }

    /** Builds an {@link EnvironmentInfoResponseDTO} containing an environment name and total sqare feet.
     * @param env an EnvironmentDTO
     * @return an environment info response
     */
    private EnvironmentInfoResponseDTO getEnvironmentInfo(EnvironmentDTO env) {
        return new EnvironmentInfoResponseDTO(env.getEnvironment_name(),
                env.getEnvironment_length() * env.getEnvironment_width());
    }

    /** Calculates the bigger environment of the passed list.
     * @param environments an {@link EnvironmentInfoResponseDTO} list
     * @return the bigger one comparing its total square feet
     */
    public EnvironmentInfoResponseDTO getBiggerEnvironment(List<EnvironmentInfoResponseDTO> environments) {
        return environments.stream().max(Comparator.comparing(EnvironmentInfoResponseDTO::getTotalSquareFeet)).get();
    }

    /** Calculates the total square feet of a list of environments.
     * @param environments a {@link EnvironmentInfoResponseDTO} list
     * @return the total square feet
     */
    public Double calculatePropertyTotalSquareFeet(List<EnvironmentInfoResponseDTO> environments) {
        return environments.stream().collect(Collectors.summingDouble(EnvironmentInfoResponseDTO::getTotalSquareFeet));
    }

}
