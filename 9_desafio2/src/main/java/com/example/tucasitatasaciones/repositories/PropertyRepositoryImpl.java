package com.example.tucasitatasaciones.repositories;

import com.example.tucasitatasaciones.dtos.DistrictDTO;
import com.example.tucasitatasaciones.dtos.PropertyDTO;
import com.example.tucasitatasaciones.exceptions.DistrictException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Repository;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.example.tucasitatasaciones.handlers.RepositoryHandlers.loadDatabase;

@Repository
public class PropertyRepositoryImpl implements PropertyRepository{
    private List<DistrictDTO> districtDB;

    public PropertyRepositoryImpl(List<DistrictDTO> districtDB) {
        this.districtDB = loadDatabase();
    }

    @Override
    public Boolean checkIfDistrictExistsFor(PropertyDTO property) {
        String nameOfDistrict = property.getDistrict().getDistrict_name();
        DistrictDTO districtFound = districtDB.stream().filter(districtDTO -> districtDTO.getDistrict_name().equals(nameOfDistrict)).findFirst().orElse(null);
        return Objects.nonNull(districtFound);
    }
}
