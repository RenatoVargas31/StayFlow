package com.codebnb.stayflow.cliente;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.codebnb.stayflow.R;
import com.codebnb.stayflow.databinding.ClienteFragmentFavoritoBinding;
import com.google.android.material.chip.Chip;

import java.util.ArrayList;
import java.util.List;

public class FavoritoFragment extends Fragment {

    private ClienteFragmentFavoritoBinding binding;
    private HotelFavoritoAdapter adapter;
    private List<HotelFavorito> listaHoteles;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ClienteFragmentFavoritoBinding.inflate(inflater, container, false);

        // Inicializar datos de ejemplo
        inicializarDatos();

        // Configurar RecyclerView
        configurarRecyclerView();

        // Configurar los chips de filtrado
        configurarChips();

        return binding.getRoot();
    }

    private void inicializarDatos() {
        listaHoteles = new ArrayList<>();

        // Añadir datos de ejemplo
        listaHoteles.add(new HotelFavorito("Gran Hotel Lima",
                "Ubicado en el corazón de Miraflores, con vistas al océano Pacífico y servicios de primera clase.",
                4.5f, R.drawable.hotel_image, new boolean[]{true, true, true}));

        listaHoteles.add(new HotelFavorito("Hotel Royal Arequipa",
                "A pocos minutos de la Plaza de Armas, ofrece habitaciones cómodas y desayuno incluido.",
                4.0f, R.drawable.hotel_image, new boolean[]{true, true, false}));

        listaHoteles.add(new HotelFavorito("Cusco Palace",
                "Hotel boutique con decoración colonial y moderna a la vez, cerca de los principales atractivos turísticos.",
                5.0f, R.drawable.hotel_image, new boolean[]{true, false, true}));

        listaHoteles.add(new HotelFavorito("Paracas Resort",
                "Resort frente al mar con múltiples piscinas, restaurantes gourmet y acceso directo a la bahía.",
                4.8f, R.drawable.hotel_image, new boolean[]{true, true, true}));
    }

    private void configurarRecyclerView() {
        RecyclerView recyclerView = binding.recyclerViewFavoritos;
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));

        adapter = new HotelFavoritoAdapter(requireContext(), listaHoteles,
                (hotel, position) -> {
                    // Manejo del clic en un hotel
                    Toast.makeText(requireContext(),
                            "Hotel seleccionado: " + hotel.getNombre(),
                            Toast.LENGTH_SHORT).show();
                });

        recyclerView.setAdapter(adapter);
    }

    private void configurarChips() {
        binding.chipPopularidad.setOnClickListener(v -> {
            if (((Chip) v).isChecked()) {
                // Ordenar por popularidad (en este ejemplo, mantenemos el orden original)
                adapter.actualizarDatos(new ArrayList<>(listaHoteles));
            }
        });

        binding.chipValoracion.setOnClickListener(v -> {
            if (((Chip) v).isChecked()) {
                // Ordenar por valoración (de mayor a menor)
                List<HotelFavorito> listaOrdenada = new ArrayList<>(listaHoteles);
                listaOrdenada.sort((h1, h2) -> Float.compare(h2.getValoracion(), h1.getValoracion()));
                adapter.actualizarDatos(listaOrdenada);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    // Clase para representar los datos de un hotel favorito
    public static class HotelFavorito {
        private String nombre;
        private String descripcion;
        private float valoracion;
        private int imagenId;
        private boolean[] servicios; // [wifi, restaurante, garage]

        public HotelFavorito(String nombre, String descripcion, float valoracion,
                             int imagenId, boolean[] servicios) {
            this.nombre = nombre;
            this.descripcion = descripcion;
            this.valoracion = valoracion;
            this.imagenId = imagenId;
            this.servicios = servicios;
        }

        // Getters
        public String getNombre() { return nombre; }
        public String getDescripcion() { return descripcion; }
        public float getValoracion() { return valoracion; }
        public int getImagenId() { return imagenId; }
        public boolean[] getServicios() { return servicios; }
    }

    // Adaptador para el RecyclerView
    public static class HotelFavoritoAdapter extends RecyclerView.Adapter<HotelFavoritoAdapter.ViewHolder> {

        private Context context;
        private List<HotelFavorito> hoteles;
        private OnHotelClickListener clickListener;

        public interface OnHotelClickListener {
            void onHotelClick(HotelFavorito hotel, int position);
        }

        public HotelFavoritoAdapter(Context context, List<HotelFavorito> hoteles,
                                    OnHotelClickListener clickListener) {
            this.context = context;
            this.hoteles = hoteles;
            this.clickListener = clickListener;
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(context)
                    .inflate(R.layout.item_hotel_favorito, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            HotelFavorito hotel = hoteles.get(position);

            holder.textViewHotelName.setText(hotel.getNombre());
            holder.textViewDescription.setText(hotel.getDescripcion());
            holder.ratingBar.setRating(hotel.getValoracion());

            // Configurar visibilidad de servicios
            boolean[] servicios = hotel.getServicios();
            holder.imageViewWifi.setVisibility(servicios[0] ? View.VISIBLE : View.GONE);
            holder.imageViewRestaurant.setVisibility(servicios[1] ? View.VISIBLE : View.GONE);
            holder.imageViewGarage.setVisibility(servicios[2] ? View.VISIBLE : View.GONE);

            // Configurar imagen si existe
            if (hotel.getImagenId() != 0) {
                holder.imageViewHotel.setImageResource(hotel.getImagenId());
                holder.imageViewHotel.setVisibility(View.VISIBLE);
            }

            // Configurar click listener
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

        public void actualizarDatos(List<HotelFavorito> nuevaLista) {
            this.hoteles = nuevaLista;
            notifyDataSetChanged();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            TextView textViewHotelName;
            TextView textViewDescription;
            RatingBar ratingBar;
            ImageView imageViewWifi;
            ImageView imageViewRestaurant;
            ImageView imageViewGarage;
            ImageView imageViewHotel;

            public ViewHolder(@NonNull View itemView) {
                super(itemView);

                textViewHotelName = itemView.findViewById(R.id.textViewHotelName);
                textViewDescription = itemView.findViewById(R.id.textViewDescription);
                ratingBar = itemView.findViewById(R.id.ratingBar);
                imageViewWifi = itemView.findViewById(R.id.imageViewWifi);
                imageViewRestaurant = itemView.findViewById(R.id.imageViewRestaurant);
                imageViewGarage = itemView.findViewById(R.id.imageViewGarage);
                imageViewHotel = itemView.findViewById(R.id.imageViewHotel);
            }
        }
    }
}