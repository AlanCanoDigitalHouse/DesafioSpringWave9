package com.mercadolibre.desafio1.controllers;

import com.mercadolibre.desafio1.services.interfaces.InitService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
public class InitController {
    private final InitService initService;

    public InitController(InitService initService) {
        this.initService = initService;
    }


    @PostMapping("/init-bdd")
    public void follow(HttpServletResponse response) throws IOException {
        initService.initBdd();
        response.sendError(HttpStatus.OK.value());
    }
}
