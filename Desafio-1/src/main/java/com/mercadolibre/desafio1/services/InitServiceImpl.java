package com.mercadolibre.desafio1.services;

import com.mercadolibre.desafio1.dto.UserDTO;
import com.mercadolibre.desafio1.repositories.interfaces.UserRepository;
import com.mercadolibre.desafio1.services.interfaces.InitService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Service
public class InitServiceImpl implements InitService {
    private final UserRepository userRepository;

    public InitServiceImpl(@Qualifier("UserRepository") UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void initBdd() {
        Map<Integer, UserDTO> usersNew = new HashMap<>();

        //create buyers
        UserDTO buyer1 = new UserDTO(1,"Comprador 1",false, new ArrayList<>(), new ArrayList<>());
        UserDTO buyer2 = new UserDTO(2,"Comprador 2",false, new ArrayList<>(), new ArrayList<>());
        UserDTO buyer3 = new UserDTO(3,"Comprador 3",false, new ArrayList<>(), new ArrayList<>());

        //create sellers
        UserDTO seller1 = new UserDTO(4,"Vendedor 1",true, new ArrayList<>(), new ArrayList<>());
        UserDTO seller2 = new UserDTO(5,"Vendedor 2",true, new ArrayList<>(), new ArrayList<>());

        usersNew.put(buyer1.getUserId(),buyer1);
        usersNew.put(buyer2.getUserId(),buyer2);
        usersNew.put(buyer3.getUserId(),buyer3);
        usersNew.put(seller1.getUserId(),seller1);
        usersNew.put(seller2.getUserId(),seller2);

        userRepository.initBdd(usersNew);
    }
}
