package com.mercadolibre.calculadorametroscuadrados.exceptions;

import lombok.Getter;
import java.util.Map;

@Getter
public class MensajeDeError {

    private Integer estatus;
    private Map<String, String> mensaje;

    public MensajeDeError(Integer estatus, Map<String, String> mensaje){
        this.estatus = estatus;
        this.mensaje = mensaje;
    }
}
