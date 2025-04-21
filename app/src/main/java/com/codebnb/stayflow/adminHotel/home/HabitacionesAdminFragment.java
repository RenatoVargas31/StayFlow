package com.codebnb.stayflow.adminHotel.home;

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
import com.codebnb.stayflow.databinding.FragmentAdminHabitacionesBinding;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

public class HabitacionesAdminFragment extends Fragment {

    private FragmentAdminHabitacionesBinding binding;
    private boolean hasRooms = true;

    public HabitacionesAdminFragment() {
        // Constructor vacío obligatorio
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentAdminHabitacionesBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.toolbar.setNavigationOnClickListener(v -> {
            NavController navController = Navigation.findNavController(requireView());
            navController.navigateUp();
        });

        binding.fabAddRoom.setOnClickListener(v -> {
            Navigation.findNavController(v).navigate(R.id.action_habitacionesAdminFragment_to_registroHabitacionFragment);
        });

        binding.btnAddFirstRoom.setOnClickListener(v -> {
            binding.fabAddRoom.performClick();
        });

        binding.btnEditDeluxe.setOnClickListener(v -> {
            // Aquí podrías pasar datos si es necesario
            Navigation.findNavController(v).navigate(R.id.action_habitacionesAdminFragment_to_registroHabitacionFragment);
        });

        binding.btnDeleteDeluxe.setOnClickListener(v -> {
            confirmarEliminacion("Deluxe");
        });

        binding.btnEditEconomica.setOnClickListener(v -> {
            Navigation.findNavController(v).navigate(R.id.action_habitacionesAdminFragment_to_registroHabitacionFragment);
        });

        binding.btnDeleteEconomica.setOnClickListener(v -> {
            confirmarEliminacion("Económica");
        });

        actualizarVisibilidad();
    }

    private void confirmarEliminacion(String tipoHabitacion) {
        new MaterialAlertDialogBuilder(requireContext())
                .setTitle("¿Eliminar habitación?")
                .setMessage("¿Deseas eliminar la habitación " + tipoHabitacion + "?")
                .setPositiveButton("Eliminar", (dialog, which) -> eliminarHabitacion(tipoHabitacion))
                .setNegativeButton("Cancelar", null)
                .show();
    }

    private void eliminarHabitacion(String tipoHabitacion) {
        if ("Deluxe".equals(tipoHabitacion)) {
            binding.cardHabitacionDeluxe.setVisibility(View.GONE);
        } else if ("Económica".equals(tipoHabitacion)) {
            binding.cardHabitacionEconomica.setVisibility(View.GONE);
        }

        if (binding.cardHabitacionDeluxe.getVisibility() == View.GONE &&
                binding.cardHabitacionEconomica.getVisibility() == View.GONE) {
            hasRooms = false;
            actualizarVisibilidad();
        }
    }

    private void actualizarVisibilidad() {
        binding.scrollView.setVisibility(hasRooms ? View.VISIBLE : View.GONE);
        binding.emptyState.setVisibility(hasRooms ? View.GONE : View.VISIBLE);
        binding.fabAddRoom.setVisibility(hasRooms ? View.VISIBLE : View.GONE);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
