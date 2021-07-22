package com.example.desafiotesting.controller;

import com.example.desafiotesting.dto.PropertyDTO;
import com.example.desafiotesting.dto.response.ResponseDTO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController(value = "/properties")
public class PropertyController {
    @PostMapping("/calculate")
    public ResponseDTO calculate(@RequestBody PropertyDTO property){
        return;
    }
}
