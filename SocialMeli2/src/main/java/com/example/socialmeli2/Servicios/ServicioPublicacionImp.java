package com.example.socialmeli2.Servicios;

import com.example.socialmeli2.Modelos.DatosUsuarios;
import com.example.socialmeli2.Modelos.Publicacion;
import com.example.socialmeli2.Modelos.Usuario;
import com.example.socialmeli2.Respositorios.IRepositorioUsuarios;
import com.example.socialmeli2.Respositorios.IRepositoriosPublicacion;
import com.example.socialmeli2.dtos.requests.PublicacionRequestDTO;
import com.example.socialmeli2.dtos.responses.ListadoPublicacionesResponseDTO;

import java.util.ArrayList;
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
                publicacionRequestDTO.getPrice()
        );
        repositoriosPublicacion.crearPublicacion(publicacion, usuario);

        return "Creado exitosamente";
    }


    @Override
    public ListadoPublicacionesResponseDTO listadoPublicacionePorVendedor(Integer idUsuario) {
        IRepositorioUsuarios repositorioUsuarios;
        Usuario usuario = repositorioUsuarios.encontrarUsuarioPorId(idUsuario);
        List<DatosUsuarios> seguidos = usuario.getListaSeguidos();
        // [{"1","pepito "},{"2","Sofia"}]
        List <Usuario> usuariosVendedores =  new ArrayList();
        // Lista de usuariosVendedores, para guardar a los usuarios que son vendedores
        List <Publicacion> publicacionesDeSeguidos = new ArrayList();
        // Lista de Lista de publicaciones, para guardar el listado de publicaciones de un Usuario
        Usuario usuarioTemporal;
        // Usuario temporal, este usuario va a servir para guardar los usuarios que sean vendedores
        //y añadirlos a la lista de usuariosVendedores
        for (DatosUsuarios usarioIterador: seguidos) { //Me voy a mover por la lista de seguidos
            //Iterador, el iterador es usarioIterador, que se va a ir moviendo por la lista de Seguidos
            usuarioTemporal = repositorioUsuarios.encontrarUsuarioPorId(usarioIterador.getId());
            publicacionesDeSeguidos.addAll(usuarioTemporal.getListaPublicaciones());
            // Por cada id de Usuario en la lista de seguidos, voy a ir a buscar a ese usuario
        }
        ListadoPublicacionesResponseDTO response = new ListadoPublicacionesResponseDTO();
        response.setUserId(idUsuario);
        response.setPosts(publicacionesDeSeguidos);

        return response;

    }

}
