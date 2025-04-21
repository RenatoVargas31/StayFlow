package com.codebnb.stayflow.adminHotel.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.fragment.app.Fragment;

import com.codebnb.stayflow.R;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.chip.Chip;
import com.google.android.material.textfield.MaterialAutoCompleteTextView;
import com.google.android.material.textfield.TextInputEditText;

public class UbicacionAdminFragment extends Fragment {

    // Vistas principales
    private CoordinatorLayout bottomSheetFormulario;
    private View scrim;
    private MaterialCardView cardFormularioLugar;
    private MaterialButton btnAgregarLugar;  // Solo usaremos este botón
    private MaterialButton btnCancelar;
    private MaterialButton btnGuardarLugar;
    private TextInputEditText etNombreLugar, etDistanciaLugar;
    private MaterialAutoCompleteTextView autoCompleteDireccion;
    private Chip chipDireccion;
    private LinearLayout layoutListaLugares;

    public UbicacionAdminFragment() {
        // Constructor vacío requerido
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_admin_ubicacion, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Inicializar vistas
        inicializarVistas(view);

        // Configurar eventos
        configurarEventos();

        // Cargar datos de ejemplo para el mockup
        cargarDatosMockup();
    }

    private void inicializarVistas(View view) {
        // Configurar Toolbar
        MaterialToolbar toolbar = view.findViewById(R.id.toolbar);
        toolbar.setNavigationOnClickListener(v -> requireActivity().onBackPressed());

        // Vistas principales
        bottomSheetFormulario = view.findViewById(R.id.bottomSheetFormulario);
        scrim = view.findViewById(R.id.scrim);
        cardFormularioLugar = view.findViewById(R.id.cardFormularioLugar);
        btnAgregarLugar = view.findViewById(R.id.btnAgregarLugar);
        btnCancelar = view.findViewById(R.id.btnCancelar);
        btnGuardarLugar = view.findViewById(R.id.btnGuardarLugar);
        etNombreLugar = view.findViewById(R.id.etNombreLugar);
        etDistanciaLugar = view.findViewById(R.id.etDistanciaLugar);
        layoutListaLugares = view.findViewById(R.id.layoutListaLugares);

        // Inicializar componentes de dirección
        autoCompleteDireccion = view.findViewById(R.id.autoCompleteDireccion);
        chipDireccion = view.findViewById(R.id.chipDireccion);
        ImageView marcadorMapa = view.findViewById(R.id.marcadorMapa);

        // Configurar autocompletar de direcciones
        configurarAutocompleteDireccion(marcadorMapa);
    }

    private void configurarAutocompleteDireccion(ImageView marcadorMapa) {
        String[] sugerencias = {"Av. El Sol 123", "Jr. Cusco 456", "Plaza Mayor 789",
                "Calle Comercio 234", "Av. Cultura 567"};

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                requireContext(),
                android.R.layout.simple_dropdown_item_1line,
                sugerencias
        );

        autoCompleteDireccion.setAdapter(adapter);
        autoCompleteDireccion.setThreshold(1);

        // Evento de selección
        autoCompleteDireccion.setOnItemClickListener((parent, view, position, id) -> {
            String direccion = parent.getItemAtPosition(position).toString();
            mostrarDireccionSeleccionada(direccion, marcadorMapa);
        });

        // Evento de cierre del dropdown
        autoCompleteDireccion.setOnDismissListener(() -> {
            String texto = autoCompleteDireccion.getText().toString();
            if (!texto.isEmpty()) {
                mostrarDireccionSeleccionada(texto, marcadorMapa);
            }
        });
    }

    private void mostrarDireccionSeleccionada(String direccion, ImageView marcadorMapa) {
        chipDireccion.setText(direccion);
        chipDireccion.setVisibility(View.VISIBLE);
        marcadorMapa.setVisibility(View.VISIBLE);

        // Animar el marcador para mejor feedback visual
        AnimatorSet animatorSet = new AnimatorSet();
        ObjectAnimator scaleX = ObjectAnimator.ofFloat(marcadorMapa, "scaleX", 0.5f, 1.2f, 1.0f);
        ObjectAnimator scaleY = ObjectAnimator.ofFloat(marcadorMapa, "scaleY", 0.5f, 1.2f, 1.0f);
        animatorSet.playTogether(scaleX, scaleY);
        animatorSet.setDuration(300);
        animatorSet.start();
    }

    private void configurarEventos() {
        // Solo usamos el botón dentro de la card para agregar lugares
        btnAgregarLugar.setOnClickListener(v -> mostrarFormularioLugar());

        // Ocultar formulario al cancelar
        btnCancelar.setOnClickListener(v -> ocultarFormularioLugar());
        scrim.setOnClickListener(v -> ocultarFormularioLugar());

        // Guardar lugar
        btnGuardarLugar.setOnClickListener(v -> guardarLugar());
    }

    private void mostrarFormularioLugar() {
        // Preparar el elemento inicialmente fuera de la pantalla
        cardFormularioLugar.setTranslationY(500);
        bottomSheetFormulario.setVisibility(View.VISIBLE);

        // Animar entrada del formulario
        cardFormularioLugar.animate()
                .translationY(0)
                .setDuration(300)
                .start();
    }

    private void ocultarFormularioLugar() {
        // Animar salida del formulario
        cardFormularioLugar.animate()
                .translationY(500)
                .setDuration(250)
                .withEndAction(() -> {
                    bottomSheetFormulario.setVisibility(View.GONE);
                    limpiarFormulario();
                })
                .start();
    }

    private void limpiarFormulario() {
        etNombreLugar.setText("");
        etDistanciaLugar.setText("");
    }

    private void guardarLugar() {
        String nombre = etNombreLugar.getText().toString().trim();
        String distancia = etDistanciaLugar.getText().toString().trim();

        if (!nombre.isEmpty() && !distancia.isEmpty()) {
            agregarTarjetaLugar(nombre, distancia + " m");
            mostrarMensaje("Lugar agregado");
            ocultarFormularioLugar();
        } else {
            mostrarMensaje("Por favor completa ambos campos");
        }
    }

    private void cargarDatosMockup() {
        // Añadir datos de ejemplo para el mockup
        agregarTarjetaLugar("Plaza de Armas", "300 m");
        agregarTarjetaLugar("Museo Central", "500 m");
        agregarTarjetaLugar("Catedral Histórica", "450 m");

        // Simular una dirección ya seleccionada
        autoCompleteDireccion.setText("Av. El Sol 123");
        chipDireccion.setText("Av. El Sol 123");
        chipDireccion.setVisibility(View.VISIBLE);

        // Mostrar el marcador
        ImageView marcadorMapa = getView().findViewById(R.id.marcadorMapa);
        if (marcadorMapa != null) {
            marcadorMapa.setVisibility(View.VISIBLE);
        }
    }

    private void agregarTarjetaLugar(String nombre, String distancia) {
        // Inflar el layout de la tarjeta de lugar
        View tarjeta = LayoutInflater.from(getContext())
                .inflate(R.layout.item_admin_lugar_historico, layoutListaLugares, false);

        // Asignar texto a la tarjeta
        TextView tvNombre = tarjeta.findViewById(R.id.tvNombreLugar);
        Chip chipDistancia = tarjeta.findViewById(R.id.chipDistancia);
        ImageButton btnEditar = tarjeta.findViewById(R.id.btnEditar);
        ImageButton btnEliminar = tarjeta.findViewById(R.id.btnEliminar);

        tvNombre.setText(nombre);
        chipDistancia.setText(distancia);

        // Configurar acciones de botones
        btnEditar.setOnClickListener(v -> editarLugar(nombre, distancia));
        btnEliminar.setOnClickListener(v -> eliminarLugar(tarjeta));

        // Añadir animación de entrada a la tarjeta
        tarjeta.setAlpha(0f);
        tarjeta.setTranslationY(50);
        layoutListaLugares.addView(tarjeta);

        tarjeta.animate()
                .alpha(1f)
                .translationY(0)
                .setDuration(300)
                .start();
    }

    private void editarLugar(String nombre, String distancia) {
        // Preparar formulario para edición
        etNombreLugar.setText(nombre);
        // Quitar "m" del final para editar solo el número
        String distanciaSoloNumero = distancia.replace(" m", "");
        etDistanciaLugar.setText(distanciaSoloNumero);

        // Mostrar formulario
        mostrarFormularioLugar();
    }

    private void eliminarLugar(View tarjeta) {
        // Animar la salida de la tarjeta
        tarjeta.animate()
                .alpha(0f)
                .translationX(tarjeta.getWidth())
                .setDuration(300)
                .withEndAction(() -> {
                    layoutListaLugares.removeView(tarjeta);
                    mostrarMensaje("Lugar eliminado");
                })
                .start();
    }

    private void mostrarMensaje(String mensaje) {
        Toast.makeText(requireContext(), mensaje, Toast.LENGTH_SHORT).show();
    }
}