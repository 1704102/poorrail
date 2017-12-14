package com.company.Frame.Models;

public class Wagon {
    private String type;
    private int aantalPersonen;

    public String getType() {
        return type;
    }
    public void removeWagon(int i){

    }
    public void setType(String naam) {
        this.type = naam;
    }

    public int getAantalPersonen() {
        return aantalPersonen;
    }

    public void setAantalPersonen(int aantalPersonen) {
        this.aantalPersonen = aantalPersonen;
    }

    public Wagon(String naam, int aantalPersonen) {
        this.type = naam;
        this.aantalPersonen = aantalPersonen;
    }


    public boolean equals(String s) {
        if(type==s) return true;
        else return false;
    }

    @Override
    public String toString() {
        return "Wagon{" +
                "naam='" + type + '\'' +
                ", aantalPersonen=" + aantalPersonen +
                '}';
    }


}
