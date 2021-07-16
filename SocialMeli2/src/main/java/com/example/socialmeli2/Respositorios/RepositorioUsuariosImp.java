package com.example.socialmeli2.Respositorios;

import com.example.socialmeli2.Excepciones.Type.IdNoEncontrado;
import com.example.socialmeli2.Modelos.DatosUsuarios;
import com.example.socialmeli2.Modelos.Usuario;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Repository;
import org.springframework.util.ResourceUtils;

import javax.swing.text.html.Option;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Repository
public class RepositorioUsuariosImp implements IRepositorioUsuarios{



    public RepositorioUsuariosImp() {
//        baseDeDatos = cargarBaseDeDatos();
        RepositorioDataBase.dataBase = cargarBaseDeDatos();
    }

    @Override
    public Usuario encontrarUsuarioPorId(Integer id) {
        Optional<Usuario> first = RepositorioDataBase.dataBase.stream().filter(usuarioDto -> usuarioDto.getId()==id).findFirst();

        if (first.isEmpty())
            throw new IdNoEncontrado();

        return first.get();
    }

    @Override
    public String seguirUsuario(Integer idUsuarioSeguidor, Integer idUsuarioASeguir) throws IdNoEncontrado {
        Optional<Usuario> first = RepositorioDataBase.dataBase.stream().filter(usuarioDto -> usuarioDto.getId()==idUsuarioSeguidor)
                .filter(usuarioDto -> usuarioDto.getSeguidos()==idUsuarioASeguir).findFirst();
        String result = null;
        if (first.isPresent())
            result = "todo OK";
        else{
            result = "Bad Request";
            throw new IdNoEncontrado();
        }
        return result;
    }

    @Override
    public void dejarDeSeguirUsuario(Usuario usuarioSeguidor, Usuario unfollowUsuario){
        usuarioSeguidor.getListaSeguidos().removeIf(us -> us.getId() == unfollowUsuario.getId());
        unfollowUsuario.getListaSeguidores().removeIf(us -> us.getId() == usuarioSeguidor.getId());
    }



    private List<Usuario> cargarBaseDeDatos() {
        File archivoBD = null;
        try {
            archivoBD = ResourceUtils.getFile("classpath:UsuariosDB.json");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        ObjectMapper objectMapper = new ObjectMapper();
        TypeReference<List<Usuario>> typeRef = new TypeReference<>() {};
        List<Usuario> usuariosDTOS = null;
        try {
            usuariosDTOS = objectMapper.readValue(archivoBD, typeRef);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return usuariosDTOS;
    }







}
