package com.codebnb.stayflow;

import android.os.Bundle;
import android.view.View;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.codebnb.stayflow.R;
import com.codebnb.stayflow.superAdmin.*;

public class SuperAdminActivity extends AppCompatActivity {

    BottomNavigationView bottomNav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.superadmin_activity);

        bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnItemSelectedListener(item -> {
            Fragment selectedFragment = null;

            int itemId = item.getItemId();
            if (itemId == R.id.nav_inicio) {
                selectedFragment = new InicioFragment();
            } else if (itemId == R.id.nav_gestion) {
                selectedFragment = new GestionFragment();
            } else if (itemId == R.id.nav_reportes) {
                selectedFragment = new ReportesFragment();
            } else if (itemId == R.id.nav_perfil) {
                selectedFragment = new PerfilFragment();
            }

            return loadFragment(selectedFragment);
        });

        // Configurar el callback para el botón de retroceso
        getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                // Verificar si el fragmento actual es el de detalles del usuario
                Fragment currentFragment = getSupportFragmentManager().findFragmentById(R.id.fragment_container);

                if (currentFragment instanceof UserDetailFragment) {
                    // Asegurarse de que se muestre la barra de navegación inferior al retroceder
                    if (bottomNav != null) {
                        bottomNav.setVisibility(View.VISIBLE);
                    }
                }

                // Si necesitas la funcionalidad predeterminada después:
                if (isEnabled()) {
                    setEnabled(false);
                    getOnBackPressedDispatcher().onBackPressed();
                    setEnabled(true); // Vuelve a habilitarlo para futuras pulsaciones
                }
            }
        });

        // Cargar fragmento inicial
        bottomNav.setSelectedItemId(R.id.nav_inicio);
    }

    private boolean loadFragment(Fragment fragment) {
        if (fragment != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, fragment)
                    .commit();
            return true;
        }
        return false;
    }

    // Elimina el método onBackPressed() obsoleto
}