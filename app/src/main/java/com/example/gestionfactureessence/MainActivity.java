package com.example.gestionfactureessence;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gestionfactureessence.db.DBAdapter;
import com.example.gestionfactureessence.models.Facture;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    private TextView tvEntrezMontant;
    private EditText txtMontant;
    private Button btnSauvegarder, btnRechercher;
    private Intent IntentRecherche;
    private DBAdapter dbAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //  va chercher les composantes dans ressource/layout
        setWidgets();

        // Obtenir un DB adapter
        dbAdapter = new DBAdapter(MainActivity.this);

        // set les listeners aux boutons
        setListeners();
    }

    private void setListeners() {
        // le OnClicklistener de btnSauvegarder va utiliser la methode onSauvegarder
        btnSauvegarder.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onSauvegarder(v);
            }
        }));

        // le OnClicklistener de btnRechercher va utiliser la methode onRechercher
        btnRechercher.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onRechercher(v);
            }
        }));
    }

    private void setWidgets() {
        tvEntrezMontant = findViewById(R.id.tvEntrezMontant);
        txtMontant = findViewById(R.id.txtMontant);
        btnSauvegarder = findViewById(R.id.btnSauvegarder);
        btnRechercher = findViewById(R.id.btnRechercher);

    }


    private void onSauvegarder(View v) {
        // prend le montant dans le EditText txtMontant
        double montant = Double.parseDouble(txtMontant.getText().toString());

        // inserer la date d'aujourd'hui ici ou bd?
        //Calendar today = Calendar.getInstance();

        // sauvegarde dans la bd le montant avec la date
        dbAdapter.insererEnrergistrement(new Facture(montant));

        //TODO: afficher sauvegarde si on a un resultat de la bd sinon, erreur
        String message = "Sauvegardé";
        Toast.makeText(MainActivity.this,message, Toast.LENGTH_LONG).show();
    }

    private void onRechercher(View v) {
        // cree le "Intent" pour aller a l'activite RechercheActivity
        IntentRecherche = new Intent(MainActivity.this, RechercheActivity.class);

        // demarre le Intent
        startActivity(IntentRecherche);
    }

}
