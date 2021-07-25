package com.meli.tucasita.repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.meli.tucasita.dto.Distrito;
import com.meli.tucasita.exception.DataBaseException;
import com.meli.tucasita.exception.NoDistrictFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Repository
public class CasaRepository implements CasaRepositoryInterface{

    private static final Logger LOGGER = LoggerFactory.getLogger(CasaRepository.class);

    @Override
    public Distrito obtenerDistrito(String districtName) throws NoDistrictFoundException, DataBaseException {

        List<Distrito> lista = loadDatabase();

        if (Objects.nonNull(lista)) {
            LOGGER.info("Buscando el distrito: {}", districtName);
            Optional<Distrito> item = lista.stream().filter(l -> l.getDistrictName().equals(districtName)).findFirst();
            return item.orElseThrow(() -> new NoDistrictFoundException(districtName));
        } else
            throw new DataBaseException();
    }

    private List<Distrito> loadDatabase() {
        LOGGER.info("Cargando base de datos");
        File file = null;
        try {
            file = ResourceUtils.getFile("classpath:static/distritos.json");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return mapObject(file);
    }

    private List<Distrito> mapObject(File file) {
        ObjectMapper objectMapper = new ObjectMapper();
        TypeReference<List<Distrito>> typeReference = new TypeReference<>() {
        };
        List<Distrito> usersDto = null;
        try {
            usersDto = objectMapper.readValue(file, typeReference);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return usersDto;
    }
}
