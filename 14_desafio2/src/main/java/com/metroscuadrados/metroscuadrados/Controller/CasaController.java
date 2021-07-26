package com.metroscuadrados.metroscuadrados.Controller;


import com.metroscuadrados.metroscuadrados.DTO.CasaDTO;
import com.metroscuadrados.metroscuadrados.DTO.ResponseDTO;
import com.metroscuadrados.metroscuadrados.Excepciones.NotFoundException;
import com.metroscuadrados.metroscuadrados.Services.CasaServiceImp;
import com.metroscuadrados.metroscuadrados.Services.ICasaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class CasaController {
    private final ICasaService casaServiceImp;

    public CasaController(ICasaService casaServiceImp) {
        this.casaServiceImp = casaServiceImp;
    }

    @PostMapping("/casa")
    public ResponseEntity metrosCuadrados(@Valid @RequestBody CasaDTO casa) throws NotFoundException {
        ResponseDTO response = new ResponseDTO();
        response.setMetrosCuadrados(casaServiceImp.metrosCuadrados(casa));
        response.setValor(casaServiceImp.valorDeLaCasa(casa));
        response.setHabitacionMasGrande(casaServiceImp.habitacionMasGrande(casa));
        response.setMetrosCuadradosPorHabitacion(casaServiceImp.listaDeMetrosCuadradosPorHabitacion(casa));


        return new ResponseEntity(response, HttpStatus.OK);
    }
}

