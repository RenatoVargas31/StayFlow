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

import com.codebnb.stayflow.databinding.FragmentDetalleTaxiAdminBinding;

public class DetalleTaxiAdminFragment extends Fragment {

    private FragmentDetalleTaxiAdminBinding binding;

    public DetalleTaxiAdminFragment() {
        // Constructor vacío requerido
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentDetalleTaxiAdminBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Flecha de retroceso
        binding.toolbar.setNavigationOnClickListener(v -> {
            NavController navController = Navigation.findNavController(requireView());
            navController.navigateUp(); // Retrocede al fragmento anterior
        });

        // Aquí puedes continuar agregando lógica
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
