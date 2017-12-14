package com.company.Frame;

import com.company.Frame.Controller.Controller;
import com.company.Frame.Models.Facade;
import com.company.Frame.Panel.MainPanel;

import javax.swing.*;

/**
 * Created by marti on 6-12-2017.
 */
public class Frame extends JFrame {

    private MainPanel panel;
    private Controller controller;

    public Frame(Controller controller){

        this.controller = controller;
        panel = new MainPanel(controller);

        this.setVisible(true);
        this.add(panel);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.pack();

    }

    public MainPanel getPanel() {
        return panel;
    }
}
