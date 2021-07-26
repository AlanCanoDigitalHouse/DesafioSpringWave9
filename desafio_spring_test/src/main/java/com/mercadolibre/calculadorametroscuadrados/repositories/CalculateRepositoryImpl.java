package com.mercadolibre.calculadorametroscuadrados.repositories;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mercadolibre.calculadorametroscuadrados.dto.EnvironmentDTO;
import com.mercadolibre.calculadorametroscuadrados.dto.HouseDTO;
import com.mercadolibre.calculadorametroscuadrados.dto.LocationDTO;
import com.mercadolibre.calculadorametroscuadrados.dto.repository.EnvironmentRepoDTO;
import com.mercadolibre.calculadorametroscuadrados.dto.repository.HouseRepositoryDTO;
import com.mercadolibre.calculadorametroscuadrados.dto.response.EnvironmentResponseDTO;
import com.mercadolibre.calculadorametroscuadrados.dto.response.HouseResponseDTO;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Repository;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

@Repository
public class CalculateRepositoryImpl implements ICalculateRepository{

    private String SCOPE;

    private Set<HouseRepositoryDTO> houses;

    private Set<LocationDTO> strata;

    public CalculateRepositoryImpl(){
        Properties properties = new Properties();
        try {
            properties.load(new ClassPathResource("application.properties").getInputStream());
            this.SCOPE = properties.getProperty("api.scope");
            this.loadData();
            this.loadStrataData();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /* TODO: Preguntar si un ambiente existe en el
    *   repositorio, debe ingresar el nombre del barrio
    *   a traves de la variable tipo String llamada location*/
    @Override
    public boolean ifDistrictAreaExist(String location) {
        boolean exist = false;
        Optional<LocationDTO> locationTemp = findStratumByLocationName(location);
        if(locationTemp.isPresent())exist = true;
        return exist;
    }

    /* TODO: Crear una casa tipo repositorio HouseRepositoryDTO
    *   a partir de una houseDTO y una HouseResponseDTO
    *   para luego ser enviado al metodo save y guardar la
    *   info en la db*/
    @Override
    public void saveHouse(HouseDTO houseDTO, HouseResponseDTO houseResponseDTO) {
        HouseRepositoryDTO houseRepository = new HouseRepositoryDTO();
        houseRepository.setProp_name(houseDTO.getProp_name());
        houseRepository.setDistrict_name(houseDTO.getDistrict_name());
        houseRepository.setDistrict_price(houseDTO.getDistrict_price());
        houseRepository.setProp_price(houseResponseDTO.getProp_price());
        houseRepository.setProp_area(houseResponseDTO.getProp_area());
        List<EnvironmentRepoDTO> environments = new ArrayList<>();
        for(EnvironmentDTO env: houseDTO.getEnvironments()){
            for(EnvironmentResponseDTO envRes: houseResponseDTO.getEnvironments()){
                if(env.getEnvironment_name().equals(envRes.getEnvironment_name())){
                    environments.add(new EnvironmentRepoDTO(env.getEnvironment_name(), env.getEnvironment_width(),env.getEnvironment_length(),envRes.getEnvironment_area()));
                }
            }
        }
        houseRepository.setEnvironments(environments);
        save(houseRepository);
    }
    /* TODO: Metodo para guardar una casa tipo repositorio
    *   en el db, recibe como parametro un HouseRepository*/
    private void save(HouseRepositoryDTO house) {
        if(exists(house.getProp_name()))
            this.delete(house.getProp_name());
        houses.add(house);
        this.saveData();
    }

    /* TODO: Metodo para eliminar una casa de la db
    *   que recibe como parametro el nombre de la casa
    *   en una variable tipo String de nombre prop_name*/
    @Override
    public boolean delete(String prop_name) {
        boolean response = false;
        Optional<HouseRepositoryDTO> house= houses
                .stream()
                .filter(hos -> hos.getProp_name().equals(prop_name))
                .findFirst();
        if(house.isPresent()){
            houses.remove(house.get());
            response = true;
            this.saveData();
        }
        return response;
    }
    /* TODO: Metodo creado para saber si una casa ya
    *   se encuentra guardada en nuestra db, en caso de que
    *   ya se encuentre alojada en nuestra db retorna un true,
    *   en caso contrario retorna un false*/
    @Override
    public boolean exists(String house) {
        boolean response = false;
        Optional<HouseRepositoryDTO> houseTemp = houses.stream()
                .filter(hos -> hos.getProp_name().equals(house))
                .findFirst();
        if(houseTemp.isPresent())response = true;
        return response;
    }

    /* TODO: Metodo para buscar un barrio en la db, puede
    *   o no retornar algo, por eso el Optional*/
    @Override
    public Optional<LocationDTO> findStratumByLocationName(String locationName) {
        loadStrataData();
        return strata.stream()
                .filter(str -> str.getLocation().equals(locationName))
                .findFirst();
    }

    /* TODO: Metodo para leer la data de casas de nuestro archivo
    *   json y ponerla en una Set array */
    private void loadData(){
        Set<HouseRepositoryDTO> loadedData = new HashSet<>();

        ObjectMapper objectMapper = new ObjectMapper();
        File file;
        try {
            file = ResourceUtils.getFile("./src/" + SCOPE + "/resources/houses.json");
            loadedData = objectMapper.readValue(file, new TypeReference<Set<HouseRepositoryDTO>>(){});
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("Failed while initializing DB, check your resources files");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Failed while initializing DB, check your JSON formatting.");
        }

        this.houses = loadedData;
    }

    /* TODO: Metodo para leer la data de barrios de nuestro archivo
     *   json y ponerla en una Set array */
    private void loadStrataData(){
        Set<LocationDTO> loadedData = new HashSet<>();
        ObjectMapper objectMapper = new ObjectMapper();
        File file;
        try {
            file = ResourceUtils.getFile("./src/" + SCOPE + "/resources/price.json");
            loadedData = objectMapper.readValue(file, new TypeReference<Set<LocationDTO>>(){});
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("Failed while initializing DB, check your resources files");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Failed while initializing DB, check your JSON formatting.");
        }
        this.strata = loadedData;
    }


    /* TODO: Metodo para actualizar el archivo .json con nuestro
    *   Set array, en este caso sobre escribe el .json con nuestro Set array
    *   convertido en un json */
    private void saveData(){
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            File file = ResourceUtils.getFile("./src/" + SCOPE + "/resources/houses.json");
            objectMapper.writeValue(file, this.houses);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("Failed while writing to DB, check your resources files");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Failed while writing to DB, check your JSON formatting.");
        }
    }
}
