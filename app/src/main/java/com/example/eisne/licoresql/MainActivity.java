package com.example.eisne.licoresql;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.eisne.licoresql.Conexion.ConexionSQLiteHelper;

public class MainActivity extends AppCompatActivity {

    Button Agregar;
    Button Consultar;
    Button Spinner;
    Button ListViewP;
    Button AgregarLicores;
    Button ListaConsultarLicores;
    Button DetalleMascotaActivitys;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //coneccion a la base de datos
        ConexionSQLiteHelper conn = new ConexionSQLiteHelper(this, "db_licores",null,1);

        //Accion del Boton Agregar

        Agregar = findViewById(R.id.btnAgregarUsuarios);
        Agregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent botonAgregar = null;
                botonAgregar = new Intent(getApplicationContext(), RegistroUsuariosActivity.class);
                startActivity(botonAgregar);
            }
        });

        // Fin del Boton agregar

        //Accion del boton Consultar
        Consultar = findViewById(R.id.btnConsultarUsuario);
        Consultar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent botonConsultar = null;
                botonConsultar = new Intent(getApplicationContext(),ConsultarUsuariosActivity.class);
                startActivity(botonConsultar);
            }
        });

        // fin boton consultar

        // Spinner
        Spinner = findViewById(R.id.btnSpinnerConsultarUsuario);
        Spinner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent buttonSpinner = null;
                buttonSpinner = new Intent(getApplicationContext(),ConsultaComboUsuarioActivity.class);
                startActivity(buttonSpinner);
            }
        });
        // fin Spinner

        // List View Personas

        ListViewP = findViewById(R.id.btnLVPersonas);
        ListViewP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent buttonListViewPersonas = null;
                buttonListViewPersonas = new Intent(getApplicationContext(),ActivityConsultarListaListView.class);
                startActivity(buttonListViewPersonas);
            }
        });

        // Fin List View


        AgregarLicores = findViewById(R.id.btnRegistroMascotas);
        AgregarLicores.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent buttonAgregarLicor = null;
                buttonAgregarLicor = new Intent(getApplicationContext(),RegistroMascotasActivity.class);
                startActivity(buttonAgregarLicor);
            }
        });

        ListaConsultarLicores = findViewById(R.id.btnListaMascotas);
        ListaConsultarLicores.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent buttonListaLicores = null;
                buttonListaLicores = new Intent(getApplicationContext(),ListaMascotasActivity.class);
                startActivity(buttonListaLicores);
            }
        });

        DetalleMascotaActivitys = findViewById(R.id.btnDetalleLicores);
        DetalleMascotaActivitys.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent buttonDetalleLicores = null;
                buttonDetalleLicores = new Intent(getApplicationContext(), DetalleMascotaActivity.class);
                startActivity(buttonDetalleLicores);
            }
        });
    }
}
