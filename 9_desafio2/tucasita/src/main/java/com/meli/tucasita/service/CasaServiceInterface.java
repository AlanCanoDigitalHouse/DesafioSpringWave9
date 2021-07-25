package com.meli.tucasita.service;

import com.meli.tucasita.dto.request.CasaRequestDTO;
import com.meli.tucasita.dto.response.CasaResponseTO;
import com.meli.tucasita.exception.DataBaseException;
import com.meli.tucasita.exception.DiferentDistrictPriceException;
import com.meli.tucasita.exception.NoDistrictFoundException;
import org.springframework.http.ResponseEntity;

public interface CasaServiceInterface {
    ResponseEntity<CasaResponseTO> calcularMetroPropiedad(CasaRequestDTO casaRequestDTO) throws NoDistrictFoundException, DataBaseException, DiferentDistrictPriceException;
}
