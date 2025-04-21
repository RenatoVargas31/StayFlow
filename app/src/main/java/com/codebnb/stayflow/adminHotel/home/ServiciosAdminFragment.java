package com.codebnb.stayflow.adminHotel.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.codebnb.stayflow.R;
import com.codebnb.stayflow.databinding.FragmentAdminServiciosBinding;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

public class ServiciosAdminFragment extends Fragment {

    private FragmentAdminServiciosBinding binding;
    private boolean serviciosVisibles = true;

    public ServiciosAdminFragment() {}

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentAdminServiciosBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.toolbar.setNavigationOnClickListener(v -> Navigation.findNavController(v).navigateUp());

        binding.btnAddService.setOnClickListener(v ->
                Navigation.findNavController(v).navigate(R.id.action_serviciosAdminFragment_to_registroServicioFragment));

        binding.btnEditPromoText.setOnClickListener(v ->
                new MaterialAlertDialogBuilder(requireContext())
                        .setTitle("Editar texto promocional")
                        .setMessage("Aquí podrías navegar a un fragmento para editarlo.")
                        .setPositiveButton("OK", null)
                        .show()
        );

        actualizarEstadoServicios();


    }

    private void actualizarEstadoServicios() {
        View scrollView = binding.getRoot().findViewById(R.id.scrollViewServices);
        View emptyState = binding.getRoot().findViewById(R.id.emptyState);

        if (scrollView != null && emptyState != null) {
            scrollView.setVisibility(serviciosVisibles ? View.VISIBLE : View.GONE);
            emptyState.setVisibility(serviciosVisibles ? View.GONE : View.VISIBLE);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
