package GUI.Unit;

import java.awt.*;

public class Unit_002Fisher extends Unit{

    @Override
    public void drawUnit(Graphics2D g, int x, int y) {
        g.setColor(new Color(50, 0, 255));
        g.fillOval(x+1, y+1, 8, 8);
    }
}
