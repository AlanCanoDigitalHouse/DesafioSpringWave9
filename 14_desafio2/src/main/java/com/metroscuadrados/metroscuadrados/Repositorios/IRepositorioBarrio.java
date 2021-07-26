package com.metroscuadrados.metroscuadrados.Repositorios;

import com.metroscuadrados.metroscuadrados.DTO.BarrioDTO;
import com.metroscuadrados.metroscuadrados.Excepciones.NotFoundException;
import org.springframework.stereotype.Repository;

@Repository
public interface IRepositorioBarrio {
        BarrioDTO BuscarBarrioPorNombre (String nombre) throws NotFoundException;
}
