package com.carmona.rafa.eventgo;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class EventosActivity extends AppCompatActivity implements listadoEventosFragment.OnFragmentInteractionListener  {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eventos);

        if(findViewById(R.id.unique_fragment) != null){
            listadoEventosFragment listadoFrag = new listadoEventosFragment();

            getSupportFragmentManager().beginTransaction().add(R.id.unique_fragment, listadoFrag).commit();
        }
    }

    public void onFragmentInteraction(int position){
        mostrarEventoFragment mostrarEventFrag = (mostrarEventoFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentMostrarEvento);

        if(mostrarEventFrag != null){
            mostrarEventFrag.updateView(position);
        }else{
            mostrarEventoFragment newmostrarEventFrag = new mostrarEventoFragment();
            Bundle args = new Bundle();
            args.putInt("position", position);

            newmostrarEventFrag.setArguments(args);
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.unique_fragment, newmostrarEventFrag);
            transaction.addToBackStack(null);

            transaction.commit();
        }
    }
}
