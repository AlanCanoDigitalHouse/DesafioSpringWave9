package com.example.desafiospring.repositories;

import com.example.desafiospring.dtos.SellerDTO;
import com.example.desafiospring.exceptions.UserNotFoundByIdException;
import com.example.desafiospring.utilities.FakeBD;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@NoArgsConstructor
public class SellerRepositoryImpl implements ISellerRepository{

    @Autowired
    private FakeBD fakeBD;

    @Override
    public SellerDTO findSellerById(Integer sellerId) throws UserNotFoundByIdException {
        Optional<SellerDTO> item = fakeBD.getSellerDTOList().stream()
                .filter(sellerDTO -> sellerDTO.getUserID().equals(sellerId)).findFirst();
        if(item.isPresent()){
            return item.get();
        }else{
            throw new UserNotFoundByIdException();
        }
    }
}
