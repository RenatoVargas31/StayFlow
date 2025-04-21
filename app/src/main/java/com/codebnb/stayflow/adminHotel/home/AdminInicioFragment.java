package com.codebnb.stayflow.adminHotel.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import com.codebnb.stayflow.R;
import com.codebnb.stayflow.databinding.CardAdminBotonHotelBinding;
import com.codebnb.stayflow.databinding.FragmentAdminInicioBinding;

public class AdminInicioFragment extends Fragment {

    private FragmentAdminInicioBinding binding;

    public AdminInicioFragment() {
        // Constructor vacío requerido
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentAdminInicioBinding.inflate(inflater, container, false);

        View root = binding.getRoot();

        // Bindings visuales (ya los tienes)
        View ubicacionView = root.findViewById(R.id.includeBtnUbicacion);
        CardAdminBotonHotelBinding ubicacionBinding = CardAdminBotonHotelBinding.bind(ubicacionView);
        ubicacionBinding.iconoBoton.setImageResource(R.drawable.ic_location);
        ubicacionBinding.textoBoton.setText("Ubicación");

        View galeriaView = root.findViewById(R.id.includeBtnGaleria);
        CardAdminBotonHotelBinding galeriaBinding = CardAdminBotonHotelBinding.bind(galeriaView);
        galeriaBinding.iconoBoton.setImageResource(R.drawable.ic_gallery);
        galeriaBinding.textoBoton.setText("Galería");

        View habitacionesView = root.findViewById(R.id.includeBtnHabitaciones);
        CardAdminBotonHotelBinding habitacionesBinding = CardAdminBotonHotelBinding.bind(habitacionesView);
        habitacionesBinding.iconoBoton.setImageResource(R.drawable.ic_rooms);
        habitacionesBinding.textoBoton.setText("Habitaciones");

        View serviciosView = root.findViewById(R.id.includeBtnServicios);
        CardAdminBotonHotelBinding serviciosBinding = CardAdminBotonHotelBinding.bind(serviciosView);
        serviciosBinding.iconoBoton.setImageResource(R.drawable.ic_services);
        serviciosBinding.textoBoton.setText("Servicios");

        // Navegación con Navigation Component - CORRECCIÓN AQUÍ
        NavController navController = NavHostFragment.findNavController(this);

        ubicacionBinding.getRoot().setOnClickListener(v -> navController.navigate(R.id.ubicacionAdminFragment));
        galeriaBinding.getRoot().setOnClickListener(v -> navController.navigate(R.id.galeriaAdminFragment));
        habitacionesBinding.getRoot().setOnClickListener(v -> navController.navigate(R.id.habitacionesAdminFragment));
        serviciosBinding.getRoot().setOnClickListener(v -> navController.navigate(R.id.serviciosAdminFragment));

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null; // evitar memory leaks
    }
}