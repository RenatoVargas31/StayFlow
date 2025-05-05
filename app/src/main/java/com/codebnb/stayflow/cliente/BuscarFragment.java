package com.codebnb.stayflow.cliente;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.codebnb.stayflow.R;
import com.codebnb.stayflow.cliente.adapter.HotelAdapter;
import com.codebnb.stayflow.cliente.model.Hotel;
import com.codebnb.stayflow.databinding.ClienteFragmentBuscarBinding;

import java.util.ArrayList;
import java.util.List;

public class BuscarFragment extends Fragment {

    private ClienteFragmentBuscarBinding binding;
    private HotelAdapter adapterHotelesPopulares;
    private HotelAdapter adapterHotelesMejorValorados;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ClienteFragmentBuscarBinding.inflate(inflater, container, false);

        //Configurar fragment de destino
        binding.button.setOnClickListener(v -> {
            Navigation.findNavController(v).navigate(R.id.action_nav_buscar_to_busquedaHotelFragment);
        });

        // Configurar RecyclerViews
        configurarRecyclerViews();

        // Cargar datos estáticos
        cargarDatosEstaticos();

        return binding.getRoot();
    }

    private void configurarRecyclerViews() {
        // Configurar RecyclerView de hoteles populares
        LinearLayoutManager layoutManagerPopulares = new LinearLayoutManager(requireContext(),
                LinearLayoutManager.HORIZONTAL, false);
        binding.recyclerViewHotelesPopulares.setLayoutManager(layoutManagerPopulares);

        // Configurar RecyclerView de hoteles mejor valorados
        LinearLayoutManager layoutManagerValorados = new LinearLayoutManager(requireContext(),
                LinearLayoutManager.HORIZONTAL, false);
        binding.recyclerViewHotelesMejorValorados.setLayoutManager(layoutManagerValorados);
    }

    private void cargarDatosEstaticos() {
        // Crear listas de datos de ejemplo
        List<Hotel> listaHotelesPopulares = new ArrayList<>();
        List<Hotel> listaHotelesMejorValorados = new ArrayList<>();

        // Hoteles populares
        listaHotelesPopulares.add(new Hotel("Gran Hotel Lima", "Lima",
                "Ubicado en el corazón de Miraflores, con vistas al océano Pacífico y servicios de primera clase.",
                R.drawable.hotel_image)); // Reemplaza con una imagen real

        listaHotelesPopulares.add(new Hotel("Hotel Royal Arequipa", "Arequipa",
                "A pocos minutos de la Plaza de Armas, ofrece habitaciones cómodas y desayuno incluido.",
                R.drawable.hotel_image)); // Reemplaza con una imagen real

        listaHotelesPopulares.add(new Hotel("Cusco Palace", "Cusco",
                "Hotel boutique con decoración colonial y moderna a la vez, cerca de los principales atractivos turísticos.",
                R.drawable.hotel_image)); // Reemplaza con una imagen real

        listaHotelesPopulares.add(new Hotel("Paracas Resort", "Paracas",
                "Resort frente al mar con múltiples piscinas, restaurantes gourmet y acceso directo a la bahía.",
                R.drawable.hotel_image)); // Reemplaza con una imagen real

        // Hoteles mejor valorados
        listaHotelesMejorValorados.add(new Hotel("Luxury Suites", "Lima",
                "Calificación: 4.9/5 - Suites de lujo con jacuzzi, servicio a la habitación 24/7 y vistas panorámicas.",
                R.drawable.hotel_image)); // Reemplaza con una imagen real

        listaHotelesMejorValorados.add(new Hotel("Andean Lodge", "Cusco",
                "Calificación: 4.8/5 - Construido con materiales locales, ofrece una experiencia auténtica y acogedora.",
                R.drawable.hotel_image)); // Reemplaza con una imagen real

        listaHotelesMejorValorados.add(new Hotel("Desert Oasis", "Ica",
                "Calificación: 4.7/5 - Único hotel con vista a las dunas, piscina de estilo oasis y experiencias en buggy.",
                R.drawable.hotel_image)); // Reemplaza con una imagen real

        listaHotelesMejorValorados.add(new Hotel("Amazon Retreat", "Iquitos",
                "Calificación: 4.7/5 - Bungalows sobre el río Amazonas, tours de observación de fauna y spa natural.",
                R.drawable.hotel_image)); // Reemplaza con una imagen real

        // Crear adaptadores
        adapterHotelesPopulares = new HotelAdapter(requireContext(), listaHotelesPopulares);
        adapterHotelesMejorValorados = new HotelAdapter(requireContext(), listaHotelesMejorValorados);

        // Asignar adaptadores a los RecyclerViews
        binding.recyclerViewHotelesPopulares.setAdapter(adapterHotelesPopulares);
        binding.recyclerViewHotelesMejorValorados.setAdapter(adapterHotelesMejorValorados);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null; // Evitar fugas de memoria
    }
}