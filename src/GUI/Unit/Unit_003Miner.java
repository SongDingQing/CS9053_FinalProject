package GUI.Unit;

import javax.swing.*;
import java.awt.*;

public class Unit_003Miner extends Unit{
    Image pic=new ImageIcon("src/Images/miner.png").getImage();
    @Override
    public void drawUnit(Graphics2D g, int x, int y,boolean enemy) {
        g.drawImage(pic,x+1, y+1, null);
        if(enemy){
            g.setColor(Color.RED);

        }else{
            g.setColor(Color.BLUE);
        }
        g.drawRect(x,y,9,9);
    }
}
