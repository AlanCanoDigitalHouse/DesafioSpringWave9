package com.mercadolibre.calculadorametroscuadrados.exceptions;


public class DistrictNotFoundException extends RuntimeException{
    private String msg;
    public DistrictNotFoundException(String msg){
        super();
        this.msg = msg;
    }

    public String getMsg() {
        return this.msg;
    }
}
