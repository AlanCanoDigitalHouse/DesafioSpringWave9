package com.example.socialmeli2.Servicios;
import com.example.socialmeli2.Excepciones.Type.IdNoEncontrado;
import com.example.socialmeli2.Modelos.Usuario;
import com.example.socialmeli2.Respositorios.IRepositorioUsuarios;
import com.example.socialmeli2.dtos.responses.UsuarioCantidadResponseDTO;
import com.example.socialmeli2.dtos.responses.UsuarioListaResponseDTO;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ServicioUsuarioImp implements IServicioUsuario {

     IRepositorioUsuarios repositorioUsuarios;

     public ServicioUsuarioImp (IRepositorioUsuarios repositorioUsuarios){
         this.repositorioUsuarios = repositorioUsuarios;
     }

    @Override
    public String seguirUsuario(Integer idUsuarioSeguidor, Integer idUsuarioASeguir) throws IdNoEncontrado {
     return repositorioUsuarios.seguirUsuario(idUsuarioSeguidor, idUsuarioASeguir);
    }


    @Override
    public UsuarioCantidadResponseDTO cantidadSeguidores(Integer id) {
        Usuario usuario = repositorioUsuarios.encontrarUsuarioPorId(id);
        return new UsuarioCantidadResponseDTO(
                usuario.getId(),
                usuario.getNombre(),
                usuario.getListaSeguidores().size()
                );
    }

    @Override
    public UsuarioListaResponseDTO listaSeguidoresPorUsuario(Integer id){
        Usuario usuario = repositorioUsuarios.encontrarUsuarioPorId(id);

        return new UsuarioListaResponseDTO(
                usuario.getId(),
                usuario.getNombre(),
                usuario.getListaSeguidores()
                );
    }

    @Override
    public UsuarioListaResponseDTO listaDeSeguidosPorUsuario (Integer id){
        Usuario usuario = repositorioUsuarios.encontrarUsuarioPorId(id);

        return new UsuarioListaResponseDTO(
                usuario.getId(),
                usuario.getNombre(),
                usuario.getListaSeguidos()
        );
    }

    @Override
    public String dejarDeSeguir(Integer id, Integer unfollowId) {
         Usuario usuario = repositorioUsuarios.encontrarUsuarioPorId(id);
         Usuario unfollowUsuario = repositorioUsuarios.encontrarUsuarioPorId(unfollowId);

         repositorioUsuarios.dejarDeSeguirUsuario(usuario,unfollowUsuario);

         return "unfollow complete";
    }

    @Override
    public Usuario traerUsuario(Integer id){
         return repositorioUsuarios.encontrarUsuarioPorId(id);
    }

}

















// 1. Controllador -> pivote !
// 2. Service -> logica de validaciones - datos con los que se va a trabajar, los organiza
// DTO -> Object Trasnfer Data
// 3. Repository -> logica de negocio - entrega los Datos al service
//---- 4. DAO -> Entidad que se encarga de representar una determinada tabla de datos. BD : ['users','tracks',...]
// DAO - USER ->  getAll  UserModel    -> List<UserModel> -> user.  Per
// LISTAS, JSON, MAPAS