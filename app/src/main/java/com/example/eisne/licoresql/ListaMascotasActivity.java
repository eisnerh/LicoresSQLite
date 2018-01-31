package com.example.eisne.licoresql;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.eisne.licoresql.Conexion.ConexionSQLiteHelper;
import com.example.eisne.licoresql.entidades.Licores;
import com.example.eisne.licoresql.entidades.Usuario;
import com.example.eisne.licoresql.utilidades.UtilidadesLicores;


import java.util.ArrayList;

public class ListaMascotasActivity extends AppCompatActivity {

    ListView listViewLicores;
    ArrayList<String> ListasInformacion;
    ArrayList<Licores> ListasLicores;

    ConexionSQLiteHelper conn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_mascotas);

        conn = new ConexionSQLiteHelper(getApplicationContext(), "db_licores", null, 1);

        listViewLicores = findViewById(R.id.listViewLicores);

        consultarListaPersonas();

        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, ListasInformacion);
        listViewLicores.setAdapter(arrayAdapter);

        listViewLicores.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int pos, long l) {
                Licores licores = ListasLicores.get(pos);

                Intent buttonDetalleLicores = null;
                buttonDetalleLicores = new Intent(getApplicationContext(), DetalleMascotaActivity.class);

                Bundle bundle = new Bundle();
                bundle.putSerializable("licores", licores);
                buttonDetalleLicores.putExtras(bundle);
                startActivity(buttonDetalleLicores);
            }
        });
    }

    private void consultarListaPersonas() {
        SQLiteDatabase bd = conn.getReadableDatabase();
        Licores licores = null;

        ListasLicores = new ArrayList<Licores>();
        Cursor cursor = bd.rawQuery("SELECT * FROM " + UtilidadesLicores.TABLA_LICORES, null);

        while (cursor.moveToNext()) {
            licores = new Licores();
            licores.setMaterial(cursor.getString(0));
            licores.setDescripcionBreve(cursor.getString(1));
            licores.setUnidades(cursor.getInt(2));
            licores.setIdUsuario(cursor.getString(3));
            ListasLicores.add(licores);
        }
        obtenerLista();
    }

    private void obtenerLista() {
        ListasInformacion = new ArrayList<String>();

        for (int i = 0; i < ListasLicores.size(); i++) {
            ListasInformacion.add(ListasLicores.get(i).getMaterial() + " \n " +
                    ListasLicores.get(i).getDescripcionBreve() + " \n " +
            ListasLicores.get(i).getUnidades() + " \n " +
            ListasLicores.get(i).getIdUsuario());
        }
    }
}
