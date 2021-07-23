package com.mercadolibre.tucasitatasaciones.repositories;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mercadolibre.tucasitatasaciones.dtos.req.DistrictDTO;
import com.mercadolibre.tucasitatasaciones.exception.DistrictNotFoundException;
import com.mercadolibre.tucasitatasaciones.repositories.interfaces.DistrictRepository;
import org.springframework.stereotype.Repository;

import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;

@Repository
public class DistrictRepositoryImpl implements DistrictRepository {

    private List<DistrictDTO> districts = new LinkedList<>();

    public DistrictRepositoryImpl() {
        loadDB();
    }

    /**
     * Method used to load district.json from the resources folder into <b>List<DistrictDTO> districts</b>
     */
    private void loadDB() {
        try {
            InputStream in = this.getClass().getResourceAsStream("/static/districts.json");
            ObjectMapper mapper = new ObjectMapper();
            JsonNode jsonNode = mapper.readValue(in, JsonNode.class);
            districts = mapper.convertValue(jsonNode, new TypeReference<>() {});
        } catch (Exception ex) {
            System.err.println("Cannot load DISTRICTS db: " + ex.getMessage());
        }
    }

    @Override
    public DistrictDTO findDistrictBy(String name) throws DistrictNotFoundException {
        return districts.stream()
                .filter(d -> d.getDistrictName().equals(name))
                .findFirst()
                .orElseThrow(() -> new DistrictNotFoundException(name));
    }

}
