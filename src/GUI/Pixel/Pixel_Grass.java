package GUI.Pixel;

import java.awt.*;

public class Pixel_Grass extends Pixel{
    @Override
    public void drawPixel(Graphics g,int x, int y) {
        g.setColor(new Color(0, 190 , 0));
        g.fillRect(x*10, y*10+70, 10, 10);
        g.setColor(new Color(0, 220 , 0));
        g.fillRect(x*10+1, y*10+71, 8, 8);
    }
}
