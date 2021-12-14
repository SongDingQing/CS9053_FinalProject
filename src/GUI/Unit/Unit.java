package GUI.Unit;

import java.awt.*;

public abstract class Unit {
	
    public Unit(){};
    
    public abstract void drawUnit(Graphics2D g2d, int x, int y,boolean enemy);
}
