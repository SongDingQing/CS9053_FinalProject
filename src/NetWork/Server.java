package NetWork;

import javax.swing.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

public class Server extends JFrame implements Runnable {
  // Text area for displaying contents
  private JTextArea ta;
  
  // Number a client
  private int clientNo = 0;
  
  public Server() {
	  ta = new JTextArea(10,10);
	  JScrollPane sp = new JScrollPane(ta);
	  this.add(sp);
	  this.setTitle("NetWork.Server");
	  this.setSize(400,200);
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
          
          ta.append("Starting thread for client " + clientNo +
              " at " + new Date() + '\n');

            // Find the client's host name, and IP address
            InetAddress inetAddress = socket.getInetAddress();
            ta.append("NetWork.Client " + clientNo + "'s IP Address is "
              + inetAddress.getHostAddress() + "\n");
          
          // Create and start a new thread for the connection
          new Thread(new HandleAClient(socket, clientNo)).start();
        }
      }
      catch(IOException ex) {
        System.err.println(ex);
      }
	    
  }
  
  // Define the thread class for handling new connection
  class HandleAClient implements Runnable {
    private Socket socket; // A connected socket
    private int clientNum;
    
    /** Construct a thread */
    public HandleAClient(Socket socket, int clientNum) {
      this.socket = socket;
      this.clientNum = clientNum;
    }
    private int getClientNum(){
      return clientNum;
    }


    /** Run a thread */
    public void run() {
      try {
        // Create data input and output streams
        DataInputStream inputFromClient = new DataInputStream(
          socket.getInputStream());
        DataOutputStream outputToClient = new DataOutputStream(
          socket.getOutputStream());

        // Continuously serve the client
        while (true) {
          // Receive radius from the client
          int id = inputFromClient.readInt();

          // return clientNo
          int playNum = clientNum;

          // Send area back to the client
          outputToClient.writeInt(playNum);
          
          ta.append("radius received from client: " + id + " " +
              this.clientNum + '\n');
          ta.append("Area found: " + playNum + '\n');
          
        }
      }
      catch(IOException ex) {
        ta.append("client:"+ this.getClientNum()+" have been closed\n");
        ex.printStackTrace();
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