package com.example.onlinejobsmovies.config;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class PostsDatabaseHelper extends SQLiteOpenHelper {
    // Database Info
    private static final String DATABASE_NAME = "EmpregosOnline";
    private static final int DATABASE_VERSION = 1;

    // Table Names
    private static final String TABLE_PESSOA = "pessoa";
    private static final String TABLE_EMPREGO = "emprego";

    // Pessoa Table Columns
    private static final String KEY_PESSOA_ID =  "id";
    private static final String KEY_PESSOA_VAGA_ID_FK = "vagaId";
    private static final String KEY_PESSOA_NOME = "nome";
    private static final String KEY_PESSOA_CPF = "cpf";
    private static final String KEY_PESSOA_EMAIL = "email";
    private static final String KEY_PESSOA_TELEFONE = "telefone";

    // Emprego Table Columns
    private static final String KEY_EMPREGO_ID = "id";
    private static final String KEY_EMPREGO_DESCRICAO = "descricao";
    private static final String KEY_EMPREGO_HORASSEMANA = "horasSemana";
    private static final String KEY_EMPREGO_VALOR = "valor";

    public PostsDatabaseHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Called when the database connection is being configured.
    // Configure database settings for things like foreign key support, write-ahead logging, etc.
    @Override
    public void onConfigure(SQLiteDatabase db) {
        super.onConfigure(db);
        db.setForeignKeyConstraintsEnabled(true);
    }

    // Called when the database is created for the FIRST time.
    // If a database already exists on disk with the same DATABASE_NAME, this method will NOT be called.
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_PESSOA_TABLE = "CREATE TABLE " + TABLE_PESSOA +
                "(" +
                KEY_PESSOA_ID + " INTEGER PRIMARY KEY," + // Define a primary key
                KEY_PESSOA_VAGA_ID_FK + " INTEGER REFERENCES " + TABLE_EMPREGO + "," + // Define a foreign key
                KEY_PESSOA_NOME + " TEXT," +
                KEY_PESSOA_CPF + " TEXT," +
                KEY_PESSOA_EMAIL + " TEXT," +
                KEY_PESSOA_TELEFONE + " TEXT" +
                ")";

        String CREATE_EMPREGO_TABLE = "CREATE TABLE " + TABLE_EMPREGO +
                "(" +
                KEY_EMPREGO_ID + " INTEGER PRIMARY KEY," +
                KEY_EMPREGO_DESCRICAO + " TEXT," +
                KEY_EMPREGO_HORASSEMANA + " INTEGER," +
                KEY_EMPREGO_VALOR + " DOUBLE" +
                ")";

        db.execSQL(CREATE_PESSOA_TABLE);
        db.execSQL(CREATE_EMPREGO_TABLE);
    }

    // Called when the database needs to be upgraded.
    // This method will only be called if a database already exists on disk with the same DATABASE_NAME,
    // but the DATABASE_VERSION is different than the version of the database that exists on disk.
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion != newVersion) {
            // Simplest implementation is to drop all old tables and recreate them
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_PESSOA);
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_EMPREGO);
            onCreate(db);
        }
    }
}
