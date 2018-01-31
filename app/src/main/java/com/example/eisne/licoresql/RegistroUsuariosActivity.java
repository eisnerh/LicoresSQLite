package com.example.eisne.licoresql;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.eisne.licoresql.Conexion.ConexionSQLiteHelper;
import com.example.eisne.licoresql.utilidades.UtilidadesUsuario;

public class RegistroUsuariosActivity extends AppCompatActivity {


    public EditText id, nombre, telefono;
    Button registrarUsuarios;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_usuarios);
        //asignar los EditText
        id = findViewById(R.id.txtID);
        nombre = findViewById(R.id.txtNombre);
        telefono = findViewById(R.id.txtTelefono);
        registrarUsuarios = findViewById(R.id.btnGuardar);
        //OnClick Guardar Usuarios
        registrarUsuarios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MetodoregistraUsuarios();
                //MetodoInsertSQL();
            }
        });
        //fin OnClick Guardar


    }

    private void MetodoInsertSQL() {
        ConexionSQLiteHelper con = new ConexionSQLiteHelper(getApplicationContext(), "db_licores", null, 1);
        SQLiteDatabase db = con.getReadableDatabase();
        String insertUsuario = "INSERT INTO usuario (id, nombre, telefono)" +
                "VALUES ('" +
                id.getText().toString() +
                "', '" + nombre.getText().toString() +
                "', '" + telefono.getText().toString() + "' );";
        db.execSQL(insertUsuario);

        String selectUSuario = "SELECT COUNT(id) FROM usuario";
        db.execSQL(selectUSuario);
        Toast.makeText(getApplicationContext(),"Registros " + selectUSuario,Toast.LENGTH_SHORT).show();
        db.close();
        id.setText("");
        nombre.setText("");
        telefono.setText("");
    }

    private void MetodoregistraUsuarios() {
        ConexionSQLiteHelper con = new ConexionSQLiteHelper(getApplicationContext(), "db_licores", null, 1);
        SQLiteDatabase db = con.getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put(UtilidadesUsuario.CAMPO_ID, id.getText().toString());
        values.put(UtilidadesUsuario.CAMPO_NOMBRE, nombre.getText().toString());
        values.put(UtilidadesUsuario.CAMPO_TELEFONO, telefono.getText().toString());
        Long idResultante = db.insert(UtilidadesUsuario.TABLA_USUARIO, UtilidadesUsuario.CAMPO_ID, values);
        Toast.makeText(getApplicationContext(), "ID Registro " + idResultante, Toast.LENGTH_SHORT).show();
        db.close();
    }
}
