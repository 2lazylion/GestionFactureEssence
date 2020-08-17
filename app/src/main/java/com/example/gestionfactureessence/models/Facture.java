package com.example.gestionfactureessence.models;

import java.util.Date;
import java.util.Objects;

public class Facture {
    private int id;
    private double montant;
    private String dateFacture;



    public Facture() {
    }

    public Facture(double montant) {
        this.montant = montant;
    }
    public Facture(double montant, String dateFacture) {
        this.montant = montant;
        this.dateFacture = dateFacture;
    }

    public Facture(int id, double montant, String dateFacture) {
        this.id = id;
        this.montant = montant;
        this.dateFacture = dateFacture;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getMontant() {
        return montant;
    }

    public void setMontant(double montant) {
        this.montant = montant;
    }

    public String getDateFacture() {
        return dateFacture;
    }

    public void setDateFacture(String dateFacture) {
        this.dateFacture = dateFacture;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Facture facture = (Facture) o;
        return id == facture.id &&
                dateFacture.equals(facture.dateFacture);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, dateFacture);
    }

    @Override
    public String toString() {
        return "Facture{" +
                "id=" + id +
                ", montant=" + montant +
                ", dateFacture=" + dateFacture +
                '}';
    }
}
