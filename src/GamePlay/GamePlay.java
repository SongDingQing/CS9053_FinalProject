package GamePlay;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/*** This Class is the the Game Drawing
 * @author Chengzuo Song
 * @version 0.0
 */


public class GamePlay extends JFrame  implements ActionListener {
    //-----------CONSTANTS--------------
    private final int HEIGHT = 800;
    private final int WIDTH = 1200;
    // 1. MAP CONSTANT
    private final int MAP_HEIGHT = 800;
    private final int MAP_WIDTH = 700;

    // 2. CONTROL CONSTANT
    private final int CPANEL_HEIGHT = 800;
    private final int CPANEL_WIDTH = 480;
    private final int STATUS_HEIGHT = 200;
    private final int STATUS_WIDTH = 480;
    private final int COMMAND_HEIGHT = 400;
    private final int COMMAND_WIDTH = 480;
    private final int LOG_HEIGHT = 200;
    private final int LOG_WIDTH = 480;
    private final int MAX_HP = 100;
    //delay per tick
    private int DELAY=1000;

    //---------------Variables------------------
    private int hp = 75;
    private int wood = 50;
    private int food = 0;
    private int coal = 0;
    private int iron = 0;
    private int unit = 0;
    private int time = 0;

    //Panels Components
    private JPanel mainPanel;
    private JPanel mapPanel;
    private JPanel controlPanel;
    private StatusPanel statusPanel;
    private JScrollPane logPanel;

    //Timer
    private Timer timer;


    public GamePlay(){
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
        setSize(WIDTH, HEIGHT);
        setVisible(true);
        //main game thread
        timer= new Timer(DELAY,this);
        timer.start();
    }

    private void setMapPanel() {
        mapPanel = new MapPanel();
        Dimension d = new Dimension(MAP_WIDTH, MAP_HEIGHT);
        mapPanel.setPreferredSize(d);
        mapPanel.setBackground(new Color(250, 200, 200));
    }

    private void setControlPanel() {
        controlPanel = new JPanel();
        Dimension d = new Dimension(CPANEL_WIDTH, CPANEL_HEIGHT);
        controlPanel.setPreferredSize(d);
        createStatusPanel();
        controlPanel.add(statusPanel, BorderLayout.NORTH);
        controlPanel.add(createCommandPanel(), BorderLayout.CENTER);
        createLogPanel();
        controlPanel.add(logPanel, BorderLayout.SOUTH);
        controlPanel.setBackground(new Color(220, 200, 50));
    }

    private void createStatusPanel() {
        StatusData sd = new StatusData(MAX_HP, hp, food, wood, coal, iron, unit, time);
        statusPanel = new StatusPanel(sd);
        Dimension d = new Dimension(STATUS_WIDTH, STATUS_HEIGHT);
        statusPanel.setPreferredSize(d);
        statusPanel.setBackground(new Color(200, 200, 250));
    }

    private JPanel createCommandPanel() {
        JPanel commandPanel = new JPanel();
        Dimension d = new Dimension(COMMAND_WIDTH, COMMAND_HEIGHT);
        commandPanel.setPreferredSize(d);
        commandPanel.setBackground(new Color(250, 100, 100));
        return commandPanel;
    }

    private void createLogPanel() {
        logPanel = new JScrollPane(new JTextArea());
        Dimension d = new Dimension(LOG_WIDTH, LOG_HEIGHT);
        logPanel.setPreferredSize(d);
        logPanel.setBackground(new Color(100, 250, 100));

    }

    private void setupPanels() {
        add(mapPanel, BorderLayout.WEST);
        add(controlPanel, BorderLayout.EAST);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        statusPanel.upDateTime();
        //System.out.println("the game is running");
        repaint();
    }
}
