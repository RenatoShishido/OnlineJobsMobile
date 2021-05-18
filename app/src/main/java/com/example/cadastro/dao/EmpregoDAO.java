package com.example.cadastro.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.cadastro.config.DatabaseHelper;
import com.example.cadastro.entities.Emprego;


import java.util.ArrayList;

public class EmpregoDAO {

    DatabaseHelper conexao;
    SQLiteDatabase db;

    public EmpregoDAO(Context context) {
        conexao = new DatabaseHelper(context);
        db = conexao.getWritableDatabase();
    }


    public long insert(Emprego emprego) {
        long retornoBD;
        ContentValues values = new ContentValues();
        values.put("descricao", emprego.getDescricao());
        values.put("horasPorSemana", emprego.getHoras());
        values.put("valor", emprego.getValor());
        retornoBD = db.insert("emprego", null, values);
        String res = Long.toString(retornoBD);
        Log.i("BDdatabaseHelper", res);
        db.close();
        return retornoBD;
    }

    public ArrayList<Emprego> selectAll() {
        String[] coluns = {"id", "descricao", "horasPorSemana", "valor"};

        Cursor cursor = db.query("emprego", coluns, null, null, null,
                null, "upper(descricao)", null);

        ArrayList<Emprego> listaEmprego = new ArrayList<Emprego>();
        while (cursor.moveToNext()) {
            Emprego emprego = new Emprego();
            emprego.setId(cursor.getInt(0));
            emprego.setDescricao(cursor.getString(1));
            emprego.setHoras(cursor.getInt(2));
            emprego.setValor(cursor.getDouble(3));
            listaEmprego.add(emprego);
        }
        return listaEmprego;
    }

    public long update(Emprego emprego) {
        long retornoBD;
        ContentValues values = new ContentValues();
        values.put("descricao", emprego.getDescricao());
        values.put("horasPorSemana", emprego.getHoras());
        values.put("valor", emprego.getValor());
        String[] args = {String.valueOf(emprego.getId())};
        retornoBD = db.update("emprego", values, "id=?", args);
        db.close();
        return retornoBD;
    }

    public long delete(Emprego emprego) throws Exception {
            long retornoBD;
            String[] args = {String.valueOf(emprego.getId())};
            retornoBD = db.delete("emprego", "id" + "=?", args);
            return retornoBD;
    }


}
