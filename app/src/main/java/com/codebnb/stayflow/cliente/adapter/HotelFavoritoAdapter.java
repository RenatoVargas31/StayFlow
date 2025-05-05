package com.codebnb.stayflow.cliente.adapter;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.codebnb.stayflow.R;
import com.codebnb.stayflow.cliente.model.HotelFavorito;


import java.util.List;

public class HotelFavoritoAdapter extends RecyclerView.Adapter<HotelFavoritoAdapter.HotelViewHolder> {

    private Context context;
    private List<HotelFavorito> listaHoteles;
    private OnHotelClickListener listener;

    public interface OnHotelClickListener {
        void onHotelClick(HotelFavorito hotel, int position);
    }

    public HotelFavoritoAdapter(Context context, List<HotelFavorito> listaHoteles, OnHotelClickListener listener) {
        this.context = context;
        this.listaHoteles = listaHoteles;
        this.listener = listener;
    }

    @NonNull
    @Override
    public HotelViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_hotel_favorito, parent, false);
        return new HotelViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HotelViewHolder holder, int position) {
        HotelFavorito hotel = listaHoteles.get(position);

        holder.textViewHotelName.setText(hotel.getNombre());
        holder.textViewDescription.setText(hotel.getDescripcion());
        holder.ratingBar.setRating(hotel.getValoracion());

        // Configurar visibilidad de servicios
        boolean[] servicios = hotel.getServicios();
        holder.imageViewWifi.setVisibility(servicios[0] ? View.VISIBLE : View.GONE);
        holder.imageViewRestaurant.setVisibility(servicios[1] ? View.VISIBLE : View.GONE);
        holder.imageViewGarage.setVisibility(servicios[2] ? View.VISIBLE : View.GONE);

        // Si hay imagen, configurarla
        if (hotel.getImagenId() != 0) {
            holder.imageViewHotel.setImageResource(hotel.getImagenId());
            holder.imageViewHotel.setVisibility(View.VISIBLE);
        } else {
            holder.imageViewHotel.setVisibility(View.GONE);
        }

        // Configurar click listener
        holder.itemView.setOnClickListener(v -> {
            if (listener != null) {
                listener.onHotelClick(hotel, holder.getAdapterPosition());
            }
        });
    }

    @Override
    public int getItemCount() {
        return listaHoteles.size();
    }

    // Método para actualizar los datos
    public void actualizarDatos(List<HotelFavorito> nuevaLista) {
        this.listaHoteles = nuevaLista;
        notifyDataSetChanged();
    }

    static class HotelViewHolder extends RecyclerView.ViewHolder {
        TextView textViewHotelName;
        TextView textViewDescription;
        RatingBar ratingBar;
        ImageView imageViewWifi;
        ImageView imageViewRestaurant;
        ImageView imageViewGarage;
        ImageView imageViewHotel;
        FrameLayout frameLayoutImage;

        public HotelViewHolder(@NonNull View itemView) {
            super(itemView);

            textViewHotelName = itemView.findViewById(R.id.textViewHotelName);
            textViewDescription = itemView.findViewById(R.id.textViewDescription);
            ratingBar = itemView.findViewById(R.id.ratingBar);
            imageViewWifi = itemView.findViewById(R.id.imageViewWifi);
            imageViewRestaurant = itemView.findViewById(R.id.imageViewRestaurant);
            imageViewGarage = itemView.findViewById(R.id.imageViewGarage);
            imageViewHotel = itemView.findViewById(R.id.imageViewHotel);
            frameLayoutImage = itemView.findViewById(R.id.frameLayoutImage);
        }
    }
}
