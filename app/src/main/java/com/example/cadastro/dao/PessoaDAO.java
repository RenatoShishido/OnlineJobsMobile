package com.example.cadastro.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.cadastro.config.DatabaseHelper;
import com.example.cadastro.entities.Emprego;
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
        values.put("vagaId", pessoa.getVagaId());
        retornoBD = db.insert("pessoa", null, values);
        String res = Long.toString(retornoBD);
        Log.i("BDContatoHelper", res);
        db.close();
        return retornoBD;
    }

    public ArrayList<Pessoa> selectAll() {
        String POSTS_SELECT_QUERY =
                String.format("SELECT * FROM pessoa JOIN emprego ON emprego.id = pessoa.vagaId");

        Cursor cursor = db.rawQuery(POSTS_SELECT_QUERY, null);


        ArrayList<Pessoa> listaPessoa = new ArrayList<Pessoa>();
        while (cursor.moveToNext()) {
            Pessoa pessoa = new Pessoa();
            pessoa.setId(cursor.getInt(cursor.getColumnIndex("pessoa.id")));
            pessoa.setVagaId(cursor.getInt(cursor.getColumnIndex("vagaId")));
            pessoa.setNome(cursor.getString(cursor.getColumnIndex("nome")));
            pessoa.setCpf(cursor.getString(cursor.getColumnIndex("cpf")));
            pessoa.setEmail(cursor.getString(cursor.getColumnIndex("email")));
            pessoa.setTelefone(cursor.getString(cursor.getColumnIndex("telefone")));

            Emprego emprego = new Emprego();
            emprego.setId(cursor.getInt(cursor.getColumnIndex("emprego.id")));
            emprego.setDescricao(cursor.getString(cursor.getColumnIndex("descricao")));
            emprego.setHoras(cursor.getInt(cursor.getColumnIndex("horasPorSemana")));
            emprego.setValor(cursor.getDouble(cursor.getColumnIndex("valor")));


            pessoa.setEmprego(emprego);

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
