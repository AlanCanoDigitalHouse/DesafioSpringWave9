package com.example.socialmeli2.ModelosDto;

import java.util.Date;

public class PublicacionDto {

    private String nombre;
    private Date fecha;
    private int precio;
    private String descripcion;
    private UsuarioDto id;

    public PublicacionDto(String nombre, Date fecha, int precio, String descripcion, UsuarioDto id) {
        this.nombre = nombre;
        this.fecha = fecha;
        this.precio = precio;
        this.descripcion = descripcion;
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public UsuarioDto getId() {
        return id;
    }

    public void setId(UsuarioDto id) {
        this.id = id;
    }
}
