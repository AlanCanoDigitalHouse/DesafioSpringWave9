package com.meli.joescaos.desafiotesting.repositories.Impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.meli.joescaos.desafiotesting.dto.PriceDTO;
import com.meli.joescaos.desafiotesting.exceptions.DistrictNotFoundException;
import com.meli.joescaos.desafiotesting.exceptions.PriceErrorException;
import com.meli.joescaos.desafiotesting.repositories.PriceRepository;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.*;

@Repository
public class PriceRepositoryImpl implements PriceRepository {

    List<PriceDTO> priceDTOList;

    public PriceRepositoryImpl(){
        ObjectMapper mapper = new ObjectMapper();
        try {
            priceDTOList = Arrays.asList(mapper.readValue(new ClassPathResource("/static/price.json").getFile(), PriceDTO[].class));
        } catch (IOException e){
            e.printStackTrace();
            priceDTOList = new ArrayList<>();
        }
    }

    public List<PriceDTO> getDistrictList(){
        return priceDTOList;
    }

    public PriceDTO districtExists(String districtName){
        return getDistrictList().stream().filter(district ->
                district.getLocation().equals(districtName))
                .findFirst()
                .orElseThrow(() -> new DistrictNotFoundException());

    }

    public double getDistrictPrice(String districtName){
        PriceDTO priceDTO = getDistrictList().stream().filter(district ->
                district.getLocation().equals(districtName))
                .findFirst()
                .orElseThrow(() -> new PriceErrorException());
        return priceDTO.getPrice();
    }

}
