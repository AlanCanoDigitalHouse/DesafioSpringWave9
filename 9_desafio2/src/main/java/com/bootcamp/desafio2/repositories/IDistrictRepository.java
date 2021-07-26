package com.bootcamp.desafio2.repositories;


import com.bootcamp.desafio2.entities.District;

import java.io.IOException;

public interface IDistrictRepository {

    /**
     * Retorna TRUE si encuentra un barrio (district) en la base de datos con el mismo nombre y precio enviados, FALSE
     * caso contrario
     * @param district_name Nombre del barrio a buscar
     * @param district_price Precio por metro cuadrado
     * @return True o False
     * @throws IOException excepcion en caso de error de lectura de la BD
     */
    boolean districtExist(String district_name, Double district_price) throws IOException;

}
