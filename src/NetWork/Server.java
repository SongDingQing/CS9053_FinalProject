package NetWork;

import DataType.TransmitData;

import javax.swing.*;
import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Date;

import Data.ServerData.Variable;
import DataType.UnitData;

public class Server extends JFrame implements Runnable {
    // Text area for displaying contents
    private JTextArea ta;

    // Number a client
    private int clientNo = 0;

    //DataStorage for both clients
    private TransmitData data1;
    private TransmitData data2;

    private int timeCounter;

    public Server() {
        //player data initialization
        data1 = new TransmitData();
        data2 = new TransmitData();
        //server log
        ta = new JTextArea(10, 10);
        timeCounter = 1;
        JScrollPane sp = new JScrollPane(ta);
        this.add(sp);
        this.setTitle("NetWork.Server");
        this.setSize(400, 200);
        Thread t = new Thread(this);
        t.start();
    }

    public void run() {
        try {
            // Create a server socket
            ServerSocket serverSocket = new ServerSocket(8000);
            ta.append("NetWork.MultiThreadServer started at "
                    + new Date() + '\n');

            while (true) {
                // Listen for a new connection request
                Socket socket = serverSocket.accept();

                // Increment clientNo
                clientNo++;

                if (clientNo < 3) {
                    //System.out.println(playNum);
                    ta.append("PlayerNum given: " + clientNo + '\n');
                    ta.append("Starting thread for client Num#" + clientNo +
                            " at " + new Date() + '\n');

                    // Find the client's host name, and IP address
                    InetAddress inetAddress = socket.getInetAddress();
                    ta.append("NetWork.Client " + clientNo + "'s IP Address is "
                            + inetAddress.getHostAddress() + "\n");
                    // Create and start a new thread for the connection
                    new Thread(new Player(socket, clientNo)).start();
                }
            }
        } catch (IOException ex) {
            System.err.println(ex);
        }

    }

    // Define the thread class for handling new connection
    class Player implements Runnable {
        private Socket socket; // A connected socket
        private int clientNum;

        /**
         * Construct a thread
         */
        public Player(Socket socket, int clientNum) {
            this.socket = socket;
            this.clientNum = clientNum;
        }

        private int getClientNum() {
            return clientNum;
        }


        /**
         * Run a thread
         */
        public void run() {
            try {
                // Create data input and output streams
                //TODO command Panel update
                DataInputStream inputFromClient = new DataInputStream(
                        socket.getInputStream());

                ObjectOutputStream outputToClient = new ObjectOutputStream(
                        socket.getOutputStream());

                // Continuously serve the client
                if (clientNum == 1) {
                    while (true) {
                        Thread.sleep(50);
                        int command = inputFromClient.readInt();
                        int locX = inputFromClient.readInt();
                        handleCommand(command,clientNum,locX);

                        // Send area back to the client
                        outputToClient.reset();
                        outputToClient.writeObject(data1);
                        data1.update(data2.getUnitDataAL());


                        // only thread 1 is in charge of time update
                        timeCounter++;
                        if (timeCounter == 20) {
                            Variable.time++;
                            timeCounter = 1;
                        }
                    }
                } else {
                    while (true) {
                        Thread.sleep(50);
                        int command = inputFromClient.readInt();
                        int locX = inputFromClient.readInt();
                        handleCommand(command,clientNum,locX);
                        outputToClient.reset();
                        outputToClient.writeObject(data2);
                        data2.update(data1.getUnitDataAL());
                    }
                }

            } catch (IOException | InterruptedException ex) {
                ta.append("client:" + this.getClientNum() + " have been closed\n");
                ex.printStackTrace();
            }
        }
    }
    public void handleCommand(int command, int playerNum,int locX){
        if(command!=0){
            if(playerNum==1){
                data1.createUnit(command,locX);
                data2.createEnemyUnit(command,locX);
            }else{
                data2.createUnit(command,locX);
                data1.createEnemyUnit(command,locX);
            }
        }

    }


    /**
     * The main method is only needed for the IDE with limited
     * JavaFX support. Not needed for running from the command line.
     */
    public static void main(String[] args) {
        Server s = new Server();
        s.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        s.setResizable(false);
        s.setVisible(true);
    }
}