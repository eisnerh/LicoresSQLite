package com.example.eisne.licoresql.Conexion;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.eisne.licoresql.utilidades.UtilidadesLicores;
import com.example.eisne.licoresql.utilidades.UtilidadesUsuario;

/**
 * Created by eisne on 25/1/2018.
 */

public class ConexionSQLiteHelper extends SQLiteOpenHelper {

    public ConexionSQLiteHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(UtilidadesUsuario.CREAR_TABLA_USUARIO);
        database.execSQL(UtilidadesLicores.CREAR_TABLA_LICOR);
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, int versionAntigua, int versionNueva) {
        database.execSQL("DROP TABLE IF EXISTS " + UtilidadesUsuario.TABLA_USUARIO);
        database.execSQL("DROP TABLE IF EXISTS " + UtilidadesLicores.TABLA_LICORES);
        onCreate(database);
    }
}
