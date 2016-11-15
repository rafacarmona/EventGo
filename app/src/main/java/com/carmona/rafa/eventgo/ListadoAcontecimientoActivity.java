package com.carmona.rafa.eventgo;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ListadoAcontecimientoActivity extends AppCompatActivity {

    private static final String ACTIVITY = "StartCreate";
    private ArrayList<AcontecimientoItem> items;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado_acontecimiento);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), AnnadirAcontecimientoActivity.class));
            }
        });

        // Crear elementos Nota: hay que cambiarlo luego por los datos de la bbdd
        items = new ArrayList<AcontecimientoItem>();
        items.add(new AcontecimientoItem("1", "Primer acontecimiento"));
        items.add(new AcontecimientoItem("10", "Segundo acontecimiento"));
        items.add(new AcontecimientoItem("11", "tercer acontecimiento"));
        items.add(new AcontecimientoItem("12", "cuarto acontecimiento"));
        items.add(new AcontecimientoItem("13", "quinto acontecimiento"));
        items.add(new AcontecimientoItem("14", "sexto acontecimiento"));
        items.add(new AcontecimientoItem("15", "Septimo acontecimiento"));
        items.add(new AcontecimientoItem("16", "Octavo acontecimiento"));
        items.add(new AcontecimientoItem("17", "Noveno acontecimiento"));
        items.add(new AcontecimientoItem("18", "Decimo acontecimiento"));

        // Se inicializa el RecyclerView
        final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        // Se crea el Adaptador con los datos
        AcontecimientoAdapter adaptador = new AcontecimientoAdapter(items);

        // Se asocia el elemento con una acción al pulsar el elemento
        adaptador.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Acción al pulsar el elemento
                MyLog.d(ACTIVITY, "Click en RecyclerView");
                int position = recyclerView.getChildAdapterPosition(v);
                Toast toast = Toast.makeText(ListadoAcontecimientoActivity.this, "Posicion: "+String.valueOf(position) + " " + items.get(position).getId() + " Nombre: " + items.get(position).getNombre(), Toast.LENGTH_SHORT);
                toast.show();
            }
        });

        // Se asocia el Adaptador al RecyclerView
        recyclerView.setAdapter(adaptador);

        // Se muestra el RecyclerView en vertical
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }


    /** Logs */
    @Override
    protected void onStart() {
        MyLog.d(ACTIVITY,"Estamos en onStart"); //creación del log del onStart
        super.onStart();
    }

    @Override
    protected void onResume() {
        MyLog.d(ACTIVITY,"Estamos en onResume"); //creación del log del onResume
        super.onResume();
    }

    @Override
    protected void onPause() {
        MyLog.d(ACTIVITY,"Estamos en onPause"); //creación del log del onPause
        super.onPause();
    }

    @Override
    protected void onStop() {
        MyLog.d(ACTIVITY,"Estamos en onStop"); //creación del log del onStop
        super.onStop();
    }

    @Override
    protected void onRestart() {
        MyLog.d(ACTIVITY,"Estamos en onRestart"); //creación del log del onRestart
        super.onRestart();
    }

    @Override
    protected void onDestroy() {
        MyLog.d(ACTIVITY,"Estamos en onDestroy"); //creación del log del onDestroy
        // TODO Auto-generated method stub
        super.onDestroy();
    }

    /**fin Logs*/

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_listado_acontecimientos, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                Log.i("ActionBar", "Settings!");

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
