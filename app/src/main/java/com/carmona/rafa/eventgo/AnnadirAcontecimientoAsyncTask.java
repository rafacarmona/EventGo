package com.carmona.rafa.eventgo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Environment;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Rafa on 27/10/2016.
 */

public class AnnadirAcontecimientoAsyncTask extends AsyncTask<String, String, String> {

    HttpURLConnection urlConnection;
    String id;
    Context myContext;
    public AnnadirAcontecimientoAsyncTask(String id, Context myContext){
        this.id = id;
        this.myContext = myContext;
    }

    @Override
    protected String doInBackground(String... args) {
        StringBuilder result = new StringBuilder();

        try {
            URL url = new URL("http://mieventorafa.hol.es/api/v1/acontecimiento/"+id);
            urlConnection = (HttpURLConnection) url.openConnection();
            InputStream in = new BufferedInputStream(urlConnection.getInputStream());

            BufferedReader reader = new BufferedReader(new InputStreamReader(in));

            String line;
            while ((line = reader.readLine()) != null) {
                result.append(line);
            }

            //sacamos el json
            JSONObject jsonCompleto = new JSONObject(result.toString());

            if (jsonCompleto.has("acontecimiento")) {
                JSONObject jsonAcontecimiento = new JSONObject(jsonCompleto.getString("acontecimiento"));
                String nombreAcontecimiento = (jsonAcontecimiento.has("nombre") ? jsonAcontecimiento.getString("nombre") : "");
                String organizador = (jsonAcontecimiento.has("organizador") ? jsonAcontecimiento.getString("nombre") : "");
                String descripcion = (jsonAcontecimiento.has("descripcion") ? jsonAcontecimiento.getString("descripcion") : "");
                String tipo = (jsonAcontecimiento.has("tipo") ? jsonAcontecimiento.getString("tipo") : "");
                String portada = (jsonAcontecimiento.has("portada") ? jsonAcontecimiento.getString("portada") : "");
                String inicio = (jsonAcontecimiento.has("inicio") ? jsonAcontecimiento.getString("inicio") : "");
                String fin = (jsonAcontecimiento.has("fin") ? jsonAcontecimiento.getString("fin") : "");
                String direccion = (jsonAcontecimiento.has("direccion") ? jsonAcontecimiento.getString("direccion") : "");
                String localidad = (jsonAcontecimiento.has("localidad") ? jsonAcontecimiento.getString("localidad") : "");
                String codPostal = (jsonAcontecimiento.has("codPostal") ? jsonAcontecimiento.getString("codPostal") : "");
                String provincia = (jsonAcontecimiento.has("provincia") ? jsonAcontecimiento.getString("provincia") : "");
                String longitud = (jsonAcontecimiento.has("longitud") ? jsonAcontecimiento.getString("longitud") : "");
                String altitud = (jsonAcontecimiento.has("altitud") ? jsonAcontecimiento.getString("altitud") : "");
                String telefono = (jsonAcontecimiento.has("telefono") ? jsonAcontecimiento.getString("telefono") : "");
                String email = (jsonAcontecimiento.has("email") ? jsonAcontecimiento.getString("email") : "");
                String web = (jsonAcontecimiento.has("web") ? jsonAcontecimiento.getString("web") : "");
                String facebook = (jsonAcontecimiento.has("facebook") ? jsonAcontecimiento.getString("facebook") : "");
                String twitter = (jsonAcontecimiento.has("twitter") ? jsonAcontecimiento.getString("twitter") : "");
                String instagram = (jsonAcontecimiento.has("instagram") ? jsonAcontecimiento.getString("instagram") : "");


                MyLog.i("NuevoAcontecimiento-Acon", (jsonAcontecimiento.has("nombre")) ? jsonAcontecimiento.getString("nombre") : "No nombre");
                //Recuperar array
                if(!jsonCompleto.has("eventos")){
                    MyLog.i("NuevoAcontecimiento-Acon", "No tiene evento. No es un error, pero mola verlo en la consola");
                }else{
                        JSONArray jsonEventoArray = new JSONArray(jsonCompleto.getString("eventos"));
                        //COMPROBAR QUE EXISTE EL JSONARRAY
                        for (int i = 0; i < jsonEventoArray.length(); i++) {
                        JSONObject jsoneventoObjeto = jsonEventoArray.getJSONObject(i);
                        String nombreEvento = (jsoneventoObjeto.has("nombre") ? jsoneventoObjeto.getString("nombre") : "");
                        String descripcionEvento  = (jsoneventoObjeto.has("descripcion") ? jsoneventoObjeto.getString("descripcion") : "");
                        String inicoEvento  = (jsoneventoObjeto.has("inicio") ? jsoneventoObjeto.getString("inicio") : "");
                        String finEvento = (jsoneventoObjeto.has("fin") ? jsoneventoObjeto.getString("fin") : "");
                        String direccionEvento = (jsoneventoObjeto.has("direccion") ? jsoneventoObjeto.getString("direccion") : "");
                        String localidadEvento = (jsoneventoObjeto.has("localidad") ? jsoneventoObjeto.getString("localidad") : "");
                        String codPostalEvento = (jsoneventoObjeto.has("codPostal") ? jsoneventoObjeto.getString("codPostal") : "");
                        String provinciaEvento = (jsonAcontecimiento.has("provincia") ? jsonAcontecimiento.getString("provincia"): "");
                        String longitudEvento = (jsoneventoObjeto.has("longitud") ? jsoneventoObjeto.getString("latitud") : "");
                        String altitudEvento = (jsonAcontecimiento.has("altitud") ? jsonAcontecimiento.getString("altitud"): "");

                        MyLog.i("NuevoAcontecimiento-Event", jsoneventoObjeto.getString("nombre"));

                    }
                }
                BBDDSQLiteHelper usdbh =
                        new BBDDSQLiteHelper(this.myContext, Environment.getExternalStorageDirectory()+"/Eventgo.db", null, 1);

                SQLiteDatabase db = usdbh.getWritableDatabase();

                //Si hemos abierto correctamente la base de datos
                if(db != null)
                {
                    //Insertamos los datos en la tabla Usuarios
                    db.execSQL("INSERT INTO `acontecimiento` (`id`, `nombre`, `organizador`, `descripcion`, " +
                            "`tipo`, `portada`, `inicio`, `fin`, `direccion`, `localidad`, `cod_postal`, `provincia`," +
                            " `longitud`, `altitud`, `telefono`, `email`, `web`, `facebook`, `twitter`, `instagram`) " +
                            "VALUES ('"+id+"', '"+nombreAcontecimiento+"','"+organizador+"', '"+descripcion+"', '"+tipo+"', " +
                            "'"+portada+"', '"+inicio+"', '"+fin+"', '"+direccion+"', '"+localidad+"', '"+codPostal+"', '"+provincia+"', " +
                            "'"+longitud+"', '"+altitud+"', '"+telefono+"', '"+email+"', '"+web+"', '"+facebook+"', '"+twitter+"'," +
                            "'"+instagram+"');");
                }

                //Cerramos la base de datos
                db.close();
            } else {
                MyLog.i("NuevoAcontecimiento-Acon", "error");
            }

            //Fin


        }catch( Exception e) {
            e.printStackTrace();
        }
        finally {
            urlConnection.disconnect();
        }

        return result.toString();
    }

    @Override
    protected void onPostExecute(String result) {
        MyLog.d("onPostExecute", result);
    }
}
