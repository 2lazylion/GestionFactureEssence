package com.example.gestionfactureessence.models;

import java.util.ArrayList;

public class ListeFactures {
    private ArrayList<Facture> registre;

    public ListeFactures() { this.registre = new ArrayList<>(); }

    public ArrayList<Facture> getregistre() { return registre; }

    public void setRegistre(ArrayList<Facture> registre) { this.registre = registre; }

    public void ajouterFacture(Facture facture) { registre.add(facture); }
}
