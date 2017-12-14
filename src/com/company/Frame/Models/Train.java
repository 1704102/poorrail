package com.company.Frame.Models;
import java.util.ArrayList;

public class Train {
    private ArrayList<Wagon> Wagons= new ArrayList<Wagon>();
    private AllWagonTypes WT= AllWagonTypes.getInstance();
    private String name;

    public String getNaam() {
        return name;
    }

    public Train(String name) {
        this.name=name;
    }

    @Override
    public String toString() {
        return "Train{" +
                "naam='" + name + '\'' +
                '}';
    }

    public int getTotalPassengers(){
        int i = 0;
        for(Wagon w : Wagons){
            i = i+w.getAantalPersonen();
        }
        return i;
    }

    public ArrayList<Wagon> getWagons() {
        return Wagons;
    }
    public void addWagon(String naam){
        Wagon w=WT.getWagon(naam);

        if(w.getType().equals("Naambestaatniet")){
        }
        else{
            Wagons.add(w);
        }
    }
    public void removeWagon(String type){
        for(int i = Wagons.size() - 1; i > -1; i--){
            if (Wagons.get(i).getType().equals(type)){
                Wagons.remove(i);
                return;
            }
        }
    }

    public void setName(String name) {
        this.name = name;
    }
}

