package GUI;

import Data.ClientData.Constants;
import Data.ClientData.Variable1;
import Data.ClientData.Variable2;
import DataType.TransmitData;
import DataType.UnitData;
import GUI.Pixel.*;
import GUI.Unit.Unit;
import GUI.Unit.Unit_001Logger;
import GUI.Unit.Unit_002Fisher;
import GUI.Unit.Unit_003Miner;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
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
    public ArrayList<Unit> enemyUnits;
    public ArrayList<UnitData> unitsData;
    public ArrayList<UnitData> enemyUnitsData;
    private int playerNum;
    private int MouseX;
    private int TempMouseX;

    public MapPanel(int playerNum) {
        super();
        this.playerNum=playerNum;
        mapData = new Pixel[Constants.Pixels_Width][Constants.Pixels_Height];
        units = new ArrayList<Unit>();
        unitsData = new ArrayList<UnitData>();
        enemyUnits = new ArrayList<Unit>();
        enemyUnitsData = new ArrayList<UnitData>();
        setMapData();
        this.addMouseMotionListener(new ConfirmationListener());
        this.addMouseListener(new ConfirmationListener());
    }


    private void setMapData() {
        //------------CONSTRUCT mapData based on PixelData.txt--------------
        try {
            java.io.File file = new java.io.File("src/Data/ClientData/PixelData.txt");
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
        //System.out.println(playerNum);
        // Fill each pixel based on its own type
        if(playerNum==1){
            for (int y = 0; y < Constants.Pixels_Height; y++) {
                for (int x = 0; x < Constants.Pixels_Width; x++) {
                    mapData[x][y] = FillPixel(pixelData[x][y]);
                }
            }
        }else{
            for (int y = 0; y < Constants.Pixels_Height; y++) {
                for (int x = 0; x < Constants.Pixels_Width; x++) {
                    mapData[x][y] = FillPixel(pixelData[x][59-y]);
                }
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

    public void update(TransmitData td) {
        unitsData = td.getUnitDataAL();
        units = new ArrayList<Unit>();
        for (UnitData unitData : unitsData) {

                units.add(readUnit(unitData.getUnitType()));


        }
        enemyUnitsData = td.getEnemyUnitDataAL();
        enemyUnits = new ArrayList<Unit>();
        for (UnitData unitData : enemyUnitsData) {

                enemyUnits.add(readUnit(unitData.getUnitType()));

        }
    }

    public Unit readUnit(int unitType) {
        //System.out.println(unitType);
        switch (unitType) {
            case 1:
                return new Unit_001Logger();
            case 2:
                return new Unit_002Fisher();
            case 3:
                return new Unit_003Miner();
            default:// default exception handler
                return null;
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
        if(playerNum==1){
            g.setColor(new Color(0, 0, 200));
            g.fillRect(0, 0, 800, 80);
            g.setColor(new Color(200, 0, 0));
            g.fillRect(0, 680, 800, 80);
        }else{
            g.setColor(new Color(200, 0, 0));
            g.fillRect(0, 0, 800, 80);
            g.setColor(new Color(0, 0, 200));
            g.fillRect(0, 680, 800, 80);
        }


        //Unit drawing
        Graphics2D g2d = (Graphics2D) g;
        //System.out.println(units.size());
        //System.out.println(unitsData.size());
        for (int i = 0; i < units.size(); i++) {
            if(unitsData.get(i).isAlive()){
                units.get(i).drawUnit(g2d, unitsData.get(i).getX(), unitsData.get(i).getY());
            }

        }
        for (int i = 0; i < enemyUnits.size(); i++) {
            if(enemyUnitsData.get(i).isAlive()){
                enemyUnits.get(i).drawUnit(g2d, enemyUnitsData.get(i).getX(), 750-enemyUnitsData.get(i).getY());
            }
        }
        //ConfirmationLine drawing
        paintConfirmationLine(g2d);
        //Test
        //System.out.println("Player "+playerNum+"'s mouse X"+MouseX);
    }
    public void paintConfirmationLine(Graphics2D g2d){

        if(Variable1.confirmationLine>=1&&playerNum==1){
            if(Variable1.confirmationLine==2){
                g2d.setColor(Color.red);
                g2d.fillRect(TempMouseX/10*10+4,80,2,600);
                g2d.setColor(Color.green);
                g2d.fillPolygon(new int[]{TempMouseX/10*10,TempMouseX/10*10+4,TempMouseX/10*10+5,TempMouseX/10*10+10}
                        ,new int[]{66,80,80,66},4);
                g2d.fillPolygon(new int[]{TempMouseX/10*10,TempMouseX/10*10+4,TempMouseX/10*10+5,TempMouseX/10*10+10}
                        ,new int[]{696,680,680,696},4);
            }else{
                g2d.setColor(Color.red);
                g2d.fillRect(MouseX/10*10+4,80,2,600);
            }

        }else if(Variable2.confirmationLine>=1&&playerNum==2){
            if(Variable2.confirmationLine==2){
                g2d.setColor(Color.red);
                g2d.fillRect(TempMouseX/10*10+4,80,2,600);
                g2d.setColor(Color.green);
                g2d.fillPolygon(new int[]{TempMouseX/10*10,TempMouseX/10*10+4,TempMouseX/10*10+5,TempMouseX/10*10+10}
                        ,new int[]{66,80,80,66},4);
                g2d.fillPolygon(new int[]{TempMouseX/10*10,TempMouseX/10*10+4,TempMouseX/10*10+5,TempMouseX/10*10+10}
                        ,new int[]{696,680,680,696},4);
            }else{
                g2d.setColor(Color.red);
                g2d.fillRect(MouseX/10*10+4,80,2,600);
            }
        }
    }
    class ConfirmationListener implements MouseListener, MouseMotionListener {
        @Override
        public void mouseClicked(MouseEvent e) {
            if(playerNum==1){
                if (Variable1.confirmationLine==1){
                   TempMouseX=MouseX;
                    Variable1.confirmationLine=2;
                }else if (Variable1.confirmationLine==2){
                    Variable1.CommandType=Variable1.unitType;
                    Variable1.tempX=TempMouseX;
                    Variable1.confirmationLine=0;
                }
                //System.out.println(Variable1.confirmationLine);
            }else{
                if (Variable2.confirmationLine==1){
                    TempMouseX=MouseX;
                    Variable2.confirmationLine=2;
                }else if (Variable2.confirmationLine==2){
                    Variable2.CommandType=Variable2.unitType;
                    Variable2.tempX=TempMouseX;
                    Variable2.confirmationLine=0;
                }

            }
        }

        @Override
        public void mousePressed(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }

        @Override
        public void mouseDragged(MouseEvent e) {

        }

        @Override
        public void mouseMoved(MouseEvent e) {
            MouseX=e.getX();
        }
    }
}
