package GUI.Pixel;

import java.awt.*;

public abstract class Pixel {
    public Pixel(){ };
    public abstract void drawPixel(Graphics g,int x,int y);
    public void drawX(Graphics g,int x,int y){
        g.setFont(new Font("time new roman", Font.PLAIN, 9));
        g.drawString(y+"",x*10,y*10+90);
    }
    public abstract int getPixelType();
    public void drawY(Graphics g,int x,int y){
        g.setFont(new Font("time new roman", Font.PLAIN, 9));
        g.drawString(y+"",x*10,y*10+90);
    }
}
