package com.codebnb.stayflow.driver.reserva;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.codebnb.stayflow.ActivoFragment;
import com.codebnb.stayflow.CanceladoFragment;
import com.codebnb.stayflow.PasadoFragment;

public class ReservasViewPagerAdapter extends FragmentStateAdapter {

    public ReservasViewPagerAdapter(@NonNull Fragment fragment) {
        super(fragment);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        // Devolver el fragmento correspondiente según la posición
        switch (position) {
            case 0:
                return new ActivoFragment();
            case 1:
                return new PasadoFragment();
            case 2:
                return new CanceladoFragment();
            default:
                return new ActivoFragment(); // Por defecto, mostrar activo
        }
    }

    @Override
    public int getItemCount() {
        // Número total de páginas
        return 3;
    }

}
