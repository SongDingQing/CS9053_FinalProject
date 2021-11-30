package GUI;

import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import Data.Constants;
import GUI.Pixel.*;

/*** This Class is the the Map Drawing (the Map Panel)
 * @author Chengzuo Song
 * @version 0.0
 *
 * the data of the map will be store in client (width*height)
 */
public class MapPanel extends JPanel {
    public Pixel[][] mapData;
    public int[][] pixelData;

    public MapPanel() {
        super();
        mapData = new Pixel[Constants.width_pixels][Constants.height_pixels];
        setMapData();
    }

    private void setMapData() {
        //------------CONSTRUCT mapData based on PixelData.txt--------------
        try {
            java.io.File file = new java.io.File("src/Data/PixelData.txt");
            Scanner input = new Scanner(file);
            pixelData = new int[80][60];
            while (input.hasNext()) {
                for (int y = 0; y < Constants.height_pixels; y++) {
                    for (int x = 0; x < Constants.width_pixels; x++) {
                        pixelData[x][y] = input.nextInt();
                    }
                }

            }
            input.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


        for (int y = 0; y < Constants.height_pixels; y++) {
            for (int x = 0; x < Constants.width_pixels; x++) {
                mapData[x][y] = CreatePixel(pixelData[x][y]);
            }
        }
    }

    public Pixel CreatePixel(int data) {
        switch (data) {
            case 0:
                return new Pixel_Grass();
            case 1:
                return new Pixel_Woods();
            case 2:
                return new Pixel_Lake();
            case 3:
                return new Pixel_Mountain();
            case 4:
                return new Pixel_Coal();
            case 5:
                return new Pixel_Iron();
            case 6:
                return new Pixel_Fish();
            default:// default exception handler
                return null;
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        //Map drawing
        for (int x = 0; x < Constants.width_pixels; x++) {
            for (int y = 0; y < Constants.height_pixels; y++) {
                mapData[x][y].drawPixel(g, x, y);
                //The Following two line is used to test whether the pixel at some location is write
                //mapData[x][y].drawX(g,x,y);
                //mapData[x][y].drawY(g,x,y);
            }
        }
        //Base drawing
        g.setColor(new Color(0, 0, 200));
        g.fillRect(0, 0, 801, 80);
        g.setColor(new Color(200, 0, 0));
        g.fillRect(0, 681, 801, 80);
    }
}
