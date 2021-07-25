package com.meli.tucasita.controller;

import com.meli.tucasita.dto.request.CasaRequestDTO;
import com.meli.tucasita.dto.response.CasaResponseTO;
import com.meli.tucasita.exception.DataBaseException;
import com.meli.tucasita.exception.DiferentDistrictPriceException;
import com.meli.tucasita.exception.NoDistrictFoundException;
import com.meli.tucasita.service.CasaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping("/casa")
public class TuCasitaController {

    @Autowired
    private CasaService casaService;


    @PostMapping(value = "/calcular")
    public ResponseEntity<CasaResponseTO> calcularMetroPropiedad(@Valid @RequestBody CasaRequestDTO request) throws NoDistrictFoundException, DataBaseException, DiferentDistrictPriceException {
        return  casaService.calcularMetroPropiedad(request);
    }

}
