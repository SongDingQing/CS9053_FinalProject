package GUI.Pixel;

import java.awt.*;

public class Pixel_000Grass extends Pixel{
    @Override
    public void drawPixel(Graphics g,int x, int y) {
        g.setColor(new Color(0, 200 , 0));
        g.fillRect(x*10, y*10+80, 10, 10);
        //border
        //g.setColor(new Color(0, 150 , 0));
        //g.drawRect(x*10, y*10+80, 10, 10);
    }

    @Override
    public int getPixelType() {
        return 0;
    }
}
