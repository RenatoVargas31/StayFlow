package com.codebnb.stayflow.cliente.adapter;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.codebnb.stayflow.R;
import com.codebnb.stayflow.cliente.model.Hotel;

import java.util.List;

public class HotelAdapter extends RecyclerView.Adapter<HotelAdapter.HotelViewHolder> {

    private List<Hotel> listaHoteles;
    private Context context;

    public HotelAdapter(Context context, List<Hotel> listaHoteles) {
        this.context = context;
        this.listaHoteles = listaHoteles;
    }

    @NonNull
    @Override
    public HotelViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_hotel, parent, false);
        return new HotelViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HotelViewHolder holder, int position) {
        Hotel hotel = listaHoteles.get(position);

        holder.textNombreHotel.setText(hotel.getNombre());
        holder.textUbicacion.setText(hotel.getUbicacion());
        holder.textDescripcion.setText(hotel.getDescripcion());

        // Si tienes imágenes reales, usa esto:
        if (hotel.getImagenId() != 0) {
            holder.imageHotel.setImageResource(hotel.getImagenId());
        }

        holder.btnSaberMas.setOnClickListener(v -> {
            Toast.makeText(context, "Ver detalles de " + hotel.getNombre(), Toast.LENGTH_SHORT).show();
            // Aquí puedes agregar la navegación a la vista de detalles
        });
    }

    @Override
    public int getItemCount() {
        return listaHoteles.size();
    }

    static class HotelViewHolder extends RecyclerView.ViewHolder {
        ImageView imageHotel;
        TextView textNombreHotel;
        TextView textUbicacion;
        TextView textDescripcion;
        Button btnSaberMas;

        public HotelViewHolder(@NonNull View itemView) {
            super(itemView);

            imageHotel = itemView.findViewById(R.id.imageHotel);
            textNombreHotel = itemView.findViewById(R.id.textNombreHotel);
            textUbicacion = itemView.findViewById(R.id.textUbicacion);
            textDescripcion = itemView.findViewById(R.id.textDescripcion);
            btnSaberMas = itemView.findViewById(R.id.btnSaberMas);
        }
    }
}
