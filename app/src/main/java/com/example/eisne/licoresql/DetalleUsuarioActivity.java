package com.example.eisne.licoresql;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.eisne.licoresql.entidades.Usuario;

public class DetalleUsuarioActivity extends AppCompatActivity {

    TextView tvID, tvNombre, tvTelefono;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_usuario);
        tvID = findViewById(R.id.textView_ID);
        tvNombre = findViewById(R.id.textView_Nombre);
        tvTelefono = findViewById(R.id.textView_Tel);

        Bundle objEnviado = getIntent().getExtras();

        Usuario users = null;

        if (objEnviado!=null){
            users=(Usuario) objEnviado.getSerializable("usuario");
            tvID.setText(users.getId().toString());
            tvNombre.setText(users.getNombre().toString());
            tvTelefono.setText(users.getTelefono().toString());
        }
    }
}
