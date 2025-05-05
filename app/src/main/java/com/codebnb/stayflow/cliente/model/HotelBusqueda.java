package com.codebnb.stayflow.cliente.model;

import com.codebnb.stayflow.R;

import java.util.ArrayList;
import java.util.List;

public class HotelBusqueda {
    private String nombre;
    private float valoracion; // De 1 a 5 estrellas
    private String descripcion;
    private boolean tieneRestaurante;
    private boolean tieneGarage;
    private boolean tieneWifi;
    private boolean aceptaMascotas;
    private int imagenId;

    public HotelBusqueda(String nombre, float valoracion, String descripcion,
                         boolean tieneRestaurante, boolean tieneGarage,
                         boolean tieneWifi, boolean aceptaMascotas, int imagenId) {
        this.nombre = nombre;
        this.valoracion = valoracion;
        this.descripcion = descripcion;
        this.tieneRestaurante = tieneRestaurante;
        this.tieneGarage = tieneGarage;
        this.tieneWifi = tieneWifi;
        this.aceptaMascotas = aceptaMascotas;
        this.imagenId = imagenId;
    }

    // Getters
    public String getNombre() { return nombre; }
    public float getValoracion() { return valoracion; }
    public String getDescripcion() { return descripcion; }
    public boolean isTieneRestaurante() { return tieneRestaurante; }
    public boolean isTieneGarage() { return tieneGarage; }
    public boolean isTieneWifi() { return tieneWifi; }
    public boolean isAceptaMascotas() { return aceptaMascotas; }
    public int getImagenId() { return imagenId; }

    // Método para generar datos de ejemplo
    public static List<HotelBusqueda> generarDatosEjemplo() {
        List<HotelBusqueda> hoteles = new ArrayList<>();

        // Hotel 1
        hoteles.add(new HotelBusqueda(
                "Gran Hotel Lima",
                5.0f,
                "Ubicado en el corazón de Miraflores, con vistas al océano Pacífico y servicios de primera clase.",
                true, true, true, true,
                R.drawable.hotel_image // Reemplaza con tu imagen real
        ));

        // Hotel 2
        hoteles.add(new HotelBusqueda(
                "Hotel Royal Arequipa",
                4.5f,
                "A pocos minutos de la Plaza de Armas, ofrece habitaciones cómodas y desayuno incluido.",
                true, true, true, false,
                R.drawable.hotel_image
        ));

        // Hotel 3
        hoteles.add(new HotelBusqueda(
                "Cusco Palace",
                4.8f,
                "Hotel boutique con decoración colonial y moderna a la vez, cerca de los principales atractivos turísticos.",
                true, false, true, true,
                R.drawable.hotel_image
        ));

        // Hotel 4
        hoteles.add(new HotelBusqueda(
                "Paracas Resort",
                4.9f,
                "Resort frente al mar con múltiples piscinas, restaurantes gourmet y acceso directo a la bahía.",
                true, true, true, false,
                R.drawable.hotel_image
        ));

        // Hotel 5
        hoteles.add(new HotelBusqueda(
                "EcoLodge Puno",
                4.0f,
                "Hotel ecológico con energía solar y habitaciones rústicas. Ideal para los amantes de la naturaleza.",
                false, false, true, true,
                R.drawable.hotel_image
        ));

        // Hotel 6
        hoteles.add(new HotelBusqueda(
                "Refugio Cajamarca",
                3.8f,
                "Hospedaje familiar en el campo cajamarquino. Ambiente acogedor y cocina tradicional.",
                true, false, false, true,
                R.drawable.hotel_image
        ));

        // Hotel 7
        hoteles.add(new HotelBusqueda(
                "Hotel Miraflores",
                4.7f,
                "Moderno hotel en Lima con suites equipadas, gimnasio y terraza con bar.",
                true, true, true, false,
                R.drawable.hotel_image
        ));

        // Hotel 8
        hoteles.add(new HotelBusqueda(
                "Lodge Tambopata",
                4.3f,
                "Aventura en la selva con excursiones guiadas, bungalows de madera y excelente atención.",
                true, false, false, true,
                R.drawable.hotel_image
        ));

        // Hotel 9
        hoteles.add(new HotelBusqueda(
                "Amazonía Dreams",
                4.6f,
                "Hotel de lujo en Iquitos con piscina infinita y acceso al río Amazonas.",
                true, true, true, true,
                R.drawable.hotel_image
        ));

        // Hotel 10
        hoteles.add(new HotelBusqueda(
                "Hostal Trujillo",
                3.5f,
                "Alojamiento económico cerca del centro histórico. Ideal para viajes cortos.",
                false, false, true, false,
                R.drawable.hotel_image
        ));


        return hoteles;
    }

    // Método para ordenar por popularidad (ejemplo simple)
    public static List<HotelBusqueda> ordenarPorPopularidad(List<HotelBusqueda> hoteles) {
        // Simplemente retornamos la lista en el orden original para este ejemplo
        return new ArrayList<>(hoteles);
    }

    // Método para ordenar por valoración (de mayor a menor)
    public static List<HotelBusqueda> ordenarPorValoracion(List<HotelBusqueda> hoteles) {
        List<HotelBusqueda> resultado = new ArrayList<>(hoteles);
        resultado.sort((h1, h2) -> Float.compare(h2.getValoracion(), h1.getValoracion()));
        return resultado;
    }
}