package com.carmona.rafa.eventgo;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class EventosActivity extends AppCompatActivity  implements ListadoEventosFragment.OnFragmentInteractionListener{
    public static Context myContext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eventos);


        if(findViewById(R.id.unique_fragment) != null){
            if (savedInstanceState == null) {
                ListadoEventosFragment listadoFrag = new ListadoEventosFragment();

                getSupportFragmentManager().beginTransaction().add(R.id.unique_fragment, listadoFrag).commit();
            }
        }

    }

    public void onFragmentInteraction(int position,String id){
        MostrarEventoFragment mostrarEventFrag = (MostrarEventoFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentMostrarEvento);

        if(mostrarEventFrag != null){
            mostrarEventFrag.updateView(id);
        }else{
            MostrarEventoFragment newmostrarEventFrag = new MostrarEventoFragment();
            Bundle args = new Bundle();
            args.putString("id", id);

            newmostrarEventFrag.setArguments(args);
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.unique_fragment, newmostrarEventFrag);
            transaction.addToBackStack(null);

            transaction.commit();
        }
    }
}
