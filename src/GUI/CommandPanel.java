package GUI;

import javax.swing.*;
import java.awt.*;

public class CommandPanel extends JPanel {
    private JButton button1;
    private JButton button2;
    private JButton button3;
    private JButton button4;
    private JButton button5;
    private JButton button6;
    public CommandPanel(){
        button1=new JButton("Logger");
        button2=new JButton("Fisher");
        button3=new JButton("Miner");
        button4=new JButton("Warrior");
        button5=new JButton("Archer");
        button6=new JButton("Other");
        setLayout(new GridLayout(3,2));
        add(button1);
        add(button2);
        add(button3);
        add(button4);
        add(button5);
        add(button6);
    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);
    }
}
