package com.bootcamp.desafio2.services;

import java.io.IOException;

public interface IDistrictService {

    /**
     * Permite consultar en la BD si existe un barrio con el nombre y precio indicados, en caso de que alguno de los
     * dos atributos no coincida se devuelve false, caso contrario true
     * @param name Nombre del barrio
     * @param price Precio por metro cuadrado
     * @return True - el barrio existe en la BD, False - el nombre o precio no coincide con los datos de la BD
     * @throws IOException Excepcion en caso de errores de lectura de la BD
     */
    boolean neighborhoodExist(String name, Double price) throws IOException;

}
