package GUI;

import Data.Constants;
import DataType.StatusData;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

/*** This Class is the the Game Drawing (the whole Panel)
 * @author Chengzuo Song
 * @version 0.0
 */

public class MainPanel extends JFrame  implements ActionListener {
    //Connection variable
    private boolean isConnected;
    //Connection socket
    Socket socket = null;
    // IO streams
    DataOutputStream toServer = null;
    DataInputStream fromServer = null;
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
    private JPanel logPanel;
    private JScrollPane jScrollPane;
    private JPanel connectPanel;
    private JTextArea logTextField;

    //Connect Button in LogPanel
    JButton connectButton;
    
    // Timer
    private Timer timer;

    public MainPanel(){
        super("budge");
        isConnected=false;//isConnected config
        logPanel= new JPanel();
        setMapPanel();  // Map Panel
        setControlPanel();  // Control Panel
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
        add(mapPanel, BorderLayout.WEST);
    }

    private void setControlPanel() {
        controlPanel = new JPanel();
        Dimension d = new Dimension(Constants.Cpanel_Width,Constants.Cpanel_Height);
        controlPanel.setPreferredSize(d);

        setStatusPanel();  // Status Panel
        setCommandPanel();  // Command Panel

        setLogPanel(); //Log panel
        controlPanel.setBackground(new Color(220, 200, 50));
        add(controlPanel, BorderLayout.EAST);
    }

    private void setStatusPanel() {
    	// Initialize Status Panel using StatusData
        StatusData sd = new StatusData(max_hp, hp, food, wood, coal, iron, unit, time);
        statusPanel = new StatusPanel(sd);
        Dimension d = new Dimension(Constants.Status_Width,Constants.Status_Height);
        statusPanel.setPreferredSize(d);
        statusPanel.setBackground(new Color(200, 200, 250));
        controlPanel.add(statusPanel, BorderLayout.NORTH);
    }

    private void setCommandPanel() {
        commandPanel = new CommandPanel();
        Dimension d = new Dimension(Constants.Command_Width, Constants.Command_Height);
        commandPanel.setPreferredSize(d);
        commandPanel.setBackground(new Color(250, 100, 100));
        controlPanel.add(commandPanel, BorderLayout.CENTER);
    }

    private void setLogPanel(){
        logPanel.removeAll();
        logPanel.revalidate();
        if(isConnected){
            setJScrollPanel();  // Log Panel
        }else{
            setConnectPanel();// Connect Panel
        }
    }

    private void setJScrollPanel() {
            logTextField = new JTextArea(Constants.VersionText);
            logTextField.setEditable(false);
            jScrollPane = new JScrollPane(logTextField);
            Dimension d = new Dimension(Constants.Log_Width,Constants.Log_Height);
            jScrollPane.setPreferredSize(d);
            jScrollPane.setBackground(new Color(100, 250, 100));
            logPanel.add(jScrollPane);
            controlPanel.add(logPanel, BorderLayout.SOUTH);
    }
    private void setConnectPanel(){
        connectPanel=new JPanel();
        connectButton=new JButton("Open Connection");
        connectButton.setMargin(new Insets(10,2,10,2));
        connectButton.setBackground(new Color(180,180,180));
        connectButton.setFont(new Font("time new roman", Font.BOLD, 15));
        connectButton.addActionListener(new OpenConnectionListener());
        Dimension d = new Dimension(Constants.Log_Width,Constants.Log_Height);
        connectPanel.setPreferredSize(d);
        connectPanel.add(connectButton,BorderLayout.CENTER);
        logPanel.add(connectPanel);
        controlPanel.add(logPanel, BorderLayout.SOUTH);

    }
    class OpenConnectionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            isConnected=true;
            //TODO connection start here
            setLogPanel();
            //System.out.println(isConnected);
        }

    }


    @Override
    //Timer ticking
    public void actionPerformed(ActionEvent e) {
        mapPanel.upDateTime();  // Update UnitData Here!
    	statusPanel.upDateTime();  // Update StatusData Here!
        //System.out.println("the game is running");
        repaint();
    }
}
