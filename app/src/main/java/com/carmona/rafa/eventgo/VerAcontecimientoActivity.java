package com.carmona.rafa.eventgo;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

public class VerAcontecimientoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_acontecimiento);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        SharedPreferences prefs =
                getSharedPreferences("Ajustes", Context.MODE_PRIVATE);
        String id = prefs.getString("id", "0");
        TextView tv=(TextView) findViewById(R.id.textViewIDAcontecimiento);
        tv.setText(id);
    }

}
