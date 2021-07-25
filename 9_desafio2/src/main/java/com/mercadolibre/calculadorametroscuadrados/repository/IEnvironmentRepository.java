package com.mercadolibre.calculadorametroscuadrados.repository;

import com.mercadolibre.calculadorametroscuadrados.exceptions.environmentValidationException.exceptions.DataBaseNotFoundException;
import com.mercadolibre.calculadorametroscuadrados.models.EnvironmentModel;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface IEnvironmentRepository {

    Optional<EnvironmentModel> findEnvironment(String environment);

    List<EnvironmentModel> mapDataBase(String path) throws IOException, DataBaseNotFoundException;
}
