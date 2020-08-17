package com.example.gestionfactureessence;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.TextView;

public class RechercheActivity extends AppCompatActivity {
    private TextView tvRecherche ,tvChoisiDate;
    private CalendarView cvRecherche;
    private Button btnConfirmer;
    private Intent intentAffichage;
    private String selectedDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recherche);

        //  va chercher les composantes dans ressource/layout
        setWidgets();

        // set les listeners au bouton
        setListeners();
    }

    private void setListeners() {
        cvRecherche.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                // Prendre la date dans le CalendarView
                selectedDate = String.valueOf(dayOfMonth) + "/"+ String.valueOf(month + 1) + "/" + String.valueOf(year);
            }
        });
        btnConfirmer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onConfirmer(v);
            }
        });
    }

    private void setWidgets() {
        tvRecherche = findViewById(R.id.tvRecherche);
        tvChoisiDate = findViewById(R.id.tvChoisiDate);
        cvRecherche = findViewById(R.id.cvRecherche);
        btnConfirmer = findViewById(R.id.btnConfirmer);
    }

    private void onConfirmer(View v){
        // envoyer la date selectionnee a AffichageActivity avec un Intent
        Log.v("edward", "date selectionnee: "+ selectedDate);
        intentAffichage = new Intent(RechercheActivity.this, AffichageActivity.class);
        intentAffichage.putExtra("selectedDate", selectedDate);
        startActivity(intentAffichage);




    }

}
