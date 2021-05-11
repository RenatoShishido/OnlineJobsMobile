package com.example.onlinejobsmovies;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private Button btnEmprego, btnPessoa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mudarParaTelalistaEmpregos();

        mudarParaTelaListaPessoas();

    }

    private void mudarParaTelalistaEmpregos() {
        btnEmprego = findViewById(R.id.empregoId);
        btnEmprego.setOnClickListener(v -> {
            Intent empregoIntent = new Intent(MainActivity.this, ListarEmpregosActivity.class);
            startActivity(empregoIntent);
        });
    }

    private void mudarParaTelaListaPessoas() {
        btnPessoa = findViewById(R.id.pessoaId);

        btnPessoa.setOnClickListener(v -> {
            Intent pessoaIntent = new Intent(MainActivity.this, ListarPessoasActivity.class);
            startActivity(pessoaIntent);
        });
    }

}