package com.metroscuadrados.metroscuadrados.Services;

import com.metroscuadrados.metroscuadrados.DTO.BarrioDTO;
import com.metroscuadrados.metroscuadrados.DTO.CasaDTO;
import com.metroscuadrados.metroscuadrados.DTO.HabitacionDTO;
import com.metroscuadrados.metroscuadrados.Excepciones.NotFoundException;
import com.metroscuadrados.metroscuadrados.Repositorios.IRepositorioBarrio;
import com.metroscuadrados.metroscuadrados.Repositorios.IRepositorioBarrioImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CasaServiceImp implements ICasaService{
    private final IRepositorioBarrio traerDatosBarrio;

   public CasaServiceImp(IRepositorioBarrio barrios) {
      this.traerDatosBarrio = barrios;
    }

    @Override
    public double metrosCuadradosPorHabitacion(HabitacionDTO habitacion){
            return habitacion.getAncho() * habitacion.getLargo();

    }

    @Override
    public double metrosCuadrados(CasaDTO casa){
        double resultado = 0;
        System.out.println("Estoy corriendo los test, estoy en el metodo metros cuadrados");
        System.out.println("Esta casa tiene : "+ casa.getHabitaciones().size() + " habitaciones");
        for (int i = 0; i < casa.getHabitaciones().size(); i++) {

            resultado += metrosCuadradosPorHabitacion(casa.getHabitaciones().get(i));
        }

        return resultado;
    }

    @Override
    public double valorDeLaCasa(CasaDTO casa) throws NotFoundException {
        BarrioDTO barrioTraido = traerDatosBarrio.BuscarBarrioPorNombre(casa.getBarrio());
        return barrioTraido.getValor() * metrosCuadrados(casa);
    }

    @Override
    public  HabitacionDTO habitacionMasGrande(CasaDTO casa){
        double mayor = 0;
        int posMayor = 0;
        for (int i = 0; i < casa.getHabitaciones().size(); i++) {
            if(metrosCuadradosPorHabitacion(casa.getHabitaciones().get(i)) > mayor){
                mayor = metrosCuadradosPorHabitacion(casa.getHabitaciones().get(i));
                posMayor = i;
            }
        }
        return casa.getHabitaciones().get(posMayor);
    }

    @Override
    public List<Double> listaDeMetrosCuadradosPorHabitacion(CasaDTO casa){
        List<Double> list = new ArrayList<>();
        for (int i = 0; i < casa.getHabitaciones().size(); i++) {
            list.add(metrosCuadradosPorHabitacion(casa.getHabitaciones().get(i)));

        }
        return list;
    }


}
