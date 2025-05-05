package com.codebnb.stayflow.cliente.model;

public class Hotel {
    private String nombre;
    private String ubicacion;
    private String descripcion;
    private int imagenId; // Resource ID para la imagen

    public Hotel(String nombre, String ubicacion, String descripcion, int imagenId) {
        this.nombre = nombre;
        this.ubicacion = ubicacion;
        this.descripcion = descripcion;
        this.imagenId = imagenId;
    }

    // Getters
    public String getNombre() {
        return nombre;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public int getImagenId() {
        return imagenId;
    }
}
