package com.mercado_libre.bootcamp.desafio2.services.neighborhood;

import com.mercado_libre.bootcamp.desafio2.dtos.DistrictDTO;
import com.mercado_libre.bootcamp.desafio2.exceptions.NeighborhoodServiceImplException;
import com.mercado_libre.bootcamp.desafio2.model.Neighborhood;
import com.mercado_libre.bootcamp.desafio2.repositories.NeighborhoodRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NeighborhoodServiceImpl implements NeighborhoodService {

    private final NeighborhoodRepository neighborhoodRepository;

    public NeighborhoodServiceImpl(NeighborhoodRepository neighborhoodRepository) {
        this.neighborhoodRepository = neighborhoodRepository;
    }

    @Override
    public void checkIfNeighborhoodExists(DistrictDTO district) {
        Neighborhood neighborhood = neighborhoodRepository.findNeighborhoodByName(district.getDistrictName());

        checkIfItHasTheCorrectPrice(district, neighborhood);
    }

    private void checkIfItHasTheCorrectPrice(DistrictDTO district, Neighborhood neighborhood) {
        if (neighborhood.getPrice() != district.getDistrictPrice()) {
            throw new NeighborhoodServiceImplException("El precio ingresado [" + district.getDistrictPrice() + "] no coincide con el precio almacenado en la base de datos.");
        }
    }

    @Override
    public List<Neighborhood> getNeighborhoods() {
        return neighborhoodRepository.getNeighborhoods();
    }
}
