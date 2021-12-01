package GUI.Pixel;

import java.awt.*;

public class Pixel_003Mountain extends Pixel{
	
    @Override
    public void drawPixel(Graphics g, int x, int y) {
    	g.setColor(new Color(150, 150, 150));
        g.fillRect(x*10, y*10+80, 10, 10);
        //border
        //g.setColor(new Color(128, 128, 128));
        //g.drawRect(x*10, y*10+80, 10, 10);
    }
    
    @Override
    public int getPixelType() {
		return 3;
	}
}
