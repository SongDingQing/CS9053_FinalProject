package GamePlay;

import javax.swing.*;
import java.awt.*;

public class StatusPanel extends JPanel {
    StatusData status;
    public StatusPanel(StatusData statusData){
        super();
        status =statusData;
    }
    public void upDateTime(){
        status.setTime(status.getTime()+1);
    };
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        drawHpBar(g);
        drawOtherStatus(g);


    }
    private void drawHpBar(Graphics g){
        //hp bar drawing
        g.setColor(new Color(0, 0 , 0));
        g.fillRect(39, 10, 402, 20);//the outer bar and background
        // the blood bar
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
        g.drawString("HP: " + status.getHp()+" / "+status.getMaxHp(), 200, 24);
    }
    private void drawOtherStatus(Graphics g){
        //food
        g.setColor(Color.BLACK);
        g.setFont(new Font("time new roman", Font.BOLD, 15));
        g.drawString("Food: " + status.getFood(), 40, 50);
        //Wood
        g.setColor(Color.BLACK);
        g.setFont(new Font("time new roman", Font.BOLD, 15));
        g.drawString("Wood: " + status.getWood(), 40, 70);
        //Coal
        g.setColor(Color.BLACK);
        g.setFont(new Font("time new roman", Font.BOLD, 15));
        g.drawString("Coal  : " +status.getCoal(), 40, 90);
        //Iron
        g.setColor(Color.BLACK);
        g.setFont(new Font("time new roman", Font.BOLD, 15));
        g.drawString("Iron   : " + status.getIron(), 40, 110);
        //Unit
        g.setColor(Color.BLACK);
        g.setFont(new Font("time new roman", Font.BOLD, 15));
        g.drawString("Unit   : " + status.getUnit(), 240, 50);
        //Time
        g.setColor(Color.BLACK);
        g.setFont(new Font("time new roman", Font.BOLD, 15));
        g.drawString("Time  : " + status.getTime(), 240, 70);
    }
}
