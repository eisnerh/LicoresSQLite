package com.example.eisne.licoresql;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.eisne.licoresql.Conexion.ConexionSQLiteHelper;
import com.example.eisne.licoresql.utilidades.UtilidadesUsuario;

public class ConsultarUsuariosActivity extends AppCompatActivity {

    EditText CUid, CUnombre, CUtelefono;
    Button lookUp, Delete, Update;
    ConexionSQLiteHelper conn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultar_usuarios);

        //Conexion SQLITE
        conn = new ConexionSQLiteHelper(getApplicationContext(), "db_licores", null, 1);


        //EditText
        CUid = findViewById(R.id.CU_txtDocumento);
        CUnombre = findViewById(R.id.CU_txtNombre);
        CUtelefono = findViewById(R.id.CU_txtTelefono);

        //Button
        lookUp = findViewById(R.id.CU_btnBuscar);
        Delete = findViewById(R.id.CU_btnEliminar);
        Update = findViewById(R.id.CU_btnActualizar);

        //Boton lookUp
        lookUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SQLiteDatabase db = conn.getReadableDatabase();
                String[] parametros = {CUid.getText().toString()};
                String[] campos = {UtilidadesUsuario.CAMPO_NOMBRE, UtilidadesUsuario.CAMPO_TELEFONO};

                try {
                    Cursor cursor = db.query(UtilidadesUsuario.TABLA_USUARIO, campos, UtilidadesUsuario.CAMPO_ID + "=?", parametros, null, null, null);
                    cursor.moveToFirst();
                    CUnombre.setText(cursor.getString(0));
                    CUtelefono.setText(cursor.getString(1));
                    cursor.close();
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "Error ! " + e, Toast.LENGTH_SHORT).show();
                    limpiar();
                }

            }
        });
    }

    public void limpiar() {
        CUid.setText("");
        CUnombre.setText("");
        CUtelefono.setText("");
    }
}
