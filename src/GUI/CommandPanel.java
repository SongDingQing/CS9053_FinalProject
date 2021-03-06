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
    private JButton cFence;
    //player Number
    private int playerNum;
    ImageIcon picLogger=new ImageIcon("src/Images/button_Logger.png");
    ImageIcon picFisher=new ImageIcon("src/Images/button_Fisher.png");
    ImageIcon picMiner=new ImageIcon("src/Images/button_Miner.png");
    ImageIcon picWarrior=new ImageIcon("src/Images/button_Warrior.png");
    ImageIcon picArcher=new ImageIcon("src/Images/button_Archer.png");
    ImageIcon picFence=new ImageIcon("src/Images/button_Fence.png");
    /*Acknowledgement
    picture axe
    https://pixabay.com/vectors/axe-cutting-wood-lumberjack-ax-4804073/
    fish pic
    https://pixabay.com/photos/the-fish-food-rope-blue-gourmet-3646498/
    */
    
    public CommandPanel(int playerNum){
        super();
        this.playerNum=playerNum;
        createButtons();
        addListeners();
    }
    
    public void createButtons(){
        cLogger =new JButton("Logger",picLogger);
        cFisher=new JButton("Fisher",picFisher);
        cMiner =new JButton("Miner",picMiner);
        cWarrior =new JButton("Warrior",picWarrior);
        cArcher =new JButton("Archer",picArcher);
        cFence =new JButton("Fence",picFence);
        setLayout(new GridLayout(2,3));
        add(cLogger);
        add(cFisher);
        add(cMiner);
        add(cWarrior);
        add(cArcher);
        add(cFence);
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
        cWarrior.addActionListener(new UnitListener(4));
        cArcher.addActionListener(new UnitListener(5));
        cFence.addActionListener(new UnitListener(6));
    }
    
    public void paintComponent(Graphics g){
        super.paintComponent(g);
    }
}
