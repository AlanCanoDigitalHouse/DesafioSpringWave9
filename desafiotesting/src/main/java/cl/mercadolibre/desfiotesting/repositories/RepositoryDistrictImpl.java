package cl.mercadolibre.desfiotesting.repositories;

import cl.mercadolibre.desfiotesting.DTOs.DistrictDTO;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Repository;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Repository
public class RepositoryDistrictImpl implements IRepositoryDistrict{


    @Override
    public DistrictDTO findDistrictByDTO(DistrictDTO districtDTO){
        List<DistrictDTO> districtList = loadBD();
        DistrictDTO result = null;
        if(districtList != null){
            Optional<DistrictDTO> district = districtList.stream()
                    .filter(districtAux -> districtAux.getName().equals(districtDTO.getName()) && districtAux.getPrice().equals(districtDTO.getPrice()))
                    .findFirst();
            if(district.isPresent()) result = district.get();
        }
        return result;
    }

    private List<DistrictDTO> loadBD(){
        File file = null;
        try{
            file = ResourceUtils.getFile("classpath:districts.json");
        }catch (IOException e){
            e.printStackTrace();
        }
        ObjectMapper objectMapper = new ObjectMapper();
        TypeReference<List<DistrictDTO>> typeReference = new TypeReference<>(){};
        List<DistrictDTO> districtList = null;
        try{
            districtList = objectMapper.readValue(file, typeReference);
        }catch (IOException exception){
            exception.printStackTrace();
        }
        return districtList;
    }

}
