package com.example.socialmeli2.Respositorios;

import com.example.socialmeli2.Modelos.Producto;
import com.example.socialmeli2.Modelos.Publicacion;
import com.example.socialmeli2.Modelos.Usuario;
import com.example.socialmeli2.Servicios.IServicioPublicacion;
import com.example.socialmeli2.dtos.requests.ProductoRequestDTO;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public  class RepositorioPublicacionImp implements IRepositoriosPublicacion {
    private List<Producto> productos;
    private Integer id = 0;

    public void crearPublicacion(){

    }

    @Override
    public void crearProducto(Producto producto) {
        productos.add(producto);
    }

    @Override
    public Integer validarProducto(ProductoRequestDTO pro) {
        Producto producto;
        Optional<Producto> productoEncontrado= productos.stream().filter(p -> p.getProductName().equals(pro.getProductName()) && p.getBrand().equals(pro.getBrand()) && p.getColor().equals(pro.getColor()) && p.getNotes().equals(pro.getNotes())).findFirst();

        if (productoEncontrado.isEmpty()){
            producto = new Producto( id++, pro.getProductName(),pro.getBrand(),pro.getColor(), pro.getNotes() );
            crearProducto(producto);
        } else {
            producto = productoEncontrado.get();
        }
        return producto.getProduct_id();
    }

    @Override
    public void crearPublicacion(Publicacion publicacion, Usuario usuario) {
        usuario.getListaPublicaciones().add(publicacion);
    }
}
