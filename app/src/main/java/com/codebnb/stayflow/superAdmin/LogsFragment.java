package com.codebnb.stayflow.superAdmin;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.codebnb.stayflow.R;
import com.codebnb.stayflow.superAdmin.adapter.LogsAdapter;
import com.codebnb.stayflow.superAdmin.model.LogItem;

import java.util.Arrays;
import java.util.List;

public class LogsFragment extends Fragment {

    public LogsFragment() {
        super(R.layout.superadmin_logs_fragment);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        RecyclerView recyclerView = view.findViewById(R.id.logsRecyclerView);

        List<LogItem> logs = Arrays.asList(
                new LogItem("Inicio de sesión exitoso", "2025-04-21 10:15 AM", "El usuario superadmin inició sesión correctamente."),
                new LogItem("Cambio de contraseña", "2025-04-21 09:45 AM", "El usuario cambió su contraseña."),
                new LogItem("Error en base de datos", "2025-04-20 11:22 PM", "No se pudo conectar a la base de datos temporalmente."),
                new LogItem("Nuevo usuario creado", "2025-04-20 08:30 PM", "Se registró un nuevo administrador."),
                new LogItem("Intento fallido de inicio de sesión", "2025-04-20 07:50 PM", "Se detectó un intento con credenciales inválidas.")
        );

        recyclerView.setAdapter(new LogsAdapter(logs));
    }
}

