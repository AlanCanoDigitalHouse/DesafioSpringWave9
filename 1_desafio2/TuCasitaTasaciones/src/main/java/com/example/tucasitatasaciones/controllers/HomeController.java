package com.example.tucasitatasaciones.controllers;

import com.example.tucasitatasaciones.dtos.requests.HomeRequestDto;
import com.example.tucasitatasaciones.dtos.response.ResponseBiggerRoomDto;
import com.example.tucasitatasaciones.dtos.response.ResponseMetersDto;
import com.example.tucasitatasaciones.dtos.response.ResponsePriceDto;
import com.example.tucasitatasaciones.dtos.response.ResponseRoomMetersDto;
import com.example.tucasitatasaciones.exceptions.GetDistrictException;
import com.example.tucasitatasaciones.services.IHomeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/home")
public class HomeController {

    IHomeService homeService;

    public HomeController(IHomeService homeService) {
        this.homeService = homeService;
    }

    @PostMapping("/meters")
    public ResponseEntity<ResponseMetersDto> totalMeters (@Valid @RequestBody HomeRequestDto home){
        ResponseMetersDto metersResponse = new ResponseMetersDto(
                homeService.calculateMeters(home)
        );
        return new ResponseEntity<>(metersResponse, HttpStatus.OK);
    }

    @PostMapping("/price")
    public ResponseEntity<ResponsePriceDto> totalPrice (@Valid @RequestBody HomeRequestDto home) {
        ResponsePriceDto price = new ResponsePriceDto(
                homeService.calculatePrice(home)
        );
        return new ResponseEntity<>(price, HttpStatus.OK);
    }

    @PostMapping("/biggest")
    public ResponseEntity<ResponseBiggerRoomDto> biggestRoom (@Valid @RequestBody HomeRequestDto home){
        ResponseBiggerRoomDto br =  new ResponseBiggerRoomDto(
                homeService.findBiggestRoom(home)
        );
        return new ResponseEntity<>(br,HttpStatus.OK);
    }

    @PostMapping("/roommeters")
    public ResponseEntity<ResponseRoomMetersDto> metersPerRoom (@Valid @RequestBody HomeRequestDto home){
        ResponseRoomMetersDto rm = new ResponseRoomMetersDto(
                homeService.calculateMetersPerRoom(home)
        );

        return new ResponseEntity<>(rm ,HttpStatus.OK);
    }
}