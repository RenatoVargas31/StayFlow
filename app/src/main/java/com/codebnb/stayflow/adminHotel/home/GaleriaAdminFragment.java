package com.codebnb.stayflow.adminHotel.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.codebnb.stayflow.R;
import com.codebnb.stayflow.databinding.FragmentAdminGaleriaBinding;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

public class GaleriaAdminFragment extends Fragment {

    private FragmentAdminGaleriaBinding binding;
    private boolean hasImages = false; // Estado inicial: sin imágenes

    // Lanzador para seleccionar imágenes de la galería
    private final ActivityResultLauncher<String> pickImages = registerForActivityResult(
            new ActivityResultContracts.GetContent(),
            uri -> {
                if (uri != null) {
                    // Aquí procesarías la imagen seleccionada
                    // Por ahora, simplemente mostraremos la vista con imágenes
                    hasImages = true;
                    updateGalleryState();
                }
            });

    public GaleriaAdminFragment() {
        // Constructor vacío requerido
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentAdminGaleriaBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setupListeners();
        updateGalleryState();
    }

    private void setupListeners() {
        // Configurar el Toolbar
        binding.toolbar.setNavigationOnClickListener(v -> {
            NavController navController = Navigation.findNavController(requireView());
            navController.navigateUp();
        });

        // Botón para agregar fotos
        binding.btnAddPhotos.setOnClickListener(v -> {
            pickImages.launch("image/*");
        });

        // Botones para eliminar imágenes
        setupDeleteButtons();

        // Botones de navegación inferior
        binding.btnCancel.setOnClickListener(v -> {
            confirmCancel();
        });

        binding.btnSave.setOnClickListener(v -> {
            // Lógica para guardar los cambios
            // Implementar lógica real aquí

            // Mostrar confirmación
            new MaterialAlertDialogBuilder(requireContext())
                    .setTitle(R.string.changes_saved)
                    .setMessage(R.string.gallery_updated_message)
                    .setPositiveButton(R.string.ok, (dialog, which) -> {
                        // Navegar hacia atrás cuando se guarda
                        NavController navController = Navigation.findNavController(requireView());
                        navController.navigateUp();
                    })
                    .show();
        });
    }

    private void confirmCancel() {
        new MaterialAlertDialogBuilder(requireContext())
                .setTitle(R.string.confirm_cancel)
                .setMessage(R.string.discard_changes_message)
                .setNegativeButton(R.string.keep_editing, null)
                .setPositiveButton(R.string.discard, (dialog, which) -> {
                    NavController navController = Navigation.findNavController(requireView());
                    navController.navigateUp();
                })
                .show();
    }

    private void setupDeleteButtons() {
        // Configurar listeners para eliminar imágenes
        binding.deleteImage1.setOnClickListener(v -> confirmDeleteImage(1));
        binding.deleteImage2.setOnClickListener(v -> confirmDeleteImage(2));
        binding.deleteImage3.setOnClickListener(v -> confirmDeleteImage(3));
        binding.deleteImage4.setOnClickListener(v -> confirmDeleteImage(4));
    }

    private void confirmDeleteImage(int imageNumber) {
        new MaterialAlertDialogBuilder(requireContext())
                .setTitle(R.string.confirm_delete)
                .setMessage(R.string.delete_image_message)
                .setNegativeButton(R.string.cancel, null)
                .setPositiveButton(R.string.delete, (dialog, which) -> removeImage(imageNumber))
                .show();
    }

    private void removeImage(int imageNumber) {
        // Aquí implementarías la lógica para eliminar una imagen específica
        // Por ahora, simplemente verificamos si aún quedan imágenes

        // Esta es una simulación - en una app real verificarías
        // cuántas imágenes quedan después de eliminar
        if (imageNumber == 1) {
            // Simulamos que ya no quedan imágenes
            hasImages = false;
            updateGalleryState();
        }
    }

    private void updateGalleryState() {
        // Actualiza la visibilidad según si hay imágenes o no
        binding.scrollView.setVisibility(hasImages ? View.VISIBLE : View.GONE);
        binding.emptyState.setVisibility(hasImages ? View.GONE : View.VISIBLE);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null; // evitar memory leaks
    }
}