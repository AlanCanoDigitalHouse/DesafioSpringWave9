package com.jbianchini.meli.socialmeli.controller;


import com.jbianchini.meli.socialmeli.service.handler.Initializer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/init")
public class InitController {

    Initializer initializer;

    public InitController(Initializer initializer) {
        this.initializer = initializer;
    }

    /**
     * Creates a sample memory stored database.
     */
    @GetMapping("/createAll")
    public void createAll() {
        this.initializer.createAll();

    }
}
