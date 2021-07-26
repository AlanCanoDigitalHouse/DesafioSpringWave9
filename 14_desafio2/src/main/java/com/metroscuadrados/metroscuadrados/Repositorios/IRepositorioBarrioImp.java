package com.metroscuadrados.metroscuadrados.Repositorios;

import com.metroscuadrados.metroscuadrados.DTO.BarrioDTO;
import com.metroscuadrados.metroscuadrados.Excepciones.NotFoundException;
import org.springframework.stereotype.Repository;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@Repository
public class IRepositorioBarrioImp implements IRepositorioBarrio {
    public IRepositorioBarrioImp() {
    }

    @Override
    public BarrioDTO BuscarBarrioPorNombre(String nombre) throws NotFoundException {
        List<BarrioDTO> barrios = new ArrayList<>();
        BarrioDTO barrio1 = new BarrioDTO("nuñez",10);
        BarrioDTO barrio2 = new BarrioDTO("palermo",30);
        BarrioDTO barrio3 = new BarrioDTO("barracas",20);
        BarrioDTO barrio4 = new BarrioDTO("almagro",50);
        barrios.add(barrio1);
        barrios.add(barrio2);
        barrios.add(barrio3);
        barrios.add(barrio4);
        for (int i = 0; i < barrios.size(); i++) {
            BarrioDTO barrioIterador = barrios.get(i);
            if (barrioIterador.getNombre().equals(nombre)){
                return barrioIterador;
            }
        }
        throw new NotFoundException();
    }


}
