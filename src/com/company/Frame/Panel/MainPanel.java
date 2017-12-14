package com.company.Frame.Panel;

import com.company.Frame.Controller.Controller;
import com.company.Frame.Models.Facade;

import javax.swing.*;
import java.awt.*;

/**
 * Created by marti on 6-12-2017.
 */
public class MainPanel extends JPanel {

    Facade facade;

    private JTextArea textArea;
    private JTextArea textArea1;

    private TrainPanel tPanel;
    private JScrollPane scrollPane;
    private OutputPanel oPanel;
    private InputPanel iPanel;

    public MainPanel(Controller controller){
        this.setBackground(Color.white);
        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

        tPanel = new TrainPanel(controller);
        scrollPane = new JScrollPane(tPanel);
        oPanel = new OutputPanel(controller);
        iPanel = new InputPanel(controller);

        scrollPane.setPreferredSize(new Dimension(1000,350));
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);

        this.add(scrollPane);
        this.add(oPanel);
        this.add(iPanel);

    }

    public TrainPanel gettPanel() {
        return tPanel;
    }

    public OutputPanel getoPanel() {
        return oPanel;
    }

    public InputPanel getiPanel() {
        return iPanel;
    }
}
