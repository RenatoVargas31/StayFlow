package com.codebnb.stayflow.adminHotel;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.appbar.MaterialToolbar;
import com.codebnb.stayflow.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class AdminHotelActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_hotel);

        // Configurar Toolbar
        MaterialToolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowTitleEnabled(false); // Ocultar título predeterminado
        }

        // Configurar contador de notificaciones (opcional)
        TextView badgeText = findViewById(R.id.badge_text);
        // Puedes establecer dinámicamente el número de notificaciones
        // Por ejemplo: badgeText.setText("3");

        // Agregar comportamiento al icono de notificaciones (opcional)
        ImageView notificationIcon = findViewById(R.id.notification_icon);
        notificationIcon.setOnClickListener(v -> {
            // Aquí puedes abrir la pantalla de notificaciones
            // Por ejemplo: navController.navigate(R.id.notificationsFragment);
        });

        // Obtener NavController de la manera correcta
        NavHostFragment navHostFragment =
                (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_container);

        if (navHostFragment != null) {
            NavController navController = navHostFragment.getNavController();

            // Configurar el BottomNavigationView con el NavController
            BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
            NavigationUI.setupWithNavController(bottomNav, navController);
        }
    }
}