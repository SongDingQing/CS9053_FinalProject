package GUI.Unit;

import javax.swing.*;
import java.awt.*;

public class Unit_006Fence extends Unit{
    Image pic=new ImageIcon("src/Images/fence.png").getImage();
    @Override
    public void drawUnit(Graphics2D g, int x, int y) {
        g.drawImage(pic,x+1,y+1,null);
    }
}
