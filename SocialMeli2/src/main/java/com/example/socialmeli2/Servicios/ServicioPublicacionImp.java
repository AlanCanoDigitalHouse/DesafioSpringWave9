package com.example.socialmeli2.Servicios;

import com.example.socialmeli2.Modelos.Publicacion;
import com.example.socialmeli2.Modelos.Usuario;
import com.example.socialmeli2.Respositorios.IRepositoriosPublicacion;
import com.example.socialmeli2.dtos.requests.PublicacionRequestDTO;

import java.util.List;

public class ServicioPublicacionImp {

    IServicioUsuario servicioUsuario;
    IRepositoriosPublicacion repositoriosPublicacion;
    private Integer id = 0;


    public ServicioPublicacionImp(IServicioUsuario servicioUsuario, IRepositoriosPublicacion repositoriosPublicacion) {
        this.servicioUsuario = servicioUsuario;
        this.repositoriosPublicacion = repositoriosPublicacion;
    }





    public String crearPublicacion(PublicacionRequestDTO publicacionRequestDTO){

        Usuario usuario = servicioUsuario.traerUsuario(publicacionRequestDTO.getUserId());
        Integer product_id = repositoriosPublicacion.validarProducto(publicacionRequestDTO.getDetail());
        Publicacion publicacion = new Publicacion(
                id++,
                publicacionRequestDTO.getDate(),
                product_id,
                publicacionRequestDTO.getCategory(),
                publicacionRequestDTO.getPrecio()
        );
        repositoriosPublicacion.crearPublicacion(publicacion, usuario);

        return "Creado exitosamente";
    }


}
