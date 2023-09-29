package com.example.geolog;

public class Voyage {
    /*
    À faire
    attributs: jour (entier), mois (entier), annee (entier), lieu de depart, lieu d'arrivee, kilometre de depart, kilometre d'arrivee
    accesseurs: get set
    methode: toString() ** utiliser StringBuilder
     */
    int jour = 0;
    int mois = 0;
    int annee = 0;

    String lieuDepart = "";
    String lieuArrivee = "";
    int kilometreDepart = 0;
    int kilometreArrivee = 0;

    Voyage(){}

    // set
    void setJour(int jour) {this.jour = jour;}
    void setMois(int mois) {this.mois = mois;}
    void setAnnee(int annee) {this.annee = annee;}
    void setLieuDepart(String lieuDepart) {this.lieuDepart = lieuDepart;}
    void setLieuArrivee(String lieuArrivee) {this.lieuArrivee = lieuArrivee;}
    void setKilometreDepart(int kilometreDepart) {this.kilometreDepart = kilometreDepart;}
    void setKilometreArrivee(int kilometreArrivee) {this.kilometreArrivee = kilometreArrivee;}

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Voyage{");
        stringBuilder.append("jour=" + jour);
        stringBuilder.append(", mois=" + mois);
        stringBuilder.append(", annee=" + annee);
        stringBuilder.append(", lieuDepart='" + lieuDepart + '\'');
        stringBuilder.append(", lieuArrivee='" + lieuArrivee + '\'');
        stringBuilder.append(", kilometreDepart=" + kilometreDepart);
        stringBuilder.append(", kilometreArrivee=" + kilometreArrivee);
        stringBuilder.append("}");
        return  stringBuilder.toString();
    }
}
