package com.codebnb.stayflow.adminHotel;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.codebnb.stayflow.R;
import com.codebnb.stayflow.databinding.FragmentAdminHuespedBinding;

public class HuespedAdminFragment extends Fragment {

    private FragmentAdminHuespedBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentAdminHuespedBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupListeners();
    }

    private void setupListeners() {
        binding.cardReservas.setOnClickListener(v ->
                Navigation.findNavController(v).navigate(R.id.action_huespedAdminFragment_to_reservasAdminFragment)
        );

        binding.cardTaxi.setOnClickListener(v ->
                Navigation.findNavController(v).navigate(R.id.action_huespedAdminFragment_to_taxiAdminFragment)
        );

        binding.cardCheckout.setOnClickListener(v ->
                Navigation.findNavController(v).navigate(R.id.action_huespedAdminFragment_to_checkoutAdminFragment)
        );
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
