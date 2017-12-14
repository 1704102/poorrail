package com.company.Frame.Panel;

import com.company.Frame.Controller.Controller;
import com.company.Frame.Models.Facade;
import com.company.Frame.Models.Observer;
import com.company.Frame.Models.Train;

import javax.swing.*;
import java.awt.*;


/**
 * Created by marti on 6-12-2017.
 */
public class TrainPanel extends JPanel implements Observer {

    Controller controller;

    public TrainPanel(Controller controller) {
        this.setPreferredSize(new Dimension(990,50));
        this.setBackground(Color.white);
        this.controller = controller;


    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D G2D = (Graphics2D)g;
        for (int i = 0; i < controller.getFacade().getTrains().size(); i++){
            // paint train
            paintTrain(G2D,i * 100, i, controller.getFacade().getTrains().get(i));
        }
    }

    public void paintTrain(Graphics2D g, int y, int i, String name){
        // draw locomotive body
        g.setColor(Color.black);
        g.fillRect(65, y + 40, 65,35);
        g.fillRect(100, y + 20, 40,55);
        g.fillRect(75, y + 20, 10,35);
        g.fillRect(140, y + 50, 10,15);

        // draw locomotive wheels
        g.fillRoundRect(70, y + 70, 15,15,90,90);
        g.fillRoundRect(90, y + 70, 15,15,90,90);
        g.fillRoundRect(110, y + 70, 15,15,90,90);
        g.setColor(Color.white);

        // draw locomotive window
        g.fillRect(110, y + 28, 20,20);

        //draw locomotive text
        g.drawString(name , 75 ,y + 62);

        // draw wagons
        for(int j = 0; j < controller.getFacade().getWagons(controller.getFacade().getTrains().get(i)).size(); j++){
            paintWagon(g,y,j,controller.getFacade().getWagons(controller.getFacade().getTrains().get(i)).get(j));
        }
    }

    public void paintWagon(Graphics2D g, int y, int x, String type){
        // draw wagon body
        g.setColor(Color.black);
        g.fillRoundRect(145 + x * 105, y + 20, 100,55,30,30);
        g.fillRect(240 + x * 105, y + 50, 13,15);

        // draw wagon wheels
        g.fillRoundRect(155 + x * 105, y + 70, 15,15,90,90);
        g.fillRoundRect(220 + x * 105, y + 70, 15,15,90,90);

        // draw wagon windows
        g.setColor(Color.white);
        g.fillRect(160 + x * 105, y + 40, 15,15);
        g.fillRect(185 + x * 105, y + 40, 15,15);
        g.fillRect(210 + x * 105, y + 40, 15,15);

        // draw wagon text  !!max 11 characters
        g.drawString(type, 160 + x * 105, y + 35);

    }

    @Override
    public void update() {
        if(getMaxWagon() > 8){
            this.setPreferredSize(new Dimension(990 + 130 + (getMaxWagon() - 8) * 100, 40 + 100 * controller.getFacade().getTrains().size()));
        }else{
            this.setPreferredSize(new Dimension(this.getWidth(), 40 + 100 * controller.getFacade().getTrains().size()));
        }
        revalidate();
        repaint();
    }

    public int getMaxWagon() {
        int max = 0;
        for (String train : controller.getFacade().getTrains()) {
            if (controller.getFacade().getTrein(train).getWagons().size() > max) {
                max = controller.getFacade().getTrein(train).getWagons().size();
            }
        }
        return max;
    }
}
