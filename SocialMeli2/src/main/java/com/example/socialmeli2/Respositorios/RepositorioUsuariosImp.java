package com.example.socialmeli2.Respositorios;

import com.example.socialmeli2.Excepciones.Type.IdNoEncontrado;
import com.example.socialmeli2.ModelosDto.UsuarioDto;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Repository;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Repository
public class RepositorioUsuariosImp implements IRepositorioUsuarios{
    private List <UsuarioDto> baseDeDatos;

    public RepositorioUsuariosImp() {
        baseDeDatos = cargarBaseDeDatos();
    }

    @Override
    public UsuarioDto encontrarUsuarioPorId(Integer id) {
        Optional<UsuarioDto> first = baseDeDatos.stream().filter(usuarioDto -> usuarioDto.getId()==id).findFirst();
        UsuarioDto result = null;
        if (first.isPresent())
            result = first.get();
        return result;
    }

    @Override
    public String buscarUsuarioDB(Integer idUsuarioSeguidor, Integer idUsuarioASeguir) throws IdNoEncontrado {
        Optional<UsuarioDto> first = baseDeDatos.stream().filter(usuarioDto -> usuarioDto.getId()==idUsuarioSeguidor)
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

    private List<UsuarioDto> cargarBaseDeDatos() {
        File archivoBD = null;
        try {
            archivoBD = ResourceUtils.getFile("classpath:UsuariosDB.json");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        ObjectMapper objectMapper = new ObjectMapper();
        TypeReference<List<UsuarioDto>> typeRef = new TypeReference<>() {};
        List<UsuarioDto> usuariosDTOS = null;
        try {
            usuariosDTOS = objectMapper.readValue(archivoBD, typeRef);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return usuariosDTOS;
    }


}
