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
import com.example.cadastro.dao.EmpregoDAO;
import com.example.cadastro.entities.Emprego;
import com.example.cadastro.util.UtilAlert;

import java.util.ArrayList;

public class ListEmpregosActivity extends AppCompatActivity {

    private ListView listEmprego;
    private Button btnListEmprego;
    private Emprego emprego;
    DatabaseHelper contatoHelper;
    EmpregoDAO empregoDAO;
    ArrayList<Emprego> arrayListEmprego;
    ArrayAdapter<Emprego> arrayAdapterEmprego;
    private int id1,id2; //menu item
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_empregos);
        empregoDAO = new EmpregoDAO(ListEmpregosActivity.this);
        listEmprego = findViewById(R.id.listEmprego);
        registerForContextMenu(listEmprego);
        btnListEmprego = findViewById(R.id.btnCadastrar);
        btnListEmprego.setOnClickListener(v -> {
            Intent it = new Intent(ListEmpregosActivity.this, CadastroEmprego.class);
            startActivity(it);
        });


        listEmprego.setOnItemClickListener((AdapterView<?> adapterView, View view, int position, long id) -> {
            Emprego emprego = (Emprego) arrayAdapterEmprego.getItem(position);
            Intent it = new Intent(ListEmpregosActivity.this, CadastroEmprego.class);
            it.putExtra("chave_contato",emprego);
            startActivity(it);
        });
        listEmprego.setOnItemLongClickListener((AdapterView<?> adapterView,View view, int position, long id) -> {
            emprego = arrayAdapterEmprego.getItem(position);
            return false;
        });
    }
    public void preencheLista(){
        contatoHelper = new DatabaseHelper(ListEmpregosActivity.this);
        arrayListEmprego = empregoDAO.selectAll();
        contatoHelper.close();
        if(listEmprego!=null){
            arrayAdapterEmprego = new ArrayAdapter<Emprego>(ListEmpregosActivity.this,
                    android.R.layout.simple_list_item_1,arrayListEmprego);
            listEmprego.setAdapter(arrayAdapterEmprego);
        }
    }
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo){
        MenuItem mDelete = menu.add(Menu.NONE, id1, 1,"Deleta Registro");
        MenuItem mSair = menu.add(Menu.NONE, id2, 2,"Cancela");
        mDelete.setOnMenuItemClickListener(menuItem -> {
            try {
                long retornoBD;
                contatoHelper = new DatabaseHelper(ListEmpregosActivity.this);
                retornoBD = empregoDAO.delete(emprego);
                contatoHelper.close();
                if(retornoBD==-1){
                    UtilAlert.alert(ListEmpregosActivity.this, "Erro de exclusão!");
                }
                else{
                    UtilAlert.alert(ListEmpregosActivity.this, "Registro excluído com sucesso!");
                }
                preencheLista();
            }catch (Exception exception) {
                UtilAlert.alert(ListEmpregosActivity.this, "Erro ao excluir emprego com pessoas relacionadas");
            }

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