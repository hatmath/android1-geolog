package com.example.geolog;

public class GeologInfo {
    String distance = "";
    String lieu = "";
    String date = "";

    GeologInfo(){}
    GeologInfo(String distance, String lieu, String date) {
        this.distance = distance;
        this.lieu = lieu;
        this.date = date;
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

    @Override
    public String toString() {
        return "GeologInfo{" +
                "distance='" + distance + '\'' +
                ", lieu='" + lieu + '\'' +
                ", date='" + date + '\'' +
                '}';
    }
}
