package com.example.gestionfactureessence;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.gestionfactureessence.db.DBAdapter;
import com.example.gestionfactureessence.db.MonCursorAdapter;
import com.example.gestionfactureessence.models.ListeFactures;

import java.sql.Date;
import java.util.Calendar;


public class AffichageActivity extends AppCompatActivity {
    private ListView listingFacture;
    private TextView tvRecherche, tvTotalPour, tvListeFacture, tvMontant;
    private DBAdapter dbAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_affichage);

        // Obtenir un DB adapter
        dbAdapter = new DBAdapter(AffichageActivity.this);

        //  va chercher les composantes dans ressource/layout
        setWidgets();

        // calendarView utilise le type "long" pour les dates
        //TODO: aller chercher la date sélectionné pour faire la sélection dans la bd
        // enlever la date "hardcoded"
        Calendar dateSelectionner = Calendar.getInstance();

        // recupere la liste des factures de la db
        ListeFactures lf = new ListeFactures();
        Cursor curseur = dbAdapter.selectionnerFacturesParDates(lf, dateSelectionner);

        // afficher le contenu
        MonCursorAdapter adapter = new MonCursorAdapter(AffichageActivity.this, curseur, CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
        listingFacture.setAdapter(adapter);
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
