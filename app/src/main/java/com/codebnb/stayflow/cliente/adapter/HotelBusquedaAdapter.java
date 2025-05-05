package com.codebnb.stayflow.cliente.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.codebnb.stayflow.R;
import com.codebnb.stayflow.cliente.model.HotelBusqueda;

import java.util.List;

public class HotelBusquedaAdapter extends RecyclerView.Adapter<HotelBusquedaAdapter.ViewHolder> {

    private Context context;
    private List<HotelBusqueda> hoteles;
    private OnHotelClickListener clickListener;

    public interface OnHotelClickListener {
        void onHotelClick(HotelBusqueda hotel, int position);
    }

    public HotelBusquedaAdapter(Context context, List<HotelBusqueda> hoteles, OnHotelClickListener clickListener) {
        this.context = context;
        this.hoteles = hoteles;
        this.clickListener = clickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_hotel_busqueda, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        HotelBusqueda hotel = hoteles.get(position);

        // Configurar nombre y descripción
        holder.textNombre.setText(hotel.getNombre());
        holder.textDescripcion.setText(hotel.getDescripcion());

        // Configurar valoración (estrellas)
        mostrarValoracion(holder.layoutRating, hotel.getValoracion());

        // Configurar visibilidad de los servicios
        holder.imgRestaurant.setVisibility(hotel.isTieneRestaurante() ? View.VISIBLE : View.GONE);
        holder.imgGarage.setVisibility(hotel.isTieneGarage() ? View.VISIBLE : View.GONE);
        holder.imgWifi.setVisibility(hotel.isTieneWifi() ? View.VISIBLE : View.GONE);
        holder.imgPets.setVisibility(hotel.isAceptaMascotas() ? View.VISIBLE : View.GONE);

        // Configurar imagen del hotel
        if (hotel.getImagenId() != 0) {
            holder.imgHotel.setImageResource(hotel.getImagenId());
            holder.imgHotel.setVisibility(View.VISIBLE);
        } else {
            holder.imgHotel.setVisibility(View.GONE);
        }

        // Configurar clic en el elemento
        holder.itemView.setOnClickListener(v -> {
            if (clickListener != null) {
                clickListener.onHotelClick(hotel, holder.getAdapterPosition());
            }
        });
    }

    @Override
    public int getItemCount() {
        return hoteles.size();
    }

    public void actualizarDatos(List<HotelBusqueda> nuevosHoteles) {
        this.hoteles = nuevosHoteles;
        notifyDataSetChanged();
    }

    // Método para mostrar las estrellas de valoración
    private void mostrarValoracion(LinearLayout container, float valoracion) {
        // Limpiar contenedor
        container.removeAllViews();

        // Número de estrellas completas
        int estrellasCompletas = (int) Math.floor(valoracion);

        // Agregar estrellas según valoración
        for (int i = 0; i < 5; i++) {
            ImageView estrella = new ImageView(context);
            estrella.setLayoutParams(new LinearLayout.LayoutParams(
                    (int) (16 * context.getResources().getDisplayMetrics().density),
                    (int) (16 * context.getResources().getDisplayMetrics().density)));

            if (i < estrellasCompletas) {
                // Estrella completa
                estrella.setImageResource(R.drawable.ic_favoritos_fill);
            } else if (i == estrellasCompletas && valoracion % 1 != 0) {
                // Media estrella (en este caso usamos estrella completa, pero podrías usar una media estrella)
                estrella.setImageResource(R.drawable.ic_favoritos_fill);
            } else {
                // Estrella vacía
                estrella.setImageResource(R.drawable.ic_favoritos_fill);
                estrella.setAlpha(0.3f); // Para simular estrella vacía
            }

            estrella.setColorFilter(context.getResources().getColor(android.R.color.holo_orange_light));
            container.addView(estrella);
        }
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textNombre;
        TextView textDescripcion;
        LinearLayout layoutRating;
        ImageView imgRestaurant;
        ImageView imgGarage;
        ImageView imgWifi;
        ImageView imgPets;
        ImageView imgHotel;
        FrameLayout hotelImageContainer;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            textNombre = itemView.findViewById(R.id.text_hotel_name);
            textDescripcion = itemView.findViewById(R.id.text_description);
            layoutRating = itemView.findViewById(R.id.layout_rating);
            imgRestaurant = itemView.findViewById(R.id.img_restaurant);
            imgGarage = itemView.findViewById(R.id.img_garage);
            imgWifi = itemView.findViewById(R.id.img_wifi);
            imgPets = itemView.findViewById(R.id.img_pets);
            imgHotel = itemView.findViewById(R.id.img_hotel);
            hotelImageContainer = itemView.findViewById(R.id.hotel_image);
        }
    }
}