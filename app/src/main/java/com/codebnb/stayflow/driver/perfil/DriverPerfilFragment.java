package com.codebnb.stayflow.driver.perfil;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.codebnb.stayflow.LoginActivity;
import com.codebnb.stayflow.R;


public class DriverPerfilFragment extends Fragment {



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_driver_perfil, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ImageView ivProfilePicture = view.findViewById(R.id.iv_profile_picture);
        TextView tvNombreTaxista = view.findViewById(R.id.tv_nombre_taxista);
        TextView tvLabelTaxista = view.findViewById(R.id.tv_label_taxista );

    }
}