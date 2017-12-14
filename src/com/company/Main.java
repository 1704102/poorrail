package com.company;

import com.company.Frame.Controller.Controller;
import com.company.Frame.Frame;
import com.company.Frame.Models.Facade;
import com.company.Frame.Models.Station;
import com.company.Frame.Panel.TrainPanel;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {

        Station station = new Station();
        Facade facade  = new Facade(station);
        Controller controller = new Controller(facade);
        Frame f = new Frame(controller);

        TrainPanel p = new TrainPanel(controller);
        JFrame f1 = new JFrame(){{add(p);}};
        f1.setVisible(true);
        f1.pack();

        controller.attach(f.getPanel().getoPanel());
        controller.attach(f.getPanel().gettPanel());
        controller.attach(p);

    }
}
