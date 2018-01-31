package com.example.eisne.licoresql;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.eisne.licoresql.Conexion.ConexionSQLiteHelper;
import com.example.eisne.licoresql.entidades.Licores;
import com.example.eisne.licoresql.utilidades.UtilidadesUsuario;

public class DetalleMascotaActivity extends AppCompatActivity {

    ConexionSQLiteHelper Conn;

    TextView txtDetal_Material;
    TextView txtDetal_Descripcion;
    TextView txtDetal_Cantidad;
    TextView txtDetal_IDUser;
    TextView txtDetal_Nombre;
    TextView txtDetal_Tel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_mascota);

        Conn = new ConexionSQLiteHelper(getApplicationContext(),
                "db_licores", null, 1);

        txtDetal_Material = findViewById(R.id.txtDetal_Material);
        txtDetal_Descripcion = findViewById(R.id.txtDetal_Descripcion);
        txtDetal_Cantidad = findViewById(R.id.txtDetal_Cantidad);
        txtDetal_IDUser = findViewById(R.id.txtDetal_IDUser);
        txtDetal_Nombre = findViewById(R.id.txtDetal_Nombre);
        txtDetal_Tel = findViewById(R.id.txtDetal_Tel);

        Bundle objetoEnviado = getIntent().getExtras();
        Licores licores = null;

        if (objetoEnviado != null) {
            licores = (Licores) objetoEnviado.getSerializable("licores");
            txtDetal_Material.setText(licores.getMaterial().toString());
            txtDetal_Descripcion.setText(licores.getDescripcionBreve().toString());
            txtDetal_Cantidad.setText(Integer.valueOf(licores.getUnidades()));
            consultarPersona(licores.getIdUsuario());

        }

    }

    private void consultarPersona(String idUsuario) {
        SQLiteDatabase db = Conn.getReadableDatabase();
        String[] parametros = {idUsuario.toString()};
        String[] campos = {UtilidadesUsuario.CAMPO_NOMBRE, UtilidadesUsuario.CAMPO_TELEFONO};
        Toast.makeText(getApplicationContext(), "El Documento " +
                idUsuario, Toast.LENGTH_SHORT).show();
        try {
            Cursor cursor = db.query(UtilidadesUsuario.TABLA_USUARIO,
                    campos, UtilidadesUsuario.CAMPO_ID + "=?",
                    parametros, null, null, null);
            txtDetal_IDUser.setText(idUsuario.toString());
            txtDetal_Nombre.setText(cursor.getString(0));
            txtDetal_Tel.setText(cursor.getString(1));
            cursor.close();
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(),
                    "El Documento no existe ", Toast.LENGTH_SHORT).show();
            txtDetal_Nombre.setText("");
            txtDetal_Tel.setText("");
        }
    }
}
