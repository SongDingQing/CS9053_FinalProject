package GUI;

import Data.ClientData.Variable1;
import Data.ClientData.Variable2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CommandPanel extends JPanel {
	
    // c+worker type means Create a Worker of that type
    private JButton cLogger;
    private JButton cFisher;
    private JButton cMiner;
    private JButton cWarrior;
    private JButton cArcher;
    private JButton cOther;
    //player Number
    private int playerNum;
    
    public CommandPanel(int playerNum){
        super();
        this.playerNum=playerNum;
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
        class UnitListener implements ActionListener {
            private int unitType;
            public UnitListener(int unitType){
                super();
                this.unitType=unitType;
            }
            @Override
            public void actionPerformed(ActionEvent e) {
                //ToDo: Mouse Event
                //System.out.println(playerNum);
                if(playerNum==1){
                    Variable1.unitType = unitType;
                    Variable1.confirmationLine=1;
                    //System.out.println(Variable1.confirmationLine);
                }else{
                    Variable2.unitType = unitType;
                    Variable2.confirmationLine=1;
                }

                //System.out.println(Variable.unitType);
            }
        }
        cLogger.addActionListener(new UnitListener(1));
        cFisher.addActionListener(new UnitListener(2));
        cMiner.addActionListener(new UnitListener(3));
    }
    
    public void paintComponent(Graphics g){
        super.paintComponent(g);
    }
}
