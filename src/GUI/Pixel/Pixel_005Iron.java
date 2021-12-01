package GUI.Pixel;

import java.awt.*;

public class Pixel_005Iron extends Pixel{
	
    @Override
    public void drawPixel(Graphics g, int x, int y) {
    	g.setColor(new Color(150, 60, 42));
        g.fillRect(x*10, y*10+80, 10, 10);
        //border
        //g.setColor(new Color(128, 128, 128));
        //g.drawRect(x*10, y*10+80, 10, 10);
    }
    
    @Override
    public int getPixelIndex() {
		return 5;
	}
}
