package com.example.onlinejobsmovies;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.widget.Button;
import android.os.Bundle;

public class ListarEmpregosActivity extends AppCompatActivity {

    private Button btnListarEmprego;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_empregos);

        btnListarEmprego = findViewById(R.id.empregoListarId);

        btnListarEmprego.setOnClickListener(v -> {
            Intent ListarIntent = new Intent(ListarEmpregosActivity.this, CadastroEmpregoActivity.class);
            startActivity(ListarIntent);
        });

    }
}