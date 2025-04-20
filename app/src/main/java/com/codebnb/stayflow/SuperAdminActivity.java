package com.codebnb.stayflow;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.appbar.MaterialToolbar;
/*
public class SuperAdminActivity extends AppCompatActivity {

    private MaterialToolbar topAppBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.superadmin_activity);
        
        // Configurar la barra superior
        topAppBar = findViewById(R.id.topAppBar);
        setSupportActionBar(topAppBar);
        
        // Configurar el listener para el ícono de navegación
        topAppBar.setNavigationOnClickListener(view -> {
            // Manejar la navegación
        });

        // Configurar el listener para los elementos del menú
        topAppBar.setOnMenuItemClickListener(menuItem -> {
            switch (menuItem.getItemId()) {
                case R.id.action_settings:
                    // Manejar configuración
                    return true;
                case R.id.action_users:
                    // Manejar gestión de usuarios
                    return true;
                case R.id.action_logout:
                    // Manejar cierre de sesión
                    return true;
                default:
                    return false;
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.superadmin_menu, menu);
        return true;
    }
}*/
