package com.example.desafiospring.controllers;

import com.example.desafiospring.dtos.BuyerDTO;
import com.example.desafiospring.exceptions.UserNotFoundByIdException;
import com.example.desafiospring.repositories.IBuyerRepository;
import com.example.desafiospring.utilities.FakeBD;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/util")
public class UtilitiController {

    private IBuyerRepository iBuyerRepository;
    private FakeBD fakeBD;

    public UtilitiController(IBuyerRepository iBuyerRepository, FakeBD fakeBD){
        this.iBuyerRepository = iBuyerRepository;
        this.fakeBD = fakeBD;
    }

    @GetMapping("/loadbd")
    public void loadbd(){
        this.fakeBD.init();;
    }

    @GetMapping("/TEST")
    public BuyerDTO test(){
        try {
            return this.iBuyerRepository.findUserById(1);
        } catch (UserNotFoundByIdException e) {
            e.printStackTrace();
        }
        return null;
    }

}
