package com.codebnb.stayflow.adminHotel.home;

import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.codebnb.stayflow.databinding.FragmentRegistroServicioBinding;

public class RegistroServicioFragment extends Fragment {

    private FragmentRegistroServicioBinding binding;
    private Uri imagenSeleccionada;

    private final ActivityResultLauncher<String> selectorImagen = registerForActivityResult(
            new ActivityResultContracts.GetContent(),
            uri -> {
                if (uri != null) {
                    imagenSeleccionada = uri;
                    binding.imagePreview.setImageURI(uri);
                    binding.imagePreview.setVisibility(View.VISIBLE);
                    binding.layoutImagePlaceholder.setVisibility(View.GONE);
                }
            });

    public RegistroServicioFragment() {}

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentRegistroServicioBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.toolbar.setNavigationOnClickListener(v -> Navigation.findNavController(v).navigateUp());

        binding.cardImage.setOnClickListener(v -> selectorImagen.launch("image/*"));

        binding.radioFree.setOnCheckedChangeListener((buttonView, isChecked) -> {
            binding.inputPrice.setEnabled(!isChecked);
            if (isChecked) {
                binding.inputPrice.setText("");
            }
        });

        binding.btnSaveService.setOnClickListener(v -> {
            // Aquí podrías guardar la info en la BD o mostrar un toast
            Navigation.findNavController(v).navigateUp();
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
