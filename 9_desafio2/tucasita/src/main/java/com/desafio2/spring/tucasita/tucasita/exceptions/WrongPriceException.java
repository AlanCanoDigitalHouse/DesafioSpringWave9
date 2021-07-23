package com.desafio2.spring.tucasita.tucasita.exceptions;

public class WrongPriceException extends ServiceExceptions {

    public WrongPriceException(){
        super();
        this.ERROR = "El precio enviado no se corresponde con el del barrio.";
    }
}
