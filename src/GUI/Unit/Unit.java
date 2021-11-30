package GUI.Unit;

import java.awt.*;

public abstract class Unit {
    private int x,y;
    public abstract void drawUnit(Graphics2D g2d, int x, int y);
}
