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
import android.widget.Toast;

import com.codebnb.stayflow.R;
import com.codebnb.stayflow.cliente.adapter.HotelBusquedaAdapter;
import com.codebnb.stayflow.cliente.model.HotelBusqueda;
import com.codebnb.stayflow.databinding.ClienteFragmentBusquedaHotelBinding;
import com.google.android.material.chip.Chip;

import java.util.List;

public class BusquedaHotelFragment extends Fragment implements HotelBusquedaAdapter.OnHotelClickListener {

    private ClienteFragmentBusquedaHotelBinding binding;
    private HotelBusquedaAdapter adapter;
    private List<HotelBusqueda> listaHoteles;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ClienteFragmentBusquedaHotelBinding.inflate(inflater, container, false);

        // Configurar botón de retorno
        binding.btnBack.setOnClickListener(v -> {
            Navigation.findNavController(v).navigate(R.id.action_busquedaHotelFragment_to_nav_buscar);
        });

        // Inicializar datos
        inicializarDatos();

        // Configurar RecyclerView
        configurarRecyclerView();

        // Configurar filtros
        configurarFiltros();

        return binding.getRoot();
    }

    private void inicializarDatos() {
        listaHoteles = HotelBusqueda.generarDatosEjemplo();
    }

    private void configurarRecyclerView() {
        RecyclerView recyclerView = binding.recyclerViewHoteles;
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));

        adapter = new HotelBusquedaAdapter(requireContext(), listaHoteles, this);
        recyclerView.setAdapter(adapter);
    }

    private void configurarFiltros() {
        binding.chipPopularity.setOnClickListener(v -> {
            if (((Chip) v).isChecked()) {
                // Ordenar por popularidad
                adapter.actualizarDatos(HotelBusqueda.ordenarPorPopularidad(listaHoteles));
            }
        });

        binding.chipRating.setOnClickListener(v -> {
            if (((Chip) v).isChecked()) {
                // Ordenar por valoración
                adapter.actualizarDatos(HotelBusqueda.ordenarPorValoracion(listaHoteles));
            }
        });
    }

    @Override
    public void onHotelClick(HotelBusqueda hotel, int position) {
        // Manejar clic en hotel
        Toast.makeText(requireContext(), "Hotel seleccionado: " + hotel.getNombre(), Toast.LENGTH_SHORT).show();
        // Aquí podrías navegar a los detalles del hotel
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}