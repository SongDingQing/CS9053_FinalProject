package GUI.Pixel;

import java.awt.*;

public class Pixel_001Woods extends Pixel implements Collectable{
	
    private int WoodStorage;

    public Pixel_001Woods(){
        super();
        WoodStorage = 1000;
    }

    @Override
    public void drawPixel(Graphics g, int x, int y) {
        g.setColor(new Color(0, 140 , 0));
        g.fillRect(x*10, y*10+80, 10, 10);
        //border
        //g.setColor(new Color(0, 100 , 0));
        //g.drawRect(x*10, y*10+80, 10, 10);
    }

    @Override
    public int getPixelIndex() {
        return 1;
    }

    @Override
    public int getAmount() {
        return WoodStorage;
    }

    @Override
    public void takeAmount(int n) {
        WoodStorage-=n;
    }
}
