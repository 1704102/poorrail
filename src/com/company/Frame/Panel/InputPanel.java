package com.company.Frame.Panel;

import com.company.Frame.Controller.Controller;
import com.company.Frame.Models.Facade;


import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * Created by marti on 6-12-2017.
 */
public class InputPanel extends JPanel{

    JButton button;
    JTextArea textField;
    JScrollPane scrollPane;
    Facade facade;


    public InputPanel(Controller controller){

        this.facade = facade;

        this.setPreferredSize(new Dimension(1000,200));
        this.setBackground(new Color(221, 218, 222));

        textField = new JTextArea();
        scrollPane = new JScrollPane(textField){{setPreferredSize(new Dimension(300,180));}};
        button = new JButton("execute"){{setPreferredSize(new Dimension(100,50));}};

        this.add(scrollPane);
        this.add(button);

        button.addActionListener(e->{
            String[] commands = textField.getText().split("\n");
            for(int i = 0; i < commands.length; i++){
                controller.execute(commands[i]);
            }
            textField.setText("");
        });


    }
}
