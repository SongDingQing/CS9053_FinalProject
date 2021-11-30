package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.Socket;

public class CommandPanel extends JPanel {
    // c+worker type means Create a Worker of that type
    private JButton cLogger;
    private JButton cFisher;
    private JButton cMiner;
    private JButton cWarrior;
    private JButton cArcher;
    private JButton cOther;
    public CommandPanel(){
        super();
        createButtons();
        addListeners();
    }
    public void createButtons(){
        cLogger =new JButton("Logger");
        cFisher=new JButton("Fisher");
        cMiner =new JButton("Miner");
        cWarrior =new JButton("Warrior");
        cArcher =new JButton("Archer");
        cOther =new JButton("Other");
        setLayout(new GridLayout(3,2));
        add(cLogger);
        add(cFisher);
        add(cMiner);
        add(cWarrior);
        add(cArcher);
        add(cOther);
    }
    public void addListeners(){
        class cLoggerListener implements ActionListener {
            @Override
            public void actionPerformed(ActionEvent e) {
                //ToDo
            }
        }
        cLogger.addActionListener(new cLoggerListener());
    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);
    }
}
