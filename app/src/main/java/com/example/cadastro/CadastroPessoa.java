package com.example.cadastro;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cadastro.config.DatabaseHelper;
import com.example.cadastro.dao.PessoaDAO;
import com.example.cadastro.entities.Pessoa;

public class CadastroPessoa extends AppCompatActivity {
    private EditText edtNome, edtEmail, edtCpf, edtTelefone;
    private Button btnVariavel;
    Pessoa pessoa, altPessoa;
    DatabaseHelper contatoHelper;
    PessoaDAO pessoaDAO;
    long retornoBD;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_pessoa);
        Intent it=getIntent();

        pessoaDAO = new PessoaDAO(CadastroPessoa.this);
        altPessoa = (Pessoa) it.getSerializableExtra("chave_contato");
        pessoa = new Pessoa();
        contatoHelper = new DatabaseHelper(CadastroPessoa.this);
        edtNome = findViewById(R.id.edtDescricao);
        edtEmail = findViewById(R.id.edtHoras);
        edtCpf = findViewById(R.id.edtValor);
        edtTelefone = findViewById(R.id.edtTelefone);
        btnVariavel = findViewById(R.id.btnVariavel);
        if(altPessoa != null){
            btnVariavel.setText("ALTERAR");
            edtNome.setText(altPessoa.getNome());
            edtEmail.setText(altPessoa.getEmail()+"");
            edtCpf.setText(altPessoa.getCpf());
            edtTelefone.setText(altPessoa.getTelefone());
            pessoa.setId(altPessoa.getId());
        }
        else{
            btnVariavel.setText("SALVAR");
        }
        btnVariavel.setOnClickListener(v -> {
                String nome = edtNome.getText().toString();
                String email = edtEmail.getText().toString();
                String cpf = edtCpf.getText().toString();
                String telefone = edtTelefone.getText().toString();
                long retornoBD;
                pessoa.setNome(nome);
                pessoa.setEmail(email);
                pessoa.setCpf(cpf);
                pessoa.setTelefone(telefone);
                if(btnVariavel.getText().toString().equals("SALVAR")) {
                    retornoBD=pessoaDAO.insert(pessoa);
                    if(retornoBD==-1){
                        alert("Erro ao Cadastrar!");
                    }
                    else{
                        alert("Cadastro realizado com sucesso!");
                    }
                }else{
                    pessoaDAO.update(pessoa);
                    contatoHelper.close();
                }
                finish();
        });
    }
    private void alert(String message){
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
    }
}