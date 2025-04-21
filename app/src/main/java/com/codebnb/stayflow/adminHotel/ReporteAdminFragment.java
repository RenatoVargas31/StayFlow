package com.codebnb.stayflow.adminHotel;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.codebnb.stayflow.R;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

public class ReporteAdminFragment extends Fragment {

    private ProgressBar progressLavanderia, progressDesayuno, progressSpa;
    private ProgressBar progressJuanPerez, progressAnaTorres, progressLuisGomez;
    private ChipGroup chipGroupFiltros;
    private Chip chipDiario, chipMensual, chipAnual;
    private MaterialButton btnVerReporteServicios, btnVerReporteUsuarios;

    public ReporteAdminFragment() {
        // Constructor vacío requerido
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Nota: Asegúrate de que el nombre del archivo XML coincida con lo que tienes en res/layout/
        return inflater.inflate(R.layout.fragment_admin_reporte, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Inicializar vistas
        inicializarVistas(view);

        // Configurar listeners
        configurarListeners();

        // Cargar datos iniciales (simulados)
        cargarDatosDiarios();
    }

    private void inicializarVistas(View view) {
        // Chips de filtro
        chipGroupFiltros = view.findViewById(R.id.chipGroupFiltros);
        chipDiario = view.findViewById(R.id.chipDiario);
        chipMensual = view.findViewById(R.id.chipMensual);
        chipAnual = view.findViewById(R.id.chipAnual);

        // ProgressBars de servicios
        progressLavanderia = view.findViewById(R.id.progressLavanderia);
        progressDesayuno = view.findViewById(R.id.progressDesayuno);
        progressSpa = view.findViewById(R.id.progressSpa);

        // ProgressBars de usuarios
        progressJuanPerez = view.findViewById(R.id.progressJuanPerez);
        progressAnaTorres = view.findViewById(R.id.progressAnaTorres);
        progressLuisGomez = view.findViewById(R.id.progressLuisGomez);

        // Botones
        btnVerReporteServicios = view.findViewById(R.id.btnVerReporteServicios);
        btnVerReporteUsuarios = view.findViewById(R.id.btnVerReporteUsuarios);
    }

    private void configurarListeners() {
        chipGroupFiltros.setOnCheckedStateChangeListener((group, checkedIds) -> {
            if (checkedIds.isEmpty()) return;

            int chipId = checkedIds.get(0);

            if (chipId == R.id.chipDiario) {
                cargarDatosDiarios();
            } else if (chipId == R.id.chipMensual) {
                cargarDatosMensuales();
            } else if (chipId == R.id.chipAnual) {
                cargarDatosAnuales();
            }
        });

        btnVerReporteServicios.setOnClickListener(v -> {
            Toast.makeText(requireContext(), "Ver reporte completo de servicios", Toast.LENGTH_SHORT).show();
            // Aquí irías a una vista detallada del reporte de servicios
        });

        btnVerReporteUsuarios.setOnClickListener(v -> {
            Toast.makeText(requireContext(), "Ver reporte completo de usuarios", Toast.LENGTH_SHORT).show();
            // Aquí irías a una vista detallada del reporte de usuarios
        });
    }

    // Métodos para simular la carga de datos
    private void cargarDatosDiarios() {
        // Estos son datos simulados
        progressLavanderia.setProgress(30);
        progressDesayuno.setProgress(55);
        progressSpa.setProgress(70);

        progressJuanPerez.setProgress(80);
        progressAnaTorres.setProgress(65);
        progressLuisGomez.setProgress(20);
    }

    private void cargarDatosMensuales() {
        // Datos simulados para vista mensual
        progressLavanderia.setProgress(45);
        progressDesayuno.setProgress(60);
        progressSpa.setProgress(75);

        progressJuanPerez.setProgress(70);
        progressAnaTorres.setProgress(85);
        progressLuisGomez.setProgress(40);
    }

    private void cargarDatosAnuales() {
        // Datos simulados para vista anual
        progressLavanderia.setProgress(60);
        progressDesayuno.setProgress(80);
        progressSpa.setProgress(90);

        progressJuanPerez.setProgress(85);
        progressAnaTorres.setProgress(75);
        progressLuisGomez.setProgress(65);
    }
}