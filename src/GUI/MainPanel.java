package GUI;

import Data.ClientData.Constants;
import DataType.StatusData;
import DataType.TransmitData;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.Socket;

import Data.ClientData.*;


/*** This Class is the the Game Drawing (the whole Panel)
 * @author Chengzuo Song
 * @version 0.0
 */

public class MainPanel extends JFrame implements ActionListener {
    //Connection variable
    private boolean isConnected;
    //Connection socket
    Socket socket = null;
    // IO streams
    private DataOutputStream toServer = null;
    private ObjectInputStream fromServer = null;
    private TransmitData localData;

    // Panels Components
    private MapPanel mapPanel;
    private JPanel controlPanel;
    private StatusPanel statusPanel;
    private CommandPanel commandPanel;
    private JPanel logPanel;
    private JScrollPane jScrollPane;
    private JPanel connectPanel;
    private JTextArea logTextField;
    //game status determination

    //playerNum
    private int playerNum;
    private boolean isPlayerNumSent;

    //Connect Button in LogPanel
    JButton connectButton;

    // Timer
    private Timer timer;

    public MainPanel(int playerNum) {
        super("budge Player" + playerNum);
        //PlayerNum config
        this.playerNum = playerNum;
        isPlayerNumSent=false;

        isConnected = false;//isConnected config
        logPanel = new JPanel();
        if(playerNum==1){
            Variable1.gameEnd=0;
        }else if(playerNum==2){
            Variable2.gameEnd=0;
        }


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

        //System.out.println(playerNum);

        // Main Game Thread
        timer = new Timer(Constants.Delay, this);
        localData = new TransmitData();
        timer.start();
    }

    private void setMapPanel() {
        mapPanel = new MapPanel(playerNum);
        Dimension d = new Dimension(Constants.Map_Width, Constants.Map_Height);
        mapPanel.setPreferredSize(d);
        mapPanel.setBackground(new Color(150, 150, 150));
        add(mapPanel, BorderLayout.WEST);
    }

    private void setControlPanel() {
        controlPanel = new JPanel();
        Dimension d = new Dimension(Constants.Cpanel_Width, Constants.Cpanel_Height);
        controlPanel.setPreferredSize(d);

        setStatusPanel();  // Status Panel
        setCommandPanel();  // Command Panel

        setLogPanel(); //Log panel
        controlPanel.setBackground(new Color(220, 200, 50));
        add(controlPanel, BorderLayout.EAST);
    }

    private void setStatusPanel() {
        // Initialize Status Panel using StatusData
        StatusData sd = new StatusData();
        statusPanel = new StatusPanel(sd);
        Dimension d = new Dimension(Constants.Status_Width, Constants.Status_Height);
        statusPanel.setPreferredSize(d);
        statusPanel.setBackground(new Color(200, 200, 250));
        controlPanel.add(statusPanel, BorderLayout.NORTH);
    }

    private void setCommandPanel() {
        commandPanel = new CommandPanel(playerNum);
        //System.out.println(playerNum);
        Dimension d = new Dimension(Constants.Command_Width, Constants.Command_Height);
        commandPanel.setPreferredSize(d);
        commandPanel.setBackground(new Color(250, 100, 100));
        controlPanel.add(commandPanel, BorderLayout.CENTER);
    }

    private void setLogPanel() {
        logPanel.removeAll();
        logPanel.revalidate();
        if (isConnected) {
            setJScrollPanel();  // Log Panel
        } else {
            setConnectPanel();// Connect Panel
        }
    }

    private void setJScrollPanel() {
        logTextField = new JTextArea(Constants.VersionText);
        logTextField.setEditable(false);
        jScrollPane = new JScrollPane(logTextField);
        Dimension d = new Dimension(Constants.Log_Width, Constants.Log_Height);
        jScrollPane.setPreferredSize(d);
        jScrollPane.setBackground(new Color(100, 250, 100));
        logPanel.add(jScrollPane);
        controlPanel.add(logPanel, BorderLayout.SOUTH);
    }

    private void setConnectPanel() {
        connectPanel = new JPanel();
        connectButton = new JButton("Open Connection");
        connectButton.setMargin(new Insets(10, 2, 10, 2));
        connectButton.setBackground(new Color(180, 180, 180));
        connectButton.setFont(new Font("time new roman", Font.BOLD, 15));
        connectButton.addActionListener(new OpenConnectionListener());
        Dimension d = new Dimension(Constants.Log_Width, Constants.Log_Height);
        connectPanel.setPreferredSize(d);
        connectPanel.add(connectButton, BorderLayout.CENTER);
        logPanel.add(connectPanel);
        controlPanel.add(logPanel, BorderLayout.SOUTH);

    }

    class OpenConnectionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            //connection start here
            try {
                socket = new Socket("localhost", 8000);
                try {
                    // Create an input stream to receive data from the server
                    fromServer = new ObjectInputStream(socket.getInputStream());
                    // Create an output stream to send data to the server
                    toServer = new DataOutputStream(socket.getOutputStream());
                } catch (IOException ex) {
                    logTextField.append(ex.toString() + '\n');
                }
                isConnected = true;

                //set output and input

            } catch (IOException e1) {
                e1.printStackTrace();
            }
            setLogPanel();
            //System.out.println(isConnected);
        }

    }

    private void checkCommand(){
        if(playerNum==1){
            if(Variable1.CommandType==1){
                if(localData.getStatusData().getFood()<40){
                    Variable1.CommandType=0;
                    logTextField.append("Error: not enough resource to generate a logger!\n");
                }
            }else if(Variable1.CommandType==2){
                if(localData.getStatusData().getFood()<30||localData.getStatusData().getWood()<20){
                    Variable1.CommandType=0;
                    logTextField.append("Error: not enough resource to generate a fisher!\n");
                }
            }else if(Variable1.CommandType==3){
                if(localData.getStatusData().getFood()<40||localData.getStatusData().getWood()<40){
                    Variable1.CommandType=0;
                    logTextField.append("Error: not enough resource to generate a miner!\n");
                }
            }else if(Variable1.CommandType==4){
                if(localData.getStatusData().getFood()<50||localData.getStatusData().getWood()<20
                        ||localData.getStatusData().getCoal()<40||localData.getStatusData().getIron()<30){
                    Variable1.CommandType=0;
                    logTextField.append("Error: not enough resource to generate a warrior!\n");
                }
            }else if(Variable1.CommandType==5){
                if(localData.getStatusData().getFood()<90||localData.getStatusData().getWood()<90
                        ||localData.getStatusData().getIron()<20){
                    Variable1.CommandType=0;
                    logTextField.append("Error: not enough resource to generate a archer!\n");
                }
            }else if(Variable1.CommandType==6){
                if(localData.getStatusData().getWood()<200){
                    Variable1.CommandType=0;
                    logTextField.append("Error: not enough resource to generate fence!\n");
                }
            }
        }else if(playerNum==2){
            if(Variable2.CommandType==1){
                if(localData.getStatusData().getFood()<40){
                    Variable2.CommandType=0;
                    logTextField.append("Error: not enough resource to generate a logger!\n");
                }
            }else if(Variable2.CommandType==2){
                if(localData.getStatusData().getFood()<30||localData.getStatusData().getWood()<20){
                    Variable2.CommandType=0;
                    logTextField.append("Error: not enough resource to generate a fisher!\n");
                }
            }else if(Variable2.CommandType==3){
                if(localData.getStatusData().getFood()<40||localData.getStatusData().getWood()<40){
                    Variable2.CommandType=0;
                    logTextField.append("Error: not enough resource to generate a miner!\n");
                }
            }else if(Variable2.CommandType==4){
                if(localData.getStatusData().getFood()<50||localData.getStatusData().getWood()<20
                        ||localData.getStatusData().getCoal()<40||localData.getStatusData().getIron()<30){
                    Variable2.CommandType=0;
                    logTextField.append("Error: not enough resource to generate a warrior!\n");
                }
            }else if(Variable2.CommandType==5){
                if(localData.getStatusData().getFood()<90||localData.getStatusData().getWood()<90
                        ||localData.getStatusData().getIron()<20){
                    Variable2.CommandType=0;
                    logTextField.append("Error: not enough resource to generate a archer!\n");
                }
            }else if(Variable2.CommandType==6){
                if(localData.getStatusData().getWood()<200){
                    Variable2.CommandType=0;
                    logTextField.append("Error: not enough resource to generate fence!\n");
                }
            }
        }

    }
    private void checkEndGame(){
        if(localData.getStatusData().getHp()<=0){
            if(playerNum==1){
                Variable1.CommandType =-1;
            }else if(playerNum==2){
                Variable2.CommandType =-1;
            }
        }
    }



    @Override
    //Timer ticking
    public void actionPerformed(ActionEvent e) {
        if (isConnected) {
            try {
                if(!isPlayerNumSent){
                    toServer.writeInt(playerNum);
                    toServer.flush();
                    isPlayerNumSent=true;
                }
                if (playerNum == 1) {
                    checkCommand();//to test comment out this line<<<<<<<<<<<<<<<<<<<
                    checkEndGame();
                    //System.out.println(Variable1.CommandType);

                    toServer.writeInt(Variable1.CommandType);
                    toServer.flush();
                    toServer.writeInt(Variable1.tempX);
                    toServer.flush();
                    Variable1.CommandType = 0;
                } else {
                    checkCommand();//to test comment out this line<<<<<<<<<<<<<<<<<<<<
                    checkEndGame();

                    toServer.writeInt(Variable2.CommandType);
                    toServer.flush();
                    toServer.writeInt(Variable2.tempX);
                    toServer.flush();
                    Variable2.CommandType = 0;
                }
                localData = (TransmitData) fromServer.readObject();
                if(playerNum==1){
                    Variable1.gameEnd =localData.getGameEnd();
                }else if(playerNum==2){
                    Variable2.gameEnd =localData.getGameEnd();
                }
            } catch (Exception err) {
                err.printStackTrace();
            }
        }
        mapPanel.update(localData);  // Update UnitData Here!
        statusPanel.update(localData);
        //System.out.println("the game is running");
        repaint();
    }
}
