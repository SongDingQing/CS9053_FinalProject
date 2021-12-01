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

    // StatusData Initialization
    private final int max_hp = 100;
    private int hp = 80;
    private int wood = 50;
    private int food = 0;
    private int coal = 0;
    private int iron = 0;
    private int unit = 0;
    private int time = 0;

    // Panels Components
    private MapPanel mapPanel;
    private JPanel controlPanel;
    private StatusPanel statusPanel;
    private CommandPanel commandPanel;
    private JScrollPane logPanel;
    private JTextArea logTextField;
    
    // Timer
    private Timer timer;

    public MainPanel(){
        super("budge");
        setMapPanel();  // Map Panel
        setControlPanel();  // Control Panel
        setupPanels();  // Add up Components
        // Project Icon
        String iconName = "src/Images/icon.png";
        setIconImage(new ImageIcon(iconName).getImage());
        // Other Configuration
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(Constants.Width, Constants.Height);
        setResizable(false);
        setVisible(true);
        // Main Game Thread
        timer= new Timer(Constants.Delay, this);
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
        createStatusPanel();  // Status Panel
        controlPanel.add(statusPanel, BorderLayout.NORTH);
        createCommandPanel();  // Command Panel
        controlPanel.add(commandPanel, BorderLayout.CENTER);
        createLogPanel();  // Log Panel
        controlPanel.add(logPanel, BorderLayout.SOUTH);
        controlPanel.setBackground(new Color(220, 200, 50));
    }

    private void createStatusPanel() {
    	// Initialize Status Panel using StatusData
        StatusData sd = new StatusData(max_hp, hp, food, wood, coal, iron, unit, time);
        statusPanel = new StatusPanel(sd);
        Dimension d = new Dimension(Constants.Status_Width,Constants.Status_Height);
        statusPanel.setPreferredSize(d);
        statusPanel.setBackground(new Color(200, 200, 250));
    }

    private void createCommandPanel() {
        commandPanel = new CommandPanel();
        Dimension d = new Dimension(Constants.Command_Width, Constants.Command_Height);
        commandPanel.setPreferredSize(d);
        commandPanel.setBackground(new Color(250, 100, 100));
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
        mapPanel.upDateTime();  // Update UnitData Here!
    	statusPanel.upDateTime();  // Update StatusData Here! 
    	
        //System.out.println("the game is running");
        repaint();
    }
}
