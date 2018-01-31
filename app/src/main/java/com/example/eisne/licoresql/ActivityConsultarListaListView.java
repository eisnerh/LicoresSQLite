package com.example.eisne.licoresql;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.eisne.licoresql.Conexion.ConexionSQLiteHelper;
import com.example.eisne.licoresql.entidades.Usuario;
import com.example.eisne.licoresql.utilidades.UtilidadesUsuario;

import java.util.ArrayList;

public class ActivityConsultarListaListView extends AppCompatActivity {

    ListView listViewPersonas;
    ArrayList<String> listaInformacion;
    ArrayList<Usuario> ListUsuario;

    ConexionSQLiteHelper conn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultar_lista_list_view);

        listViewPersonas = findViewById(R.id.listviewPersonas);

        conn = new ConexionSQLiteHelper(getApplicationContext(), "db_licores", null, 1);
        
        consultarListPersonas();

        ArrayAdapter arrayAdapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,listaInformacion);
        listViewPersonas.setAdapter(arrayAdapter);
        listViewPersonas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int pos, long l) {
                String informacion = "id: " + ListUsuario.get(pos).getId()+"\n";
                informacion += "Nombre: " + ListUsuario.get(pos).getNombre()+"\n";
                informacion += "Teléfono: " + ListUsuario.get(pos).getTelefono()+".";

                Toast.makeText(getApplicationContext(),informacion,Toast.LENGTH_SHORT).show();

                Usuario user = ListUsuario.get(pos);

                Intent intent= new Intent(ActivityConsultarListaListView.this,DetalleUsuarioActivity.class);

                Bundle bundle = new Bundle();
                bundle.putSerializable("usuario",user);

                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

    }

    private void consultarListPersonas() {
        SQLiteDatabase db = conn.getReadableDatabase();
        Usuario usuarios = null;
        ListUsuario = new ArrayList<Usuario>();
        Cursor cursor = db.rawQuery("SELECT * FROM " + UtilidadesUsuario.TABLA_USUARIO, null);

        while (cursor.moveToNext()) {
            usuarios = new Usuario();
            usuarios.setId(cursor.getInt(0));
            usuarios.setNombre(cursor.getString(1));
            usuarios.setTelefono(cursor.getString(2));

            ListUsuario.add(usuarios);

        }
        obtenerLista();
    }

    private void obtenerLista() {
        listaInformacion = new ArrayList<String>();

        for (int i = 0; i < ListUsuario.size(); i++) {
            listaInformacion.add(ListUsuario.get(i).getId() + " - " + ListUsuario.get(i).getNombre());
        }
    }
}
