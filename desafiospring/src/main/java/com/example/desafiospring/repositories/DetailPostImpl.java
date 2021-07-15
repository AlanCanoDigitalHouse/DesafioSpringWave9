package com.example.desafiospring.repositories;

import com.example.desafiospring.dtos.BuyerDTO;
import com.example.desafiospring.dtos.DetailPostDTO;
import com.example.desafiospring.utilities.FakeBD;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DetailPostImpl implements IDetailPost {

    @Autowired
    private FakeBD fakeBD;

    @Override
    public DetailPostDTO findDetailById(Integer productId) {
        Optional<DetailPostDTO> item = fakeBD.getProductList().stream()
                .filter(detailPostDTO -> detailPostDTO.getProductID().equals(productId)).findFirst();
        if(item.isPresent()){
            return item.get();
        }else{
            return null;
        }
    }
}
