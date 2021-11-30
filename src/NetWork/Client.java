package NetWork;

import GUI.MainPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Client extends JFrame {
    static int NumOfInstance;
    // IO streams
    DataOutputStream toServer = null;
    DataInputStream fromServer = null;
    JTextField textField = null;
    JTextArea textArea = null;
    Socket socket = null;
    JButton openButton;
    JButton closeButton;
    MainPanel Game;
    private int id;


    public Client() {
        super("NetWork.Client");
        id=NumOfInstance + 1;
        NumOfInstance++;
        createUI();
    }
    private void createUI(){
        textField = new JTextField(5);
        textArea = new JTextArea(30, 30);
        this.setLayout(new BorderLayout());
        //this.add(textField, BorderLayout.NORTH);
        textField.addActionListener(new TextFieldListener());

        JPanel topPanel = new JPanel(new GridLayout(2, 1));
        JPanel controlPanel = new JPanel();
        topPanel.add(textField);
        openButton = new JButton("Open Connection");
        closeButton = new JButton("Close Connection");
        controlPanel.add(openButton);
        controlPanel.add(closeButton);
        topPanel.add(controlPanel);
        this.add(topPanel, BorderLayout.NORTH);

        this.add(textArea, BorderLayout.CENTER);
        closeButton.addActionListener(new CloseConnectionListener());
        openButton.addActionListener(new OpenConnectionListener());
        setSize(400, 200);
    }

    class OpenConnectionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            // TODO Auto-generated method stub
            try {
                socket = new Socket("localhost", 8000);
                textArea.append("connected\n");
                Game = new MainPanel();
            } catch (Exception e1) {
                System.err.println("error");
            }
        }

    }

    class CloseConnectionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                socket.close();
                textArea.append("connection closed\n");
                Game.dispose();
            } catch (IOException e1) {
                e1.printStackTrace();
                textArea.append("connection Failure\n");
            }
        }

    }

    class TextFieldListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            //set output and input
            try {
                // Create an input stream to receive data from the server
                fromServer = new DataInputStream(socket.getInputStream());
                // Create an output stream to send data to the server
                toServer = new DataOutputStream(socket.getOutputStream());
            } catch (IOException ex) {
                textArea.append(ex.toString() + '\n');
            }

            try {
                // Send the id to the server
                toServer.writeDouble(id);
                toServer.flush();

                // Get area from the server
                double playerNum = fromServer.readDouble();

                // Display to the text area
                textArea.append("id sent as "+ id);
                textArea.append("PlayerNumber received from the server is "
                        + playerNum + '\n');
                //socket.close();
            } catch (IOException ex) {
                System.err.println(ex);
            }


        }
    }


    public static void main(String[] args) {
        Client c = new Client();
        c.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        c.setResizable(false);
        c.setVisible(true);
    }
}
