package com.codebnb.stayflow.cliente.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HotelFavorito {
    private String nombre;
    private String descripcion;
    private float valoracion;
    private int imagenId;
    private boolean[] servicios; // [wifi, restaurante, garage]

    public HotelFavorito(String nombre, String descripcion, float valoracion, int imagenId, boolean[] servicios) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.valoracion = valoracion;
        this.imagenId = imagenId;
        this.servicios = servicios;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public float getValoracion() {
        return valoracion;
    }

    public int getImagenId() {
        return imagenId;
    }

    public boolean[] getServicios() {
        return servicios;
    }

    // Método para generar datos de ejemplo
    public static List<HotelFavorito> generarDatosEjemplo() {
        List<HotelFavorito> hotelesFavoritos = new ArrayList<>();

        hotelesFavoritos.add(new HotelFavorito(
                "Gran Hotel Lima",
                "Ubicado en el corazón de Miraflores, con vistas al océano Pacífico y servicios de primera clase.",
                4.5f,
                0, // Reemplazar con ID de imagen real
                new boolean[]{true, true, true} // wifi, restaurante, garage
        ));

        hotelesFavoritos.add(new HotelFavorito(
                "Hotel Royal Arequipa",
                "A pocos minutos de la Plaza de Armas, ofrece habitaciones cómodas y desayuno incluido.",
                4.0f,
                0,
                new boolean[]{true, true, false}
        ));

        hotelesFavoritos.add(new HotelFavorito(
                "Cusco Palace",
                "Hotel boutique con decoración colonial y moderna a la vez, cerca de los principales atractivos turísticos.",
                5.0f,
                0,
                new boolean[]{true, false, true}
        ));

        hotelesFavoritos.add(new HotelFavorito(
                "Paracas Resort",
                "Resort frente al mar con múltiples piscinas, restaurantes gourmet y acceso directo a la bahía.",
                4.8f,
                0,
                new boolean[]{true, true, true}
        ));

        return hotelesFavoritos;
    }

    // Método para ordenar por popularidad (simple para este ejemplo)
    public static List<HotelFavorito> ordenarPorPopularidad(List<HotelFavorito> hoteles) {
        // Aplicar algún criterio de ordenamiento si es necesario
        return new ArrayList<>(hoteles);
    }

    // Método para ordenar por valoración (de mayor a menor)
    public static List<HotelFavorito> ordenarPorValoracion(List<HotelFavorito> hoteles) {
        List<HotelFavorito> resultado = new ArrayList<>(hoteles);
        resultado.sort((h1, h2) -> Float.compare(h2.getValoracion(), h1.getValoracion()));
        return resultado;
    }
}
