package GUI;

import DataType.StatusData;

import javax.swing.*;
import java.awt.*;

/*** This Class is the the Status Drawing (the Status Panel)
 * @author Chengzuo Song
 * @version 0.0
 */

public class StatusPanel extends JPanel {
	
    StatusData status;
    private int counter = 0;
    
    public StatusPanel(StatusData statusData){
        super();
        status = statusData;
    }
    
    public void upDateTime(){
        if(counter == 30){
            status.setTime(status.getTime() + 1);
            counter = 0;
        }else{
            counter++;
        }
    }
    
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        drawHpBar(g);
        drawOtherStatus(g);
    }
    
    private void drawHpBar(Graphics g){
        // Maxhp bar drawing
        g.setColor(new Color(0, 0 , 0));
        g.fillRect(39, 10, 402, 20);//the 
        // Real time hp bar drawing
        int length =(int)((status.getHp()/(double)status.getMaxHp())*400);
        g.setColor(new Color(200, 50 , 50));
        g.fillRect(40, 11, length, 18);
        g.setColor(new Color(220, 180 , 180));
        g.fillRect(40, 11, length, 2);
        g.setColor(new Color(200, 150 , 150));
        g.fillRect(40, 13, length, 2);
        g.setColor(new Color(200, 100 , 100));
        g.fillRect(40, 15, length, 2);
        g.setColor(new Color(150, 50 , 50));
        g.fillRect(40, 24, length, 2);
        g.setColor(new Color(150, 0 , 0));
        g.fillRect(40, 26, length, 2);
        g.setColor(new Color(100, 0 , 0));
        g.fillRect(40, 28, length, 2);
        g.setColor(new Color(40, 180 , 250));
        g.setFont(new Font("time new roman", Font.BOLD, 15));
        g.drawString("HP: " + status.getHp() + " / " + status.getMaxHp(), 200, 24);
    }
    
    private void drawOtherStatus(Graphics g){
        g.setColor(Color.BLACK);
        g.setFont(new Font("time new roman", Font.BOLD, 15));
        g.drawString("Food: " + status.getFood(), 40, 50);
        g.drawString("Wood: " + status.getWood(), 40, 70);
        g.drawString("Coal  : " +status.getCoal(), 40, 90);
        g.drawString("Iron   : " + status.getIron(), 40, 110);
        g.drawString("Unit   : " + status.getUnit(), 240, 50);
        g.drawString("Time  : " + status.getTime(), 240, 70);      
    }
}
