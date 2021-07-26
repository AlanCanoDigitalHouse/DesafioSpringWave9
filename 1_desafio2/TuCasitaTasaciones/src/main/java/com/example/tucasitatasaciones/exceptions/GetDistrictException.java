package com.example.tucasitatasaciones.exceptions;

import com.example.tucasitatasaciones.dtos.ErrorDto;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class GetDistrictException extends RuntimeException{
    public GetDistrictException() {
        super("District not found");
    }
}
