package GUI.Unit;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Unit_005Archer extends Unit{
    Image pic=new ImageIcon("src/Images/archer.png").getImage();
    @Override
    public void drawUnit(Graphics2D g, int x, int y,boolean enemy) {
        g.drawImage(pic,x+1,y+1,null);
        if(enemy){
            g.setColor(Color.RED);

        }else{
            g.setColor(Color.BLUE);
        }
        g.drawRect(x,y,9,9);
    }
}
