package GUI.Pixel;

import java.awt.*;

public class Pixel_004Coal extends Pixel implements Collectable{

    private int CoalStorage;

    public Pixel_004Coal(){
        super();
        CoalStorage=100;
    }
    @Override
    public void drawPixel(Graphics g, int x, int y) {
    	g.setColor(new Color(40, 40, 40));
        g.fillRect(x*10, y*10+80, 10, 10);
        //border
        //g.setColor(new Color(128, 128, 128));
        //g.drawRect(x*10, y*10+80, 10, 10);
    }

    @Override
    public int getPixelIndex() {
		return 4;
	}

    @Override
    public int getAmount() {
        return CoalStorage;
    }

    @Override
    public void takeAmount(int n) {
        CoalStorage-=n;
    }
}
