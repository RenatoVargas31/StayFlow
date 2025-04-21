package com.codebnb.stayflow.adminHotel.huesped;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.codebnb.stayflow.R;
import com.codebnb.stayflow.databinding.FragmentTaxiAdminBinding;

public class TaxiAdminFragment extends Fragment {

    private FragmentTaxiAdminBinding binding;

    public TaxiAdminFragment() {
        // Constructor vacío obligatorio
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentTaxiAdminBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Flecha de retroceso
        binding.toolbar.setNavigationOnClickListener(v -> {
            NavController navController = Navigation.findNavController(requireView());
            navController.navigateUp();
        });

        // Ejemplo: añadir tarjetas hardcodeadas
        agregarTarjetaTaxi();
        agregarTarjetaTaxi();
        agregarTarjetaTaxi();
    }

    private void agregarTarjetaTaxi() {
        View card = LayoutInflater.from(requireContext()).inflate(R.layout.item_taxi, binding.layoutContenedorTaxi, false);

        // Puedes agregar lógica aquí para setear valores dinámicamente si deseas
        // Ejemplo: TextView tvNombre = card.findViewById(R.id.tvNombre);

        // Manejar click en "Información del viaje"
        card.findViewById(R.id.tvInfoViaje).setOnClickListener(v -> {
            NavController navController = Navigation.findNavController(requireView());
            navController.navigate(R.id.action_taxiAdminFragment_to_detalleTaxiAdminFragment);
        });

        binding.layoutContenedorTaxi.addView(card);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
