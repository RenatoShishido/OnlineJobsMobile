package com.example.onlinejobsmovies;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class ListarPessoasActivity extends AppCompatActivity {

    private Button btnListaPessoa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_pessoas);

        btnListaPessoa = findViewById(R.id.pessoaListarId);

        btnListaPessoa.setOnClickListener(v -> {
            Intent ListarIntent = new Intent(ListarPessoasActivity.this, CadastroPessoaActivity.class);
            startActivity(ListarIntent);
        });
    }
}