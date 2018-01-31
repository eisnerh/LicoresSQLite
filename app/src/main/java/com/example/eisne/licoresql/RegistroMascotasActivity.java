package com.example.eisne.licoresql;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.eisne.licoresql.Conexion.ConexionSQLiteHelper;
import com.example.eisne.licoresql.entidades.Usuario;
import com.example.eisne.licoresql.utilidades.UtilidadesLicores;
import com.example.eisne.licoresql.utilidades.UtilidadesUsuario;

import java.util.ArrayList;

public class RegistroMascotasActivity extends AppCompatActivity {

    EditText Reg_Material, Reg_Descripcion, Reg_Cantidad;
    Spinner Reg_Usuario;
    Button Guardad;

    ArrayList<String> List_Personas;
    ArrayList<Usuario> Personas_List;

    ConexionSQLiteHelper conn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_mascotas);

        Reg_Material = findViewById(R.id.txt_Reg_Material);
        Reg_Descripcion = findViewById(R.id.txt_Reg_Descripcion);
        Reg_Cantidad = findViewById(R.id.txt_Reg_Cantidad);

        Reg_Usuario = findViewById(R.id.Spinner_Reg_Usuarioi);

        Guardad = findViewById(R.id.btn_Reg_Guardar);

        conn = new ConexionSQLiteHelper(getApplicationContext(), "db_licores",
                null, 1);

        consultarListaPersonas();

        ArrayAdapter<CharSequence> adapter = new ArrayAdapter(this,
                android.R.layout.simple_spinner_item, List_Personas);

        Reg_Usuario.setAdapter(adapter);

        Reg_Usuario.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        Guardad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SQLiteDatabase db = conn.getWritableDatabase();

                ContentValues values = new ContentValues();
                values.put(UtilidadesLicores.MATERIAL, Reg_Material.getText().toString());
                values.put(UtilidadesLicores.DESCRIPCION_BREVE,
                        Reg_Descripcion.getText().toString());
                values.put(UtilidadesLicores.UNIDADES, Reg_Cantidad.getText().toString());

                int idCombo = (int) Reg_Usuario.getSelectedItemId();

                if (idCombo != 0) {
                    Log.i("Tamaño", Personas_List.size() + "");
                    Log.i("id combo", idCombo + "");
                    Log.i("id combo -1", (idCombo - 1) + "");
                    int idUsuario = Personas_List.get(idCombo - 1).getId();
                    Log.i("Id Dueño", idUsuario + "");

                    values.put(UtilidadesLicores.IDUSUARIO,idUsuario);

                    Long idResultante = db.insert(UtilidadesLicores.TABLA_LICORES,
                            UtilidadesLicores.MATERIAL, values);
                    Toast.makeText(getApplicationContext(), "Id Registro " + idResultante,
                            Toast.LENGTH_SHORT).show();
                    db.close();
                } else {
                    Toast.makeText(getApplicationContext(), "Debe seleccionar un dueño",
                            Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

    private void consultarListaPersonas() {
        SQLiteDatabase db = conn.getReadableDatabase();
        Usuario persona = null;
        Personas_List = new ArrayList<Usuario>();
        Cursor cursor = db.rawQuery("SELECT * FROM " + UtilidadesUsuario.TABLA_USUARIO, null);

        while (cursor.moveToNext()) {
            persona = new Usuario();
            persona.setId(cursor.getInt(0));
            persona.setNombre(cursor.getString(1));
            persona.setTelefono(cursor.getString(2));

            Log.i("id", persona.getId().toString());
            Log.i("nombre", persona.getNombre());
            Log.i("telefono", persona.getTelefono());

            Personas_List.add(persona);

        }
        obtenerLista();
    }

    private void obtenerLista() {
        List_Personas = new ArrayList<String>();
        List_Personas.add("Seleccione");

        for (int i = 0; i < Personas_List.size(); i++) {
            List_Personas.add(Personas_List.get(i).getId() + " - " + Personas_List.get(i).getNombre());
        }

    }
}
