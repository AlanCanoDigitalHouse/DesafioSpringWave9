package com.example.desafiotesting.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class DistrictNotFoundException extends Exception {
        public DistrictNotFoundException(){
            super("Property not found");
        }
}

