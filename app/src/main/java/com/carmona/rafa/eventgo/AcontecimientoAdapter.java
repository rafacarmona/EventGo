package com.carmona.rafa.eventgo;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by Rafa on 18/10/2016.
 */

public class AcontecimientoAdapter
        extends RecyclerView.Adapter<AcontecimientoAdapter.AcontecimientoViewHolder>
        implements View.OnClickListener {

    private ArrayList<AcontecimientoItem> items;
    private View.OnClickListener listener;

    // Clase interna:
    // Se implementa el ViewHolder que se encargará
    // de almacenar la vista del elemento y sus datos
    public static class AcontecimientoViewHolder
            extends RecyclerView.ViewHolder {

        private TextView TextView_nombre;
        private TextView TextView_id;

        public AcontecimientoViewHolder(View itemView) {
            super(itemView);
            TextView_id = (TextView) itemView.findViewById(R.id.TexViewRow);
            TextView_nombre = (TextView) itemView.findViewById(R.id.TexViewRowDate);
        }

        public void AcontecimientoBind(AcontecimientoItem item) {
            TextView_id.setText(item.getId());
            TextView_nombre.setText(item.getNombre());
        }
    }

    // Contruye el objeto adaptador recibiendo la lista de datos
    public AcontecimientoAdapter(@NonNull ArrayList<AcontecimientoItem> items) {
        this.items = items;
    }

    // Se encarga de crear los nuevos objetos ViewHolder necesarios para los elementos de la colección.
    // Infla la vista del layout y crea y devuelve el objeto ViewHolder
    @Override
    public AcontecimientoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View row = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_listado_acontecimiento, parent, false);
        row.setOnClickListener(this);
        AcontecimientoViewHolder avh = new AcontecimientoViewHolder(row);
        return avh;
    }

    // Se encarga de actualizar los datos de un ViewHolder ya existente.
    @Override
    public void onBindViewHolder(AcontecimientoViewHolder viewHolder, int position) {
        AcontecimientoItem item = items.get(position);
        viewHolder.AcontecimientoBind(item);
    }

    // Indica el número de elementos de la colección de datos.
    @Override
    public int getItemCount() {
        return items.size();
    }

    // Asigna un listener
    public void setOnClickListener(View.OnClickListener listener) {
        this.listener = listener;
    }

    @Override
    public void onClick(View view) {
        if(listener != null)
            listener.onClick(view);
    }
}