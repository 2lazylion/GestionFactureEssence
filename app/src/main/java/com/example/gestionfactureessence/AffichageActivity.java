package com.example.gestionfactureessence;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.gestionfactureessence.db.DBAdapter;
import com.example.gestionfactureessence.db.MonCursorAdapter;
import com.example.gestionfactureessence.models.Facture;
import com.example.gestionfactureessence.models.ListeFactures;

import java.sql.Date;
import java.util.Calendar;


public class AffichageActivity extends AppCompatActivity {
    private ListView listingFacture;
    private TextView tvRecherche, tvTotalPour, tvListeFacture, tvMontant;
    private DBAdapter dbAdapter;
    private Intent intentRecu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_affichage);

        //  va chercher les composantes dans ressource/layout
        setWidgets();

        // Obtenir un DB adapter
        dbAdapter = new DBAdapter(AffichageActivity.this);

        // calendarView utilise le type "long" pour les dates
        //TODO: aller chercher la date sélectionné pour faire la sélection dans la bd
        intentRecu = getIntent();
        String selectedDate = intentRecu.getStringExtra("selectedDate");

        // recupere la liste des factures de la db
        ListeFactures lf = new ListeFactures();
        Cursor curseur = dbAdapter.selectionnerFacturesParDates(lf, selectedDate);

        // afficher le contenu
        MonCursorAdapter adapter = new MonCursorAdapter(AffichageActivity.this, curseur, CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
        listingFacture.setAdapter(adapter);

        // parcourir le curseur et calcul le total des factures
        double total = 0;
        curseur.moveToFirst();
        while(!curseur.isAfterLast()) {
            double montant = curseur.getDouble(2);
            total += montant;
            curseur.moveToNext();
        }

        // affiche le total des factures
        tvMontant.setText(String.valueOf(total));

        dbAdapter.fermerBD();
    }

    private void setWidgets() {
        listingFacture = findViewById(R.id.listingFacture);
        tvRecherche = findViewById(R.id.tvRecherche);
        tvTotalPour = findViewById(R.id.tvTotalPour);
        tvListeFacture = findViewById(R.id.tvListeFacture);
        tvMontant = findViewById(R.id.tvMontant);
    }
}
