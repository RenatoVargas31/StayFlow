package com.codebnb.stayflow.superAdmin;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.codebnb.stayflow.R;
import com.codebnb.stayflow.superAdmin.adapter.UserAdapter;
import com.codebnb.stayflow.superAdmin.model.User;
import com.google.android.material.chip.ChipGroup;

import java.util.ArrayList;
import java.util.List;

public class GestionFragment extends Fragment implements UserAdapter.OnUserClickListener {

    private RecyclerView recyclerViewUsers;
    private UserAdapter userAdapter;
    private List<User> userList;
    private EditText searchEditText;

    public GestionFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_gestion_superadmin, container, false);

        // Inicializar vistas
        recyclerViewUsers = view.findViewById(R.id.recyclerViewUsers);
        ChipGroup chipGroupFiltro = view.findViewById(R.id.chipGroupFiltro);
        searchEditText = view.findViewById(R.id.searchEditText);

        // Configurar RecyclerView
        recyclerViewUsers.setLayoutManager(new LinearLayoutManager(getContext()));

        // Inicializar datos (datos hardcodeados)
        initUserData();

        // Configurar adaptador
        userAdapter = new UserAdapter(userList, this);
        recyclerViewUsers.setAdapter(userAdapter);

        // Configurar listener para los chips
        chipGroupFiltro.setOnCheckedStateChangeListener((group, checkedIds) -> {
            if (checkedIds.isEmpty()) return;

            int selectedId = checkedIds.get(0);
            String filterType = "Todos";

            if (selectedId == R.id.chipTodos) {
                filterType = "Todos";
            } else if (selectedId == R.id.chipAdmins) {
                filterType = "Admin";
            } else if (selectedId == R.id.chipTaxistas) {
                filterType = "Taxista";
            } else if (selectedId == R.id.chipClientes) {
                filterType = "Cliente";
            }

            userAdapter.filterByType(filterType);
            Toast.makeText(getContext(), "Filtro: " + filterType, Toast.LENGTH_SHORT).show();
        });

        // Configurar listener para la búsqueda
        searchEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {
                userAdapter.filterByText(s.toString());
            }
        });

        return view;
    }

    private void initUserData() {
        // Datos hardcodeados para pruebas
        userList = new ArrayList<>();
        userList.add(new User("Julian Casablancas", "Admin", "Admin Hotel El Cielo", false));
        userList.add(new User("Juan Perez", "Taxista", "Taxista", true));
        userList.add(new User("Luis Navejas", "Cliente", "Cliente", true));
        userList.add(new User("Julian Casablancas", "Admin", "Admin Hotel El Cielo", false));
        userList.add(new User("Juan Perez", "Taxista", "Taxista", true));
        userList.add(new User("María González", "Cliente", "Cliente", true));
        userList.add(new User("Carlos López", "Admin", "Admin Hotel Las Estrellas", true));
        userList.add(new User("Ana Torres", "Taxista", "Taxista", false));
    }

    @Override
    public void onDetailsClick(int position) {
        User user = userList.get(position);
        Toast.makeText(getContext(), "Detalles de " + user.getName(), Toast.LENGTH_SHORT).show();
        // Aquí puedes implementar la navegación a la pantalla de detalles
    }

    @Override
    public void onStatusChanged(int position, boolean isEnabled) {
        User user = userList.get(position);
        user.setEnabled(isEnabled);
        // Aquí podrías implementar la lógica para guardar el estado en una DB
        Toast.makeText(getContext(),
                user.getName() + " " + (isEnabled ? "habilitado" : "deshabilitado"),
                Toast.LENGTH_SHORT).show();
    }
}