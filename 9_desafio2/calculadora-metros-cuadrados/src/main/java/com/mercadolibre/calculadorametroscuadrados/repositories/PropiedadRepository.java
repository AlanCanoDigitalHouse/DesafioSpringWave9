package com.mercadolibre.calculadorametroscuadrados.repositories;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mercadolibre.calculadorametroscuadrados.dto.BarrioDTO;
import com.mercadolibre.calculadorametroscuadrados.dto.PropiedadDTO;
import com.mercadolibre.calculadorametroscuadrados.exceptions.BarrioNoEncontradoException;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Repository
@Data
public class PropiedadRepository {
    PropiedadDTO propiedad;

    public BarrioDTO inicializar(PropiedadDTO propiedad) throws BarrioNoEncontradoException {
        List<BarrioDTO> barrioDTOS=cargarBaseDeDatos();
        this.propiedad=new PropiedadDTO();
        if (barrioDTOS.contains(propiedad.getDistrict())){
            this.propiedad=propiedad;
            return propiedad.getDistrict();
        }
        throw new BarrioNoEncontradoException();

    }


    private ArrayList<BarrioDTO> cargarBaseDeDatos(){
        File file = null;
        try{
            file = ResourceUtils.getFile("classpath:price.json");
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }
        return mapObject(file);
    }

    private ArrayList<BarrioDTO> mapObject(File file){
        ObjectMapper objectMapper = new ObjectMapper();
        TypeReference<ArrayList<BarrioDTO>> typeReference = new TypeReference<>(){};
        ArrayList<BarrioDTO> barrioDTO =null;
        try {
            barrioDTO = objectMapper.readValue(file, typeReference);
        }catch (IOException e){
            e.printStackTrace();
        }
        return barrioDTO;
    }
}
