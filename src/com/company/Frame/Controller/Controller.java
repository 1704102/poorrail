package com.company.Frame.Controller;

import com.company.Frame.Models.Facade;
import com.company.Frame.Models.Observer;
import com.company.Frame.Models.Wagon;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;


/**
 * Created by marti on 6-12-2017.
 */
public class Controller{

    private String state;
    private ArrayList<String> output;
    private ArrayList<Observer> observers;
    Facade facade;

    public Controller(Facade facade) {
        output = new ArrayList<>();
        observers = new ArrayList();
        this.facade = facade;
        notifyAllObservers();
    }

    public void execute(String s){
        String[] command = s.split(" ");
        switch (command[0].toLowerCase()){
            case "new": executeNew(command); break;
            case "add": executeAdd(command); break;
            case "remove": executeRemove(command); break;
            case "getnumseats": executeGetNumseats(command);break;
            case "delete": executeDelete(command); break;
            case "create": executeCreate(s);break;
            default: output.add("command not found"); break;
        }

        notifyAllObservers();
    }

    public void executeNew(String[] command){
        switch (command[1]){
            case "train":
                if(command.length != 3)output.add("incorrect command");
                else if(trainExists(command[2])) output.add("train already exists");
                else {
                    if (nameCheck(command[2], "train")){
                        facade.addtrein(command[2]);
                        output.add("train " + command[2] + " added");
                    }
                }
                break;
            case "wagon":
                if(command.length == 3){
                    if(wagonExists(command[2])) output.add("wagon already exists");
                    else if(nameCheck(command[2], "wagon")) {
                        facade.addWagon(command[2], 20);
                        output.add("wagon " + command[2] + " added with 20 seats");
                    }
                }
                else if(command.length == 5){
                    if(wagonExists(command[2])) output.add("wagon already exists");
                    else if(command[3].equals("numseats")){
                        if(nameCheck(command[2], "wagon")){
                            try{
                                int numseats = Integer.parseInt(command[4]);
                                facade.addWagon(command[2], numseats);
                                output.add("wagon " + command[2] + " created with " + numseats + " seats");
                            }catch (Exception e){
                                output.add("incorrect number");
                            }
                        }
                    }
                }else{
                    output.add("incorrect command");
                }
                break;
            default:
                output.add("incorrect vehicle type");
                break;
        }
    }

    public void executeAdd(String[] command){
        if (command.length == 4){
            if(trainExists(command[3]) && wagonExists(command[1])) {
                facade.addWagonToTrain(command[3], command[1]);
                output.add("wagon added to train");
            }else{
                output.add("incorrect names");
            }
        }else{
            output.add("command not found");
        }
    }

    public void executeRemove(String[] command){
        if (command.length == 4){
            if(trainExists(command[3]) && wagonExists(command[1])) {
                facade.removeWagonFromTrain(command[3], command[1]);
                output.add("wagon removed from train");
            }else{
                output.add("incorrct names");
            }
        }else{
            output.add("command not found");
        }
    }

    public void executeGetNumseats(String[] command){
        if (command.length == 3){
            switch (command[1]){
                case "train":
                    if (nameCheck(command[2], "train")) output.add("number of seats in train " + command[2] + ": " + facade.getTotalPassengersTrain(command[2]));
                    break;
                case "wagon":
                    if(nameCheck(command[2], "train")) output.add("number of seats in wagon " + command[2] + ": " + facade.getTotalPassengersWagon(command[2]));
                    break;
                default:
                    output.add("incorrect vehicle type");
                    break;
            }
        }else{
            output.add("command not found");
        }
    }

    public void executeDelete(String[] command){
        if (command.length == 3 && command[1].equals("train")){
            facade.removetrein(command[2]);
            output.add("train " + command[2] + " removed");
        }else{
            output.add("command not found");
        }
    }

    public void executeCreate(String command) {
        try {
            if (command.equals("create script")) {
                FileWriter fileWriter = new FileWriter(new File("script/script.txt"));

                facade.getPresetWagons().forEach(e -> {
                    try {
                        fileWriter.append("new wagon " + e.getType() + " numseats " + facade.getTotalPassengersWagon(e.getType()) + "\n");
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                });

                facade.getTrains().forEach(e -> {
                    try {
                        fileWriter.append("new train " + e + "\n");
                        facade.getWagons(e).forEach(b->{
                            try {
                                fileWriter.append("add " + b + " to " + e + "\n");
                            } catch (IOException e1) {
                                e1.printStackTrace();
                            }
                        });
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                });
                fileWriter.close();
            } else {
                output.add("command not found");
            }

        }catch (Exception e){}
    }

    public boolean nameCheck(String name, String type){
        if(!name.equals("")){
            switch (type){
                case "train": return  true;
                case "wagon": return  true;
            }
        }else{
            output.add("incorrect name input");
            return false;
        }
        return false;
    }

    public ArrayList<String> getOutput() {
        return output;
    }

    public void attach(Observer observer){
        observers.add(observer);
    }

    public void notifyAllObservers(){
        for (Observer observer : observers) {
            observer.update();
        }
    }

    public boolean trainExists(String name){
        for(String s : facade.getTrains()){
            if(s.equals(name))return true;
        }
        return false;
    }

    public boolean wagonExists(String name){
        for(Wagon s : facade.getPresetWagons()){
            if(s.getType().equals(name))return true;
        }
        return false;
    }

    public Facade getFacade() {
        return facade;
    }
}
