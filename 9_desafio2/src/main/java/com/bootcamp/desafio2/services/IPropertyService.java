package com.bootcamp.desafio2.services;

import com.bootcamp.desafio2.dtos.request.PropertyDto;
import com.bootcamp.desafio2.dtos.response.ResponseDto;
import com.bootcamp.desafio2.exceptions.DistrictNotFoundException;
import com.bootcamp.desafio2.exceptions.BusinessException;

import java.io.IOException;


public interface IPropertyService {

    /**
     * Permite el calculo del precio de una propiedad por el area de los entornos que la componen, asi como
     * resaltar el entorno de mayor area e indicar el area de cada entorno
     * @param property DTO con los datos de la propiedad y barrio
     * @return DTO con informacion del area total de la propiedad y su precio, asi como el area de cada entorno
     * resaltando el entorno de mayor tamaño
     * @throws BusinessException Excepcion en caso de haber errores en el DTO de entrada o en el proceso de calculo
     * @throws IOException Excepcion en caso de problemas de conexión con la BD
     * @throws DistrictNotFoundException Excepcion en caso de que el barrio enviado no exista en la BD con el precio
     * enviado
     */
    ResponseDto calculatePrice(PropertyDto property) throws BusinessException, IOException, DistrictNotFoundException;

}
