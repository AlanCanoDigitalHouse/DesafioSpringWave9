package com.example.socialmeli2.Respositorios;


import com.example.socialmeli2.Modelos.Producto;
import com.example.socialmeli2.Modelos.Publicacion;
import com.example.socialmeli2.Modelos.Usuario;
import com.example.socialmeli2.dtos.requests.ProductoRequestDTO;

public interface IRepositoriosPublicacion {

    void crearProducto(Producto producto);

    Integer validarProducto(ProductoRequestDTO productoRequestDTO);

    void crearPublicacion(Publicacion publicacion, Usuario usuario);
}

