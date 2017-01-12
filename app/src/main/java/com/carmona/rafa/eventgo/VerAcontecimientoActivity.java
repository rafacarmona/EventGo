package com.carmona.rafa.eventgo;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.ContextThemeWrapper;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.sql.SQLOutput;

public class VerAcontecimientoActivity extends AppCompatActivity {
       private static final String ACTIVITY = "StartCreate";
    private TextView tv;
    private ImageView iv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_acontecimiento);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


        /**
         * Boton flotante
         */

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fabMostrarAcon);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), EventosActivity.class));
            }
        });

        SharedPreferences prefs =
                getSharedPreferences("Ajustes", Context.MODE_PRIVATE);
        String id = prefs.getString("id", "0");
        // TextView tv=(TextView) findViewById(R.id.textViewIDAcontecimiento);
        // tv.setText(id);
        //leer de la base de datos.
        BBDDSQLiteHelper usdbh =
                new BBDDSQLiteHelper(this, Environment.getExternalStorageDirectory()+"/Eventgo.db", null, 1);
        //instancia la db.
        SQLiteDatabase db = usdbh.getReadableDatabase();

        String[] argsID = new String[] {id};
        Cursor cursor = db.rawQuery(" SELECT * FROM acontecimiento WHERE id=? ", argsID);

        //Nos aseguramos de que existe al menos un registro
        if (cursor.moveToFirst()) {
            //Recorremos el cursor hasta que no haya más registros
            LinearLayout layoutPrincipal = (LinearLayout) findViewById(R.id.LLVerAcontecimiento);
            layoutPrincipal.setOrientation(LinearLayout.VERTICAL);
            do{
                String nombreAcontecimiento = cursor.getString(cursor.getColumnIndex("nombre"));
                String organizador = cursor.getString(cursor.getColumnIndex("organizador"));
                String descripcion = cursor.getString(cursor.getColumnIndex("descripcion"));
                String tipo = cursor.getString(cursor.getColumnIndex("tipo"));
                String portada = cursor.getString(cursor.getColumnIndex("portada"));
                String inicio = cursor.getString(cursor.getColumnIndex("inicio"));
                String fin = cursor.getString(cursor.getColumnIndex("fin"));
                String direccion = cursor.getString(cursor.getColumnIndex("direccion"));
                String localidad = cursor.getString(cursor.getColumnIndex("localidad"));
                String codPostal = cursor.getString(cursor.getColumnIndex("cod_postal"));
                String provincia = cursor.getString(cursor.getColumnIndex("provincia"));
                String longitud = cursor.getString(cursor.getColumnIndex("longitud"));;
                String latitud = cursor.getString(cursor.getColumnIndex("latitud"));
                String telefono = cursor.getString(cursor.getColumnIndex("telefono"));
                String email = cursor.getString(cursor.getColumnIndex("email"));
                String web = cursor.getString(cursor.getColumnIndex("web"));
                String facebook = cursor.getString(cursor.getColumnIndex("facebook"));
                String twitter = cursor.getString(cursor.getColumnIndex("twitter"));
                String instagram = cursor.getString(cursor.getColumnIndex("instagram"));

                if (!nombreAcontecimiento.isEmpty()) crearElementoVista(nombreAcontecimiento, R.drawable.ic_account_balance_black_24dp, layoutPrincipal);
                if(!organizador.isEmpty()) crearElementoVista(organizador, R.drawable.ic_account_box_black_24dp, layoutPrincipal);
                if(!descripcion.isEmpty()) crearElementoVista(descripcion, R.drawable.ic_description_black_24dp, layoutPrincipal);
                if(!tipo.isEmpty()) crearElementoVista(tipo, R.drawable.ic_list_black_24dp, layoutPrincipal);
                if(!portada.isEmpty()) crearElementoVista(portada, R.drawable.ic_picture_in_picture_black_24dp, layoutPrincipal);
                if(!inicio.isEmpty()) crearElementoVista(inicio, R.drawable.ic_date_range_black_24dp, layoutPrincipal);
                if(!fin.isEmpty()) crearElementoVista(fin, R.drawable.ic_date_range_black_24dp, layoutPrincipal);
                if(!direccion.isEmpty()) crearElementoVista(direccion, R.drawable.ic_markunread_mailbox_black_24dp, layoutPrincipal);
                if(!localidad.isEmpty()) crearElementoVista(localidad, R.drawable.ic_markunread_mailbox_black_24dp, layoutPrincipal);
                if(!codPostal.isEmpty()) crearElementoVista(codPostal, R.drawable.ic_date_range_black_24dp, layoutPrincipal);
                if(!provincia.isEmpty()) crearElementoVista(provincia, R.drawable.ic_account_balance_black_24dp, layoutPrincipal);
                if(!longitud.isEmpty()) crearElementoVista(longitud, R.drawable.ic_picture_in_picture_black_24dp, layoutPrincipal);
                if(!latitud.isEmpty()) crearElementoVista(latitud, R.drawable.ic_picture_in_picture_black_24dp, layoutPrincipal);
                if(!telefono.isEmpty()) crearElementoVista(telefono, R.drawable.ic_perm_phone_msg_black_24dp, layoutPrincipal);
                if(!email.isEmpty()) crearElementoVista(email, R.drawable.ic_email_black_24dp, layoutPrincipal);
                if(!web.isEmpty()) crearElementoVista(web , R.drawable.ic_email_black_24dp, layoutPrincipal);
                if(!facebook.isEmpty()) crearElementoVista(facebook, R.drawable.ic_email_black_24dp, layoutPrincipal);
                if(!twitter.isEmpty()) crearElementoVista(twitter, R.drawable.ic_email_black_24dp, layoutPrincipal);
                if(!instagram.isEmpty()) crearElementoVista(instagram, R.drawable.ic_email_black_24dp,layoutPrincipal);
            }while(cursor.moveToNext());
        }
    }

    public void crearElementoVista(String nombre, int rutaImage, LinearLayout layout){
        //creamos el segundo Layout.
        LinearLayout milayout = new LinearLayout(new ContextThemeWrapper(this, R.style.AppTheme));
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        //le pasamos los parametros y la orientación.
        milayout.setLayoutParams(params);
        milayout.setOrientation(LinearLayout.HORIZONTAL);
        //add textView
        tv = new TextView(new ContextThemeWrapper(this, R.style.AppTheme));
        tv.setText(nombre);
        iv = new ImageView(new ContextThemeWrapper(this, R.style.AppTheme));
        iv.setImageResource(rutaImage);
        //Le añadimos los parametros a los view.
        iv.setLayoutParams(params);
        tv.setLayoutParams(params);
        //añadimos al layout que hemos creado tanto el texto como la imagen.
        milayout.addView(iv);
        milayout.addView(tv);
        //le añadimos el layout que hemos creado al principal.
        layout.addView(milayout);
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
    public void onBackPressed() {
        this.startActivity(new Intent(this, ListadoAcontecimientoActivity.class));
        this.finish();
    }

    @Override
    public boolean onNavigateUp() {
        this.startActivity(new Intent(this, ListadoAcontecimientoActivity.class));
        return true;
    }

}

