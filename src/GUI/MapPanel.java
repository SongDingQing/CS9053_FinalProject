package GUI;

import javax.swing.*;
import java.awt.*;
import GUI.Pixel.Pixel;

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
        mapData= new Pixel[80][60];
    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);
    }
}
