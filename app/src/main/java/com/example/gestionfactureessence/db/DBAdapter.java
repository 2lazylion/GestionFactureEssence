package com.example.gestionfactureessence.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.gestionfactureessence.models.Facture;
import com.example.gestionfactureessence.models.ListeFactures;

import java.util.Calendar;
import java.util.Date;

public class DBAdapter {
    private DBFactureHelper DBFactureHelper;
    private SQLiteDatabase db;
    private final Context context;

    public DBAdapter(Context context) {
        this.context = context;
        DBFactureHelper = new DBFactureHelper(context, DBFactureHelper.BD_NOM, null, DBFactureHelper.VERSION);
    }

    public void ouvrirBD(){
        db = DBFactureHelper.getWritableDatabase();
    }

    public void fermerBD() {
        db.close();
    }

    public void insererEnrergistrement(Facture facture) {
        // ouvrir bd
        ouvrirBD();

        // executer insertion
        ContentValues cv = new ContentValues();
        cv.put(DBFactureHelper.COL_MONTANT, facture.getMontant());
        db.insert(DBFactureHelper.TABLE_1, null, cv);

        // fermer bd
        fermerBD();
    }

    public Cursor selectionnerFactures(ListeFactures liste) {
        // ouvrir la BD
        ouvrirBD();

        // faire un select
        String[] cols = {DBFactureHelper.COL_ID, DBFactureHelper.COL_MONTANT};
        Cursor curseur = db.query(DBFactureHelper.TABLE_1, cols, null, null,null,null,null,null);

        // parcourir le curseur et mettre dans le registre de Liste
        curseur.moveToFirst();
        while(!curseur.isAfterLast()) {
            //int id = curseur.getInt(0);
            double montant = curseur.getDouble(2);
            liste.ajouterFacture(new Facture(montant));
            curseur.moveToNext();
        }

        //fermeture de BD
        //fermerBD();
        return curseur;
    }

    public Cursor selectionnerFacturesParDates(ListeFactures liste, Calendar dateSelectionner) {
        // ouvrir la BD
        ouvrirBD();

        // faire un select
        String[] cols = {DBFactureHelper.COL_ID, DBFactureHelper.COL_MONTANT};
        Cursor curseur = db.query(DBFactureHelper.TABLE_1, cols, DBFactureHelper.COL_DATE + " =" + dateSelectionner.toString(), null,null,null,null,null);

        // parcourir le curseur et mettre dans le registre de Liste
        curseur.moveToFirst();
        while(!curseur.isAfterLast()) {
            //int id = curseur.getInt(0);
            double montant = curseur.getDouble(2);
            liste.ajouterFacture(new Facture(montant));
            curseur.moveToNext();
        }

        //fermeture de BD
        //fermerBD();
        return curseur;
    }
}
