package com.example.gestionfactureessence.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class DBFactureHelper extends SQLiteOpenHelper {
    // Declaration des constantes
    public static final String TABLE_1 = "facture";
    public static final String COL_ID = "_id";
    public static final String COL_DATE = "dateFacture";
    public static final String COL_MONTANT = "montant";
    public static final String DDL_CREATE_FACTURE = "CREATE TABLE " + TABLE_1 + "(" +
            COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT UNIQUE, " + COL_DATE + " TEXT," +
            COL_MONTANT + " REAL)";

    public static final String BD_NOM = "facture";
    public static final int VERSION =1;

    public DBFactureHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // execute le DDL
        //TODO: la bd n'est pas cree?
        Log.v("edward","creation de la bd");
        db.execSQL(DDL_CREATE_FACTURE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // mise a jour
        //TODO: la bd n'est pas cree ou maj?
        db.execSQL("drop table "+TABLE_1+";");
        Log.v("edward","mise a jour de la bd");
        // cree la db
        onCreate(db);
    }
}
