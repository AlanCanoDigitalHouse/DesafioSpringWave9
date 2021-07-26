package com.mercadolibre.calculadorametroscuadrados.repository;

import com.mercadolibre.calculadorametroscuadrados.dto.DistrictDTO;
import com.mercadolibre.calculadorametroscuadrados.exception.apiValidationException.DistrictNotFoundException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public interface IDistrictRepository {
    public void findNameDistrict(String nameDistrict, String path) throws DistrictNotFoundException;
    public List<DistrictDTO> loadDatabase(String path ) throws FileNotFoundException;
    public List<DistrictDTO> mapObject(File file) throws IOException;
}
