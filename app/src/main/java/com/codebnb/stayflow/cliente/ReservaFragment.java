package com.codebnb.stayflow.cliente;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.codebnb.stayflow.R;
import com.codebnb.stayflow.cliente.adapter.ReservaAdapter;
import com.codebnb.stayflow.cliente.model.Reserva;
import com.codebnb.stayflow.databinding.ClienteFragmentReservaBinding;
import com.google.android.material.chip.Chip;

import java.util.ArrayList;
import java.util.List;

public class ReservaFragment extends Fragment implements ReservaAdapter.OnReservaClickListener {

    private ClienteFragmentReservaBinding binding;
    private ReservaAdapter adapter;
    private List<Reserva> listaReservas;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ClienteFragmentReservaBinding.inflate(inflater, container, false);

        // Inicializar datos
        inicializarDatos();

        // Configurar RecyclerView
        configurarRecyclerView();

        // Configurar los chips de filtrado
        configurarChips();

        return binding.getRoot();
    }

    private void inicializarDatos() {
        // Cargar datos de ejemplo
        listaReservas = Reserva.generarDatosEjemplo();
    }

    private void configurarRecyclerView() {
        RecyclerView recyclerView = binding.recyclerViewReservas;
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));

        // Filtrar por reservas activas inicialmente
        List<Reserva> reservasActivas = filtrarReservasPorEstado(Reserva.EstadoReserva.ACTIVO);

        // Ahora pasamos 'this' como el listener
        adapter = new ReservaAdapter(requireContext(), reservasActivas, this);
        recyclerView.setAdapter(adapter);
    }

    private void configurarChips() {
        binding.chipActivos.setOnClickListener(v -> {
            if (((Chip) v).isChecked()) {
                List<Reserva> reservasFiltradas = filtrarReservasPorEstado(Reserva.EstadoReserva.ACTIVO);
                adapter.actualizarDatos(reservasFiltradas);
            }
        });

        binding.chipPasados.setOnClickListener(v -> {
            if (((Chip) v).isChecked()) {
                List<Reserva> reservasFiltradas = filtrarReservasPorEstado(Reserva.EstadoReserva.PASADO);
                adapter.actualizarDatos(reservasFiltradas);
            }
        });

        binding.chipCancelados.setOnClickListener(v -> {
            if (((Chip) v).isChecked()) {
                List<Reserva> reservasFiltradas = filtrarReservasPorEstado(Reserva.EstadoReserva.CANCELADO);
                adapter.actualizarDatos(reservasFiltradas);
            }
        });
    }

    private List<Reserva> filtrarReservasPorEstado(Reserva.EstadoReserva estado) {
        List<Reserva> listaFiltrada = new ArrayList<>();
        for (Reserva reserva : listaReservas) {
            if (reserva.getEstado() == estado) {
                listaFiltrada.add(reserva);
            }
        }
        return listaFiltrada;
    }

    // Implementación de los métodos de la interfaz OnReservaClickListener
    @Override
    public void onReservaClick(Reserva reserva, int position) {
        // Manejar clic en la reserva
        Toast.makeText(requireContext(),
                "Reserva seleccionada: " + reserva.getNumeroReserva(),
                Toast.LENGTH_SHORT).show();
        // Aquí podrías navegar a la pantalla de detalles
    }

    @Override
    public void onDatosClick(Reserva reserva, int position) {
        // Manejar clic en el botón de datos
        Toast.makeText(requireContext(),
                "Ver datos del taxi de la reserva: " + reserva.getNumeroReserva(),
                Toast.LENGTH_SHORT).show();
        // Aquí podrías mostrar un diálogo con los datos del taxi
    }

    @Override
    public void onMapaClick(Reserva reserva, int position) {
        // Manejar clic en el botón de mapa
        Toast.makeText(requireContext(),
                "Ver mapa del taxi de la reserva: " + reserva.getNumeroReserva(),
                Toast.LENGTH_SHORT).show();
        // Aquí podrías navegar a un fragmento con un mapa
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}