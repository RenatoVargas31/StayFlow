package com.codebnb.stayflow.cliente.adapter;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.codebnb.stayflow.R;
import com.codebnb.stayflow.cliente.model.Reserva;

import java.util.List;

public class ReservaAdapter extends RecyclerView.Adapter<ReservaAdapter.ViewHolder> {

    private Context context;
    private List<Reserva> reservas;
    private OnReservaClickListener clickListener;

    public interface OnReservaClickListener {
        void onReservaClick(Reserva reserva, int position);
        void onDatosClick(Reserva reserva, int position);
        void onMapaClick(Reserva reserva, int position);
    }

    public ReservaAdapter(Context context, List<Reserva> reservas, OnReservaClickListener clickListener) {
        this.context = context;
        this.reservas = reservas;
        this.clickListener = clickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_reserva, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Reserva reserva = reservas.get(position);

        // Configurar datos del hotel
        holder.textViewHotelName.setText(reserva.getHotelNombre());
        holder.textViewReservationId.setText(reserva.getNumeroReserva());
        holder.textViewDescription.setText(reserva.getDescripcion());

        // Configurar servicios
        boolean[] servicios = reserva.getServicios();
        holder.imageViewWifi.setVisibility(servicios[0] ? View.VISIBLE : View.GONE);
        holder.imageViewRestaurant.setVisibility(servicios[1] ? View.VISIBLE : View.GONE);
        holder.imageViewGarage.setVisibility(servicios[2] ? View.VISIBLE : View.GONE);

        // Configurar imagen del hotel
        if (reserva.getImagenHotelId() != 0) {
            holder.imageViewHotel.setImageResource(reserva.getImagenHotelId());
        }

        // Configurar sección de taxi
        if (reserva.isTieneTaxi() && reserva.getDatosTaxi() != null) {
            Reserva.DatosTaxi datosTaxi = reserva.getDatosTaxi();

            holder.viewSeparator.setVisibility(View.VISIBLE);
            holder.layoutTaxi.setVisibility(View.VISIBLE);

            holder.textViewModeloAuto.setText(datosTaxi.getModeloAuto());
            holder.textViewPlaca.setText(datosTaxi.getPlaca());
            holder.textViewNombreChofer.setText(datosTaxi.getNombreChofer() + " (Chofer)");
            holder.textViewTelefonoChofer.setText(datosTaxi.getTelefonoChofer());

            if (datosTaxi.getImagenAutoId() != 0) {
                holder.imageViewAuto.setImageResource(datosTaxi.getImagenAutoId());
            }

            // Configurar botones
            holder.btnDatos.setOnClickListener(v -> {
                if (clickListener != null) {
                    clickListener.onDatosClick(reserva, holder.getAdapterPosition());
                }
            });

            holder.btnMapa.setOnClickListener(v -> {
                if (clickListener != null) {
                    clickListener.onMapaClick(reserva, holder.getAdapterPosition());
                }
            });
        } else {
            holder.viewSeparator.setVisibility(View.GONE);
            holder.layoutTaxi.setVisibility(View.GONE);
        }

        // Configurar click en toda la reserva
        holder.itemView.setOnClickListener(v -> {
            if (clickListener != null) {
                clickListener.onReservaClick(reserva, holder.getAdapterPosition());
            }
        });
    }

    @Override
    public int getItemCount() {
        return reservas.size();
    }

    public void actualizarDatos(List<Reserva> nuevasReservas) {
        this.reservas = nuevasReservas;
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        // Vistas del hotel
        TextView textViewHotelName;
        TextView textViewReservationId;
        TextView textViewDescription;
        ImageView imageViewWifi;
        ImageView imageViewRestaurant;
        ImageView imageViewGarage;
        ImageView imageViewHotel;
        FrameLayout frameLayoutImageHotel;

        // Separador
        View viewSeparator;

        // Vistas del taxi
        LinearLayout layoutTaxi;
        TextView textViewModeloAuto;
        TextView textViewPlaca;
        TextView textViewNombreChofer;
        TextView textViewTelefonoChofer;
        ImageView imageViewAuto;
        Button btnDatos;
        Button btnMapa;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            // Inicializar vistas del hotel
            textViewHotelName = itemView.findViewById(R.id.textViewHotelName);
            textViewReservationId = itemView.findViewById(R.id.textViewReservationId);
            textViewDescription = itemView.findViewById(R.id.textViewDescription);
            imageViewWifi = itemView.findViewById(R.id.imageViewWifi);
            imageViewRestaurant = itemView.findViewById(R.id.imageViewRestaurant);
            imageViewGarage = itemView.findViewById(R.id.imageViewGarage);
            imageViewHotel = itemView.findViewById(R.id.imageViewHotel);
            frameLayoutImageHotel = itemView.findViewById(R.id.frameLayoutImageHotel);

            // Inicializar separador
            viewSeparator = itemView.findViewById(R.id.viewSeparator);

            // Inicializar vistas del taxi
            layoutTaxi = itemView.findViewById(R.id.layoutTaxi);
            textViewModeloAuto = itemView.findViewById(R.id.textViewModeloAuto);
            textViewPlaca = itemView.findViewById(R.id.textViewPlaca);
            textViewNombreChofer = itemView.findViewById(R.id.textViewNombreChofer);
            textViewTelefonoChofer = itemView.findViewById(R.id.textViewTelefonoChofer);
            imageViewAuto = itemView.findViewById(R.id.imageViewAuto);
            btnDatos = itemView.findViewById(R.id.btnDatos);
            btnMapa = itemView.findViewById(R.id.btnMapa);
        }
    }
}