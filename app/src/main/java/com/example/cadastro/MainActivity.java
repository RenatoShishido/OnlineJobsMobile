package com.example.cadastro;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cadastro.config.DatabaseHelper;
import com.example.cadastro.dao.EmpregoDAO;
import com.example.cadastro.dao.PessoaDAO;
import com.example.cadastro.entities.Pessoa;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private Button btnListarPessoas, btnListarEmpregos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnListarPessoas = findViewById(R.id.btnListarPessoa);
        btnListarEmpregos = findViewById(R.id.btnListarEmprego);


        btnListarPessoas.setOnClickListener(v -> {
            Intent intentList = new Intent(MainActivity.this, ListPessoasActivity.class);
            startActivity(intentList);
        });

        btnListarEmpregos.setOnClickListener(v -> {
            Intent intentList = new Intent(MainActivity.this, ListEmpregosActivity.class);
            startActivity(intentList);
        });
    }
}