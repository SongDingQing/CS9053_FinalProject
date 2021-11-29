package GUI;

import Data.Constants;
import DataType.StatusData;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/*** This Class is the the Game Drawing (the whole Panel)
 * @author Chengzuo Song
 * @version 0.0
 */


public class MainPanel extends JFrame  implements ActionListener {

    //---------------Variables------------------
    private final int max_hp = 100;
    private int hp = 80;
    private int wood = 50;
    private int food = 0;
    private int coal = 0;
    private int iron = 0;
    private int unit = 0;
    private int time = 0;

    //Panels Components
    private JPanel mapPanel;
    private JPanel controlPanel;
    private StatusPanel statusPanel;
    private JScrollPane logPanel;
    private JTextArea logTextField;

    //Timer
    private Timer timer;


    public MainPanel(){
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
        setSize(Constants.Width, Constants.Height);
        setVisible(true);
        //main game thread
        timer= new Timer(Constants.Delay,this);
        timer.start();
    }

    private void setMapPanel() {
        mapPanel = new MapPanel();
        Dimension d = new Dimension(Constants.Map_Width,Constants.Map_Height);
        mapPanel.setPreferredSize(d);
        mapPanel.setBackground(new Color(250, 200, 200));
    }

    private void setControlPanel() {
        controlPanel = new JPanel();
        Dimension d = new Dimension(Constants.Cpanel_Width,Constants.Cpanel_Height);
        controlPanel.setPreferredSize(d);
        createStatusPanel();
        controlPanel.add(statusPanel, BorderLayout.NORTH);
        controlPanel.add(createCommandPanel(), BorderLayout.CENTER);
        createLogPanel();
        controlPanel.add(logPanel, BorderLayout.SOUTH);
        controlPanel.setBackground(new Color(220, 200, 50));
    }

    private void createStatusPanel() {
        StatusData sd = new StatusData(max_hp, hp, food, wood, coal, iron, unit, time);
        statusPanel = new StatusPanel(sd);
        Dimension d = new Dimension(Constants.Status_Width,Constants.Status_Height);
        statusPanel.setPreferredSize(d);
        statusPanel.setBackground(new Color(200, 200, 250));
    }

    private JPanel createCommandPanel() {
        JPanel commandPanel = new JPanel();
        Dimension d = new Dimension(Constants.Command_Width, Constants.Command_Height);
        commandPanel.setPreferredSize(d);
        commandPanel.setBackground(new Color(250, 100, 100));
        return commandPanel;
    }

    private void createLogPanel() {
        logTextField = new JTextArea(Constants.VersionText);
        logTextField.setEditable(false);
        logPanel = new JScrollPane(logTextField);
        Dimension d = new Dimension(Constants.Log_Width,Constants.Log_Height);
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
