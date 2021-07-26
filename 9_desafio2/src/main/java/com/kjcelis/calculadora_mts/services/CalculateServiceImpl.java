package com.kjcelis.calculadora_mts.services;

import com.kjcelis.calculadora_mts.dto.DistrictDTO;
import com.kjcelis.calculadora_mts.dto.request.EnvironmentDTO;
import com.kjcelis.calculadora_mts.dto.request.HouseRequestDTO;
import com.kjcelis.calculadora_mts.dto.response.HouseResponseDTO;
import com.kjcelis.calculadora_mts.exceptions.NotFoundDistricException;
import com.kjcelis.calculadora_mts.repository.DistrictRepository;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CalculateServiceImpl implements CalculateService {

    private final DistrictRepository priceRepository;

    public CalculateServiceImpl(DistrictRepository priceRepository) {
        this.priceRepository = priceRepository;
    }

    /**
     * Calculate the area of a space with length and width
     *
     * @param length
     * @param width
     * @return double
     */
    public static double squareMeter(Double length, Double width) {
        return length * width;
    }

    /**
     * Calculate the total square meters of a property
     *
     * @param environments
     * @return double
     */
    public double totalAmountMts(List<EnvironmentDTO> environments) {
        Double total = 0.0;
        for (EnvironmentDTO e : environments) {
            total += squareMeter(e.getEnvironment_width(), e.getEnvironment_length());
        }
        return total;
    }

    /**
     * Returns the name of the largest environment in a property
     *
     * @param environments
     * @return String
     */
    public String largerEnvironment(List<EnvironmentDTO> environments) {
        EnvironmentDTO environmentDTO = environments.get(0);
        Double mts = squareMeter(environmentDTO.getEnvironment_width(), environmentDTO.getEnvironment_length());
        for (int i = 1; i < environments.size(); i++) {
            if (squareMeter(environments.get(i).getEnvironment_width(), environments.get(i).getEnvironment_length()) > mts) {
                environmentDTO = environments.get(i);
                mts = squareMeter(environments.get(i).getEnvironment_width(), environments.get(i).getEnvironment_length());
            }
        }
        return environmentDTO.getEvironment_name();
    }

    /**
     * Returns the name of each environment together with its total square meters
     *
     * @param environments
     * @return Map<String, Double>
     */
    public Map<String, Double> environmentsMts(List<EnvironmentDTO> environments) {
        Map<String, Double> squareMetersEnvs = new HashMap<>();
        for (EnvironmentDTO e : environments) {
            squareMetersEnvs.put(e.getEvironment_name(), (e.getEnvironment_width() * e.getEnvironment_length()));
        }
        return squareMetersEnvs;
    }

    /**
     * Calculate the price of the property given its square meters and the neighborhood in which it is located
     *
     * @param result
     * @param districtName
     * @param districtPrice
     * @return double
     * @throws NotFoundDistricException
     */
    public double calculatePrice(double result, String districtName, double districtPrice) throws NotFoundDistricException {
        double price = 0;
        DistrictDTO districtDTO = priceRepository.findDistrictRepo(districtName, districtPrice);
        if (districtDTO != null) {
            price = result * districtDTO.getPrice();
        }
        return price;
    }

    /**
     * Returns a constructed object of type HouseResponseDTO
     *
     * @param house
     * @return HouseResponseDTO
     * @throws NotFoundDistricException
     */
    @Override
    public HouseResponseDTO calculate(HouseRequestDTO house) throws NotFoundDistricException {
        return new HouseResponseDTO(
                house.getProp_name(),
                new DistrictDTO(house.getDistrict_name(), house.getDistrict_price()),
                totalAmountMts(house.getEnvironments()),
                calculatePrice(totalAmountMts(house.getEnvironments()), house.getDistrict_name(), house.getDistrict_price()),
                largerEnvironment(house.getEnvironments()),
                environmentsMts(house.getEnvironments())
        );
    }
}
