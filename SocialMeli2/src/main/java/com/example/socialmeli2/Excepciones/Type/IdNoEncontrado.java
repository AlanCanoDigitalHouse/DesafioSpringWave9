package com.example.socialmeli2.Excepciones.Type;

import lombok.AllArgsConstructor;


public class IdNoEncontrado extends  Exception {

   public final String ERROR_MESSAGE = "No se encontró el id buscado";

    public IdNoEncontrado() {
        super();
    }


}
