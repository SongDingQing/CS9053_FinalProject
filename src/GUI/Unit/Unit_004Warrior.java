package GUI.Unit;

import javax.swing.*;
import java.awt.*;

public class Unit_004Warrior extends Unit{
    Image pic=new ImageIcon("src/Images/warrior.png").getImage();
    @Override
    public void drawUnit(Graphics2D g, int x, int y) {
        g.drawImage(pic,x+1,y+1,null);
    }
}
