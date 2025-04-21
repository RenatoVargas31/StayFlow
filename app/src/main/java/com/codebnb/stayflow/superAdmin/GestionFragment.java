package com.codebnb.stayflow.superAdmin;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.codebnb.stayflow.R;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

public class GestionFragment extends Fragment {

    public GestionFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_gestion_superadmin, container, false);

        ChipGroup chipGroupFiltro = view.findViewById(R.id.chipGroupFiltro);

        chipGroupFiltro.setOnCheckedStateChangeListener((group, checkedIds) -> {
            if (checkedIds.isEmpty()) return;

            int selectedId = checkedIds.get(0);
            String mensaje = "";
            if (selectedId == R.id.chipTodos) {
                mensaje = "Filtro: Todos";
            } else if (selectedId == R.id.chipAdmins) {
                mensaje = "Filtro: Admins";
            } else if (selectedId == R.id.chipTaxistas) {
                mensaje = "Filtro: Taxistas";
            } else if (selectedId == R.id.chipClientes) {
                mensaje = "Filtro: Clientes";
            }

            Toast.makeText(getContext(), mensaje, Toast.LENGTH_SHORT).show();
        });

        return view;
    }
}
