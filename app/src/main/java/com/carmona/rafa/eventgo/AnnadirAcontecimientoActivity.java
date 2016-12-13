package com.carmona.rafa.eventgo;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

public class AnnadirAcontecimientoActivity extends AppCompatActivity {
    final private int REQUEST_CODE_INTERNET = 10;
    public static Context myContext;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;
    private static final String ACTIVITY = "StartCreate";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_annadir_acontecimiento);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        myContext = this;
        //Barra de cargar
        final ProgressBar progressbar=(ProgressBar) findViewById(R.id.progressBarAnnadir);
        final Button boton = (Button) findViewById(R.id.button_Annadir);
        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //creamos el edit texty lo instanciamos
                EditText editTextNombre = (EditText) findViewById(R.id.editText_nombre);
                InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(editTextNombre.getWindowToken(), 0);
                if (!isOnline()) {
                    Snackbar.make(view, "No hay conexión!", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();

                } else {

                    String editTextNombreText = editTextNombre.getText().toString();
                    int longitudTexto = editTextNombreText.length();
                    if (longitudTexto == 0) {
                        Snackbar.make(view, "Error!", Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
                    } else {
                        int permissionCheck = ContextCompat.checkSelfPermission(myContext, Manifest.permission.INTERNET);

                        if (permissionCheck == PackageManager.PERMISSION_GRANTED) {
                            new AnnadirAcontecimientoAsyncTask(editTextNombre.getText().toString(), myContext, progressbar, boton).execute();
                        } else {
                            // Explicar permiso
                            if (ActivityCompat.shouldShowRequestPermissionRationale(AnnadirAcontecimientoActivity.this, Manifest.permission.INTERNET)) {
                                Toast.makeText(myContext, "El permiso es necesario para utilizar el internet.", Toast.LENGTH_SHORT).show();
                            }

// Solicitar el permiso
                            ActivityCompat.requestPermissions(AnnadirAcontecimientoActivity.this, new String[]{Manifest.permission.INTERNET}, REQUEST_CODE_INTERNET);
                        }
                    }
                }


            }
        });


        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    /*
        /**
         * ATTENTION: This was auto-generated to implement the App Indexing API.
         * See https://g.co/AppIndexing/AndroidStudio for more information.
         */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("AnnadirAcontecimiento Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();
        MyLog.d(ACTIVITY,"Estamos en onStart de annadie acontecimiento"); //creación del log del onStart
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();
        MyLog.d(ACTIVITY,"Estamos en ON STOP"); //creación del log del onStart
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }

    public boolean isOnline() {
        ConnectivityManager conMgr = (ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = conMgr.getActiveNetworkInfo();

        if (netInfo == null || !netInfo.isConnected() || !netInfo.isAvailable()) {
            Toast.makeText(myContext, "No hay conexión internet!", Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }
    /** Logs */

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

}
