package com.example.desafiospring.repositories;

import com.example.desafiospring.dtos.BuyerDTO;
import com.example.desafiospring.exceptions.UserNotFoundByIdException;
import com.example.desafiospring.utilities.FakeBD;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@NoArgsConstructor
public class BuyerRepositoryImpl implements IBuyerRepository{

    @Autowired
    private FakeBD fakeBD;

    @Override
    public BuyerDTO findUserById(Integer id) throws UserNotFoundByIdException {
        Optional<BuyerDTO> item = fakeBD.getBuyerDTOList().stream()
                .filter(buyerDTO -> buyerDTO.getUserID().equals(id)).findFirst();
        if(item.isPresent()){
            return item.get();
        }else{
            throw new UserNotFoundByIdException();
        }
    }
}
