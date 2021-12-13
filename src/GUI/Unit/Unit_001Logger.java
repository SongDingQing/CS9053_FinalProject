package GUI.Unit;

import javax.swing.*;
import java.awt.*;

public class Unit_001Logger extends Unit{
    Image pic=new ImageIcon("src/Images/logger.png").getImage();
    @Override
    public void drawUnit(Graphics2D g, int x, int y) {
        g.drawImage(pic,x+1, y+1, null);
    }
}
