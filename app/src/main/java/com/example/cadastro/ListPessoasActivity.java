package com.example.cadastro;

import androidx.appcompat.app.AppCompatActivity;

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

import com.example.cadastro.config.DatabaseHelper;
import com.example.cadastro.dao.PessoaDAO;
import com.example.cadastro.entities.Pessoa;
import com.example.cadastro.util.UtilAlert;

import java.util.ArrayList;

public class ListPessoasActivity extends AppCompatActivity {
    private ListView listPessoas;
    private Button btnNovoCadastro;
    private Pessoa pessoa;
    DatabaseHelper databaseHelper;
    PessoaDAO pessoaDAO;
    ArrayList<Pessoa> arrayListPessoa;
    ArrayAdapter<Pessoa> arrayAdapterPessoa;
    private int id1,id2; //menu item
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_pessoas);
        pessoaDAO = new PessoaDAO(ListPessoasActivity.this);
        listPessoas = findViewById(R.id.listPessoas);
        registerForContextMenu(listPessoas);
        btnNovoCadastro = findViewById(R.id.btnNovoCadastro);
        btnNovoCadastro.setOnClickListener(v -> {
            Intent it = new Intent(ListPessoasActivity.this, CadastroPessoa.class);
            startActivity(it);
        });


        listPessoas.setOnItemClickListener((AdapterView<?> adapterView, View view, int position, long id) -> {
            Pessoa pessoaEnviada = (Pessoa) arrayAdapterPessoa.getItem(position);
            Intent it = new Intent(ListPessoasActivity.this, CadastroPessoa.class);
            it.putExtra("chave",pessoaEnviada);
            startActivity(it);
        });
        listPessoas.setOnItemLongClickListener((AdapterView<?> adapterView,View view, int position, long id) -> {
            pessoa = arrayAdapterPessoa.getItem(position);
            return false;
        });
    }
    public void preencheLista(){
        databaseHelper = new DatabaseHelper(ListPessoasActivity.this);
        arrayListPessoa = pessoaDAO.selectAll();
        databaseHelper.close();
        if(listPessoas!=null){
            arrayAdapterPessoa = new ArrayAdapter<Pessoa>(ListPessoasActivity.this,
                    android.R.layout.simple_list_item_1,arrayListPessoa);
            listPessoas.setAdapter(arrayAdapterPessoa);
        }
    }
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo){

        MenuItem mDelete = menu.add(Menu.NONE, id1, 1,"Deleta Registro");
        MenuItem mSair = menu.add(Menu.NONE, id2, 2,"Cancela");
        mDelete.setOnMenuItemClickListener(menuItem -> {

                long retornoBD;
                databaseHelper = new DatabaseHelper(ListPessoasActivity.this);
                retornoBD = pessoaDAO.delete(pessoa);
                databaseHelper.close();
                if (retornoBD == -1) {
                    UtilAlert.alert(ListPessoasActivity.this, "Erro de exclusão!");
                } else {
                    UtilAlert.alert(ListPessoasActivity.this, "Registro excluído com sucesso!");
                }
                preencheLista();
                return false;


        });
        super.onCreateContextMenu(menu, v, menuInfo);
    }
    @Override
    protected void onResume(){
        super.onResume();
        preencheLista();
    }
}