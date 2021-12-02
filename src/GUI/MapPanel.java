package GUI;

import Data.ClientData.Constants;
import DataType.UnitData;
import GUI.Pixel.*;
import GUI.Unit.Unit;
import GUI.Unit.Unit_001Logger;

import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/*** This Class is the the Map Drawing (the Map Panel)
 * @author Chengzuo Song
 * @version 0.0
 *
 * the data of the map will be store in client (width*height)
 */

public class MapPanel extends JPanel {
	
    public Pixel[][] mapData;
    public int[][] pixelData;
    public ArrayList<Unit> units;
    public ArrayList<UnitData> unitsData;

    public MapPanel() {
        super();
        mapData = new Pixel[Constants.Pixels_Width][Constants.Pixels_Height];
        setMapData();
        //Test
        units = new ArrayList<Unit>();
        unitsData = new ArrayList<UnitData>();
        units.add(new Unit_001Logger());
        unitsData.add(new UnitData(200, 400));
        units.add(new Unit_001Logger());
        unitsData.add(new UnitData(300, 500));
        units.add(new Unit_001Logger());
        unitsData.add(new UnitData(100, 300));
    }

    private void setMapData() {
        //------------CONSTRUCT mapData based on PixelData.txt--------------
        try {
            java.io.File file = new java.io.File("src/Data/PixelData.txt");
            Scanner input = new Scanner(file);
            pixelData = new int[80][60];
            while (input.hasNext()) {
                for (int y = 0; y < Constants.Pixels_Height; y++) {
                    for (int x = 0; x < Constants.Pixels_Width; x++) {
                        pixelData[x][y] = input.nextInt();
                    }
                }
            }
            input.close();
        } catch (FileNotFoundException e) {
            System.out.println("Cannot find PixelData.txt");
        }

        // Fill each pixel based on its own type
        for (int y = 0; y < Constants.Pixels_Height; y++) {
            for (int x = 0; x < Constants.Pixels_Width; x++) {
                mapData[x][y] = FillPixel(pixelData[x][y]);
            }
        }
    }

    public Pixel FillPixel(int data) {
        switch (data) {
            case 0:
                return new Pixel_000Grass();
            case 1:
                return new Pixel_001Woods();
            case 2:
                return new Pixel_002Lake();
            case 3:
                return new Pixel_003Mountain();
            case 4:
                return new Pixel_004Coal();
            case 5:
                return new Pixel_005Iron();
            case 6:
                return new Pixel_006Fish();
            default:// default exception handler
                return null;
        }
    }

    public void upDateTime(){
        for (int i = 0; i < units.size(); i++) {
        	// Set boundary
            if(unitsData.get(i).getY() < 80){
                unitsData.get(i).setState(1);
            } else if(unitsData.get(i).getY() > 670){
                unitsData.get(i).setState(-1);
            }
            
            //TODO: Move and Stop
            unitsData.get(i).setY(unitsData.get(i).getY() + unitsData.get(i).getState());
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        //Map drawing
        for (int x = 0; x < Constants.Pixels_Width; x++) {
            for (int y = 0; y < Constants.Pixels_Height; y++) {
                mapData[x][y].drawPixel(g, x, y);
                //The Following two line is used to test whether the pixel at some location is write
                //mapData[x][y].drawX(g,x,y); mapData[x][y].drawY(g,x,y);
            }
        }
        
        //Base drawing
        g.setColor(new Color(0, 0, 200));
        g.fillRect(0, 0, 800, 80);
        g.setColor(new Color(200, 0, 0));
        g.fillRect(0, 680, 800, 80);

        //Unit drawing
        Graphics2D g2d = (Graphics2D) g;
        for (int i = 0; i < units.size(); i++) {
            units.get(i).drawUnit(g2d, unitsData.get(i).getX(), unitsData.get(i).getY());
        }
    }
}
