package com.company.Frame.Panel;

import com.company.Frame.Controller.Controller;
import com.company.Frame.Models.Facade;
import com.company.Frame.Models.Observer;

import javax.swing.*;
import java.awt.*;

/**
 * Created by marti on 6-12-2017.
 */
public class OutputPanel extends JPanel implements Observer{

    JTextArea overView;
    private JTextArea outputPanel;

    private JScrollPane scrollPane;
    private JScrollPane scrollPane1;
    Controller controller;


    public OutputPanel(Controller controller) {

        this.controller = controller;

        this.setBackground(new Color(221, 218, 222));
        this.setPreferredSize(new Dimension(1000,210));

        //this.setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));

        overView = new JTextArea(){{setPreferredSize(new Dimension(490,190)); setBackground(Color.white);}};
        outputPanel = new JTextArea();
        scrollPane = new JScrollPane(outputPanel){{setPreferredSize(new Dimension(490,200)); setBackground(Color.white);}};
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane1 = new JScrollPane(overView){{setPreferredSize(new Dimension(490,200)); setBackground(Color.white);}};
        scrollPane1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane1.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        this.add(scrollPane1);
        this.add(scrollPane);
        overView.append("wagons \n");
        overView.append("trains \n");
    }

    @Override
    public void update() {
        overView.setText("");
        outputPanel.setText("");
        controller.getOutput().forEach(e->{
            outputPanel.append(e + "\n");
        });
        overView.append("wagons \n");
        controller.getFacade().getPresetWagons().forEach(e->{
            overView.append("(" +e.getType()+ ":" + e.getAantalPersonen() + ")\n");
        });

        overView.append("trains \n");
        controller.getFacade().getTrains().forEach(e->{
            overView.append("(" +e + ")-");
            controller.getFacade().getWagons(e).forEach(b->{
                overView.append("(" + b + ":" + controller.getFacade().getTotalPassengersWagon(b) + ")-");
            });
            overView.replaceRange("",overView.getText().length()-1, overView.getText().length());
            overView.append("\n");
        });
        revalidate();
    }
}
