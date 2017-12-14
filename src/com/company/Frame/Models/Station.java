package com.company.Frame.Models;

        import java.util.ArrayList;

public class Station {
    private ArrayList<Train> treinen= new ArrayList<Train>();
    private AllWagonTypes wagTp;

    public Station() {
        wagTp= AllWagonTypes.getInstance();
    }

    public ArrayList<Train> getTreinen() {
        return treinen;
    }

    public void addtrein(String s){
        Train t = new Train(s);
        String s1 = t.getNaam();
        boolean b = false;
        for(Train t1 : treinen){
            String s2= t1.getNaam();
            if (s2==s1){
                b=true;
            }
        }
        if(b==false)treinen.add(t);
    }
    public void removetrein(String s){

        for(Train t : treinen){
            if (t.getNaam().equals(s)){

                treinen.remove(t);
                break;
            }

        }

    }
    public Train getTrein(String s){
        Train t1= new Train(null);
        for (Train t : treinen){
            if(t.getNaam().equals(s))
                return t;
        }
        return t1;
    }
    public void getTreinSize(String treinNaam){
        for(Train t : treinen){
            if(t.getNaam()==treinNaam){
                t.getTotalPassengers();
            }
        }
    }

    public AllWagonTypes getWagTp() {
        return wagTp;
    }
}
