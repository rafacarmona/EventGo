package com.carmona.rafa.eventgo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by rAfA on 17/01/2017.
 */

public class EventoAdapter  extends ArrayAdapter<EventoItem>{
    private final ArrayList<EventoItem> items;
    private final Context context;

    public EventoAdapter(Context context, int textViewResourceId,ArrayList<EventoItem> items) {
        super(context,textViewResourceId,items);
        this.items = items;
        this.context=context;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.row_listado_eventos, parent, false);
        //EventoItem item = getItem(position);
        TextView textView = (TextView) rowView.findViewById(R.id.textView_row_nombre_evento);
        textView.setText(items.get(position).getNombre());


        return rowView;
    }
}