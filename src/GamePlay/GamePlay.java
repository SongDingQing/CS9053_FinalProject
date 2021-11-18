package GamePlay;

import javax.swing.*;
import java.awt.*;

/*** 这个类绘制一个条形图
 * @author Chengzuo Song
 * @version 0.0
 */


public class GamePlay extends JFrame {
    //CONSTANTS
    private final int HEIGHT= 800;
    private final int WIDTH = 1200;
    private final int MAP_HEIGHT= 800;
    private final int MAP_WIDTH = 700;
    private final int CPANEL_HEIGHT= 800;
    private final int CPANEL_WIDTH = 500;

    private JPanel mapPanel;
    private JPanel controlPanel;

    public GamePlay() {
        super("budge");
        //Map Panel
        setMapPanel();
        //Control Panel
        setControlPanel();
        //Add up Component
        setupPanels();
        // Project Icon
        String iconName = "src/Images/icon.png";
        setIconImage(new ImageIcon(iconName).getImage());

        // Other Configuration
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setSize(WIDTH,HEIGHT);
        setVisible(true);
    }
    private void setMapPanel(){
        mapPanel = new JPanel();
        mapPanel.setSize(MAP_WIDTH,MAP_HEIGHT);
        mapPanel.setBackground(new Color(250,200,200));
    };
    private void setControlPanel(){
        controlPanel= new JPanel();
        controlPanel.setSize(CPANEL_WIDTH,CPANEL_WIDTH);
        controlPanel.setBackground(new Color(200,200,250));
    };
    private void setupPanels(){
        add(mapPanel);
        add(controlPanel);
    }
}
