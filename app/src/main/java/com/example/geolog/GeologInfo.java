package com.example.geolog;

public class GeologInfo {
    String distance = "";
    String lieu = "";
    String date = "";
    String modeDeDeplacement = "";

    GeologInfo(){}
    GeologInfo(String distance, String lieu, String date, String modeDeDeplacement) {
        this.distance = distance;
        this.lieu = lieu;
        this.date = date;
        this.modeDeDeplacement = modeDeDeplacement;
    }
    void setDistance(String distance) {
        this.distance = distance;
    }
    String getDistance() {
        return this.distance;
    }

    void setLieu(String lieu) {
        this.lieu = lieu;
    }
    String getLieu() {
        return this.lieu;
    }

    void setDate(String date) {
        this.date = date;
    }
    String getDate() {
        return this.date;
    }

    void setModeDeDeplacement(String modeDeDeplacement) {
        this.modeDeDeplacement = modeDeDeplacement;
    }
    String getModeDeDeplacement() {
        return this.modeDeDeplacement;
    }

    @Override
    public String toString() {
        return "GeologInfo{" +
                "distance='" + distance + '\'' +
                ", lieu='" + lieu + '\'' +
                ", date='" + date + '\'' +
                ", modeDeDeplacement='" + modeDeDeplacement + '\'' +
                '}';
    }
}
