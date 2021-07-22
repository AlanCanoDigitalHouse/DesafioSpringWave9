package com.mercadolibre.desafio1.repositories;

import com.mercadolibre.desafio1.dto.response.ProductResponseDTO;
import com.mercadolibre.desafio1.dto.response.PublicationResponseDTO;
import com.mercadolibre.desafio1.repositories.interfaces.PublicationRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

@Repository("PublicationRepository")
public class PublicationRepositoryImpl implements PublicationRepository {
    private Map<Integer, PublicationResponseDTO> publications = new HashMap<>();
    private static final AtomicInteger publicationIdCount = new AtomicInteger(0);

    @Override
    public PublicationResponseDTO getPublicationById(Integer productId) {
        PublicationResponseDTO result = null;
        if(this.publications.containsKey(productId)){
            result = this.publications.get(productId);
        }
        return result;
    }

    @Override
    public PublicationResponseDTO addPublication(Integer userId, LocalDate date, ProductResponseDTO detail, Integer category, Double price, Boolean hasPromo, Double discount) {
        PublicationResponseDTO newPublication = new PublicationResponseDTO(publicationIdCount.addAndGet(1),userId,date,detail,category,price,hasPromo,discount);
        publications.put(newPublication.getId_post(),newPublication);

        return newPublication;
    }

    @Override
    public ArrayList<PublicationResponseDTO> getPublicationsByUserId(Integer userId, Boolean promo) {
        ArrayList<PublicationResponseDTO> result = new ArrayList<>();

        for(Map.Entry<Integer, PublicationResponseDTO> entry : publications.entrySet()) {
            PublicationResponseDTO value = new PublicationResponseDTO(entry.getValue());

            if(value.getUserId().equals(userId)){
                value.setUserId(null);

                if(promo){
                    if(!Objects.isNull(value.getHasPromo()) && value.getHasPromo()){
                        result.add(value);
                    }
                }else{
                    value.setHasPromo(null);
                    result.add(value);
                }
            }
        }

        return result;
    }


}
