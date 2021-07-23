package com.example.desafiotesting.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class PropertyNotFoundException  extends ResponseStatusException {

        public PropertyNotFoundException(){
            super(HttpStatus.NOT_FOUND, "Property not found");
        }

}

