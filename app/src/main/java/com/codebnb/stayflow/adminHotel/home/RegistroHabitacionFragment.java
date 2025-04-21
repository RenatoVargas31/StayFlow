package com.codebnb.stayflow.adminHotel.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.codebnb.stayflow.databinding.FragmentRegistroHabitacionBinding;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

public class RegistroHabitacionFragment extends Fragment {

    private FragmentRegistroHabitacionBinding binding;

    private final String[] roomTypes = {"Habitación Deluxe", "Habitación Económica", "Habitación Premium", "Suite"};
    private final String[] adultCapacities = {"1 adulto", "2 adultos", "3 adultos", "4 adultos"};
    private final String[] childCapacities = {"0 niños", "1 niño", "2 niños", "3 niños"};

    public RegistroHabitacionFragment() {
        // Constructor público vacío
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentRegistroHabitacionBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setupToolbar();
        setupDropdowns();

        binding.btnSaveRoom.setOnClickListener(v -> guardarHabitacion());
    }

    private void setupToolbar() {
        binding.toolbar.setNavigationOnClickListener(v -> {
            NavController navController = Navigation.findNavController(requireView());
            navController.navigateUp();
        });
    }

    private void setupDropdowns() {
        ArrayAdapter<String> adapterTipo = new ArrayAdapter<>(requireContext(), android.R.layout.simple_dropdown_item_1line, roomTypes);
        binding.roomTypeDropdown.setAdapter(adapterTipo);

        ArrayAdapter<String> adapterAdultos = new ArrayAdapter<>(requireContext(), android.R.layout.simple_dropdown_item_1line, adultCapacities);
        binding.adultCapacityDropdown.setAdapter(adapterAdultos);

        ArrayAdapter<String> adapterNinos = new ArrayAdapter<>(requireContext(), android.R.layout.simple_dropdown_item_1line, childCapacities);
        binding.childCapacityDropdown.setAdapter(adapterNinos);
    }

    private void guardarHabitacion() {
        String tipo = binding.roomTypeDropdown.getText().toString();
        String adultos = binding.adultCapacityDropdown.getText().toString();
        String ninos = binding.childCapacityDropdown.getText().toString();
        String tamano = binding.roomSizeInput.getText().toString();

        if (tipo.isEmpty() || adultos.isEmpty() || tamano.isEmpty()) {
            new MaterialAlertDialogBuilder(requireContext())
                    .setTitle("Campos incompletos")
                    .setMessage("Por favor completa todos los campos requeridos.")
                    .setPositiveButton("OK", null)
                    .show();
            return;
        }

        new MaterialAlertDialogBuilder(requireContext())
                .setTitle("¡Guardado!")
                .setMessage("La habitación ha sido registrada exitosamente.")
                .setPositiveButton("OK", (dialog, which) -> {
                    NavController navController = Navigation.findNavController(requireView());
                    navController.navigateUp();
                })
                .show();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
