package NetWork;

import Data.ClientData.Constants;
import DataType.TransmitData;

import javax.swing.*;
import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
import java.util.Scanner;

import Data.ServerData.Variable;


public class Server extends JFrame implements Runnable {
    // Text area for displaying contents
    private JTextArea ta;
    //Game Ongoing
    private boolean game;
    // Number a client
    private int clientNo = 0;

    //DataStorage for both clients
    //time counter
    private int timeCounter;

    public Server() {
        game = true;
        //player data initialization
        Variable.data1 = new TransmitData(1);
        Variable.data2 = new TransmitData(2);
        //server log
        ta = new JTextArea(10, 10);
        //time counter reset
        timeCounter = 1;
        //pixelData initialization
        configPixelData();
        //System.out.println(Variable.pixelData[0][0]);
        JScrollPane sp = new JScrollPane(ta);
        this.add(sp);
        this.setTitle("NetWork.Server");
        this.setSize(400, 200);
        Thread t = new Thread(this);
        t.start();
    }

    public void configPixelData() {
        Variable.pixelData1 = new int[80][60];
        Variable.pixelData2 = new int[80][60];
        //------------CONSTRUCT mapData based on PixelData.txt--------------
        try {
            java.io.File file = new java.io.File("src/Data/ServerData/PixelData.txt");
            Scanner input = new Scanner(file);
            while (input.hasNext()) {
                for (int y = 0; y < Constants.Pixels_Height; y++) {
                    for (int x = 0; x < Constants.Pixels_Width; x++) {
                        int temp = input.nextInt();
                        Variable.pixelData1[x][y] = temp;
                        Variable.pixelData2[x][59 - y] = temp;
                    }
                }
            }
            input.close();
        } catch (FileNotFoundException e) {
            System.out.println("Cannot find PixelData.txt");
        }
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
            //System.out.println(clientNum);
        }

        private int getClientNum() {
            return clientNum;
        }


        /**
         * Run a thread
         */
        public void run() {
            while (game) {
                try {
                    // Create data input and output streams
                    DataInputStream inputFromClient = new DataInputStream(
                            socket.getInputStream());

                    ObjectOutputStream outputToClient = new ObjectOutputStream(
                            socket.getOutputStream());

                    // Continuously serve the client
                    while (true) {
                        if (clientNum == 1) {

                            Thread.sleep(40);
                            int command = inputFromClient.readInt();
                            int locX = inputFromClient.readInt();
                            handleCommand(command, clientNum, locX);

                            // Send area back to the client
                            outputToClient.reset();
                            outputToClient.writeObject(Variable.data1);
                            Variable.data1.update(Variable.data2.getUnitDataAL());


                            // only thread 1 is in charge of time update
                            timeCounter++;
                            if (timeCounter == 25) {
                                Variable.time++;
                                timeCounter = 1;
                            }
                        } else if (clientNum == 2) {

                            Thread.sleep(40);
                            int command = inputFromClient.readInt();
                            int locX = inputFromClient.readInt();
                            handleCommand(command, clientNum, locX);
                            outputToClient.reset();
                            outputToClient.writeObject(Variable.data2);
                            Variable.data2.update(Variable.data1.getUnitDataAL());

                        }
                    }


                } catch (IOException | InterruptedException ex) {
                    ta.append("client:" + this.getClientNum() + " have been closed\n");
                    ex.printStackTrace();
                }
            }

        }
        public void handleCommand(int command, int playerNum, int locX) {
            if (command > 0) {
                if (playerNum == 1) {
                    Variable.data1.createUnit(command, locX);
                    Variable.data2.updateEnemyUnit();
                } else if (playerNum == 2) {
                    Variable.data2.createUnit(command, locX);
                    Variable.data1.updateEnemyUnit();
                }
            }else if(command==-1){
                if(playerNum==1){
                    Variable.data1.lost();
                    Variable.data2.win();
                }else if(playerNum==2){
                    Variable.data1.lost();
                    Variable.data2.win();
                }
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