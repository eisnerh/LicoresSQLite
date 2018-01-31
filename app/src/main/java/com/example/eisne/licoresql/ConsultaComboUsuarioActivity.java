package com.example.eisne.licoresql;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.eisne.licoresql.Conexion.ConexionSQLiteHelper;
import com.example.eisne.licoresql.entidades.Usuario;
import com.example.eisne.licoresql.utilidades.UtilidadesUsuario;

import java.util.ArrayList;

public class ConsultaComboUsuarioActivity extends AppCompatActivity {

    EditText cmbNombre, cmbID, cmbTelefono;
    Spinner SpinnerQuery;
    // Representa la información en el combo
    ArrayList<String> listaPersonas;
    // Entidad Usuario
    ArrayList<Usuario> personasList;

    ConexionSQLiteHelper conn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulta_combo_usuario);
        cmbNombre = findViewById(R.id.cmbTextNombre);
        cmbID = findViewById(R.id.cmbTextID);
        cmbTelefono = findViewById(R.id.cmbTextTelefono);
        SpinnerQuery = findViewById(R.id.SpinnerQuery);
        conn = new ConexionSQLiteHelper(getApplicationContext(), "db_licores", null, 1);
        consultaListaPersonass();

        ArrayAdapter<CharSequence> adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, listaPersonas);

        SpinnerQuery.setAdapter(adapter);

        SpinnerQuery.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int posicion, long id) {
                if (posicion != 0) {
                    cmbID.setText(personasList.get(posicion - 1).getId().toString());
                    cmbNombre.setText(personasList.get(posicion - 1).getNombre().toString());
                    cmbTelefono.setText(personasList.get(posicion - 1).getTelefono().toString());
                } else {
                    cmbID.setText("");
                    cmbNombre.setText("");
                    cmbTelefono.setText("");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void consultaListaPersonass() {
        SQLiteDatabase db = conn.getReadableDatabase();
        Usuario persona = null;
        personasList = new ArrayList<Usuario>();
        Cursor cursor = db.rawQuery("SELECT * FROM " + UtilidadesUsuario.TABLA_USUARIO, null);

        while (cursor.moveToNext()) {
            persona = new Usuario();
            persona.setId(cursor.getInt(0));
            persona.setNombre(cursor.getString(1));
            persona.setTelefono(cursor.getString(2));

            Log.i("id", persona.getId().toString());
            Log.i("nombre", persona.getNombre());
            Log.i("telefono", persona.getTelefono());

            personasList.add(persona);

        }
        obtenerLista();

    }

    private void obtenerLista() {
        listaPersonas = new ArrayList<String>();
        listaPersonas.add("Seleccione");

        for (int i = 0; i < personasList.size(); i++) {
            listaPersonas.add(personasList.get(i).getId() + " - " + personasList.get(i).getNombre());
        }
    }
}
