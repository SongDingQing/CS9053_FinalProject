package GamePlay;

import javax.swing.*;
import java.awt.*;

/*** This Class is the the Game Drawing
 * @author Chengzuo Song
 * @version 0.0
 */


public class GamePlay extends JFrame {
    //-----------CONSTANTS--------------
    private final int HEIGHT= 800;
    private final int WIDTH = 1200;
    // 1. MAP CONSTANT
    private final int MAP_HEIGHT= 800;
    private final int MAP_WIDTH = 700;

    // 2. CONTROL CONSTANT
    private final int CPANEL_HEIGHT= 800;
    private final int CPANEL_WIDTH = 500;
    private final int STATUS_HEIGHT= 200;
    private final int STATUS_WIDTH = 500;
    private final int COMMAND_HEIGHT= 400;
    private final int COMMAND_WIDTH = 500;
    private final int LOG_HEIGHT= 200;
    private final int LOG_WIDTH = 500;
    private final int MAX_HP=100;
    private int HP=75;
    private int WOOD=50;
    private int FOOD=0;
    private int COAL=0;
    private int IRON=0;
    private int UNIT=0;
    private int TIME=0;

    //Panels Components
    private JPanel mapPanel;
    private JPanel controlPanel;

    public GamePlay() {
        super("budge");
        //Map Panel
        setMapPanel();
        //Control Panel
        setControlPanel();
        //Add up Component
        setupPanels();
        // Project Icon
        String iconName = "src/Images/icon.png";
        setIconImage(new ImageIcon(iconName).getImage());

        // Other Configuration
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setSize(WIDTH,HEIGHT);
        setVisible(true);
    }
    private void setMapPanel(){
        mapPanel = new JPanel();
        Dimension d = new Dimension(MAP_WIDTH,MAP_HEIGHT);
        mapPanel.setPreferredSize(d);
        mapPanel.setBackground(new Color(250,200,200));
    };
    private void setControlPanel(){
        controlPanel= new JPanel();
        Dimension d = new Dimension(CPANEL_WIDTH,CPANEL_HEIGHT);
        controlPanel.setPreferredSize(d);
        controlPanel.add(createStatusPanel(),BorderLayout.NORTH);
        controlPanel.add(createCommandPanel(),BorderLayout.CENTER);
        controlPanel.add(createLogPanel(),BorderLayout.SOUTH);
        controlPanel.setBackground(new Color(220,200,50));
    };
    private JPanel createStatusPanel(){
        StatusData sd=new StatusData(MAX_HP,HP,FOOD,WOOD,COAL,IRON,UNIT,TIME);
        JPanel statusPanel = new StatusPanel(sd);
        Dimension d = new Dimension(STATUS_WIDTH,STATUS_HEIGHT);
        statusPanel.setPreferredSize(d);
        statusPanel.setBackground(new Color(200,200,250));
        return statusPanel;
    };
    private JPanel createCommandPanel(){
        JPanel commandPanel = new JPanel();
        Dimension d = new Dimension(COMMAND_WIDTH,COMMAND_HEIGHT);
        commandPanel.setPreferredSize(d);
        commandPanel.setBackground(new Color(250,100,100));
        return commandPanel;
    };
    private JPanel createLogPanel(){
        JPanel logPanel = new JPanel();
        Dimension d = new Dimension(LOG_WIDTH,LOG_HEIGHT);
        logPanel.setPreferredSize(d);
        logPanel.setBackground(new Color(100,250,100));
        return logPanel;
    };

    private void setupPanels(){
        add(mapPanel,BorderLayout.WEST);
        add(controlPanel,BorderLayout.EAST);
    }
}
