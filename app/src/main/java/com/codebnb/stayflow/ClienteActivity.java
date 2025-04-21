package com.codebnb.stayflow;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import androidx.navigation.NavController;

import androidx.navigation.fragment.NavHostFragment;

import androidx.navigation.ui.NavigationUI;

import com.codebnb.stayflow.databinding.ClienteActivityBinding;

public class ClienteActivity extends AppCompatActivity {
    ClienteActivityBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ClienteActivityBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentContainerView);
        NavController navController = navHostFragment.getNavController();
        NavigationUI.setupWithNavController(binding.bottomNavigation, navController);
    }
}