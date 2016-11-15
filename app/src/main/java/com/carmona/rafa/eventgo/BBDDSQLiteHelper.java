package com.carmona.rafa.eventgo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class BBDDSQLiteHelper extends SQLiteOpenHelper {

    //Sentencia SQL para crear la tabla de Usuarios
    String sqlCreateAcontecimiento = "CREATE TABLE IF NOT EXISTS `acontecimiento` (" +
            "  `id` int(11) NOT NULL," +
            "  `nombre` varchar(256) NOT NULL," +
            "  `organizador` varchar(256) DEFAULT NULL," +
            "  `descripcion` varchar(1024) NOT NULL," +
            "  `tipo` int(11) NOT NULL," +
            "  `portada` varchar(256) DEFAULT NULL," +
            "  `inicio` datetime NOT NULL," +
            "  `fin` datetime NOT NULL," +
            "  `direccion` varchar(256) DEFAULT NULL," +
            "  `localidad` varchar(256) DEFAULT NULL," +
            "  `cod_postal` int(5) DEFAULT NULL," +
            "  `provincia` varchar(256) DEFAULT NULL," +
            "  `longitud` float DEFAULT NULL," +
            "  `latitud` float DEFAULT NULL," +
            "  `telefono` int(9) DEFAULT NULL," +
            "  `email` varchar(256) DEFAULT NULL," +
            "  `web` varchar(256) DEFAULT NULL," +
            "  `facebook` varchar(256) DEFAULT NULL," +
            "  `twitter` varchar(256) DEFAULT NULL," +
            "  `instagram` varchar(256) DEFAULT NULL," +
            "  PRIMARY KEY (`id`)" +
            ")";

        String sqlCreateEventos = "CREATE TABLE IF NOT EXISTS `evento` (" +
                "  `id` int(11) NOT NULL," +
                "  `id_acontecimiento` int(11) NOT NULL," +
                "  `nombre` varchar(256) NOT NULL," +
                "  `descripcion` varchar(1024) NOT NULL," +
                "  `inicio` datetime NOT NULL," +
                "  `fin` datetime NOT NULL," +
                "  `direccion` varchar(256) DEFAULT NULL," +
                "  `localidad` varchar(256) DEFAULT NULL," +
                "  `cod_postal` int(5) DEFAULT NULL," +
                "  `provincia` varchar(256) DEFAULT NULL," +
                "  `longitud` float DEFAULT NULL," +
                "  `latitud` float DEFAULT NULL," +
                "  PRIMARY KEY (`id`,`id_acontecimiento`)" +
               // "  KEY `id_acontecimiento` (`id_acontecimiento`)" +
                ")";

    public BBDDSQLiteHelper(Context contexto, String nombre,
                            CursorFactory factory, int version) {
        super(contexto, nombre, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(sqlCreateAcontecimiento);
        db.execSQL(sqlCreateEventos);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int versionAnterior, int versionNueva) {
        //NOTA: Por simplicidad del ejemplo aquí utilizamos directamente la opción de
        //      eliminar la tabla anterior y crearla de nuevo vacía con el nuevo formato.
        //      Sin embargo lo normal será que haya que migrar datos de la tabla antigua
        //      a la nueva, por lo que este método debería ser más elaborado.
        // ESTO ES COPIADO DEL MUCHACHO DE LA PAGINA SGOLIVER, NO TIENE QUE HACER NADA DE MOMENTO

        //Se elimina la versión anterior de la tabla
        db.execSQL("DROP TABLE IF EXISTS acontecimiento");
        db.execSQL("DROP TABLE IF EXISTS evento");

        //Se crea la nueva versión de la tabla
        db.execSQL(sqlCreateAcontecimiento);
        db.execSQL(sqlCreateEventos);
    }
}