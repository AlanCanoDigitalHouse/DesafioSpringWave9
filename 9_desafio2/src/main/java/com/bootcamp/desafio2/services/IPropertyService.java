package com.bootcamp.desafio2.services;

import com.bootcamp.desafio2.dtos.request.CasaDto;
import com.bootcamp.desafio2.dtos.response.CasaResponseDto;
import com.bootcamp.desafio2.exceptions.ErrorMessage;

public interface IPropertyService {

    public CasaResponseDto calcularArea(CasaDto casa) throws ErrorMessage;

}
