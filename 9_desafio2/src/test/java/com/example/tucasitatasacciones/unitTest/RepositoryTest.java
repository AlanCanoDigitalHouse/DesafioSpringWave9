package com.example.tucasitatasacciones.unitTest;

import com.example.tucasitatasacciones.exception.exception.DistrictNotExistsException;
import com.example.tucasitatasacciones.model.DistrictModel;
import com.example.tucasitatasacciones.repository.DistrictRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class RepositoryTest {

    final DistrictRepository districtRepository = new DistrictRepository();


// 2 Test Unitario = Verificar que el barrio de entrada exista en el repositorio de barrios.
    @Test
    @DisplayName("Verificar que el barrio de entrada exista en el repositorio de barrios. ")
    public void districtExist() {
        // Se cumple:
        //Permite continuar con normalidad.
        try {
            DistrictModel model = districtRepository.findDistrictByName("Soacha");
            Assertions.assertEquals("Soacha", model.getDistrict_name());
        } catch (DistrictNotExistsException e) {
            /*No se cumple:
            Notifica la no coincidencia mediante una excepción.*/
            Assertions.fail();
        }
    }

}
