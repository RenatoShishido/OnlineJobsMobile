package com.example.cadastro;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cadastro.config.DatabaseHelper;
import com.example.cadastro.dao.EmpregoDAO;
import com.example.cadastro.dao.PessoaDAO;
import com.example.cadastro.entities.Emprego;
import com.example.cadastro.entities.Pessoa;

public class CadastroEmprego extends AppCompatActivity {
    private EditText edtDescricao, edtHoras, edtValor;
    private Button btnVariavelEmprego;
    Emprego emprego, altEmprego;
    DatabaseHelper contatoHelper;
    EmpregoDAO empregoDAO;
    long retornoBD;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_pessoa);
        Intent it=getIntent();
        emprego = new Emprego();
        empregoDAO = new EmpregoDAO(CadastroEmprego.this);
        altEmprego = (Emprego) it.getSerializableExtra("chave_contato");

        contatoHelper = new DatabaseHelper(CadastroEmprego.this);

        edtDescricao = findViewById(R.id.edtDescricao);
        edtHoras = findViewById(R.id.edtHoras);
        edtValor = findViewById(R.id.edtValor);
        btnVariavelEmprego = findViewById(R.id.btnVariavelEmprego);
        if(altEmprego != null){
            btnVariavelEmprego.setText("ALTERAR");
            edtDescricao.setText(altEmprego.getDescricao());
            edtHoras.setText(altEmprego.getHoras()+"");
            edtValor.setText(altEmprego.getValor()+"");
            emprego.setId(altEmprego.getId());
        }
        else{
            btnVariavelEmprego.setText("SALVAR");
        }
        btnVariavelEmprego.setOnClickListener(v -> {
                String descricao = edtDescricao.getText().toString();
                int horas = Integer.parseInt(edtHoras.getText().toString());
                double valor = Double.parseDouble(edtValor.getText().toString());
                long retornoBD;
                emprego.setDescricao(descricao);
                emprego.setHoras(horas);
                emprego.setValor(valor);
                if(btnVariavelEmprego.getText().toString().equals("SALVAR")) {
                    retornoBD=empregoDAO.insert(emprego);
                    if(retornoBD==-1){
                        alert("Erro ao Cadastrar!");
                    }
                    else{
                        alert("Cadastro realizado com sucesso!");
                    }
                }else{
                    empregoDAO.update(emprego);
                    contatoHelper.close();
                }
                finish();
        });
    }
    private void alert(String message){
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
    }
}