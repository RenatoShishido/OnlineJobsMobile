package com.example.cadastro.config;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.cadastro.entities.Pessoa;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "EmpregosOnline";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_PESSOA = "pessoa";
    private static final String TABLE_EMPREGO = "emprego";

    private static final String KEY_PESSOA_ID = "id";
    private static final String KEY_PESSOA_VAGAID = "vagaId";
    private static final String KEY_PESSOA_NOME = "nome";
    private static final String KEY_PESSOA_CPF = "cpf";
    private static final String KEY_PESSOA_EMAIL = "email";
    private static final String KEY_PESSOA_TELEFONE = "telefone";

    private static final String KEY_EMPREGO_ID = "id";
    private static final String KEY_EMPREGO_DESCRICAO = "descricao";
    private static final String KEY_EMPREGO_HORASPORSEMANA = "horasPorSemana";
    private static final String KEY_EMPREGO_VALOR = "valor";

    SQLiteDatabase db;

    public DatabaseHelper(Context context){
        super(context, DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_PESSOA_TABLE = "CREATE TABLE " + TABLE_PESSOA +
                "(" +
                KEY_PESSOA_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + // Define a primary key
                KEY_PESSOA_VAGAID + " INTEGER REFERENCES " + TABLE_EMPREGO + "," + // Define a foreign key
                KEY_PESSOA_NOME + " TEXT," +
                KEY_PESSOA_CPF + " TEXT," +
                KEY_PESSOA_EMAIL + " TEXT," +
                KEY_PESSOA_TELEFONE + " TEXT" +
                ")";

        String CREATE_EMPREGO_TABLE = "CREATE TABLE " + TABLE_EMPREGO +
                "(" +
                KEY_EMPREGO_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                KEY_EMPREGO_DESCRICAO + " TEXT," +
                KEY_EMPREGO_HORASPORSEMANA + " INTEGER," +
                KEY_EMPREGO_VALOR + " DOUBLE" +
                ")";

        db.execSQL(CREATE_PESSOA_TABLE);
        db.execSQL(CREATE_EMPREGO_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion != newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_PESSOA);
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_EMPREGO);
            onCreate(db);
        }
    }
}
