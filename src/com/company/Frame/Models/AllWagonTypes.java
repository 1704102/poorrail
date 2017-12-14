package com.company.Frame.Models;
import java.util.ArrayList;

public class AllWagonTypes {
    private ArrayList<Wagon> Wagons = new ArrayList<Wagon>();

    private static AllWagonTypes instance = null;
    protected AllWagonTypes() {
    }
    public static AllWagonTypes getInstance(){
        if(instance == null){
            instance = new AllWagonTypes();
        }
        return instance;
    }



    public void addWagon(String naam, int aantal) {
        boolean b = false;
        for (Wagon w : Wagons) {
            if (w.equals(naam) == true) {
                b = true;
            }
        }
        if (b == false) {
            Wagon w = new Wagon(naam, aantal);
            Wagons.add(w);
        }
        ;
    }

    public void removeWagons(String naam) {
        for (Wagon w : Wagons) {
            if (w.equals(naam) == true) {
                Wagons.remove(w);
                break;
            }
        }
    }

    public Wagon getWagon(String naam) {
        Wagon x= new Wagon("Naambestaatniet", 0);
        for (Wagon w : Wagons) {
            if (w.getType().equals(naam) == true) {

                x= w;
            }

        }

        return x;
    }

    public ArrayList<Wagon> getWagons() {
        return Wagons;
    }
}