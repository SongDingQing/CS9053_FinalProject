package GUI.Unit;

import java.awt.*;

public class Unit_003Miner extends Unit{

    @Override
    public void drawUnit(Graphics2D g, int x, int y) {
        g.setColor(new Color(150, 100, 0));
        g.fillOval(x+1, y+1, 8, 8);
    }
}
