package GUI;

import javax.swing.*;
import java.awt.*;

import Data.Constants;
import GUI.Pixel.Pixel;
import Data.Constants.*;
import GUI.Pixel.Pixel_Grass;

/*** This Class is the the Map Drawing (the Map Panel)
 * @author Chengzuo Song
 * @version 0.0
 *
 * the data of the map will be store in client (width*height)
 */
public class MapPanel extends JPanel {
    public Pixel[][] mapData;
    public MapPanel(){
        super();
        mapData= new Pixel[Constants.width_pixels][Constants.Height_pixels];
        setMapData();
    }
    private void setMapData(){
        for(int x=0;x<Constants.width_pixels;x++){
            for(int y=0;y<Constants.Height_pixels;y++){
                mapData[x][y]= new Pixel_Grass();
            }
        }
    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        //Map drawing
        for(int x=0;x<Constants.width_pixels;x++){
            for(int y=0;y<Constants.Height_pixels;y++){
                mapData[x][y].drawPixel(g,x,y);
            }
        }
        //Base drawing
        g.setColor(Color.RED);
    }
}
