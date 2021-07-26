package com.example.tucasitatasaciones.repositories;

import com.example.tucasitatasaciones.dtos.DistrictDTO;
import com.example.tucasitatasaciones.dtos.PropertyDTO;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Objects;

import static com.example.tucasitatasaciones.handlers.RepositoryHandlers.loadDatabase;

@Repository
public class DistrictRepositoryImpl implements DistrictRepository {
    private final List<DistrictDTO> districtDB;

    public DistrictRepositoryImpl() {
        this.districtDB = loadDatabase();
    }

    @Override
    public Boolean checkIfDistrictExistsFor(PropertyDTO property) {
        String nameOfDistrict = property.getDistrict().getDistrict_name();
        DistrictDTO districtFound = districtDB.stream().filter(districtDTO -> districtDTO.getDistrict_name().equals(nameOfDistrict)).findFirst().orElse(null);
        return Objects.nonNull(districtFound);
    }
}
