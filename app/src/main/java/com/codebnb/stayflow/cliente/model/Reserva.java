package com.codebnb.stayflow.cliente.model;

import com.codebnb.stayflow.R;

import java.util.ArrayList;
import java.util.List;

public class Reserva {
    private String hotelNombre;
    private String numeroReserva;
    private String descripcion;
    private boolean[] servicios; // [wifi, restaurante, garage]
    private int imagenHotelId;
    private boolean tieneTaxi;
    private DatosTaxi datosTaxi; // Será null si no tiene taxi
    private EstadoReserva estado;

    // Enumeración para el estado de la reserva
    public enum EstadoReserva {
        ACTIVO,
        PASADO,
        CANCELADO
    }

    // Clase interna para almacenar datos del taxi
    public static class DatosTaxi {
        private String modeloAuto;
        private String placa;
        private String nombreChofer;
        private String telefonoChofer;
        private int imagenAutoId;

        public DatosTaxi(String modeloAuto, String placa, String nombreChofer,
                         String telefonoChofer, int imagenAutoId) {
            this.modeloAuto = modeloAuto;
            this.placa = placa;
            this.nombreChofer = nombreChofer;
            this.telefonoChofer = telefonoChofer;
            this.imagenAutoId = imagenAutoId;
        }

        // Getters
        public String getModeloAuto() { return modeloAuto; }
        public String getPlaca() { return placa; }
        public String getNombreChofer() { return nombreChofer; }
        public String getTelefonoChofer() { return telefonoChofer; }
        public int getImagenAutoId() { return imagenAutoId; }
    }

    public Reserva(String hotelNombre, String numeroReserva, String descripcion,
                   boolean[] servicios, int imagenHotelId, boolean tieneTaxi,
                   DatosTaxi datosTaxi, EstadoReserva estado) {
        this.hotelNombre = hotelNombre;
        this.numeroReserva = numeroReserva;
        this.descripcion = descripcion;
        this.servicios = servicios;
        this.imagenHotelId = imagenHotelId;
        this.tieneTaxi = tieneTaxi;
        this.datosTaxi = datosTaxi;
        this.estado = estado;
    }

    // Getters
    public String getHotelNombre() { return hotelNombre; }
    public String getNumeroReserva() { return numeroReserva; }
    public String getDescripcion() { return descripcion; }
    public boolean[] getServicios() { return servicios; }
    public int getImagenHotelId() { return imagenHotelId; }
    public boolean isTieneTaxi() { return tieneTaxi; }
    public DatosTaxi getDatosTaxi() { return datosTaxi; }
    public EstadoReserva getEstado() { return estado; }

    // Método para generar datos de ejemplo
    public static List<Reserva> generarDatosEjemplo() {
        List<Reserva> listaReservas = new ArrayList<>();

        // Reserva 1 - con taxi, activa
        listaReservas.add(new Reserva(
                "Gran Hotel Lima",
                "#D56337",
                "Habitación doble con vista al mar. Check-in: 15/05/2025, Check-out: 18/05/2025",
                new boolean[]{true, true, true}, // wifi, restaurante, garage
                R.drawable.hotel_image,
                true,
                new DatosTaxi("Hyundai New Accent", "AYX-658", "Juliano Vargaz", "+51 912846518", R.drawable.car_image),
                EstadoReserva.ACTIVO
        ));

        // Reserva 2 - sin taxi, activa
        listaReservas.add(new Reserva(
                "Hotel Royal Arequipa",
                "#D58258",
                "Suite presidencial. Check-in: 20/05/2025, Check-out: 25/05/2025",
                new boolean[]{true, true, false},
                R.drawable.hotel_image,
                false,
                null,
                EstadoReserva.ACTIVO
        ));

        // Reserva 3 - sin taxi, pasada
        listaReservas.add(new Reserva(
                "Cusco Palace",
                "#C45789",
                "Habitación individual con desayuno incluido. Check-in: 01/04/2025, Check-out: 05/04/2025",
                new boolean[]{true, false, true},
                R.drawable.hotel_image,
                false,
                null,
                EstadoReserva.PASADO
        ));

        // Reserva 4 - con taxi, cancelada
        listaReservas.add(new Reserva(
                "Paracas Resort",
                "#B78945",
                "Bungalow frente al mar. Check-in: 10/05/2025, Check-out: 15/05/2025",
                new boolean[]{true, true, true},
                R.drawable.hotel_image,
                true,
                new DatosTaxi("Toyota Corolla", "BDF-123", "Carlos Mendoza", "+51 987654321", R.drawable.car_image),
                EstadoReserva.CANCELADO
        ));
        // Reserva 5 - con taxi, activa
        listaReservas.add(new Reserva(
                "Hotel Amazonía",
                "#A11223",
                "Cabaña en la selva. Check-in: 22/05/2025, Check-out: 26/05/2025",
                new boolean[]{true, false, true},
                R.drawable.hotel_image,
                true,
                new DatosTaxi("Suzuki Vitara", "XTY-334", "Ana Castillo", "+51 901234567", R.drawable.car_image),
                EstadoReserva.ACTIVO
        ));

        // Reserva 6 - sin taxi, cancelada
        listaReservas.add(new Reserva(
                "EcoLodge Puno",
                "#E88991",
                "Habitación ecológica. Check-in: 05/05/2025, Check-out: 09/05/2025",
                new boolean[]{false, true, false},
                R.drawable.hotel_image,
                false,
                null,
                EstadoReserva.CANCELADO
        ));

        // Reserva 7 - con taxi, pasada
        listaReservas.add(new Reserva(
                "Hostal Trujillo",
                "#F99881",
                "Habitación estándar. Check-in: 12/03/2025, Check-out: 15/03/2025",
                new boolean[]{true, false, false},
                R.drawable.hotel_image,
                true,
                new DatosTaxi("Chevrolet Spark", "XYZ-987", "Luis Palacios", "+51 900112233", R.drawable.car_image),
                EstadoReserva.PASADO
        ));

        // Reserva 8 - sin taxi, activa
        listaReservas.add(new Reserva(
                "Hotel Miraflores",
                "#G12987",
                "Suite con jacuzzi. Check-in: 30/05/2025, Check-out: 02/06/2025",
                new boolean[]{true, true, true},
                R.drawable.hotel_image,
                false,
                null,
                EstadoReserva.ACTIVO
        ));

        // Reserva 9 - sin taxi, pasada
        listaReservas.add(new Reserva(
                "Refugio Cajamarca",
                "#H22334",
                "Habitación matrimonial. Check-in: 10/02/2025, Check-out: 14/02/2025",
                new boolean[]{true, false, true},
                R.drawable.hotel_image,
                false,
                null,
                EstadoReserva.PASADO
        ));

        // Reserva 10 - con taxi, cancelada
        listaReservas.add(new Reserva(
                "Lodge Tambopata",
                "#I33445",
                "Suite en la selva. Check-in: 08/05/2025, Check-out: 12/05/2025",
                new boolean[]{true, true, true},
                R.drawable.hotel_image,
                true,
                new DatosTaxi("Kia Sportage", "MNO-456", "Rosa Gamarra", "+51 911223344", R.drawable.car_image),
                EstadoReserva.CANCELADO
        ));


        return listaReservas;
    }
}
