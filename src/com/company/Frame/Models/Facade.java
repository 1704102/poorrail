package com.company.Frame.Models;

import com.company.Frame.Controller.Controller;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class Facade {
    Station station;
    public Facade(Station station){
        this.station = station;
    }
    public void addWagon(String type, int numseats){
        station.getWagTp().addWagon(type, numseats);
    }
    public void addtrein(String s){
        station.addtrein(s);
    }
    public void removetrein(String s){
        station.removetrein(s);
    }
    public Train getTrein(String s){
        return station.getTrein(s);
    }
    public void addWagonToTrain(String treinnaam, String wagonType){
        Train t= getTrein(treinnaam);
        t.addWagon(wagonType);
    }
    public void removeWagonFromTrain(String s, String w){
        Train t=getTrein(s);
        t.removeWagon(w);
    }
    public ArrayList<String> getWagons(String s){
        ArrayList<String> w = new ArrayList();
        Train t = getTrein(s);
        t.getWagons().forEach(e->{
            w.add(e.getType());
        });
        return w;
    }
    public ArrayList<String> getTrains(){
        ArrayList<String> trains = new ArrayList();
        station.getTreinen().forEach(e->{
            trains.add(e.getNaam());
        });
        return trains;
    }
    public int getTotalPassengersTrain(String s){
        Train t= station.getTrein(s);
        int i =t.getTotalPassengers();
        return i;
    }

    public int getTotalPassengersWagon(String s){
        Wagon t= getWagon(s);
        int i =t.getAantalPersonen();
        return i;
    }

    public Wagon getWagon(String s){
        return station.getWagTp().getWagon(s);
    }
    public ArrayList<Wagon> getPresetWagons(){
        return station.getWagTp().getWagons();
    }
}
