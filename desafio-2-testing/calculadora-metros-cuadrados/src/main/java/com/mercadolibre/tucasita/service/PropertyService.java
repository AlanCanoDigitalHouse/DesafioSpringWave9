package com.mercadolibre.tucasita.service;

import com.mercadolibre.tucasita.dto.DistrictDTO;
import com.mercadolibre.tucasita.dto.PropertyDTO;
import com.mercadolibre.tucasita.dto.response.PropertyInfoResponseDTO;
import com.mercadolibre.tucasita.handler.PropertyInfoHandler;
import com.mercadolibre.tucasita.repositories.PriceRepositoryImpl;
import org.springframework.stereotype.Service;

/**
 * This service provides information for a property using the dto passed as parameter.
 */
@Service
public class PropertyService {
    private PropertyInfoHandler handler;
    private PriceRepositoryImpl priceRepository;

    public PropertyService(PropertyInfoHandler handler, PriceRepositoryImpl priceRepository) {
        this.handler = handler;
        this.priceRepository = priceRepository;
    }

    /** Builds a {@link PropertyInfoResponseDTO} containing the list of environments with their respective name and
     * measures, the bigger environment, the total square feet of the property and final price.
     * @param propertyDTO dto containing base information
     * @return a {@link PropertyInfoResponseDTO}
     */
    public PropertyInfoResponseDTO getInfo(PropertyDTO propertyDTO) {
        PropertyInfoResponseDTO propertyInfo = new PropertyInfoResponseDTO();

        //Get environment info list
        propertyInfo.setEnvironmentList(handler.getEnvironmentInfoList(propertyDTO.getEnvironments()));

        //Get bigger environment
        propertyInfo.setBiggerEnvironment(handler.getBiggerEnvironment(propertyInfo.getEnvironmentList()));

        //Get total square feet
        propertyInfo.setTotalSquareFeet(handler.calculatePropertyTotalSquareFeet(propertyInfo.getEnvironmentList()));

        //Get district price from database
        Double districtPrice = this.getDistrictPrice(propertyDTO.getDistrict().getDistrict_name());

        //Get total price
        propertyInfo.setTotalPrice(propertyInfo.getTotalSquareFeet() * districtPrice);

        return propertyInfo;

    }

    /** Obtains the price for a desired district using its name.
     * @param district String containing district name
     * @return a Double with the district price
     */
    private Double getDistrictPrice(String district) {
        DistrictDTO districtDTO = this.priceRepository.findPriceLocation(district);
        return districtDTO.getDistrict_price();
    }


}
