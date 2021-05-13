package com.example.cadastro.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.cadastro.config.DatabaseHelper;
import com.example.cadastro.entities.Pessoa;

import java.util.ArrayList;

public class PessoaDAO {

    DatabaseHelper conexao;
    SQLiteDatabase db;

    public PessoaDAO(Context context) {
        conexao = new DatabaseHelper(context);
        db = conexao.getWritableDatabase();
    }


    public long insert(Pessoa pessoa) {
        long retornoBD;
        ContentValues values = new ContentValues();
        values.put("nome", pessoa.getNome());
        values.put("email", pessoa.getEmail());
        values.put("telefone", pessoa.getTelefone());
        values.put("cpf", pessoa.getCpf());
        retornoBD = db.insert("pessoa", null, values);
        String res = Long.toString(retornoBD);
        Log.i("BDContatoHelper", res);
        db.close();
        return retornoBD;
    }

    public ArrayList<Pessoa> selectAll() {
        String[] coluns = {"id", "vagaId", "nome", "cpf", "email", "telefone"};

        Cursor cursor = db.query("pessoa", coluns, null, null, null,
                null, "upper(nome)", null);

        ArrayList<Pessoa> listaPessoa = new ArrayList<Pessoa>();
        while (cursor.moveToNext()) {
            Pessoa pessoa = new Pessoa();
            pessoa.setId(cursor.getInt(0));
            pessoa.setVagaId(cursor.getInt(1));
            pessoa.setNome(cursor.getString(2));
            pessoa.setCpf(cursor.getString(3));
            pessoa.setEmail(cursor.getString(4));
            pessoa.setTelefone(cursor.getString(5));

            listaPessoa.add(pessoa);
        }
        return listaPessoa;
    }

    public long update(Pessoa pessoa) {
        long retornoBD;
        ContentValues values = new ContentValues();
        values.put("nome", pessoa.getNome());
        values.put("email", pessoa.getEmail());
        values.put("telefone", pessoa.getTelefone());
        values.put("cpf", pessoa.getCpf());
        String[] args = {String.valueOf(pessoa.getId())};
        retornoBD = db.update("pessoa", values, "id=?", args);
        db.close();
        return retornoBD;
    }

    public long delete(Pessoa pessoa) {
        long retornoBD;
        String[] args = {String.valueOf(pessoa.getId())};
        retornoBD = db.delete("pessoa", "id" + "=?", args);
        return retornoBD;
    }


}
