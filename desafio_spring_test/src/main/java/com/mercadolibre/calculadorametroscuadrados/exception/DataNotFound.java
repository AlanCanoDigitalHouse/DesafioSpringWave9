package com.mercadolibre.calculadorametroscuadrados.exception;

public class DataNotFound extends Exception{
    public String reason;
    public String error;
    public final String ERROR = "Error en la data de Ingreso";
    public DataNotFound(String error, String reason)
    {this.error = error;this.reason = reason;}

    public String getReason(){
        return this.reason;
    }

    public String getError(){
        return this.error;
    }
}
