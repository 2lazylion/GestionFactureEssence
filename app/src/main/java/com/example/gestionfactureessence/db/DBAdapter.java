package com.example.gestionfactureessence.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.gestionfactureessence.models.Facture;
import com.example.gestionfactureessence.models.ListeFactures;

public class DBAdapter {
    private DBFactureHelper dbFactureHelper;
    private SQLiteDatabase db;
    private final Context context;

    public DBAdapter(Context context) {
        this.context = context;
        dbFactureHelper = new DBFactureHelper(context, dbFactureHelper.BD_NOM, null, dbFactureHelper.VERSION);
    }

    public void ouvrirBD(){
        db = dbFactureHelper.getWritableDatabase();
    }

    public void fermerBD() {
        db.close();
    }

    public void insererEnrergistrement(Facture facture) {
        // ouvrir bd
        ouvrirBD();

        // executer insertion
        ContentValues cv = new ContentValues();
        // met le montant pour la colonne MONTANT
        cv.put(dbFactureHelper.COL_MONTANT, facture.getMontant());
        // met la date d'aujourd'hui pour la colonne DATE
        cv.put(dbFactureHelper.COL_DATE, facture.getDateFacture());
        db.insert(dbFactureHelper.TABLE_1, null, cv);

        // fermer bd
        fermerBD();
    }
    //TODO: plante
    public Cursor selectionnerFactures(ListeFactures liste) {
        // ouvrir la BD
        ouvrirBD();

        // faire un select
        String[] cols = {dbFactureHelper.COL_ID, dbFactureHelper.COL_MONTANT};
        Cursor curseur = db.query(dbFactureHelper.TABLE_1, cols, null, null,null,null,null,null);

        // parcourir le curseur et mettre dans le registre de Liste
        curseur.moveToFirst();
        while(!curseur.isAfterLast()) {
            double montant = curseur.getDouble(2);
            liste.ajouterFacture(new Facture(montant));
            curseur.moveToNext();
        }

        return curseur;
    }

    public Cursor selectionnerFacturesParDates(ListeFactures liste, String dateSelectionner) {
        // ouvrir la BD
        ouvrirBD();
        Log.v("edward", "ouverture de la DB");

        // faire un select
        String[] cols = {dbFactureHelper.COL_ID, dbFactureHelper.COL_MONTANT};
        Log.v("edward", "Select "+ dbFactureHelper.COL_ID + ", " + dbFactureHelper.COL_MONTANT);
        Log.v("edward", "From "+ dbFactureHelper.TABLE_1);
        Log.v("edward", "Where "+ dbFactureHelper.COL_DATE + "=" + dateSelectionner);
        Cursor curseur = db.query(dbFactureHelper.TABLE_1, cols, dbFactureHelper.COL_DATE + " =?", null,null,null,null,null);
        // parcourir le curseur et mettre dans le registre de Liste
        curseur.moveToFirst();
        while(!curseur.isAfterLast()) {
            double montant = curseur.getDouble(2);
            //TODO: le log n'affiche rien. curseur vide? bd vide? creation de bd mal faite?
            Log.v("edward", "Curseur: montant: "+ montant);
            liste.ajouterFacture(new Facture(montant));
            curseur.moveToNext();
        }

        return curseur;
    }
}
