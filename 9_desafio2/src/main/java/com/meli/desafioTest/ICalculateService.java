package com.meli.desafioTest;

import com.meli.desafioTest.Dtos.HouseDTO;
import com.meli.desafioTest.Dtos.HouseResponseDTO;

public interface ICalculateService {
    HouseResponseDTO calculate(HouseDTO house);
}
