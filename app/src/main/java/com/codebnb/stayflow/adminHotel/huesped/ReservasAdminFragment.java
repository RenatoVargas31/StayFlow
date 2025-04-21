package com.codebnb.stayflow.adminHotel.huesped;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.codebnb.stayflow.R;
import com.codebnb.stayflow.databinding.FragmentReservasAdminBinding;
import com.google.android.material.card.MaterialCardView;

public class ReservasAdminFragment extends Fragment {

    private FragmentReservasAdminBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentReservasAdminBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Configurar flecha de retroceso
        binding.toolbar.setNavigationOnClickListener(v -> {
            NavController navController = Navigation.findNavController(requireView());
            navController.navigateUp();
        });

        // Aquí puedes seguir agregando la lógica de búsqueda o carga de reservas
        agregarTarjetaReserva("Carlos Ramírez", "Habitación Suite", "2 adultos");
        agregarTarjetaReserva("Lucía Fernández", "Habitación Económica", "1 adulto");
        agregarTarjetaReserva("Jorge Soto", "Habitación Deluxe", "2 adultos + 1 niño");

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void agregarTarjetaReserva(String nombre, String habitacion, String detallePersonas) {
        LayoutInflater inflater = LayoutInflater.from(requireContext());
        View cardView = inflater.inflate(R.layout.item_reserva, binding.layoutContenedorReservas, false);

        TextView tvNombre = cardView.findViewById(R.id.tvNombre);
        TextView tvHabitacion = cardView.findViewById(R.id.tvHabitacion);
        TextView tvDetalle = cardView.findViewById(R.id.tvDetallePersonas);

        TextView tvMasInfo = cardView.findViewById(R.id.tvMasInfo);
        tvMasInfo.setOnClickListener(v -> {
            Navigation.findNavController(v).navigate(R.id.action_reservasAdminFragment_to_detalleReservaFragment);
        });

        tvNombre.setText(nombre);
        tvHabitacion.setText(habitacion);
        tvDetalle.setText(detallePersonas);

        binding.layoutContenedorReservas.addView(cardView);
    }



}
